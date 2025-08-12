package inbound;

import com.fasterxml.jackson.annotation.JsonProperty;
import inbound.common_block.CommonBlock;
import inbound.if_041.CustomBlock;

public class Payload {

    @JsonProperty("CommonBlock")
    private CommonBlock commonBlock;
    @JsonProperty("CustomBlock")
    private CustomBlock customBlock;

    public CommonBlock getCommonBlock() {
        return commonBlock;
    }

    public void setCommonBlock(CommonBlock commonBlock) {
        this.commonBlock = commonBlock;
    }

    public CustomBlock getCustomBlock() {
        return customBlock;
    }

    public void setCustomBlock(CustomBlock customBlock) {
        this.customBlock = customBlock;
    }

}
