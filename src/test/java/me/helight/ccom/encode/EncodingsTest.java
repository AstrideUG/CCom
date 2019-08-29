package me.helight.ccom.encode;

import org.junit.jupiter.api.Test;

import java.nio.charset.Charset;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class EncodingsTest {

    @Test
    void encodeHex() {
        byte[] actual = "Debug123".getBytes(Charset.defaultCharset());
        assertArrayEquals(actual, Encodings.HEX.decode(Encodings.HEX.encode(actual)));
    }

    @Test
    void encodeBase64() {
        byte[] actual = "Debug123".getBytes(Charset.defaultCharset());
        assertArrayEquals(actual, Encodings.BASE64.decode(Encodings.BASE64.encode(actual)));
    }

    @Test
    void encodeUTF8() {
        String actual = "Debug123";
        assertEquals(actual, Encodings.BASE64.decodeUTF8(Encodings.BASE64.encodeUTF8(actual)));
    }
}