package me.helight.ccom.concurrency.chain;

import lombok.Data;
import me.helight.ccom.concurrency.Chain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

@Data
public class EnvAdrr {

    private Object value;
    private boolean named;

    public int toIndex() {
        return Integer.parseInt(value.toString());
    }

    public String toKey() {
        return value.toString();
    }

    public Object resolve(Chain chain) {
        if (named) {
            for (Map<String, Object> map : getEnvStack(chain)) {
                if (map.containsKey(toKey())) {
                    return map.get(toKey());
                }
                System.out.println(map.toString());
            }
            return null;
        }

        try {
            return chain.environment[toIndex()];
        } catch (Exception e) {
            return null;
        }
    }

    public static ArrayList<Map<String,Object>> getEnvStack(Chain chain) {
        ArrayList<Map<String,Object>> arrayList = new ArrayList<>();
        arrayList.add(chain.getNamedEnvironment());
        Chain current = chain.parent;
        while (current != null) {
            arrayList.add(current.getNamedEnvironment());
            current = current.parent;
        }
        return arrayList;
    }

    public static EnvAdrr from(int i) {
        EnvAdrr envAdrr = new EnvAdrr();
        envAdrr.setValue(i);
        envAdrr.setNamed(false);
        return envAdrr;
    }

    public static EnvAdrr from(String k) {
        EnvAdrr envAdrr = new EnvAdrr();
        envAdrr.setValue(k);
        envAdrr.setNamed(true);
        return envAdrr;
    }

    public static Map<String,Object> getHighestMap(Chain chain) {
        ArrayList<Map<String,Object>> arrayList = getEnvStack(chain);
        Collections.shuffle(arrayList);
        return arrayList.get(0);
    }
}
