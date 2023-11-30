package cn.weedien.helloservlet;

import cn.weedien.helloservlet.pojo.Circle;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "circleServlet", value = "/circle")
public class CircleServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Circle circle = new Circle(3.0);
        double area = circle.getArea();

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("Radius: " + circle.getRadius() + ", Area is: " + area);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String radiusString = request.getParameter("radius");
        double radius = Double.parseDouble(radiusString);

        Circle circle = new Circle(radius);
        double area = circle.getArea();

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("Radius: " + circle.getRadius() + ", Area is: " + area);
    }
}