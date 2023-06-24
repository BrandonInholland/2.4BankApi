package bank.api.bankapi.model;

import bank.api.bankapi.model.enums.Roles;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;



@Entity
public class User {

    @JsonProperty("userid")
    @Id
    @GeneratedValue
    private Long userid = null;
    @JsonProperty("username")
    private String username = null;
    private String password = null;
    @JsonProperty("firstName")
    private String firstName = null;
    @JsonProperty("lastName")
    private String lastName = null;
    @JsonProperty("email")
    private String email = null;
    /**
     * Gets or Sets roles
     */

    @JsonProperty("roles")
    @Valid
    @ElementCollection(fetch = FetchType.EAGER)
    private List<Roles> roles = null;
    @JsonProperty("dayLimit")
    private BigDecimal dayLimit = null;
    @JsonProperty("transactionLimit")
    private BigDecimal transactionLimit = null;
    @JsonProperty("totalBalance")
    private Double totalBalance = null;
    @JsonProperty("isActive")
    private Boolean isActive = null;

    public User() {
        this.setTransactionLimit(BigDecimal.valueOf(1500));
        this.setDayLimit(BigDecimal.valueOf(5000));
        this.isActive(true);
        this.totalBalance(0.00);
    }

    public User userid(Long userid) {
        this.userid = userid;
        return this;
    }

    /**
     * Get userid
     *
     * @return userid
     **/
    @Schema(example = "212", description = "")

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public User username(String username) {
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

    public User password(String password) {
        this.password = password;
        return this;
    }

    /**
     * Get password
     *
     * @return password
     **/
    @Schema(example = "SecretPass123!", required = true, description = "")
    @JsonIgnore
    public String getPassword() {
        return password;
    }
    @JsonProperty("password")
    public void setPassword(String password) {
        this.password = password;
    }

    public User firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    /**
     * Get firstName
     *
     * @return firstName
     **/
    @Schema(example = "Firstname", required = true, description = "")
    @NotNull

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public User lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    /**
     * Get lastName
     *
     * @return lastName
     **/
    @Schema(example = "Lastname", required = true, description = "")
    @NotNull

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public User email(String email) {
        this.email = email;
        return this;
    }

    /**
     * Get email
     *
     * @return email
     **/
    @Schema(example = "user@example.com", required = true, description = "")
    @NotNull

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User roles(List<Roles> roles) {
        this.roles = roles;
        return this;
    }

    public User addRolesItem(Roles rolesItem) {
        if (this.roles == null) {
            this.roles = new ArrayList<Roles>();
        }
        this.roles.add(rolesItem);
        return this;
    }

    /**
     * Get roles
     *
     * @return roles
     **/
    @Schema(example = "[\"customer\"]", description = "")

    public List<Roles> getRoles() {
        return roles;
    }

    public void setRoles(List<Roles> roles) {
        this.roles = roles;
    }

    public User dayLimit(BigDecimal dayLimit) {
        this.dayLimit = dayLimit;
        return this;
    }

    /**
     * Get dayLimit
     *
     * @return dayLimit
     **/
    @Schema(example = "420", description = "")

    @Valid
    public BigDecimal getDayLimit() {
        return dayLimit;
    }

    public void setDayLimit(BigDecimal dayLimit) {
        this.dayLimit = dayLimit;
    }

    public User transactionLimit(BigDecimal transactionLimit) {
        this.transactionLimit = transactionLimit;
        return this;
    }

    /**
     * Get transactionLimit
     *
     * @return transactionLimit
     **/
    @Schema(example = "300", description = "")

    @Valid
    public BigDecimal getTransactionLimit() {
        return transactionLimit;
    }

    public void setTransactionLimit(BigDecimal transactionLimit) {
        this.transactionLimit = transactionLimit;
    }

    public User totalBalance(Double totalBalance) {
        this.totalBalance = totalBalance;
        return this;
    }

    /**
     * Get totalBalance
     *
     * @return totalBalance
     **/
    @Schema(example = "1000.12", description = "")

    public Double getTotalBalance() {
        return totalBalance;
    }

    public void setTotalBalance(Double totalBalance) {
        this.totalBalance = totalBalance;
    }

    public User isActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    /**
     * Get isActive
     *
     * @return isActive
     **/
    @Schema(example = "true", description = "")

    public Boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(this.userid, user.userid) &&
                Objects.equals(this.username, user.username) &&
                Objects.equals(this.password, user.password) &&
                Objects.equals(this.firstName, user.firstName) &&
                Objects.equals(this.lastName, user.lastName) &&
                Objects.equals(this.email, user.email) &&
                Objects.equals(this.roles, user.roles) &&
                Objects.equals(this.dayLimit, user.dayLimit) &&
                Objects.equals(this.transactionLimit, user.transactionLimit) &&
                Objects.equals(this.totalBalance, user.totalBalance) &&
                Objects.equals(this.isActive, user.isActive);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userid, username, password, firstName, lastName, email, roles, dayLimit, transactionLimit, totalBalance, isActive);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class User {\n");

        sb.append("    userid: ").append(toIndentedString(userid)).append("\n");
        sb.append("    username: ").append(toIndentedString(username)).append("\n");
        sb.append("    password: ").append(toIndentedString(password)).append("\n");
        sb.append("    firstName: ").append(toIndentedString(firstName)).append("\n");
        sb.append("    lastName: ").append(toIndentedString(lastName)).append("\n");
        sb.append("    email: ").append(toIndentedString(email)).append("\n");
        sb.append("    roles: ").append(toIndentedString(roles)).append("\n");
        sb.append("    dayLimit: ").append(toIndentedString(dayLimit)).append("\n");
        sb.append("    transactionLimit: ").append(toIndentedString(transactionLimit)).append("\n");
        sb.append("    totalBalance: ").append(toIndentedString(totalBalance)).append("\n");
        sb.append("    isActive: ").append(toIndentedString(isActive)).append("\n");
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
