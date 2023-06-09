package bank.api.bankapi.service;


import bank.api.bankapi.configuration.IbanGenerator;
import bank.api.bankapi.model.Account;
import bank.api.bankapi.model.User;
import bank.api.bankapi.model.dto.PostAccountDTO;
import bank.api.bankapi.model.dto.PutAccountDTO;
import bank.api.bankapi.model.enums.Roles;
import bank.api.bankapi.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.threeten.bp.OffsetDateTime;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class AccountService {
    //Custom IbanGenerator for generating IBANs (from previous project)
    private final IbanGenerator ibanGenerator = new IbanGenerator("NL", "01", "INHO", 20, 10);
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private UserService userService;

    //Only Employees can do stuff with Accounts

    // Find an account by its IBAN
    public Account findByIBAN(String IBAN, User currentUser) throws Exception {
        if (currentUser.getRoles().contains(Roles.EMPLOYEE)) {
            Account account = accountRepository.getAccountByIban(IBAN);
            return accountRepository.findById(account.getId()).orElse(null);
        } else {
            throw new Exception("User is not authorized to view accounts other than their own");
        }

    }
    // Get an account by its IBAN
    public Account getAccountByIBAN(String IBAN) {
        Account account = accountRepository.getAccountByIban(IBAN);
        return accountRepository.findById(account.getId()).orElse(null);
    }


    public Account addAccount(PostAccountDTO accountDTO, User currentUser) throws Exception {
        Account createdAccount = new Account();
        if ((currentUser.getRoles().contains(Roles.EMPLOYEE))) {

            createdAccount.setAccountholder(userService.getUserById(accountDTO.getUserid()));
            // Set the account type if it is either CURRENT or SAVINGS
            if (accountDTO.getAccounttype().toString().equalsIgnoreCase(Account.AccounttypeEnum.CURRENT.toString()) || accountDTO.getAccounttype().toString().equalsIgnoreCase(Account.AccounttypeEnum.SAVINGS.toString())) {
                createdAccount.setAccounttype(accountDTO.getAccounttype());
            }

            createdAccount.setIban(ibanGenerator.generateIban());
            createdAccount.status(Account.StatusEnum.OPEN);
            createdAccount.balance(00.0);
            createdAccount.creationdate(OffsetDateTime.now());
            createdAccount.setAbsoluteLimit(BigDecimal.ZERO);
            return accountRepository.save(createdAccount);
        } else {
            throw new Exception("User is not authorized to create new accounts");
        }
    }
    // Get accounts based on filtering criteria
    public List<Account> getAccounts(Long userid, String userRole, String status, String type, String creationDate, User currentUser) {
        List<Account> results = new ArrayList<>();
        List<List<Account>> filters = new ArrayList<>();

        // Check if the current user is an employee
        if (currentUser.getRoles().contains(Roles.EMPLOYEE)) {
            // Check if any filtering criteria is provided
            if (userid != null || type != null || status != null || userRole != null || creationDate != null) {
                // Filter by user ID
                if (userid != null) {
                    List<Account> accountsByAccountHolderId = accountRepository.getAccountsByAccountholderUserid(userid);
                    filters.add(accountsByAccountHolderId);
                    results.addAll(accountsByAccountHolderId);
                }

                // Filter by account type
                if (type != null) {
                    List<Account> accountsByAccountType = accountRepository.getAccountsByAccountType(Account.AccounttypeEnum.valueOf(type.toUpperCase()));
                    filters.add(accountsByAccountType);
                    results = Stream.concat(accountsByAccountType.stream(), results.stream()).collect(Collectors.toList());
                }

                // Filter by account status
                if (status != null) {
                    List<Account> accountsByStatus = accountRepository.getAccountsByStatus(Account.StatusEnum.valueOf(status.toUpperCase()));
                    filters.add(accountsByStatus);
                    results = Stream.concat(accountsByStatus.stream(), results.stream()).collect(Collectors.toList());
                }

                // Filter by user role
                if (userRole != null) {
                    List<Account> accountsByAccountHolderRoles = accountRepository.getAccountsByAccountholderRoles(Roles.valueOf(userRole.toUpperCase()));
                    filters.add(accountsByAccountHolderRoles);
                    results = Stream.concat(accountsByAccountHolderRoles.stream(), results.stream()).collect(Collectors.toList());
                }

                // Find common accounts across filters
                if (!filters.isEmpty()) {
                    for (List<Account> accList : filters) {
                        // keep accounts that meet all filter
                        results.retainAll(accList);
                    }
                }
            } else {
                // If no filters are provided, retrieve all accounts
                results = (List<Account>) accountRepository.findAll();
            }
        } else if (currentUser.getRoles().contains(Roles.CUSTOMER)) {
            // If the current user is a customer, retrieve their accounts
            results = accountRepository.getAccountsByAccountholderUserid(currentUser.getUserid());
        } else {
            // If the user is not authorized or not logged in, throw an exception
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User is not logged in or is not authorized");
        }

        // Remove duplicates and return the filtered accounts
        return results.stream().distinct().collect(Collectors.toList());
    }

    // Update an account by its IBAN
    public Account updateAccountByIBAN(String IBAN, PutAccountDTO account, User currentUser) throws Exception {
        if (currentUser.getRoles().contains(Roles.EMPLOYEE)) {
            Account updatedAccount;
            if (accountRepository.getAccountByIban(IBAN) != null) {
                updatedAccount = accountRepository.getAccountByIban(IBAN);
            } else {
                throw new IllegalArgumentException("User with IBAN " + IBAN + " not found!");
            }

            if (account.getStatus() != null) {
                updatedAccount.setStatus(account.getStatus());
            }
            if (account.getAbsoluteLimit() != null) {
                updatedAccount.setAbsoluteLimit(account.getAbsoluteLimit());
            }
            accountRepository.save(updatedAccount);
            return updatedAccount;
        } else {
            throw new Exception("User is not authorized to update accounts");
        }
    }
}
