package bank.api.bankapi.repository;

import bank.api.bankapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByEmail(String email);
    @Query("select u from User u inner join Account a on (u.userid = a.accountholder.userid) where (:firstName is null or u.firstName = :firstName) and (:lastName is null or u.lastName = :lastName) and (:email is null or u.email = :email) and (:username is null or u.username = :username) ")
    List<User> findUsersByFirstNameAndLastNameAndEmailAndUsernameAndAccount(String firstName, String lastName, String email, String username);

    @Query("select u from User u left join Account a on (u.userid = a.accountholder.userid) where (a.accountholder.userid is null) and (:firstName is null or u.firstName = :firstName) and (:lastName is null or u.lastName = :lastName) and (:email is null or u.email = :email) and (:username is null or u.username = :username) ")
    List<User> findUsersByFirstNameAndLastNameAndEmailAndUsernameAndNoAccount(String firstName, String lastName, String email, String username);

    @Query("select u from User u where (:firstName is null or u.firstName = :firstName) and (:lastName is null or u.lastName = :lastName) and (:email is null or u.email = :email) and (:username is null or u.username = :username) ")
    List<User> findUsersByFirstNameAndLastNameAndEmailAndUsername(String firstName, String lastName, String email, String username);
}
