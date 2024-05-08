package cn.weedien.csust.basic.homework.图形2;

abstract class GeoGraph implements Comparable<GeoGraph>, Colorable {
    protected int color; // 颜色
    protected boolean filled; // 填充标志

    public GeoGraph(int color, boolean filled) {
        this.color = color;
        this.filled = filled;
    }

    // 获取面积
    public abstract double getArea();

    // 获取周长
    public abstract double getPerimeter();

    // 其他必要行为特性
    public void draw() {
        System.out.println("Drawing...");
    }

    // 实现Comparable接口，根据面积大小进行排序
    public int compareTo(GeoGraph other) {
        return Double.compare(this.getArea(), other.getArea());
    }

    // 实现Colorable接口，对图形进行上色
    public abstract void procColor();
}
