package me.helight.ccom.collections;

public class Quartet<A,B,C,D> {

    private A a;
    private B b;
    private C c;
    private D d;

    public Quartet(A a, B b, C c, D d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    public A getA() {
        return this.a;
    }

    public B getB() {
        return this.b;
    }

    public C getC() {
        return this.c;
    }

    public D getD() {
        return this.d;
    }

    public void setA(A a) {
        this.a = a;
    }

    public void setB(B b) {
        this.b = b;
    }

    public void setC(C c) {
        this.c = c;
    }

    public void setD(D d) {
        this.d = d;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Quartet)) return false;
        final Quartet<?, ?, ?, ?> other = (Quartet<?, ?, ?, ?>) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$a = this.getA();
        final Object other$a = other.getA();
        if (this$a == null ? other$a != null : !this$a.equals(other$a)) return false;
        final Object this$b = this.getB();
        final Object other$b = other.getB();
        if (this$b == null ? other$b != null : !this$b.equals(other$b)) return false;
        final Object this$c = this.getC();
        final Object other$c = other.getC();
        if (this$c == null ? other$c != null : !this$c.equals(other$c)) return false;
        final Object this$d = this.getD();
        final Object other$d = other.getD();
        if (this$d == null ? other$d != null : !this$d.equals(other$d)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Quartet;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $a = this.getA();
        result = result * PRIME + ($a == null ? 43 : $a.hashCode());
        final Object $b = this.getB();
        result = result * PRIME + ($b == null ? 43 : $b.hashCode());
        final Object $c = this.getC();
        result = result * PRIME + ($c == null ? 43 : $c.hashCode());
        final Object $d = this.getD();
        result = result * PRIME + ($d == null ? 43 : $d.hashCode());
        return result;
    }

    public String toString() {
        return "Quartet(a=" + this.getA() + ", b=" + this.getB() + ", c=" + this.getC() + ", d=" + this.getD() + ")";
    }
}
