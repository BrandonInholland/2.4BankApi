package bank.api.bankapi.api.interfaces;

import bank.api.bankapi.model.Account;
import bank.api.bankapi.model.User;
import bank.api.bankapi.model.dto.GetDayLimitDTO;
import bank.api.bankapi.model.dto.PostUserDTO;
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
import javax.validation.constraints.Min;
import java.util.List;

@Validated
@Api(tags = {"register", "employee", "customer"})
public interface UsersApi {

    @Operation(summary = "Create user", description = "", tags = {"register"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User has been created", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = User.class)))),

            @ApiResponse(responseCode = "400", description = "Bad request"),

            @ApiResponse(responseCode = "401", description = "Unauthorized"),

            @ApiResponse(responseCode = "404", description = "The specified resource was not found"),

            @ApiResponse(responseCode = "500", description = "Unexpected error")})
    @RequestMapping(value = "/users",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<User> createUser(@Parameter(in = ParameterIn.DEFAULT, description = "The user to be created", required = true, schema = @Schema()) @Valid @RequestBody PostUserDTO body);


    @Operation(summary = "Delete user without accounts", description = "", security = {
            @SecurityRequirement(name = "bearerAuth")}, tags = {"employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),

            @ApiResponse(responseCode = "400", description = "Bad request"),

            @ApiResponse(responseCode = "401", description = "Unauthorized"),

            @ApiResponse(responseCode = "404", description = "The specified resource was not found"),

            @ApiResponse(responseCode = "500", description = "Unexpected error")})
    @RequestMapping(value = "/users/{userId}",
            method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteUser(@Min(1) @Parameter(in = ParameterIn.PATH, description = "The id of a user", required = true, schema = @Schema(allowableValues = {}, minimum = "1"
    )) @PathVariable("userId") Long userId);


    @Operation(summary = "Get user's own accounts", description = "", security = {
            @SecurityRequirement(name = "bearerAuth")}, tags = {"customer"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = User.class)))),

            @ApiResponse(responseCode = "400", description = "Bad request"),

            @ApiResponse(responseCode = "401", description = "Unauthorized"),

            @ApiResponse(responseCode = "404", description = "The specified resource was not found"),

            @ApiResponse(responseCode = "500", description = "Unexpected error")})
    @RequestMapping(value = "/users/accounts",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<Account>> getOwnAccounts();


    @Operation(summary = "Get remaining day limit", description = "", security = {
            @SecurityRequirement(name = "bearerAuth")}, tags = {"employee", "customer"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GetDayLimitDTO.class))),

            @ApiResponse(responseCode = "400", description = "Bad request"),

            @ApiResponse(responseCode = "401", description = "Unauthorized"),

            @ApiResponse(responseCode = "404", description = "The specified resource was not found"),

            @ApiResponse(responseCode = "500", description = "Unexpected error")})
    @RequestMapping(value = "/users/limit",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<GetDayLimitDTO> getRemainingLimit();


    @Operation(summary = "Get users", description = "", security = {
            @SecurityRequirement(name = "bearerAuth")}, tags = {"employee", "customer"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = User.class)))),

            @ApiResponse(responseCode = "400", description = "Bad request"),

            @ApiResponse(responseCode = "401", description = "Unauthorized"),

            @ApiResponse(responseCode = "404", description = "The specified resource was not found"),

            @ApiResponse(responseCode = "500", description = "Unexpected error")})
    @RequestMapping(value = "/users",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<User>> getUsers(@Parameter(in = ParameterIn.QUERY, description = "The userid to filter on", schema = @Schema()) @Valid @RequestParam(value = "userid", required = false) Integer userid, @Parameter(in = ParameterIn.QUERY, description = "The username to filter on", schema = @Schema()) @Valid @RequestParam(value = "username", required = false) String username, @Parameter(in = ParameterIn.QUERY, description = "The first name to filter on", schema = @Schema()) @Valid @RequestParam(value = "firstName", required = false) String firstName, @Parameter(in = ParameterIn.QUERY, description = "The last name to filter on", schema = @Schema()) @Valid @RequestParam(value = "lastName", required = false) String lastName, @Parameter(in = ParameterIn.QUERY, description = "The email to filter on", schema = @Schema()) @Valid @RequestParam(value = "email", required = false) String email, @Parameter(in = ParameterIn.QUERY, description = "The role of a user to filter on", schema = @Schema(allowableValues = {"customer", "employee"}
    )) @Valid @RequestParam(value = "role", required = false) String role, @Parameter(in = ParameterIn.QUERY, description = "The numbers of users to return.", schema = @Schema()) @Valid @RequestParam(value = "limit", required = false) Integer limit, @Parameter(in = ParameterIn.QUERY, description = "Return users if they have an account.", schema = @Schema()) @Valid @RequestParam(value = "hasAccount", required = false) Boolean hasAccount);


    @Operation(summary = "Update user", description = "", security = {
            @SecurityRequirement(name = "bearerAuth")}, tags = {"employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "OK", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = User.class)))),

            @ApiResponse(responseCode = "400", description = "Bad request"),

            @ApiResponse(responseCode = "401", description = "Unauthorized"),

            @ApiResponse(responseCode = "404", description = "The specified resource was not found"),

            @ApiResponse(responseCode = "500", description = "Unexpected error")})
    @RequestMapping(value = "/users",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.PUT)
    ResponseEntity<User> updateUser(@Parameter(in = ParameterIn.DEFAULT, description = "The new user", required = true, schema = @Schema()) @Valid @RequestBody User body);

}