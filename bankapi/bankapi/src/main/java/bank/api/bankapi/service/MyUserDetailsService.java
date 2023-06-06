package bank.api.bankapi.service;

import bank.api.bankapi.model.AuthorizedUser;
import bank.api.bankapi.model.User;
import bank.api.bankapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Finds a user by email
     * @param email
     * @return Authorized user
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final User user = userRepository.findUserByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("Username %s not found", email));
        }
        return new AuthorizedUser(user);
    }
}
