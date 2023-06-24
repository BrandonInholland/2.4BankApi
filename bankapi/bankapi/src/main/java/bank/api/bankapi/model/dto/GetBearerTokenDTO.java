package bank.api.bankapi.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * GetBearerTokenDTO
 */
@AllArgsConstructor
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-06-17T16:11:18.306Z[GMT]")


public class GetBearerTokenDTO {

    @JsonProperty("bearerToken")
    private String bearerToken = null;

    public GetBearerTokenDTO bearerToken(String bearerToken) {
        this.bearerToken = bearerToken;
        return this;
    }

    /**
     * Get bearerToken
     *
     * @return bearerToken
     **/
    @Schema(example = "34ad9.dh485.k30shd", description = "")

    public String getBearerToken() {
        return bearerToken;
    }

    public void setBearerToken(String bearerToken) {
        this.bearerToken = bearerToken;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GetBearerTokenDTO getBearerTokenDTO = (GetBearerTokenDTO) o;
        return Objects.equals(this.bearerToken, getBearerTokenDTO.bearerToken);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bearerToken);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class GetBearerTokenDTO {\n");
        sb.append("    bearerToken: ").append(toIndentedString(bearerToken)).append("\n");
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
