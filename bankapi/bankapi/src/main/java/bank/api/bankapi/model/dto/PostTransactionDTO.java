package bank.api.bankapi.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * PostTransactionDTO
 */
@Validated
//@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-05-17T16:11:18.306Z[GMT]")


public class PostTransactionDTO {
    @JsonProperty("IBAN_sender")
    private String IBAN_sender = null;

    @JsonProperty("IBAN_reciever")
    private String IBAN_reciever = null;

    @JsonProperty("amount")
    private Double amount = null;

    public PostTransactionDTO ibANSender(String ibANSender) {
        this.IBAN_sender = ibANSender;
        return this;
    }

    /**
     * Get ibANSender
     *
     * @return ibANSender
     **/
    @Schema(example = "NLxxINHO0xxxxxxxxx", description = "")

    public String getIBAN_sender() {
        return IBAN_sender;
    }

    public void setIBAN_sender(String IBAN_sender) {
        this.IBAN_sender = IBAN_sender;
    }


    public PostTransactionDTO ibANReciever(String ibANReciever) {
        this.IBAN_reciever = ibANReciever;
        return this;
    }

    /**
     * Get ibANReciever
     *
     * @return ibANReciever
     **/
    @Schema(example = "NLxxINHO0xxxxxxxxx", description = "")

    public String getIBAN_reciever() {
        return IBAN_reciever;
    }

    public void setIBAN_reciever(String IBAN_reciever) {
        this.IBAN_reciever = IBAN_reciever;
    }

    public PostTransactionDTO amount(Double amount) {
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


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PostTransactionDTO postTransactionDTO = (PostTransactionDTO) o;
        return Objects.equals(this.IBAN_sender, postTransactionDTO.IBAN_sender) &&
                Objects.equals(this.IBAN_reciever, postTransactionDTO.IBAN_reciever) &&
                Objects.equals(this.amount, postTransactionDTO.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(IBAN_sender, IBAN_reciever, amount);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class PostTransactionDTO {\n");

        sb.append("    IBAN_Sender: ").append(toIndentedString(IBAN_sender)).append("\n");
        sb.append("    IBAN_Reciever: ").append(toIndentedString(IBAN_reciever)).append("\n");
        sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
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
