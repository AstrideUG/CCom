package me.helight.ccom.collections;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Triplet<A,B,C> {

    private A a;
    private B b;
    private C c;

}
