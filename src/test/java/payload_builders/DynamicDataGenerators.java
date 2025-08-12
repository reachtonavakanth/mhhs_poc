package payload_builders;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class DynamicDataGenerators {

    private final DateTimeFormatter TIMESTAMP_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");
    private final DateTimeFormatter ID_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");

    public String generateSenderUniqueReference(String messageName, String senderDipId, String senderRoleID) {
        String datePart = ZonedDateTime.now().format(ID_DATE_FORMATTER);
        String randomSuffix = generateRandomHex(5);
        return String.format("S-%s-%s-%s-%s-%s", messageName, senderDipId, senderRoleID, datePart, randomSuffix);
    }

    public String generateSenderTimestamp() {
        return ZonedDateTime.now().format(TIMESTAMP_FORMATTER);
    }

    public String generateSenderCorrelationID() {
        String datePart = ZonedDateTime.now().format(ID_DATE_FORMATTER);
        return String.format("CI-%s-%s", datePart, generateUUIDSuffix());
    }

    public String generateTransactionID(String messageName, String senderDipId, String roleID) {
        String datePart = ZonedDateTime.now().format(ID_DATE_FORMATTER);
        return String.format("T-%s-%s-%s-%s-%s", messageName, senderDipId, roleID, datePart, generateUUIDSuffix());
    }

    public String generateTransactionTimestamp() {
        return generateSenderTimestamp();
    }

    public String generateInitialCorrelationID() {
        return generateSenderCorrelationID();
    }

    private String generateUUIDSuffix() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 16).toUpperCase();
    }

    private String generateRandomHex(int length) {
        return UUID.randomUUID().toString().replace("-", "").substring(0, length).toUpperCase();
    }
}
