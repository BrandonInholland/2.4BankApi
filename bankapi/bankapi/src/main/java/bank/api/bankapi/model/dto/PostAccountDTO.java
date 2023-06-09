package bank.api.bankapi.model.dto;

import bank.api.bankapi.model.Account;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * PostAccountDTO
 */
@Validated

public class PostAccountDTO {
    @JsonProperty("userid")
    private Long userid = null;

    /**
     * Gets or Sets accounttype
     */

    @JsonProperty("accounttype")
    private Account.AccounttypeEnum accounttype = null;

    public PostAccountDTO userid(Long userid) {
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

    public PostAccountDTO accounttype(Account.AccounttypeEnum accounttype) {
        this.accounttype = accounttype;
        return this;
    }

    /**
     * Get accounttype
     *
     * @return accounttype
     **/
    @Schema(example = "current", description = "")

    public Account.AccounttypeEnum getAccounttype() {
        return accounttype;
    }

    public void setAccounttype(Account.AccounttypeEnum accounttype) {
        this.accounttype = accounttype;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PostAccountDTO postAccountDTO = (PostAccountDTO) o;
        return Objects.equals(this.userid, postAccountDTO.userid) &&
                Objects.equals(this.accounttype, postAccountDTO.accounttype);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userid, accounttype);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class PostAccountDTO {\n");

        sb.append("    userid: ").append(toIndentedString(userid)).append("\n");
        sb.append("    accounttype: ").append(toIndentedString(accounttype)).append("\n");
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
