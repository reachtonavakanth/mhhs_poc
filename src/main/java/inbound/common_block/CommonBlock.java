package inbound.common_block;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CommonBlock {
    @JsonProperty("M0")
    private M0 m0;

    @JsonProperty("S0")
    private S0 s0;

    @JsonProperty("S1")
    private S1 s1;

    @JsonProperty("A0")
    private A0 a0;

    @JsonProperty("D0")
    private D0 d0;

    public M0 getM0() {
        return m0;
    }

    public void setM0(M0 m0) {
        this.m0 = m0;
    }

    public S0 getS0() {
        return s0;
    }

    public void setSO(S0 s0) {
        this.s0 = s0;
    }

    public S1 getS1() {
        return s1;
    }

    public void setS1(S1 s1) {
        this.s1 = s1;
    }

    public A0 getA0() {
        return a0;
    }

    public void setA0(A0 a0) {
        this.a0 = a0;
    }

    public D0 getD0() {
        return d0;
    }

    public void setD0(D0 d0) {
        this.d0 = d0;
    }


    public static class M0 {
        @JsonProperty("MPANCore")
        private String mpanCore;
        @JsonProperty("distributorDIPID")
        private String distributorDipID;
        @JsonProperty("gspGroupID")
        private String gspGroupID;

        public String getMpanCore() {
            return mpanCore;
        }

        public void setMpanCore(String mpanCore) {
            this.mpanCore = mpanCore;
        }

        public String getDistributorDipID() {
            return distributorDipID;
        }

        public void setDistributorDipID(String distributorDipID) {
            this.distributorDipID = distributorDipID;
        }

        public String getGspGroupID() {
            return gspGroupID;
        }

        public void setGspGroupID(String gspGroupID) {
            this.gspGroupID = gspGroupID;
        }
    }

    public static class S0 {

        @JsonProperty("interfaceID")
        private String interfaceID;

        @JsonProperty("schemaVersion")
        private String schemaVersion;

        @JsonProperty("eventCode")
        private List<String> eventCode;

        public String getInterfaceID() {
            return interfaceID;
        }

        public void setInterfaceID(String interfaceID) {
            this.interfaceID = interfaceID;
        }

        public String getSchemaVersion() {
            return schemaVersion;
        }

        public void setSchemaVersion(String schemaVersion) {
            this.schemaVersion = schemaVersion;
        }

        public List<String> getEventCode() {
            return eventCode;
        }

        public void setEventCode(List<String> eventCode) {
            this.eventCode = eventCode;
        }

    }

    public static class S1 {
        @JsonProperty("environmentTag")
        private String environmentTag;
        @JsonProperty("subText")
        private String subText;
        @JsonProperty("senderUniqueReference")
        private String senderUniqueReference;
        @JsonProperty("senderTimestamp")
        private String senderTimestamp;
        @JsonProperty("senderDIPID")
        private String senderDIPID;
        @JsonProperty("senderRoleID")
        private String senderRoleID;
        @JsonProperty("senderCorrelationID")
        private String senderCorrelationID;
        @JsonProperty("DIPConnectionProviderID")
        private String dipConnectionProviderID;

        public String getEnvironmentTag() {
            return environmentTag;
        }

        public void setEnvironmentTag(String environmentTag) {
            this.environmentTag = environmentTag;
        }

        public String getSubText() {
            return subText;
        }

        public void setSubText(String subText) {
            this.subText = subText;
        }

        public String getSenderUniqueReference() {
            return senderUniqueReference;
        }

        public void setSenderUniqueReference(String senderUniqueReference) {
            this.senderUniqueReference = senderUniqueReference;
        }

        public String getSenderTimestamp() {
            return senderTimestamp;
        }

        public void setSenderTimestamp(String senderTimestamp) {
            this.senderTimestamp = senderTimestamp;
        }

        public String getSenderDIPID() {
            return senderDIPID;
        }

        public void setSenderDIPID(String senderDIPID) {
            this.senderDIPID = senderDIPID;
        }

        public String getSenderRoleID() {
            return senderRoleID;
        }

        public void setSenderRoleID(String senderRoleID) {
            this.senderRoleID = senderRoleID;
        }

        public String getSenderCorrelationID() {
            return senderCorrelationID;
        }

        public void setSenderCorrelationID(String senderCorrelationID) {
            this.senderCorrelationID = senderCorrelationID;
        }

        public String getDipConnectionProviderID() {
            return dipConnectionProviderID;
        }

        public void setDipConnectionProviderID(String dipConnectionProviderID) {
            this.dipConnectionProviderID = dipConnectionProviderID;
        }

    }
    public static class D0 {
        @JsonProperty("transactionID")
        private String transactionID;
        @JsonProperty("transactionTimestamp")
        private String transactionTimestamp;
        @JsonProperty("publicationID")
        private String publicationID;
        @JsonProperty("initialCorrelationID")
        private String initialCorrelationID;
        @JsonProperty("replayIndicator")
        private boolean replayIndicator;
        @JsonProperty("serviceTicketURL")
        private String serviceTicketURL;

        public String getTransactionID() {
            return transactionID;
        }

        public void setTransactionID(String transactionID) {
            this.transactionID = transactionID;
        }

        public String getTransactionTimestamp() {
            return transactionTimestamp;
        }

        public void setTransactionTimestamp(String transactionTimestamp) {
            this.transactionTimestamp = transactionTimestamp;
        }

        public String getPublicationID() {
            return publicationID;
        }

        public void setPublicationID(String publicationID) {
            this.publicationID = publicationID;
        }

        public String getInitialCorrelationID() {
            return initialCorrelationID;
        }

        public void setInitialCorrelationID(String initialCorrelationID) {
            this.initialCorrelationID = initialCorrelationID;
        }

        public boolean isReplayIndicator() {
            return replayIndicator;
        }

        public void setReplayIndicator(boolean replayIndicator) {
            this.replayIndicator = replayIndicator;
        }

        public String getServiceTicketURL() {
            return serviceTicketURL;
        }

        public void setServiceTicketURL(String serviceTicketURL) {
            this.serviceTicketURL = serviceTicketURL;
        }

    }

    public static class A0 {
        @JsonProperty("primaryRecipients")
        private List<String> primaryRecipients;
        @JsonProperty("secondaryRecipients")
        private List<String> secondaryRecipients;
        @JsonProperty("always")
        private List<String> always;

        public List<String> getPrimaryRecipients() {
            return primaryRecipients;
        }

        public void setPrimaryRecipients(List<String> primaryRecipients) {
            this.primaryRecipients = primaryRecipients;
        }

        public List<String> getSecondaryRecipients() {
            return secondaryRecipients;
        }

        public void setSecondaryRecipients(List<String> secondaryRecipients) {
            this.secondaryRecipients = secondaryRecipients;
        }

        public List<String> getAlways() {
            return always;
        }

        public void setAlways(List<String> always) {
            this.always = always;
        }

    }

}
