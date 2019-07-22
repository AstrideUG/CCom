package me.helight.ccom.encode;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import org.bouncycastle.util.encoders.Hex;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.function.Function;

@AllArgsConstructor
public enum Encodings {

    HEX(Hex::toHexString, Hex::decode),
    BASE64(x -> {
       return Base64.getEncoder().encodeToString(x);
    }, x -> {
        return Base64.getDecoder().decode(x);
    });

    private Function<byte[], String> encoder;
    private Function<String, byte[]> decoder;

    public String encode(byte[] bytes) {
        return encoder.apply(bytes);
    }

    public byte[] decode(String string) {
        return decoder.apply(string);
    }

    /**
     * Encodes a UTF-8 String
     */
    public String encodeUTF8(String string) {
        return encode(string.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Decodes to a UTF-8 String
     */
    public String decodeUTF8(String string) {
        return new String(decode(string), StandardCharsets.UTF_8);
    }

    /**
     * Encode a Pojo using {@link Gson}
     */
    public <K> String encodePojo(K pojo) {
        Gson gson = new Gson();
        return encodeUTF8(gson.toJson(pojo, pojo.getClass()));
    }

    /**
     * Decode a Pojo using {@link Gson}
     */
    public <K> K decodePojo(String string, Class<K> clazz) {
        Gson gson = new Gson();
        return gson.fromJson(decodeUTF8(string), clazz);
    }

    public String encodeSerializable(Serializable serializable) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(serializable);
        objectOutputStream.close();
        byte[] bytes = byteArrayOutputStream.toByteArray();
        return encode(bytes);
    }

    public Serializable decodeSerializable(String string) throws IOException, ClassNotFoundException {
        byte[] data = decode(string);
        ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(data));
        Object o = objectInputStream.readObject();
        objectInputStream.close();
        return (Serializable) o;
    }

}
