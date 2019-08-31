package me.helight.ccom.concurrency.chain;

import lombok.Data;

import java.util.Map;

@Data
public class EnvAdrr {

    private String value;

    public Object resolve(Map<String,Object> environment) {

        if (value.equalsIgnoreCase("env") || value.equalsIgnoreCase("environment")) {
            return environment;
        }

        return environment.getOrDefault(value, null);
    }

    public void set(Map<String,Object> environment, Object object) {
        if (environment.containsKey(value) && object == null) {
            environment.remove(value);
        } else if (object != null) {
            environment.put(value, object);
        }
    }

    public static EnvAdrr from(String k) {
        EnvAdrr envAdrr = new EnvAdrr();
        envAdrr.setValue(k);
        return envAdrr;
    }
}
