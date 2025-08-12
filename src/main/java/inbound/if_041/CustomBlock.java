package inbound.if_041;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CustomBlock {

    @JsonProperty("B064List")
    private List<B064List> b064list;

    public List<B064List> getB064list() {
        return b064list;
    }

    public void setB064list(List<B064List> b064list) {
        this.b064list = b064list;
    }

}
