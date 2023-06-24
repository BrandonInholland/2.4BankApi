package bank.api.bankapi.model.dto;

import bank.api.bankapi.model.enums.Roles;
import com.fasterxml.jackson.annotation.JsonProperty;
import bank.api.bankapi.model.enums.Roles;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import org.threeten.bp.OffsetDateTime;

import javax.validation.Valid;
import java.util.Objects;

/**
 * GetTransactionDTO
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-06-17T16:11:18.306Z[GMT]")


public class GetTransactionDTO {
    @JsonProperty("IBAN_sender")
    private String ibANSender = null;

    @JsonProperty("IBAN_reciever")
    private String ibANReciever = null;

    @JsonProperty("amount")
    private Double amount = null;

    @JsonProperty("timestamp")
    private OffsetDateTime timestamp = null;


    @JsonProperty("performingUsername")
    private Roles performingUsername = null;

    public GetTransactionDTO ibANSender(String ibANSender) {
        this.ibANSender = ibANSender;
        return this;
    }

    /**
     * Get ibANSender
     *
     * @return ibANSender
     **/
    @Schema(example = "NLxxINHO0xxxxxxxxx", description = "")

    public String getIbANSender() {
        return ibANSender;
    }

    public void setIbANSender(String ibANSender) {
        this.ibANSender = ibANSender;
    }

    public GetTransactionDTO ibANReciever(String ibANReciever) {
        this.ibANReciever = ibANReciever;
        return this;
    }

    /**
     * Get ibANReciever
     *
     * @return ibANReciever
     **/
    @Schema(example = "NLxxINHO0xxxxxxxxx", description = "")

    public String getIbANReciever() {
        return ibANReciever;
    }

    public void setIbANReciever(String ibANReciever) {
        this.ibANReciever = ibANReciever;
    }

    public GetTransactionDTO amount(Double amount) {
        this.amount = amount;
        return this;
    }

    /**
     * Get amount
     *
     * @return amount
     **/
    @Schema(example = "500", description = "")

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public GetTransactionDTO timestamp(OffsetDateTime timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    /**
     * Get timestamp
     *
     * @return timestamp
     **/
    @Schema(description = "")

    @Valid
    public OffsetDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(OffsetDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public GetTransactionDTO performingUsername(Roles performingUsername) {
        this.performingUsername = performingUsername;
        return this;
    }

    /**
     * Get performingUsername
     *
     * @return performingUsername
     **/
    @Schema(example = "Floris Banana", description = "")

    public Roles getPerformingUsername() {
        return performingUsername;
    }

    public void setPerformingUsername(Roles performingUsername) {
        this.performingUsername = performingUsername;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GetTransactionDTO getTransactionDTO = (GetTransactionDTO) o;
        return Objects.equals(this.ibANSender, getTransactionDTO.ibANSender) &&
                Objects.equals(this.ibANReciever, getTransactionDTO.ibANReciever) &&
                Objects.equals(this.amount, getTransactionDTO.amount) &&
                Objects.equals(this.timestamp, getTransactionDTO.timestamp) &&
                Objects.equals(this.performingUsername, getTransactionDTO.performingUsername);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ibANSender, ibANReciever, amount, timestamp, performingUsername);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class GetTransactionDTO {\n");

        sb.append("    ibANSender: ").append(toIndentedString(ibANSender)).append("\n");
        sb.append("    ibANReciever: ").append(toIndentedString(ibANReciever)).append("\n");
        sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
        sb.append("    timestamp: ").append(toIndentedString(timestamp)).append("\n");
        sb.append("    performingUsername: ").append(toIndentedString(performingUsername)).append("\n");
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