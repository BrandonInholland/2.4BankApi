package bank.api.bankapi.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.security.core.GrantedAuthority;

public enum Roles implements GrantedAuthority {
    CUSTOMER("customer"),

    EMPLOYEE("employee");

    private final String value;

    Roles(String value) {
        this.value = value;
    }

    @JsonCreator
    public static Roles fromValue(String text) {
        for (Roles b : Roles.values()) {
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

    @Override
    public String getAuthority() {
        return name();
    }
}
