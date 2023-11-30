package cn.weedien.visitcounter;

import cn.weedien.visitcounter.pojo.Counter;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class CounterServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        // 在Servlet初始化时读取计数器的初始值，并将其存放在ServletContext中
        ServletContext context = getServletContext();
        String path = context.getRealPath("/WEB-INF/classes/count.properties");

        try (FileInputStream input = new FileInputStream(path)) {
            Properties properties = new Properties();
            properties.load(input);

            int initialCount = Integer.parseInt(properties.getProperty("count", "0"));

            Counter counter = new Counter(initialCount);
            context.setAttribute("counter", counter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        // 每次请求增加计数器的值
        ServletContext context = getServletContext();
        Counter counter = (Counter) context.getAttribute("counter");
        counter.add();

        response.getWriter().println("Visit Times: " + counter.getCount());
    }

    @Override
    public void destroy() {
        // 在Servlet销毁时将计数器的值保存到文件
        ServletContext context = getServletContext();
        Counter counter = (Counter) context.getAttribute("counter");

        String path = context.getRealPath("/WEB-INF/classes/count.properties");

        try (FileOutputStream output = new FileOutputStream(path)) {
            Properties properties = new Properties();
            properties.setProperty("count", String.valueOf(counter.getCount()));
            properties.store(output, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
