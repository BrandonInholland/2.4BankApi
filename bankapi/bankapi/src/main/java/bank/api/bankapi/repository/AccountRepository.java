package bank.api.bankapi.repository;

import bank.api.bankapi.model.Account;
import org.springframework.data.jpa.repository.Query;
import bank.api.bankapi.model.enums.Roles;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.threeten.bp.OffsetDateTime;

import java.util.List;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {
    Account getAccountByIban(String iban);

    List<Account> getAccountsByAccountholder_Userid(Long lUserID);
    @Query("select sum (a.balance) from Account a where  a.accountholder.userid = :userid")
    Double getTotalBalanceByAccountholder_Userid(Long userid);

    List<Account> getAccountsByAccountholder_Username(String sUsername);

    List<Account> getAccountsByAccounttype(Account.AccounttypeEnum accounttype);

    List<Account> getAccountsByStatus(Account.StatusEnum status);

    List<Account> getAccountsByAccountholder_Roles(Roles accountHolder_roles);
}
