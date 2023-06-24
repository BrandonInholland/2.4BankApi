package bank.api.bankapi.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;
import org.threeten.bp.OffsetDateTime;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
@Validated
@EqualsAndHashCode
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-06-17T16:11:18.306Z[GMT]")

@Entity
public class Account {

    @Id
    @GeneratedValue
    private Long id = null;

    @Getter
    @JsonIgnore
    @JsonProperty("iban")
    private String iban = null;
    @JsonProperty("status")
    private StatusEnum status = null;
    @ManyToOne
    //@JsonBackReference
    @JsonProperty("accountholder")
    private User accountholder = null;
    @Getter
    @JsonProperty("balance")
    private Double balance = null;
    @Getter
    @JsonProperty("absoluteLimit")
    private BigDecimal absoluteLimit = null;
    @Getter
    @JsonIgnore
    @OneToMany
    private List<Transaction> transactions;
    @JsonProperty("account-type")
    private AccounttypeEnum accounttype = null;
    @JsonProperty("creationdate")
    private OffsetDateTime creationdate = null;

    public Account iban(String iban) {
        this.iban = iban;
        return this;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public Account status(StatusEnum status) {
        this.status = status;
        return this;
    }

    /**
     * Get ID
     *
     * @return id
     */
    @Schema(example = "1", description = "ID of the account")
    public Long getId() {
        return id;
    }

    /**
     * Get status
     *
     * @return status
     **/
    @Schema(example = "open", description = "")

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public Account accountholder(User accountholder) {
        if (accountholder == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid accountholder");
        }
        this.accountholder = accountholder;
        return this;
    }

    /**
     * Get accountholder
     *
     * @return accountholder
     **/
    @Schema(description = "")

    @Valid
    public User getAccountholder() {
        return accountholder;
    }

    public void setAccountholder(User accountholder) {
        this.accountholder = accountholder;
    }

    public Account balance(Double balance) {
        this.balance = balance;
        return this;
    }

    /**
     * Get balance
     *
     * @return balance
     **/
    @Schema(example = "1400.34", description = "")

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Account absoluteLimit(BigDecimal absoluteLimit) {
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

    /**
     * Get accounttype
     *
     * @return accounttype
     **/
    @Schema(example = "current", description = "")

    public AccounttypeEnum getAccounttype() {
        return accounttype;
    }

    public void setAccounttype(AccounttypeEnum accounttype) {
        this.accounttype = accounttype;
    }

    public Account creationdate(OffsetDateTime creationdate) {
        this.creationdate = creationdate;
        return this;
    }

    /**
     * Get creationdate
     *
     * @return creationdate
     **/
    @Schema(description = "")

    @Valid
    public OffsetDateTime getCreationdate() {
        return creationdate;
    }

    public void setCreationdate(OffsetDateTime creationdate) {
        this.creationdate = creationdate;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Account account = (Account) o;
        return Objects.equals(this.iban, account.iban) &&
                Objects.equals(this.status, account.status) &&
                Objects.equals(this.accountholder, account.accountholder) &&
                Objects.equals(this.balance, account.balance) &&
                Objects.equals(this.absoluteLimit, account.absoluteLimit) &&
                Objects.equals(this.accounttype, account.accounttype) &&
                Objects.equals(this.creationdate, account.creationdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iban, status, accountholder, balance, absoluteLimit, accounttype, creationdate);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Account {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    iban: ").append(toIndentedString(iban)).append("\n");
        sb.append("    status: ").append(toIndentedString(status)).append("\n");
        sb.append("    accountholder: ").append(toIndentedString(accountholder)).append("\n");
        sb.append("    balance: ").append(toIndentedString(balance)).append("\n");
        sb.append("    absoluteLimit: ").append(toIndentedString(absoluteLimit)).append("\n");
        sb.append("    accounttype: ").append(toIndentedString(accounttype)).append("\n");
        sb.append("    creationdate: ").append(toIndentedString(creationdate)).append("\n");
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

    /**
     * Gets or Sets accounttype
     */
    public enum AccounttypeEnum {
        SAVINGS("savings"),

        CURRENT("current");

        private String value;

        AccounttypeEnum(String value) {
            this.value = value;
        }

        @JsonCreator
        public static AccounttypeEnum fromValue(String text) {
            for (AccounttypeEnum b : AccounttypeEnum.values()) {
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
