package bank.api.bankapi.configuration;

public class IbanGenerator {
    String countryCode;
    String securityCode;
    String institution;
    Integer length;
    Integer counter;             //NL             01                 INHOO               1              10

    public IbanGenerator(String countryCode, String securityCode, String institution, int startingNum, int length) {
        this.countryCode = countryCode;
        this.securityCode = securityCode;
        this.institution = institution;
        this.counter = startingNum;
        this.length = length;
    }

    public String generateIban() {
        return countryCode + securityCode + institution + getEnd();
    }

    public String getEnd() {
        StringBuilder sb = new StringBuilder(counter.toString());
        counter++;
        while (sb.length() < length) {
            sb.insert(0, "0");
        }
        return sb.toString();
    }
}

