package cn.weedien.csust.basic.homework.图形2;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        GeoGraph[] GeoArray = new GeoGraph[6];
        GeoArray[0] = new Circle(3, 0, true);
        GeoArray[1] = new SemiCircle(2, 0, false);
        GeoArray[2] = new Rectangle(5, 2, 0, true);
        GeoArray[3] = new Triangle(3, 4, 5, 0, false);
        GeoArray[4] = new Oval(4, 3, 0, true);
        GeoArray[5] = new Trapezoid(4, 6, 3, 2, 4, 0, false);

        Arrays.sort(GeoArray);

        for (GeoGraph graph : GeoArray) {
            System.out.println(graph.toString());
        }
    }
}
