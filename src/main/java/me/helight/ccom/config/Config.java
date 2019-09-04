package me.helight.ccom.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Cleanup;
import lombok.NonNull;
import lombok.SneakyThrows;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class Config<T> {

    @NonNull
    private File location;

    @NonNull
    private Class<T> clazz;

    private T defaultValue;

    public Config(File location, Class<T> clazz) {
        this.location = location;
        this.clazz = clazz;
    }

    /**
     * Sets the Config-Default
     */
    public void setDefault(T value) {
        defaultValue = value;
    }

    /**
     * Reads or creates the file, if nonexistent
     *
     * @return The current value of the Config
     */
    @SneakyThrows
    public T read() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        if (!location.exists()) {
            @Cleanup FileWriter fileWriter = new FileWriter(location);
            gson.toJson(defaultValue, fileWriter);
            return defaultValue;
        } else {
            @Cleanup FileReader fileReader = new FileReader(location);
            return gson.fromJson(fileReader, clazz);
        }
    }

    /**
     * Constructs a ConfigObject for a File in a subfolder
     *
     * @param kClass The class of the ConfigPojo
     */
    public static <K> Config<K> inSubfolder(String subfolder, String filename, Class<K> kClass) {
        File sub = new File(subfolder);

        if (!sub.exists()) sub.mkdirs();

        return new Config<K>(new File(sub, filename), kClass);
    }

    /**
     * Constructs a ConfigObject for a File in the current folder
     *
     * @param kClass The class of the ConfigPojo
     */
    public static <K> Config<K> inCurrent(String filename, Class<K> kClass) {
        return new Config<K>(new File(filename), kClass);
    }

    @NonNull
    public File getLocation() {
        return this.location;
    }

    @NonNull
    public Class<T> getClazz() {
        return this.clazz;
    }

    public T getDefaultValue() {
        return this.defaultValue;
    }
}
