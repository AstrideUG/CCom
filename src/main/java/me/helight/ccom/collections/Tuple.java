package me.helight.ccom.collections;

import java.util.List;

public interface Tuple {

    static <A,B> Pair<A,B> pair(List list, Class<A> aClass, Class<B> bClass) {
        return new Pair<A,B>(aClass.cast(list.get(0)), bClass.cast(list.get(1)));
    }

    static <A,B,C> Triplet<A,B,C> triplet(List list, Class<A> aClass, Class<B> bClass, Class<C> cClass) {
        return new Triplet<>(aClass.cast(list.get(0)), bClass.cast(list.get(1)), cClass.cast(list.get(2)));
    }

    static <A,B,C,D> Quartet<A,B,C,D> quartet(List list, Class<A> aClass, Class<B> bClass, Class<C> cClass, Class<D> dClass) {
        return new Quartet<>(aClass.cast(list.get(0)), bClass.cast(list.get(1)), cClass.cast(list.get(2)), dClass.cast(list.get(3)));
    }

    static <A,B,C,D,E> Quintet<A,B,C,D,E> quintet(List list, Class<A> aClass, Class<B> bClass, Class<C> cClass, Class<D> dClass, Class<E> eClass) {
        return new Quintet<>(aClass.cast(list.get(0)), bClass.cast(list.get(1)), cClass.cast(list.get(2)), dClass.cast(list.get(3)), eClass.cast(list.get(4)));
    }

}
