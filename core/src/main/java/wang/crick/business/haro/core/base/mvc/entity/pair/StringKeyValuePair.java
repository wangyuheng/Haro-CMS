package wang.crick.business.haro.core.base.mvc.entity.pair;

import java.io.Serializable;

public class StringKeyValuePair implements Serializable {

    private String key;
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
