package me.helight.ccom.collections;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Quartet<A,B,C,D> {

    private A a;
    private B b;
    private C c;
    private D d;

}
