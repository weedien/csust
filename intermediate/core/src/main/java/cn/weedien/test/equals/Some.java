package cn.weedien.test.equals;

import java.util.HashSet;

public class Some extends Object {
    public int hashCode() {
        return 1;
    }

    // public boolean equals(Object obj) {
    // return true;
    // }

    public static void main(String[] args) {
        HashSet<Some> somes = new HashSet<>();
        Some a = new Some();
        Some b = new Some();
        System.out.println(a);
        System.out.println(a.getClass().getName() + "@" + Integer.toHexString(a.hashCode()));
        System.out.println(b);
        System.out.println(b.getClass().getName() + "@" + Integer.toHexString(b.hashCode()));
        System.out.println(a.equals(b));
        somes.add(a);
        somes.add(b);
        System.out.println(somes.size());
    }
}
