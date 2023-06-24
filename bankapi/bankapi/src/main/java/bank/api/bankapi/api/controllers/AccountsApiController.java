package bank.api.bankapi.api.controllers;

import bank.api.bankapi.api.interfaces.AccountsApi;
import bank.api.bankapi.model.Account;
import bank.api.bankapi.model.User;
import bank.api.bankapi.model.dto.PostAccountDTO;
import bank.api.bankapi.model.dto.PutAccountDTO;
import bank.api.bankapi.service.AccountService;
import bank.api.bankapi.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-06-17T16:11:18.306Z[GMT]")

@RestController
public class AccountsApiController implements AccountsApi{

    private static final Logger log = LoggerFactory.getLogger(AccountsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

//    @Autowired
//    private JwtTokenProvider tokenProvider;

    @Autowired
    private AccountService accountService;

    @Autowired
    private UserService userService;

    @org.springframework.beans.factory.annotation.Autowired
    public AccountsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Account> addAccount(@Parameter(in = ParameterIn.DEFAULT, description = "Account object that will be added", required = true, schema = @Schema()) @Valid @RequestBody PostAccountDTO body) {
        try {
//            User currentUser = userService.getUserByUsername(tokenProvider.getUsername(tokenProvider.resolveToken(request)));
            var currentUser = new User();
            return ResponseEntity.status(201).body(accountService.addAccount(body, currentUser));
        } catch (NullPointerException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, ex.getMessage());
        }
    }

    public ResponseEntity<Account> getAccountByIban(@Parameter(in = ParameterIn.PATH, description = "iban of account to return", required = true, schema = @Schema()) @PathVariable("iban") String iban) {
        try {
//            User currentUser = userService.getUserByUsername(tokenProvider.getUsername(tokenProvider.resolveToken(request)));
            var currentUser = new User();
            Account account = accountService.findByIBAN(iban, currentUser);
            return ResponseEntity.status(200).body(account);
        } catch (NullPointerException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with IBAN " + iban + " not found!");
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, String.format(ex.getMessage()));
        }
    }
    public ResponseEntity<List<Account>> getUserAccounts(
            @Parameter(in = ParameterIn.QUERY, description = "ID of user", schema = @Schema()) @Valid @RequestParam(value = "userid", required = false) Long userid,
            @Parameter(in = ParameterIn.QUERY, description = "Role of the account owner", schema = @Schema(allowableValues = {"employee", "customer"})) @Valid @RequestParam(value = "userrole", required = false) String userrole,
            @Parameter(in = ParameterIn.QUERY, description = "The status of the account", schema = @Schema(allowableValues = {"open", "closed"})) @Valid @RequestParam(value = "status", required = false) String status,
            @Parameter(in = ParameterIn.QUERY, description = "Type of account", schema = @Schema(allowableValues = {"savings", "current"})) @Valid @RequestParam(value = "type", required = false) String type,
            @Parameter(in = ParameterIn.QUERY, description = "The date the account was made", schema = @Schema()) @Valid @RequestParam(value = "creationDate", required = false) String creationDate) {
        try {
//            User currentUser = userService.getUserByUsername(tokenProvider.getUsername(tokenProvider.resolveToken(request)));
            var currentUser = new User();
            return ResponseEntity.status(200).body(accountService.getAccounts(userid, userrole, status, type, creationDate, currentUser));
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, String.format(ex.getMessage()));
        }
    }


    public ResponseEntity<Account> updateAccount(@Parameter(in = ParameterIn.PATH, description = "iban of account to return", required = true, schema = @Schema()) @PathVariable("iban") String iban, @Parameter(in = ParameterIn.DEFAULT, description = "", schema = @Schema()) @Valid @RequestBody PutAccountDTO body) {
        try {
//            User currentUser = userService.getUserByUsername(tokenProvider.getUsername(tokenProvider.resolveToken(request)));
            var currentUser = new User();
            Account updatedAccount = accountService.updateAccountByIBAN(iban, body, currentUser);
            return ResponseEntity.status(200).body(updatedAccount);
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format(ex.getMessage()));
        } catch (Exception ex) { //ForbiddenException
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, String.format(ex.getMessage()));
        }
    }




}
