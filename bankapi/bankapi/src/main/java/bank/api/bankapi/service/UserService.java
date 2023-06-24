package bank.api.bankapi.service;

import bank.api.bankapi.model.Account;
import bank.api.bankapi.model.User;
import bank.api.bankapi.model.dto.LoginDTO;
import bank.api.bankapi.model.dto.PostUserDTO;
import bank.api.bankapi.model.enums.Roles;
import bank.api.bankapi.repository.AccountRepository;
import bank.api.bankapi.repository.TransactionRepository;
import bank.api.bankapi.repository.UserRepository;
import bank.api.bankapi.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.threeten.bp.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String loginUser(LoginDTO oLoginDTO) {


        User oUser = userRepository.findByUsername(oLoginDTO.getUsername());
        if (oUser != null) {
            if (oUser.isIsActive()) {
                if (passwordEncoder.matches(oLoginDTO.getPassword(), oUser.getPassword())) {

                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(oLoginDTO.getUsername(), oLoginDTO.getPassword()));
                    return jwtTokenProvider.createToken(oLoginDTO.getUsername(), oUser.getRoles());
                } else {
                    throw new BadCredentialsException("Invalid credentials");
                }
            } else {
                throw new ForbiddenException("This user is not allowed to log in");
            }
        } else {
            throw new UsernameNotFoundException("User " + oLoginDTO.getUsername() + " not found");
        }
    }

    public User addUser(PostUserDTO oPostUserDTO) throws Exception {


        Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
        Matcher mat = pattern.matcher(oPostUserDTO.getEmail());

        if (mat.matches()) {
            if (oPostUserDTO.getUsername().equals("") || oPostUserDTO.getEmail().equals("") || oPostUserDTO.getPassword().equals("") || oPostUserDTO.getFirstName().equals("") || oPostUserDTO.getLastName().equals("")) {
                throw new Exception("empty fields not allowed");
            } else if (userRepository.findByUsername(oPostUserDTO.getUsername()) != null || userRepository.findByEmail(oPostUserDTO.getEmail()) != null) {
                throw new Exception("user already exists");
            } else {

                User oUser = this.convertPostUserDTOToUser(oPostUserDTO);
                userRepository.save(oUser);
                return userRepository.findByUsername(oUser.getUsername());
            }

        } else {
            throw new AddressException(oPostUserDTO.getEmail() + " is not a valid email adress");
        }


    }

    private User convertPostUserDTOToUser(PostUserDTO oPostUserDTO) {

        User oUser = new User();
        oUser.setFirstName(oPostUserDTO.getFirstName());
        oUser.setLastName(oPostUserDTO.getLastName());
        oUser.setEmail(oPostUserDTO.getEmail());
        oUser.setUsername(oPostUserDTO.getUsername());
        oUser.setPassword(passwordEncoder.encode(oPostUserDTO.getPassword()));
        return oUser;
    }

    public List<User> getUsers(String sUsernameLoggedInUser, Integer userid, String username, String firstName, String lastName, String email, String roles, Integer limit, Boolean hasAccount) {

        List<User> lUser = new ArrayList<>();
        User oLoggedInUser = userRepository.findByUsername(sUsernameLoggedInUser);

        if (oLoggedInUser.getRoles().contains(Roles.EMPLOYEE)) {
            if (userid != null) {
                if (userRepository.findById(userid.longValue()).isPresent()) {
                    lUser.add(userRepository.findById(userid.longValue()).get());
                }
            } else if (hasAccount != null) {
                if (hasAccount) {
                    lUser = userRepository.findUsersByFirstNameAndLastNameAndEmailAndUsernameAndAccount(firstName, lastName,email,username);
                } else {
                    lUser = userRepository.findUsersByFirstNameAndLastNameAndEmailAndUsernameAndNoAccount(firstName, lastName,email,username);
                }
            } else {
                lUser = userRepository.findUsersByFirstNameAndLastNameAndEmailAndUsername(firstName, lastName, email, username);
            }
            if (roles != null) {
                lUser = lUser.stream().filter((u) -> u.getRoles().contains(Roles.valueOf(roles.toUpperCase()))).collect(Collectors.toList());
            }
            if (limit != null) {
                lUser = lUser.stream().limit(limit).collect(Collectors.toList());
            }
        } else if (oLoggedInUser.getRoles().contains(Roles.CUSTOMER)) {
            lUser.add(oLoggedInUser);
        }
        for (User user : lUser) {
            this.calculateTotalBalance(user);
        }
        return lUser;
    }

    public User updateUser(User oUser, String sUsernameLoggedInUser) throws NotFoundException {

        User oLoggedInUser = userRepository.findByUsername(sUsernameLoggedInUser);
        if (oLoggedInUser.getRoles().contains(Roles.EMPLOYEE)) {
            Optional<User> oOriginalUser = userRepository.findById(oUser.getUserid());
            if (oOriginalUser.isEmpty()) {
                throw new NotFoundException(404, "User not found");
            } else {
                User oUserToBeUpdated = oOriginalUser.get();
                if (oUser.getFirstName() != null && !oUser.getFirstName().equals("")) {
                    oUserToBeUpdated.setFirstName(oUser.getFirstName());
                }
                if (oUser.getLastName() != null && !oUser.getLastName().equals("")) {
                    oUserToBeUpdated.setLastName(oUser.getLastName());
                }
                if (oUser.getEmail() != null && !oUser.getEmail().equals("")) {
                    Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
                    Matcher mat = pattern.matcher(oUser.getEmail());
                    if (mat.matches()) {
                        oUserToBeUpdated.setEmail(oUser.getEmail());
                    }
                }
                if (oUser.getRoles() != null) {
                    oUserToBeUpdated.setRoles(oUser.getRoles());
                }
                if (oUser.getPassword() != null && !oUser.getPassword().equals("")) {
                    oUserToBeUpdated.setPassword(passwordEncoder.encode(oUser.getPassword()));
                }
                if (oUser.getUsername() != null && !oUser.getUsername().equals("")) {
                    oUserToBeUpdated.setUsername(oUser.getUsername());
                }
                if (oUser.getDayLimit() != null) {
                    oUserToBeUpdated.setDayLimit(oUser.getDayLimit());
                }
                if (oUser.getTransactionLimit() != null) {
                    oUserToBeUpdated.setTransactionLimit(oUser.getTransactionLimit());
                }
                if (oUser.isIsActive() != null) {
                    oUserToBeUpdated.setIsActive((oUser.isIsActive()));
                }
                userRepository.save(oUserToBeUpdated);
                return oUserToBeUpdated;
            }
        } else {
            throw new ForbiddenException("this user is not allowed to update users");
        }
    }

    public void deleteUserById(long lUserID, String sUsernameLoggedInUser) throws Exception {
        User oLoggedInUser = userRepository.findByUsername(sUsernameLoggedInUser);
        if (oLoggedInUser.getRoles().contains(Roles.EMPLOYEE)) {
            if (accountRepository.getAccountsByAccountholder_Userid(lUserID) != null) {
                if (lUserID != oLoggedInUser.getUserid()) {
                    userRepository.deleteById(lUserID);
                } else {
                    throw new Exception("Cannot delete yourself");
                }

            } else {
                throw new Exception("User has an account");
            }
        } else {
            throw new ForbiddenException("This user is not allowed to delete other users");
        }

    }

    public List<Account> getOwnAccounts(String sUsername) {
        return accountRepository.getAccountsByAccountholder_Username(sUsername);
    }

    public Double getRemainingDayLimit(String sUsername) {

        OffsetDateTime today = OffsetDateTime.of(LocalDateTime.of(LocalDate.now(), LocalTime.MIN), ZoneOffset.UTC);
        return transactionRepository.getTransactionsByUsernameFromToday(sUsername, today);
    }

    public User getUserById(long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User %d could not be found", id));
        }
        return user.get();
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    private void calculateTotalBalance(User user) {
        user.setTotalBalance(accountRepository.getTotalBalanceByAccountholder_Userid(user.getUserid()));
    }
}