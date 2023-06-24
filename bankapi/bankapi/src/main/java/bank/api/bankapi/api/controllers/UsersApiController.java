package bank.api.bankapi.api.controllers;

import bank.api.bankapi.api.interfaces.UsersApi;
import bank.api.bankapi.model.Account;
import bank.api.bankapi.model.User;
import bank.api.bankapi.model.dto.GetDayLimitDTO;
import bank.api.bankapi.security.JwtTokenProvider;
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
import javax.validation.constraints.Min;
import java.util.List;

@RestController
public class UsersApiController implements UsersApi {

    private static final Logger log = LoggerFactory.getLogger(UsersApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @org.springframework.beans.factory.annotation.Autowired
    public UsersApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<User> createUser(@Parameter(in = ParameterIn.DEFAULT, description = "The user to be created", required = true, schema = @Schema()) @Valid @RequestBody PostUserDTO oPostUserDTO) {
        try {
            User oUser = userService.addUser(oPostUserDTO);
            return new ResponseEntity<User>(oUser, HttpStatus.CREATED);
        } catch (NullPointerException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (AddressException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

    }

    public ResponseEntity<Void> deleteUser(@Min(1) @Parameter(in = ParameterIn.PATH, description = "The id of a user", required = true, schema = @Schema(allowableValues = {}, minimum = "1"
    )) @PathVariable("userId") Long userId) {
        try {
            userService.deleteUserById(userId, tokenProvider.getUsername(tokenProvider.resolveToken(request)));
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        } catch (ForbiddenException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    public ResponseEntity<List<Account>> getOwnAccounts() {
        try {
            return new ResponseEntity<List<Account>>(userService.getOwnAccounts(tokenProvider.getUsername(tokenProvider.resolveToken(request))), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    public ResponseEntity<GetDayLimitDTO> getRemainingLimit() {
        try {
            return new ResponseEntity<GetDayLimitDTO>(new GetDayLimitDTO(userService.getRemainingDayLimit(tokenProvider.getUsername(tokenProvider.resolveToken(request)))), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    public ResponseEntity<List<User>> getUsers(@Parameter(in = ParameterIn.QUERY, description = "The userid to filter on", schema = @Schema()) @Valid @RequestParam(value = "userid", required = false) Integer userid, @Parameter(in = ParameterIn.QUERY, description = "The username to filter on", schema = @Schema()) @Valid @RequestParam(value = "username", required = false) String username, @Parameter(in = ParameterIn.QUERY, description = "The first name to filter on", schema = @Schema()) @Valid @RequestParam(value = "firstName", required = false) String firstName, @Parameter(in = ParameterIn.QUERY, description = "The last name to filter on", schema = @Schema()) @Valid @RequestParam(value = "lastName", required = false) String lastName, @Parameter(in = ParameterIn.QUERY, description = "The email to filter on", schema = @Schema()) @Valid @RequestParam(value = "email", required = false) String email, @Parameter(in = ParameterIn.QUERY, description = "The role of a user to filter on", schema = @Schema(allowableValues = {"customer", "employee"}
    )) @Valid @RequestParam(value = "role", required = false) String role, @Parameter(in = ParameterIn.QUERY, description = "The numbers of users to return.", schema = @Schema()) @Valid @RequestParam(value = "limit", required = false) Integer limit, @Parameter(in = ParameterIn.QUERY, description = "Return users if they have an account.", schema = @Schema()) @Valid @RequestParam(value = "hasAccount", required = false) Boolean hasAccount) {
        try {
            return new ResponseEntity<List<User>>(userService.getUsers(tokenProvider.getUsername(tokenProvider.resolveToken(request)), userid, username, firstName, lastName, email, role, limit, hasAccount), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    public ResponseEntity<User> updateUser(@Parameter(in = ParameterIn.DEFAULT, description = "The new user", required = true, schema = @Schema()) @Valid @RequestBody User oUser) {
        try {
            return new ResponseEntity<User>(userService.updateUser(oUser, tokenProvider.getUsername(tokenProvider.resolveToken(request))), HttpStatus.OK);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format(e.getMessage()));
        } catch (ForbiddenException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, String.format(e.getMessage()));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, String.format(e.getMessage()));
        }

    }

}