package bank.api.bankapi.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * Login dto
 */
@Schema(description = "Login dto")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-06-17T16:11:18.306Z[GMT]")


public class LoginDTO {
    @JsonProperty("username")
    private String username = null;

    @JsonProperty("password")
    private String password = null;

    public LoginDTO username(String username) {
        this.username = username;
        return this;
    }

    /**
     * Get username
     *
     * @return username
     **/
    @Schema(example = "fLastname123", required = true, description = "")
    @NotNull

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LoginDTO password(String password) {
        this.password = password;
        return this;
    }

    /**
     * Get password
     *
     * @return password
     **/
    @Schema(example = "SecretPass123!", required = true, description = "")
    @NotNull

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LoginDTO loginDTO = (LoginDTO) o;
        return Objects.equals(this.username, loginDTO.username) &&
                Objects.equals(this.password, loginDTO.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class LoginDTO {\n");

        sb.append("    username: ").append(toIndentedString(username)).append("\n");
        sb.append("    password: ").append(toIndentedString(password)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
