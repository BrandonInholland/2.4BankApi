package bank.api.bankapi.model.dto;

import bank.api.bankapi.model.Account;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * PutAccountDTO
 */
@Validated



public class PutAccountDTO {
    @JsonProperty("status")
    private Account.StatusEnum status = null;
    @JsonProperty("absoluteLimit")
    private BigDecimal absoluteLimit = null;

    public PutAccountDTO status(Account.StatusEnum status) {
        this.status = status;
        return this;
    }

    /**
     * Get status
     *
     * @return status
     **/
    @Schema(example = "open", description = "")

    public Account.StatusEnum getStatus() {
        return status;
    }

    public void setStatus(Account.StatusEnum status) {
        this.status = status;
    }

    public PutAccountDTO absoluteLimit(BigDecimal absoluteLimit) {
        this.absoluteLimit = absoluteLimit;
        return this;
    }

    /**
     * Get absoluteLimit
     *
     * @return absoluteLimit
     **/
    @Schema(example = "0", description = "")

    @Valid
    public BigDecimal getAbsoluteLimit() {
        return absoluteLimit;
    }

    public void setAbsoluteLimit(BigDecimal absoluteLimit) {
        this.absoluteLimit = absoluteLimit;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PutAccountDTO putAccountDTO = (PutAccountDTO) o;
        return Objects.equals(this.status, putAccountDTO.status) &&
                Objects.equals(this.absoluteLimit, putAccountDTO.absoluteLimit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, absoluteLimit);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class PutAccountDTO {\n");

        sb.append("    status: ").append(toIndentedString(status)).append("\n");
        sb.append("    absoluteLimit: ").append(toIndentedString(absoluteLimit)).append("\n");
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

    /**
     * Gets or Sets status
     */
    public enum StatusEnum {
        OPEN("open"),

        CLOSED("closed");

        private String value;

        StatusEnum(String value) {
            this.value = value;
        }

        @JsonCreator
        public static StatusEnum fromValue(String text) {
            for (StatusEnum b : StatusEnum.values()) {
                if (String.valueOf(b.value).equals(text)) {
                    return b;
                }
            }
            return null;
        }

        @Override
        @JsonValue
        public String toString() {
            return String.valueOf(value);
        }
    }
}
