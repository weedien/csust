package cn.homework.图形;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Circle[] circles = {
                new Circle(5),
                new Circle(2),
                new Circle(4),
                new Circle(3)
        };
        Rectangle[] rectangles = {
                new Rectangle(2, 3),
                new Rectangle(1, 5),
                new Rectangle(4, 2),
                new Rectangle(3, 4)
        };
        ArrayList<Shape> shapes = new ArrayList<Shape>();
        GeoArrayList geoArrayList = new GeoArrayList(shapes);

        for (Circle c : circles) {
            geoArrayList.insertGeoShape(c);
        }
        for (Rectangle r : rectangles) {
            geoArrayList.insertGeoShape(r);
        }

        geoArrayList.displayGeoList();
    }
}
