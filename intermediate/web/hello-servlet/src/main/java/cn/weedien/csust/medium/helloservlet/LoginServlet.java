package cn.weedien.csust.medium.helloservlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author weedien
 * @date 2023/11/23
 */
public class LoginServlet extends HttpServlet {
    private ServletConfig config;

    @Override
    public void init() {
        this.config = getServletConfig();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if (config.getInitParameter(username) != null) {
            String expectedPassword = config.getInitParameter(username);
            if (password.equals(expectedPassword)) {
                out.println("<h1>Login Successful</h1>");
                out.println("<p>Welcome, " + username + "!</p>");
            } else {
                out.println("<h1>Login Failed</h1>");
                out.println("<p>Incorrect password for username: " + username + "</p>");
            }
        } else {
            out.println("<h1>Login Failed</h1>");
            out.println("<p>Invalid username: " + username + "</p>");
        }

        out.close();
    }
}
