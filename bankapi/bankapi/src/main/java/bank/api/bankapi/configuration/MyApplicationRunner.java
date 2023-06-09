package bank.api.bankapi.configuration;

import bank.api.bankapi.model.Account;
import bank.api.bankapi.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.threeten.bp.OffsetDateTime;
import org.threeten.bp.ZoneOffset;

import java.math.BigDecimal;
@Component
public class MyApplicationRunner implements ApplicationRunner {

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;



    @Override
    public void run(ApplicationArguments args) throws Exception {
        //-----------------------------------------------------------------------ACCOUNTS---------------------------------------------------------------
        //Bank account
        Account account = new Account();
        account.setIban("NL01INHO0000000001");
        account.setAccountholder(user);
        account.setStatus(Account.StatusEnum.OPEN);
        account.setAccounttype(Account.AccounttypeEnum.CURRENT);
        account.setCreationdate(OffsetDateTime.now());
        account.setBalance(2000000000.00);
        account.setAbsoluteLimit(BigDecimal.valueOf(200000000000.00));
        accountRepository.save(account);

        //User accounts
        Account currentCust = new Account().iban("NL01INHO0000000003").absoluteLimit(BigDecimal.valueOf(500.00)).accountholder(user1).creationdate(OffsetDateTime.of(2022, 2, 5, 15, 10, 30, 0, ZoneOffset.UTC)).accounttype(Account.AccounttypeEnum.CURRENT).status(Account.StatusEnum.OPEN).balance(2500.0);
        Account savingsCust = new Account().iban("NL01INHO0000000004").absoluteLimit(BigDecimal.valueOf(500.00)).accountholder(user1).creationdate(OffsetDateTime.of(2020, 1, 5, 15, 10, 30, 0, ZoneOffset.UTC)).accounttype(Account.AccounttypeEnum.SAVINGS).status(Account.StatusEnum.OPEN).balance(2000.0);
        Account accountTest2EmpSavings = new Account().iban("NL01INHO0000000005").absoluteLimit(BigDecimal.valueOf(500.00)).accountholder(user2).creationdate(OffsetDateTime.of(2022, 6, 3, 16, 30, 30, 0, ZoneOffset.UTC)).accounttype(Account.AccounttypeEnum.SAVINGS).status(Account.StatusEnum.OPEN).balance(100.0);
        Account accountTest2EmpCurrent = new Account().iban("NL01INHO0000000006").absoluteLimit(BigDecimal.valueOf(1000.00)).accountholder(user2).creationdate(OffsetDateTime.of(1980, 6, 3, 8, 25, 10, 0, ZoneOffset.UTC)).accounttype(Account.AccounttypeEnum.CURRENT).status(Account.StatusEnum.OPEN).balance(600.0);
        Account accountClosed = new Account().iban("NL01INHO0000000007").absoluteLimit(BigDecimal.valueOf(1000.00)).accountholder(user2).creationdate(OffsetDateTime.of(2021, 6, 3, 20, 30, 30, 0, ZoneOffset.UTC)).accounttype(Account.AccounttypeEnum.CURRENT).status(Account.StatusEnum.CLOSED).balance(200.0);

        accountRepository.save(currentCust);
        accountRepository.save(savingsCust);
        accountRepository.save(accountTest2EmpSavings);
        accountRepository.save(accountTest2EmpCurrent);
        accountRepository.save(accountClosed);
    }
}
