package bank.api.bankapi.exception;

public class AddressException extends Exception {
    public AddressException(String msg) {
        super(msg);
    }

    public AddressException(String msg, Throwable t) {
        super(msg, t);
    }
}