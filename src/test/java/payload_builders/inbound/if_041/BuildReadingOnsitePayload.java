package payload_builders.inbound.if_041;

import utils.FileUtils;
import inbound.Payload;
import inbound.common_block.*;
import inbound.if_041.B064List;
import inbound.if_041.CustomBlock;
import org.json.JSONObject;
import payload_builders.DynamicDataGenerators;
import payload_builders.MessagesConstants;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BuildReadingOnsitePayload extends DynamicDataGenerators {
    public JSONObject runnerObj;

    public Payload getPayload(String mpan, String gspGroupId, String meterId) throws IOException {

        String runnerConfigFile = System.getProperty("user.dir") + File.separator + "src" +
                File.separator + "test" + File.separator + "resources" +
                File.separator + "runnerconfig.json";

        FileUtils fileUtils = new FileUtils();
        runnerObj = fileUtils.getJsonObject(runnerConfigFile, "test02");

        CommonBlock.S0 s0 = new CommonBlock.S0();
        List<String> eventCode = new ArrayList<>();
        eventCode.add(MessagesConstants.IF_041_READINGONSITE);

        s0.setInterfaceID(MessagesConstants.IF_041);
        s0.setSchemaVersion(runnerObj.getString("schemaversion"));
        s0.setEventCode(eventCode);

        CommonBlock.M0 m0 = new CommonBlock.M0();
        m0.setMpanCore(mpan);
        m0.setDistributorDipID(runnerObj.getString("distributorDipID"));
        m0.setGspGroupID(gspGroupId);

        CommonBlock.S1 s1 = new CommonBlock.S1();
        String correlationId = generateSenderCorrelationID();
        s1.setEnvironmentTag(runnerObj.getString("environmentTag"));
        s1.setSubText(null);
        s1.setSenderUniqueReference(generateSenderUniqueReference(MessagesConstants.IF_041, runnerObj.getString("senderDipId"), "REGS"));
        s1.setSenderTimestamp(generateSenderTimestamp());
        s1.setSenderDIPID(runnerObj.getString("senderDipId"));
        s1.setSenderRoleID("REGS");
        s1.setSenderCorrelationID(correlationId);
        s1.setDipConnectionProviderID(runnerObj.getString("dipConnectionProviderID"));

        CommonBlock.A0 a0 = new CommonBlock.A0();

        List<String> alwaysLi = new ArrayList<>();
        List<String> primaryRecipientsLi = new ArrayList<>();
        primaryRecipientsLi.add(runnerObj.getString("sdsDipId"));
        List<String> secondaryRecipientsLi = new ArrayList<>();

        a0.setAlways(alwaysLi);
        a0.setPrimaryRecipients(primaryRecipientsLi);
        a0.setSecondaryRecipients(secondaryRecipientsLi);

        CommonBlock.D0 d0 = new CommonBlock.D0();

        d0.setTransactionID(generateTransactionID(MessagesConstants.IF_041, runnerObj.getString("senderDipId"), "REGS"));
        d0.setTransactionTimestamp(generateTransactionTimestamp());
        d0.setPublicationID(MessagesConstants.IF_041);
        d0.setInitialCorrelationID(correlationId);
        d0.setReplayIndicator(false);
        d0.setServiceTicketURL(null);

        CommonBlock commonBlock = new CommonBlock();
        commonBlock.setSO(s0);
        commonBlock.setA0(a0);
        commonBlock.setD0(d0);
        commonBlock.setS1(s1);
        commonBlock.setM0(m0);

        B064List b064 = new B064List();
        b064.setMeterID(meterId);
        b064.setReadingMethod("A");
        b064.setSiteVisitCheckCode("01");
        b064.setCumulativeRegisterReading("1000");
        b064.setCumulativeRegisterReadingDateTime("24-07-2025");

        List<B064List> b064List = new ArrayList<>();
        b064List.add(b064);

        CustomBlock customBlock = new CustomBlock();
        customBlock.setB064list(b064List);

        Payload payload = new Payload();
        payload.setCommonBlock(commonBlock);
        payload.setCustomBlock(customBlock);
        return payload;
    }
}
