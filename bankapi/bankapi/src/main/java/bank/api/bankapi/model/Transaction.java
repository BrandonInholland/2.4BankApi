package bank.api.bankapi.model;

import bank.api.bankapi.model.enums.Roles;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.validation.annotation.Validated;
import org.threeten.bp.OffsetDateTime;

//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.ManyToOne;
import javax.validation.Valid;
import java.util.Objects;

/**
 * Transaction
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-06-17T16:11:18.306Z[GMT]")

@Entity
public class Transaction {
  @Id
  @GeneratedValue
  @JsonProperty("transactionId")
  private Long transactionId = null;

  @JsonProperty("IBAN_sender")
  private String ibANSender = null;

  @JsonProperty("IBAN_reciever")
  private String ibANReciever = null;

  @JsonProperty("amount")
  private Double amount = null;

  @JsonProperty("timestamp")
  private OffsetDateTime timestamp = null;


  //@JsonIgnore
  @Getter
  @Setter
  @ManyToOne
  @JsonProperty("account")
  private Account account = null;

  @JsonProperty("userPerforming")
  private Roles userPerforming = null;

  public Transaction transactionId(Long transactionId) {
    this.transactionId = transactionId;
    return this;
  }

  /**
   * Get transactionId
   *
   * @return transactionId
   **/
  @Schema(example = "1", description = "")

  public Long getTransactionId() {
    return transactionId;
  }

  public void setTransactionId(Long transactionId) {
    this.transactionId = transactionId;
  }

  public Transaction ibANSender(String ibANSender) {
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

  public Transaction ibANReciever(String ibANReciever) {
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

  public Transaction amount(Double amount) {
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
    if (amount > 0)
    {
      this.amount = amount;
    }
    else
    {
      throw new IllegalArgumentException("Amount must be above 0");
    }
  }

  public Transaction timestamp(OffsetDateTime timestamp) {
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

  public Transaction userPerforming(Roles userPerforming) {
    this.userPerforming = userPerforming;
    return this;
  }

  /**
   * Get userPerforming
   *
   * @return userPerforming
   **/
  @Schema(description = "")

  @Valid
  public Roles getUserPerforming() {
    return userPerforming;
  }

  public void setUserPerforming(Roles userPerforming) {
    this.userPerforming = userPerforming;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Transaction transaction = (Transaction) o;
    return Objects.equals(this.transactionId, transaction.transactionId) &&
            Objects.equals(this.ibANSender, transaction.ibANSender) &&
            Objects.equals(this.ibANReciever, transaction.ibANReciever) &&
            Objects.equals(this.amount, transaction.amount) &&
            Objects.equals(this.timestamp, transaction.timestamp) &&
            Objects.equals(this.userPerforming, transaction.userPerforming);
  }

  @Override
  public int hashCode() {
    return Objects.hash(transactionId, ibANSender, ibANReciever, amount, timestamp, userPerforming);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Transaction {\n");

    sb.append("    transactionId: ").append(toIndentedString(transactionId)).append("\n");
    sb.append("    ibANSender: ").append(toIndentedString(ibANSender)).append("\n");
    sb.append("    ibANReciever: ").append(toIndentedString(ibANReciever)).append("\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
    sb.append("    timestamp: ").append(toIndentedString(timestamp)).append("\n");
    sb.append("    userPerforming: ").append(toIndentedString(userPerforming)).append("\n");
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
