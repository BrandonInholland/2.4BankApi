/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.34).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package bank.api.bankapi.api.interfaces;

import bank.api.bankapi.annotations.Api;
import bank.api.bankapi.model.Account;
import bank.api.bankapi.model.dto.ErrorDTO;
import bank.api.bankapi.model.dto.PostAccountDTO;
import bank.api.bankapi.model.dto.PutAccountDTO;
import bank.api.bankapi.v3.oas.annotations.Operation;
import bank.api.bankapi.v3.oas.annotations.Parameter;
import bank.api.bankapi.v3.oas.annotations.enums.ParameterIn;
import bank.api.bankapi.v3.oas.annotations.media.ArraySchema;
import bank.api.bankapi.v3.oas.annotations.media.Content;
import bank.api.bankapi.v3.oas.annotations.media.Schema;
import bank.api.bankapi.v3.oas.annotations.responses.ApiResponse;
import bank.api.bankapi.v3.oas.annotations.responses.ApiResponses;
import bank.api.bankapi.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.annotations.Api;
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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Validated
@Api(tags = "employee")
public interface AccountsApi {

    @Operation(summary = "Add a new account", description = "", security = {
            @SecurityRequirement(name = "bearerAuth")}, tags = {"employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Account was created", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Account.class)))),

            @ApiResponse(responseCode = "400", description = "Bad request"),

            @ApiResponse(responseCode = "401", description = "Unauthorized"),

            @ApiResponse(responseCode = "404", description = "The specified resource was not found"),

            @ApiResponse(responseCode = "500", description = "Unexpected error")})
    @RequestMapping(value = "/accounts",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<Account> addAccount(@Parameter(in = ParameterIn.DEFAULT, description = "Account object that will be added", required = true, schema = @Schema()) @Valid @RequestBody PostAccountDTO body);


    @Operation(summary = "Get account by iban", description = "Return a single account", security = {
            @SecurityRequirement(name = "bearerAuth")}, tags = {"employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Account.class))),

            @ApiResponse(responseCode = "400", description = "Bad request"),

            @ApiResponse(responseCode = "401", description = "Unauthorized"),

            @ApiResponse(responseCode = "404", description = "The specified resource was not found"),

            @ApiResponse(responseCode = "500", description = "Unexpected error")})
    @RequestMapping(value = "/accounts/{iban}",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<Account> getAccountByIban(@Parameter(in = ParameterIn.PATH, description = "iban of account to return", required = true, schema = @Schema()) @PathVariable("iban") String iban);


    @Operation(summary = "Get accounts.", description = "", security = {
            @SecurityRequirement(name = "bearerAuth")}, tags = {"employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Account.class)))),

            @ApiResponse(responseCode = "400", description = "Bad request"),

            @ApiResponse(responseCode = "401", description = "Unauthorized"),

            @ApiResponse(responseCode = "404", description = "The specified resource was not found"),

            @ApiResponse(responseCode = "500", description = "Unexpected error")})
    @RequestMapping(value = "/accounts",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<Account>> getUserAccounts(@Parameter(in = ParameterIn.QUERY, description = "ID of user", schema = @Schema()) @Valid @RequestParam(value = "userid", required = false) Long userid, @Parameter(in = ParameterIn.QUERY, description = "Role of the account owner", schema = @Schema(allowableValues = {"employee", "customer"}
    )) @Valid @RequestParam(value = "userrole", required = false) String userrole, @Parameter(in = ParameterIn.QUERY, description = "The status of the account", schema = @Schema(allowableValues = {"open", "closed"}
    )) @Valid @RequestParam(value = "status", required = false) String status, @Parameter(in = ParameterIn.QUERY, description = "Type of account", schema = @Schema(allowableValues = {"savings", "current"}
    )) @Valid @RequestParam(value = "type", required = false) String type, @Parameter(in = ParameterIn.QUERY, description = "The date the account was made", schema = @Schema()) @Valid @RequestParam(value = "creationDate", required = false) String creationDate);


    @Operation(summary = "Update existing account", description = "", security = {
            @SecurityRequirement(name = "bearerAuth")}, tags = {"employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Account was updated", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Account.class)))),

            @ApiResponse(responseCode = "400", description = "Bad request"),

            @ApiResponse(responseCode = "401", description = "Unauthorized"),

            @ApiResponse(responseCode = "404", description = "The specified resource was not found"),

            @ApiResponse(responseCode = "500", description = "Unexpected error")})
    @RequestMapping(value = "/accounts/{iban}",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.PUT)
    ResponseEntity<Account> updateAccount(@Parameter(in = ParameterIn.PATH, description = "iban of account to return", required = true, schema = @Schema()) @PathVariable("iban") String iban, @Parameter(in = ParameterIn.DEFAULT, description = "", schema = @Schema()) @Valid @RequestBody PutAccountDTO body);

}

