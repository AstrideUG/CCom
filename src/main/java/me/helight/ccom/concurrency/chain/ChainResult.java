package me.helight.ccom.concurrency.chain;

import lombok.Data;

import java.util.Map;

@Data
public class ChainResult {

    long duration;
    Map<String, Object> environment;

}
