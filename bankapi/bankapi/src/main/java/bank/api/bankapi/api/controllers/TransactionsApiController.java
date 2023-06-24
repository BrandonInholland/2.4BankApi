package bank.api.bankapi.api.controllers;


import bank.api.bankapi.security.JwtTokenProvider;
import bank.api.bankapi.service.TransactionService;
import bank.api.bankapi.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import bank.api.bankapi.api.interfaces.TransactionsApi;
import bank.api.bankapi.model.Transaction;
import bank.api.bankapi.model.User;
import bank.api.bankapi.model.dto.GetTransactionDTO;
import bank.api.bankapi.model.dto.PostTransactionDTO;
import bank.api.bankapi.model.enums.Roles;
//import io.swagger.security.JwtTokenProvider;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.threeten.bp.OffsetDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-06-17T16:11:18.306Z[GMT]")

@RestController
public class TransactionsApiController implements TransactionsApi {

    private static final Logger log = LoggerFactory.getLogger(TransactionsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private TransactionService transactionService;

    @org.springframework.beans.factory.annotation.Autowired
    public TransactionsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<GetTransactionDTO> createTransaction(@Parameter(in = ParameterIn.DEFAULT, description = "transaction placed for processing", required = true, schema = @Schema()) @Valid @RequestBody PostTransactionDTO body) {
        try
        {
            User currentUser = userService.getUserByUsername(tokenProvider.getUsername(tokenProvider.resolveToken(request)));
            GetTransactionDTO getTransactionDTO = transactionService.addTransaction(body,currentUser);
            return new ResponseEntity<GetTransactionDTO>(getTransactionDTO, HttpStatus.CREATED);
        }
        catch (Exception exception)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage());
        }

    }


    public ResponseEntity<List<GetTransactionDTO>> getTransactions(
            @Parameter(in = ParameterIn.QUERY, description = "The number of transactions to return.", schema = @Schema())
            @Valid @RequestParam(value = "limit", required = false) Integer limit,
            @Parameter(in = ParameterIn.QUERY, description = "Filter by transaction ID", schema = @Schema())
            @Valid @RequestParam(value = "transactionId", required = false) Long transactionId,
            @Parameter(in = ParameterIn.QUERY, description = "Filter by IBAN of the sender", schema = @Schema())
            @Valid @RequestParam(value = "IBAN_sender", required = false) String ibANSender,
            @Parameter(in = ParameterIn.QUERY, description = "Filter by IBAN of the receiver", schema = @Schema())
            @Valid @RequestParam(value = "IBAN_receiver", required = false) String ibANReceiver,
            @Parameter(in = ParameterIn.QUERY, description = "Filter by amount", schema = @Schema())
            @Valid @RequestParam(value = "amount", required = false) String amount,
            @Parameter(in = ParameterIn.QUERY, description = "Filter by start date", schema = @Schema())
            @Valid @RequestParam(value = "startDate", required = false) String startDate,
            @Parameter(in = ParameterIn.QUERY, description = "Filter by end date", schema = @Schema())
            @Valid @RequestParam(value = "endDate", required = false) String endDate,
            @Parameter(in = ParameterIn.QUERY, description = "Filter by the role of the user performing the transaction", schema = @Schema(allowableValues = {"employee", "customer"}))
            @Valid @RequestParam(value = "userPerforming", required = false) String userPerforming,
            @Parameter(in = ParameterIn.QUERY, description = "Filter by status of the transaction", schema = @Schema(allowableValues = {"placed", "approved", "completed"}))
            @Valid @RequestParam(value = "status", required = false) String status) {

        User currentUser = userService.getUserByUsername(tokenProvider.getUsername(tokenProvider.resolveToken(request)));
        try{
            List<GetTransactionDTO> results = new ArrayList<GetTransactionDTO>();
            results = transactionService.getTransactions(limit, transactionId, ibANSender, ibANReceiver, amount, startDate, endDate, userPerforming, currentUser);

            return ResponseEntity.status(200).body(results);
        }
        catch (Exception exception)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage());
        }

    }

}
