package bank.api.bankapi.service;


import bank.api.bankapi.model.Account;
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

    private final IbanGenerator ibanGenerator = new IbanGenerator("NL", "01", "INHO", 20, 10);
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private UserService userService;

    public Account findByIBAN(String IBAN, User currentUser) {
        if (currentUser.getRoles().contains(Roles.EMPLOYEE)) {
            Account account = accountRepository.getAccountByIban(IBAN);
            return accountRepository.findById(account.getId()).orElse(null);
        } else {
            throw new ForbiddenException("User is not authorized to view accounts other than their own");
        }

    }

    public Account getAccountByIBAN(String IBAN) {
        Account account = accountRepository.getAccountByIban(IBAN);
        return accountRepository.findById(account.getId()).orElse(null);
    }


    public Account addAccount(PostAccountDTO accountDTO, User currentUser) {
        Account createdAccount = new Account();
        if ((currentUser.getRoles().contains(Roles.EMPLOYEE))) {

            createdAccount.setAccountholder(userService.getUserById(accountDTO.getUserid()));

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
            throw new ForbiddenException("User is not authorized to create new accounts");
        }
    }

    public List<Account> getAccounts(Long userid, String userRole, String status, String type, String creationDate, User currentUser) {
        List<Account> results = new ArrayList<>();
        List<List<Account>> filters = new ArrayList<>();
        if (currentUser.getRoles().contains(Roles.EMPLOYEE)) {
            if(userid != null || type != null || status != null || userRole != null || creationDate != null){
                if(userid != null){
                    List<Account> accountsByAccountHolderId = accountRepository.getAccountsByAccountholder_Userid(userid);
                    filters.add(accountsByAccountHolderId);
                    results.addAll(accountsByAccountHolderId);}
                if(type !=null){
                    List<Account> accountsByAccountType = accountRepository.getAccountsByAccounttype(Account.AccounttypeEnum.valueOf(type.toUpperCase()));
                    filters.add(accountsByAccountType);
                    results = Stream.concat(accountsByAccountType.stream(),results.stream()).collect(Collectors.toList());}
                if(status != null){
                    List<Account> accountsByStatus = accountRepository.getAccountsByStatus(Account.StatusEnum.valueOf(status.toUpperCase()));
                    filters.add(accountsByStatus);
                    results = Stream.concat(accountsByStatus.stream(),results.stream()).collect(Collectors.toList());}
                if(userRole != null){
                    List<Account> accountsByAccountHolderRoles = accountRepository.getAccountsByAccountholder_Roles(Roles.valueOf(userRole.toUpperCase()));
                    filters.add(accountsByAccountHolderRoles);
                    results = Stream.concat(accountsByAccountHolderRoles.stream(),results.stream()).collect(Collectors.toList());
                }
                if(!filters.isEmpty()){
                    for (List<Account> accList:filters) {results.retainAll(accList);}
                }
            }
            else{
                results = (List<Account>) accountRepository.findAll();
            }
        } else if (currentUser.getRoles().contains(Roles.CUSTOMER)) {
            results = accountRepository.getAccountsByAccountholder_Userid(currentUser.getUserid());
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User is not logged in or is not authorized");
        }
        return results.stream().distinct().collect(Collectors.toList());
    }


    public Account updateAccountByIBAN(String IBAN, PutAccountDTO account, User currentUser) {
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
            throw new ForbiddenException("User is not authorized to update accounts");
        }
    }
}
