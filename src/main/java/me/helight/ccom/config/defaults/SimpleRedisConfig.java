package me.helight.ccom.config.defaults;

import jdk.internal.dynalink.support.Guards;
import lombok.Data;

@Data
public class SimpleRedisConfig {

    public static final SimpleRedisConfig DEFAULT = new SimpleRedisConfig("localhost",6379,"bbcce7140946fe100cc5b1b2057bf8fe1474954541af3d483aaff2c7bb843168");

    public SimpleRedisConfig(String host, Integer port, String password) {
        this.host = host;
        this.port = port;
        this.password = password;
    }

    public SimpleRedisConfig() { }

    private String host;

    private Integer port;

    private String password;

}
