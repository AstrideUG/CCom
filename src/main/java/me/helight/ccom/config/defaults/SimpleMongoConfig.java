package me.helight.ccom.config.defaults;

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

    public String getHost() {
        return this.host;
    }

    public Integer getPort() {
        return this.port;
    }

    public String getDatabase() {
        return this.database;
    }

    public String getUser() {
        return this.user;
    }

    public String getPassword() {
        return this.password;
    }

    public Boolean getLocal() {
        return this.local;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLocal(Boolean local) {
        this.local = local;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof SimpleMongoConfig)) return false;
        final SimpleMongoConfig other = (SimpleMongoConfig) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$host = this.getHost();
        final Object other$host = other.getHost();
        if (this$host == null ? other$host != null : !this$host.equals(other$host)) return false;
        final Object this$port = this.getPort();
        final Object other$port = other.getPort();
        if (this$port == null ? other$port != null : !this$port.equals(other$port)) return false;
        final Object this$database = this.getDatabase();
        final Object other$database = other.getDatabase();
        if (this$database == null ? other$database != null : !this$database.equals(other$database)) return false;
        final Object this$user = this.getUser();
        final Object other$user = other.getUser();
        if (this$user == null ? other$user != null : !this$user.equals(other$user)) return false;
        final Object this$password = this.getPassword();
        final Object other$password = other.getPassword();
        if (this$password == null ? other$password != null : !this$password.equals(other$password)) return false;
        final Object this$local = this.getLocal();
        final Object other$local = other.getLocal();
        if (this$local == null ? other$local != null : !this$local.equals(other$local)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof SimpleMongoConfig;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $host = this.getHost();
        result = result * PRIME + ($host == null ? 43 : $host.hashCode());
        final Object $port = this.getPort();
        result = result * PRIME + ($port == null ? 43 : $port.hashCode());
        final Object $database = this.getDatabase();
        result = result * PRIME + ($database == null ? 43 : $database.hashCode());
        final Object $user = this.getUser();
        result = result * PRIME + ($user == null ? 43 : $user.hashCode());
        final Object $password = this.getPassword();
        result = result * PRIME + ($password == null ? 43 : $password.hashCode());
        final Object $local = this.getLocal();
        result = result * PRIME + ($local == null ? 43 : $local.hashCode());
        return result;
    }

    public String toString() {
        return "SimpleMongoConfig(host=" + this.getHost() + ", port=" + this.getPort() + ", database=" + this.getDatabase() + ", user=" + this.getUser() + ", password=" + this.getPassword() + ", local=" + this.getLocal() + ")";
    }
}
