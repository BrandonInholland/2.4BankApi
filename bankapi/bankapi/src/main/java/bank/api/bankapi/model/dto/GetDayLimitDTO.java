package bank.api.bankapi.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

@Validated
@AllArgsConstructor
public class GetDayLimitDTO {
    @JsonProperty("remainingLimit")
    private Double remainingLimit = null;

    public GetDayLimitDTO remainingLimit(Double remainingLimit) {
        this.remainingLimit = remainingLimit;
        return this;
    }

    /**
     * Get remainingLimit
     *
     * @return remainingLimit
     **/
    @Schema(description = "")

    public Double getRemainingLimit() {
        return remainingLimit;
    }

    public void setRemainingLimit(Double remainingLimit) {
        this.remainingLimit = remainingLimit;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GetDayLimitDTO getDayLimitDTO = (GetDayLimitDTO) o;
        return Objects.equals(this.remainingLimit, getDayLimitDTO.remainingLimit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(remainingLimit);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class GetDayLimitDTO {\n");

        sb.append("    remainingLimit: ").append(toIndentedString(remainingLimit)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
