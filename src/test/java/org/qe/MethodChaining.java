package org.qe;

public class MethodChaining {
    public static void main(String[] args) {
        a1().a2().a3();
    }

    private MethodChaining a2() {
        System.out.println("inside method a2");
        return this;
    }
    private MethodChaining a3() {
        System.out.println("inside method a3");
        return this;
    }

    private static MethodChaining a1() {
        System.out.println("inside method a1");
        return new MethodChaining();
    }
}
