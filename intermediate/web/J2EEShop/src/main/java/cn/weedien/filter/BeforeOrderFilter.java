package cn.weedien.filter;

import cn.weedien.domain.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author weedien
 * @date 2023/12/10
 */
@WebFilter(filterName = "BeforeOrderFilter", urlPatterns = "/orderServlet")
public class BeforeOrderFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        User loginUser = (User) request.getSession().getAttribute("loginUser");
        if (loginUser == null) {
            request.getSession().setAttribute("msg", "请登陆后继续购买商品");
            response.sendRedirect(request.getContextPath() + "/jsp/login.jsp");
            return;
        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
