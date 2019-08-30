package me.helight.ccom.collections;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Quintet<A,B,C,D,E> {

    private A a;
    private B b;
    private C c;
    private D d;
    private E e;

}
