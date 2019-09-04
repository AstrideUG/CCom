package me.helight.ccom.concurrency.chain;

import java.util.Map;

public class EnvAdrr {

    private String value;

    public EnvAdrr() {
    }

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

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof EnvAdrr)) return false;
        final EnvAdrr other = (EnvAdrr) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$value = this.getValue();
        final Object other$value = other.getValue();
        if (this$value == null ? other$value != null : !this$value.equals(other$value)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof EnvAdrr;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $value = this.getValue();
        result = result * PRIME + ($value == null ? 43 : $value.hashCode());
        return result;
    }

    public String toString() {
        return "EnvAdrr(value=" + this.getValue() + ")";
    }
}
