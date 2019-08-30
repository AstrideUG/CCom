package me.helight.ccom.concurrency;

import me.helight.ccom.concurrency.chain.EnvAdrr;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Environment extends ConcurrentHashMap<String,Object> {

    public Environment() { }

    public Environment(Map<String,Object> map) {
        putAll(map);
    }

    public Environment env(String key, Object val) {
        put(key,val);
        return this;
    }

    public Environment env(EnvAdrr adrr, Object val) {
        put(adrr.getValue(),val);
        return this;
    }

    public Object resolve(EnvAdrr... addrs) {
        if (addrs.length == 1 ) {
            return addrs[0].resolve(this);
        } else {
            ArrayList<Object> arrayList = new ArrayList<>();
            for (EnvAdrr addr : addrs) {
                arrayList.add(addr.resolve(this));
            }
            return arrayList;
        }
    }

    public static Environment create() {
        return new Environment();
    }

}
