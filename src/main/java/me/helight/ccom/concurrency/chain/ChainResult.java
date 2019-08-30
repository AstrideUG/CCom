package me.helight.ccom.concurrency.chain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.helight.ccom.concurrency.Environment;

@Getter
@AllArgsConstructor
public class ChainResult {

    private long duration;
    private Environment environment;

}
