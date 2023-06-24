package bank.api.bankapi.repository;

import bank.api.bankapi.model.Account;
import bank.api.bankapi.model.enums.Roles;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {
    // Retrieves an account by iBan
    Account getAccountByIban(String iban);
    // Retrieves accounts by the account holders userID
    List<Account> getAccountsByAccountholderUserid(Long userID);

    // Retrieves the total balance of accounts for a given user ID
    @Query("select sum (a.balance) from Account a where  a.accountholder.userid = :userid")
    Double getTotalBalanceByAccountholderUserid(Long userid);

    // Retrieves accounts by the account holder's username
    List<Account> getAccountsByAccountholderUsername(String username);

    // Retrieves accounts by the account type (Aka Savings or Current)
    List<Account> getAccountsByAccountType(Account.AccounttypeEnum accounttype);

    // Retrieves accounts by the status ( aka Active/inactive)
    List<Account> getAccountsByStatus(Account.StatusEnum status);

    // Retrieves accounts by the account holder's role (aka Employee or Customer)
    List<Account> getAccountsByAccountholderRoles(Roles accountHolderRoles);
}
