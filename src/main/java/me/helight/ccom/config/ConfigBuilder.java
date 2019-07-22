package me.helight.ccom.config;

public class ConfigBuilder<K> {

    private Class<K> clazz;
    private K defaultValue;

    private String subfolder;
    private String filename;

    public ConfigBuilder setClass(Class<K> clazz) {
        this.clazz = clazz;
        return this;
    }

    public ConfigBuilder setSubfolder(String folder) {
        this.subfolder = folder;
        return this;
    }

    public ConfigBuilder setFilename(String name) {
        this.filename = name;
        return this;
    }

    public ConfigBuilder setDefault(K defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    public Config<K> build() {
        if (subfolder == null) {
            Config config = Config.inCurrent(filename,clazz);
            config.setDefault(defaultValue);
            return config;
        } else {
            Config config = Config.inSubfolder(subfolder,filename,clazz);
            config.setDefault(defaultValue);
            return config;
        }
    }
}
