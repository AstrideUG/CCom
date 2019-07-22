package me.helight.ccom.config.defaults;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class SimpleMongoConfig {

    public static final SimpleMongoConfig DEFAULT = new SimpleMongoConfig("localhost",27017,"test","admin","password",true);

    public SimpleMongoConfig(String host, Integer port, String database, String user, String password, Boolean local) {
        this.host = host;
        this.port = port;
        this.database = database;
        this.user = user;
        this.password = password;
        this.local = local;
    }

    public SimpleMongoConfig() { }

    public String host;

    public Integer port;

    public String database;

    public String user;

    public String password;

    public Boolean local;

}
