package io.github.ctlove0523.pattern.pipeline.core.deploy;

import lombok.Setter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chentong
 */
public class BaseDeployData {
    private Map<String,String> data = new HashMap<>();

    public void setProperty(String key,String val) {
        data.putIfAbsent(key,val);
    }

    public String getProperty(String key) {
        return data.get(key);
    }

    public Map<String, String> getData() {
        return Collections.unmodifiableMap(data);
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }

    public void addData(Map<String,String> addedData) {
        this.data.putAll(addedData);
    }
}
