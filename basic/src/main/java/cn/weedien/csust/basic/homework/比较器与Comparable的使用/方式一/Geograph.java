package cn.weedien.csust.basic.homework.比较器与Comparable的使用.方式一;

abstract class Geograph implements Shape, Comparable<Shape> {
    @Override
    public int compareTo(Shape o) {
        if (this.getPerim() < o.getPerim()) {
            return -1;
        } else if (this.getPerim() > o.getPerim()) {
            return 1;
        } else {
            return 0;
        }
    }
}