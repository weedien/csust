package cn.weedien.csust.basic.homework.图形;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class GeoArrayList {
    private final ArrayList<Shape> shapes;

    public GeoArrayList(ArrayList<Shape> shapes) {
        this.shapes = shapes;
    }

    public GeoArrayList() {
        this(new ArrayList<Shape>());
    }

    public void insertGeoShape(Shape shape) {
        shapes.add(shape);
        shapes.sort(Comparator.comparingDouble(Shape::getArea));
    }

    public void reMove(Shape shape) {
        shapes.remove(shape);
    }

    public int getIndex(Shape shape) {
        return shapes.indexOf(shape);
    }

    public Shape get(int index) {
        return shapes.get(index);
    }

    public int size() {
        return shapes.size();
    }

    public void clear() {
        shapes.clear();
    }

    public boolean contains(Shape shape) {
        return shapes.contains(shape);
    }

    public void displayGeoList() {
        Iterator<Shape> iterator = shapes.iterator();
        while (iterator.hasNext()) {
            Shape shape = iterator.next();
            System.out.println("Shape: " + shape.getClass().getSimpleName());
            System.out.println("Area: " + shape.getArea());
        }
    }
}
