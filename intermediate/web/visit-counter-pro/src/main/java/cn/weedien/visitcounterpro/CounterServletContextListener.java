package cn.weedien.visitcounterpro;

import cn.weedien.visitcounterpro.pojo.Counter;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class CounterServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // 在Web应用启动时读取计数器的初始值，并将其存放在ServletContext中
        ServletContext context = sce.getServletContext();
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
    public void contextDestroyed(ServletContextEvent sce) {
        // 在Web应用销毁时将计数器的值保存到文件
        ServletContext context = sce.getServletContext();
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
