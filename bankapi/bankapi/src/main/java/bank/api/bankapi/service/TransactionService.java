package bank.api.bankapi.service;


import bank.api.bankapi.model.Account;
import bank.api.bankapi.model.Transaction;
import bank.api.bankapi.model.User;
import bank.api.bankapi.model.dto.GetTransactionDTO;
import bank.api.bankapi.model.dto.PostTransactionDTO;
import bank.api.bankapi.model.enums.Roles;
import bank.api.bankapi.repository.AccountRepository;
import bank.api.bankapi.repository.TransactionRepository;
import bank.api.bankapi.repository.UserRepository;
//import io.swagger.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.threeten.bp.LocalTime;
import org.threeten.bp.OffsetDateTime;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class TransactionService {

    @Autowired
    AccountService accountService = new AccountService();
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    UserService userService = new UserService();
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private JwtTokenProvider tokenProvider;

    public List<Transaction> getTransactions() {
        return transactionRepository.findAll();
    }

    public List<GetTransactionDTO> getTransactions(Integer limit, Long transactionId, String sender, String receiver, String amount, String startDate, String endDate, String userPerforming, User user) throws Exception {
        //Get specific results

        List<Transaction> lTransaction = new ArrayList<>();
        if (user.getRoles().contains(Roles.EMPLOYEE)) {
            lTransaction = transactionRepository.findAll();

            // Filtering of Results
            if (limit != null) {
                //lTransaction = transactionRepository.findLimit(limit);
                lTransaction = lTransaction.stream().limit(limit).collect(Collectors.toList());
            }
            if (transactionId != null) {
                lTransaction.add(transactionRepository.findById(transactionId).get());
                //lTransaction = lTransaction.stream().filter((t) -> t.getTransactionId().equals(transactionId)).collect(Collectors.toList());
            } else {
                lTransaction = transactionRepository.findBySenderAndByReceiver(sender, receiver);
            }


            if (amount != null) {
                String[] arrayString = amount.split(",");
                switch (arrayString[0]) {
                    case "=":
                        lTransaction = lTransaction.stream().filter((t) -> t.getAmount().equals(Double.parseDouble(arrayString[1]))).collect(Collectors.toList());
                        break;
                    case "<":

                        lTransaction = lTransaction.stream().filter((t) -> (t.getAmount() < Double.parseDouble(arrayString[1]))).collect(Collectors.toList());

                        break;
                    case ">":
                        lTransaction = lTransaction.stream().filter((t) -> (t.getAmount() > Double.parseDouble(arrayString[1]))).collect(Collectors.toList());
                        break;
                }
            }
            if (startDate != null && endDate != null) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
                //convert String to LocalDate
                LocalDate startDates = LocalDate.parse(startDate, formatter);
                LocalDate endDates = LocalDate.parse(endDate, formatter);


                // show dates between target range
                lTransaction.removeIf(t -> LocalDate.parse(t.getTimestamp().toString().substring(0, 10)).isBefore(startDates) || LocalDate.parse(t.getTimestamp().toString().substring(0, 10)).isAfter(endDates));

            } else if (startDate != null) {

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
                //convert String to LocalDate
                LocalDate startDates = LocalDate.parse(startDate, formatter);


                // show all dates from start till now.
                lTransaction.removeIf(t -> LocalDate.parse(t.getTimestamp().toString().substring(0, 10)).isBefore(startDates));

            } else if (endDate != null) {

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
                //convert String to LocalDate

                LocalDate endDates = LocalDate.parse(endDate, formatter);


                // show all dates leading up to this date
                lTransaction.removeIf(t -> LocalDate.parse(t.getTimestamp().toString().substring(0, 10)).isAfter(endDates));
            }

            if (userPerforming != null) {
                lTransaction = lTransaction.stream().filter((t) -> t.getUserPerforming().toString().equals(userPerforming)).collect(Collectors.toList());
            }

        } else if (user.getRoles().contains(Roles.CUSTOMER)) {

            lTransaction = transactionRepository.getTransactionByAccount_Accountholder_Username(user.getUsername());


        } else {
            throw new Exception("You are not logged in");
        }


        List<GetTransactionDTO> results = new ArrayList<GetTransactionDTO>();


        for (Transaction transaction : lTransaction) {
            results.add(getTransactionDTO(transaction));


        }


        System.out.println(results);


        return results;
    }


    public Transaction getTransactionById(long id) {
        Optional<Transaction> result = transactionRepository.findById(id);

        if (result.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("The transaction with id %s could not be found.", id));
        }

        return result.get();
    }

    public GetTransactionDTO addTransaction(PostTransactionDTO postTransactionDTO, User user) throws Exception {
        // Get user and accounts
        Account sender;
        Account receiver;
        Transaction transaction = new Transaction();

        // Determine the type of transaction (normal, deposit, withdrawal)
        if (postTransactionDTO.getIBAN_sender().equals("cash")) {

            transaction.ibANSender("NL01INHO0000000001");
        } else {
            transaction.ibANSender(postTransactionDTO.getIBAN_sender());
        }


        if (postTransactionDTO.getIBAN_reciever().equals("cash")) {
            transaction.ibANReciever("NL01INHO0000000001");
        } else {
            transaction.ibANReciever(postTransactionDTO.getIBAN_reciever());
        }


        try {
            if (!transaction.getIbANSender().equals("NL01INHO0000000001")) {
                sender = accountService.getAccountByIBAN(postTransactionDTO.getIBAN_sender());

            } else {
                sender = accountService.getAccountByIBAN("NL01INHO0000000001");
            }
        } catch (Exception exception) {
            throw new Exception("Invalid Sender");
        }

        try {
            if (!transaction.getIbANReciever().equals("NL01INHO0000000001")) {
                receiver = accountService.getAccountByIBAN(postTransactionDTO.getIBAN_reciever());

            } else {
                receiver = accountService.getAccountByIBAN("NL01INHO0000000001");
            }
        } catch (Exception exception) {
            throw new Exception("Invalid Reciever");
        }

        transaction.setIbANSender(postTransactionDTO.getIBAN_sender());
        transaction.setIbANReciever(postTransactionDTO.getIBAN_reciever());
        transaction.setAmount(postTransactionDTO.getAmount());

        transaction.setTimestamp(OffsetDateTime.now());

        //Add user performing based on role
        transaction.setUserPerforming(user.getRoles().contains(Roles.CUSTOMER) ? Roles.CUSTOMER : Roles.EMPLOYEE);


        // Transaction can't be cash to cash
        if (transaction.getIbANSender().equals("cash") && transaction.getIbANReciever().equals("cash")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot send cash to cash.");
        }


        //If validations are true
        try {

            if (validateTransaction(user, sender, receiver, transaction)) {

                // Increase receiver account balance and decrease sender account balance
                sender.setBalance(sender.getBalance() - transaction.getAmount());

                receiver.setBalance(receiver.getBalance() + transaction.getAmount());

            }

        } catch (Exception exception) {
            throw new Exception(exception.getMessage());
        }


        transactionRepository.save(transaction);


        return getTransactionDTO(transaction);
    }

    //Converting Method for transaction to transaction DTO
    public GetTransactionDTO getTransactionDTO(Transaction transaction) {
        GetTransactionDTO getTransactionDTO = new GetTransactionDTO();
        getTransactionDTO.setPerformingUsername(transaction.getUserPerforming());
        getTransactionDTO.setIbANSender(transaction.getIbANSender());
        getTransactionDTO.setIbANReciever(transaction.getIbANReciever());
        getTransactionDTO.setAmount(transaction.getAmount());

        getTransactionDTO.setTimestamp(transaction.getTimestamp());

        return getTransactionDTO;
    }

    private boolean validateTransaction(User user, Account sender, Account receiver, Transaction transaction) throws Exception {

        validateSender(user, sender, receiver, transaction);
        validateCustomerTransaction(user, sender, receiver, transaction);
        return true;
    }

    private void validateSender(User user, Account sender, Account receiver, Transaction transaction) throws Exception {

        if (sender.getIban().equals("NL01INHO0000000001")) {
            receiver.setBalance(receiver.getBalance() + transaction.getAmount());
        } else if (!user.equals(sender.getAccountholder())) {
            // If the sender account does not belong to the user
            throw new Exception("The sender account does not belong to you.");
        }

        // If the new balance would be lower than zero
        if (sender.getBalance() - transaction.getAmount() < -sender.getAbsoluteLimit().doubleValue()) {
            throw new Exception("The sender account balance can't become lower than the absolute limit.{" + sender.getAbsoluteLimit().toString() + "}");
        }
    }

    private void validateCustomerTransaction(User user, Account sender, Account receiver, Transaction transaction) throws Exception {
        // If the amount the user wants to transfer is higher than that account's set limit

        if (transaction.getAmount() > user.getTransactionLimit().doubleValue()) {
            throw new Exception("The transaction amount exceeds the transaction limit of " + user.getTransactionLimit());
        }

        // If the new balance would become lower than the account's set absolute limit

        if (sender.getAbsoluteLimit().equals(BigDecimal.valueOf(0.0))) {
            if (sender.getBalance() - transaction.getAmount() < 0) {
                throw new Exception("Your new balance would become lower that your absolute limit of " + sender.getAbsoluteLimit());
            }

        } else if (sender.getBalance() - transaction.getAmount() < -sender.getAbsoluteLimit().doubleValue()) {

            throw new Exception("Your new balance would become lower that your absolute limit of " + sender.getAbsoluteLimit());
        }
        double transactionDayValue = 0.0;
        // The total amount transferred today
        if (userService.getRemainingDayLimit(sender.getAccountholder().getUsername()) == null) {

            transactionDayValue = sender.getAccountholder().getDayLimit().doubleValue();
        } else {

            transactionDayValue = userService.getRemainingDayLimit(sender.getAccountholder().getUsername());
        }


        // If the new total amount transferred today would become higher than the account's set limit
        if (transaction.getAmount() > transactionDayValue) {
            throw new Exception("You can't exceed your daily limit of " + user.getDayLimit());
        }
        // IF amount is less then 0
        if (transaction.getAmount() <= 0) {
            throw new IllegalArgumentException("The amount can not be 0 or lower! ");
        }

        if (sender.getAccounttype() == Account.AccounttypeEnum.SAVINGS && receiver.getAccountholder() != sender.getAccountholder()) {
            throw new Exception("You can not send money from your savings account to accounts you don't own");
        }

        if (receiver.getAccountholder() != sender.getAccountholder() && receiver.getAccounttype() == Account.AccounttypeEnum.SAVINGS) {
            throw new Exception("You can not send money to savings accounts that you don't own");
        }
    }


}
