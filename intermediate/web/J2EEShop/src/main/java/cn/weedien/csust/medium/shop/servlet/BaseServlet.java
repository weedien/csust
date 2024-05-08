package cn.weedien.csust.medium.shop.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * BaseServlet
 *
 * @author weedien
 * @date 2023/12/10
 */
@WebServlet(name = "BaseServlet")
public class BaseServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            // 1、获得请求的method的名称
            String methodName = req.getParameter("method");
            if (methodName == null) {
                methodName = "excute";
            }
            // 2、获得当前被访问的对象的字节码对象
            Class<? extends BaseServlet> clazz = this.getClass();
            // 3、获得当前字节码对象的中的指定方法
            Method method = clazz.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);

            // 4、执行相应功能方法
            String jsPath = (String) method.invoke(this, req, resp);
            if (jsPath != null) {
                req.getRequestDispatcher(jsPath).forward(req, resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
