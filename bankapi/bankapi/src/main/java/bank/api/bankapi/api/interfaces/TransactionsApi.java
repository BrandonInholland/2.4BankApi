package bank.api.bankapi.api.interfaces;

import bank.api.bankapi.model.dto.GetTransactionDTO;
import io.swagger.annotations.Api;
import bank.api.bankapi.model.dto.GetTransactionDTO;
import bank.api.bankapi.model.dto.PostTransactionDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-06-17T16:11:18.306Z[GMT]")
@Validated
@Api(tags = "customer")
public interface TransactionsApi {

    @Operation(summary = "Create transaction", description = "", security = {
            @SecurityRequirement(name = "bearerAuth")}, tags = {"customer"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "OK", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = GetTransactionDTO.class)))),

            @ApiResponse(responseCode = "400", description = "Bad request"),

            @ApiResponse(responseCode = "401", description = "Unauthorized"),

            @ApiResponse(responseCode = "404", description = "The specified resource was not found"),

            @ApiResponse(responseCode = "500", description = "Unexpected error")})
    @RequestMapping(value = "/transactions",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<GetTransactionDTO> createTransaction(@Parameter(in = ParameterIn.DEFAULT, description = "transaction placed for processing", required = true, schema = @Schema()) @Valid @RequestBody PostTransactionDTO body);


    @Operation(summary = "Get multiple transactions.", description = "", security = {
            @SecurityRequirement(name = "bearerAuth")}, tags = {"customer"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = GetTransactionDTO.class)))),

            @ApiResponse(responseCode = "400", description = "Bad request"),

            @ApiResponse(responseCode = "401", description = "Unauthorized"),

            @ApiResponse(responseCode = "404", description = "The specified resource was not found"),

            @ApiResponse(responseCode = "500", description = "Unexpected error")})
    @RequestMapping(value = "/transactions",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<GetTransactionDTO>> getTransactions(@Parameter(in = ParameterIn.QUERY, description = "The number of transactions to return.", schema = @Schema()) @Valid @RequestParam(value = "limit", required = false) Integer limit, @Parameter(in = ParameterIn.QUERY, description = "Filter by transaction ID", schema = @Schema()) @Valid @RequestParam(value = "transactionId", required = false) Long transactionId, @Parameter(in = ParameterIn.QUERY, description = "Filter by IBAN of the sender", schema = @Schema()) @Valid @RequestParam(value = "IBAN_sender", required = false) String ibANSender, @Parameter(in = ParameterIn.QUERY, description = "Filter by IBAN of the receiver", schema = @Schema()) @Valid @RequestParam(value = "IBAN_receiver", required = false) String ibANReceiver, @Parameter(in = ParameterIn.QUERY, description = "Filter by amount", schema = @Schema()) @Valid @RequestParam(value = "amount", required = false) String amount, @Parameter(in = ParameterIn.QUERY, description = "Filter by start date", schema = @Schema()) @Valid @RequestParam(value = "startDate", required = false) String startDate, @Parameter(in = ParameterIn.QUERY, description = "Filter by end date", schema = @Schema()) @Valid @RequestParam(value = "endDate", required = false) String endDate, @Parameter(in = ParameterIn.QUERY, description = "Filter by the role of the user performing the transaction", schema = @Schema(allowableValues = {"employee", "customer"}
    )) @Valid @RequestParam(value = "userPerforming", required = false) String userPerforming, @Parameter(in = ParameterIn.QUERY, description = "Filter by status of the transaction", schema = @Schema(allowableValues = {"placed", "approved", "completed"}
    )) @Valid @RequestParam(value = "status", required = false) String status);

}
