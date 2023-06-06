package bank.api.bankapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
public class UserLogin   {

    @Schema(example = "someone@gmail.com",required = true, description = "Email of the user to log in ")
    @NotNull
    @JsonProperty("emailAddress")
    private String emailAddress = null;

    @Schema(example="password", required = true, description = "password of the user")
    @NotNull
    @JsonProperty("password")
    private String password = null;

    public UserLogin(String emailAddress, String password) {
        this.emailAddress = emailAddress;
        this.password = password;
    }
}