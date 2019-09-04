package me.helight.ccom.config.defaults;

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

    public String getHost() {
        return this.host;
    }

    public Integer getPort() {
        return this.port;
    }

    public String getPassword() {
        return this.password;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof SimpleRedisConfig)) return false;
        final SimpleRedisConfig other = (SimpleRedisConfig) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$host = this.getHost();
        final Object other$host = other.getHost();
        if (this$host == null ? other$host != null : !this$host.equals(other$host)) return false;
        final Object this$port = this.getPort();
        final Object other$port = other.getPort();
        if (this$port == null ? other$port != null : !this$port.equals(other$port)) return false;
        final Object this$password = this.getPassword();
        final Object other$password = other.getPassword();
        if (this$password == null ? other$password != null : !this$password.equals(other$password)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof SimpleRedisConfig;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $host = this.getHost();
        result = result * PRIME + ($host == null ? 43 : $host.hashCode());
        final Object $port = this.getPort();
        result = result * PRIME + ($port == null ? 43 : $port.hashCode());
        final Object $password = this.getPassword();
        result = result * PRIME + ($password == null ? 43 : $password.hashCode());
        return result;
    }

    public String toString() {
        return "SimpleRedisConfig(host=" + this.getHost() + ", port=" + this.getPort() + ", password=" + this.getPassword() + ")";
    }
}
