package me.helight.ccom.collections;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Pair<A,B> implements Tuple{

    private A a;
    private B b;

}
