package cn.weedien.helloservlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServlet;

import java.io.IOException;
import java.io.PrintWriter;

public class LifeServlet extends HttpServlet {
    private int initVar = 0;
    private int serviceVar = 0;
    private int destroyVar = 0;
    private String name;

    public LifeServlet() {
        System.out.println("LifeServlet create");
    }

    public void init(ServletConfig config) {
        name = config.getServletName();
        initVar++;
        System.out.println(name + ">init(): Servlet initializing..." + initVar + "time(s)");
    }

    public void destroy() {
        destroyVar++;
        System.out.println(name + ">destroy(): Servlet destroy..." + destroyVar + "time(s)");
    }

    public void service(ServletRequest request, ServletResponse response)
            throws IOException {
        serviceVar++;
        System.out.println(name + ">service(): Servlet response" + serviceVar + "time(s)");

        String content1 = "Initialization count: " + initVar;
        String content2 = "Response count: " + serviceVar;
        String content3 = "Destruction count: " + destroyVar;

        response.setContentType("text/html;charset=utf-8");

        PrintWriter out = response.getWriter();
        out.print("<html><head><title>LifeServlet</title>");
        out.print("</head><body>");
        out.print("<h1>" + content1 + "</h1>");
        out.print("<h1>" + content2 + "</h1>");
        out.print("<h1>" + content3 + "</h1>");
        out.print("</body></html>");
        out.close();
    }
}
