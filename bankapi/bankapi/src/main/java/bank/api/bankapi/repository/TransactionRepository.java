package bank.api.bankapi.repository;

import bank.api.bankapi.model.Transaction;
import bank.api.bankapi.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.OffsetDateTime;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> getTransactionByAccount_Iban(String sIban);
    List<Transaction> getTransactionByIbANSender(String sIban);
    @Query("select u.dayLimit - sum(t.amount) from Transaction t inner join Account a on (t.ibANSender = a.iban) inner join User u on (u.userid = a.accountholder.userid) where u.username = :username and t.timestamp >= :today  ")
    Double getTransactionsByUsernameFromToday(String username, OffsetDateTime today);

    @Query("select t from Transaction t inner join Account a on (t.ibANSender = a.iban) inner join User u on (u.userid = a.accountholder.userid) where u.username = :username  ")
    List<Transaction> getTransactionByAccount_Accountholder_Username(String username);

    @Query("select t from Transaction t where (:sender is null or t.ibANSender = :sender) AND (:receiver is null or t.ibANReciever = :receiver)")
    List<Transaction> findBySenderAndByReceiver(String sender , String receiver);



}
