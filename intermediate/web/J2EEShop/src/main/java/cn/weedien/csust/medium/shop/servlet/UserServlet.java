package cn.weedien.csust.medium.shop.servlet;

import cn.weedien.csust.medium.shop.domain.User;
import cn.weedien.csust.medium.shop.service.UserService;
import cn.weedien.csust.medium.shop.service.impl.UserServiceImpl;
import cn.weedien.csust.medium.shop.util.Md5Util;
import cn.weedien.csust.medium.shop.util.UUIDUtil;
import cn.weedien.csust.medium.shop.util.UserBeanUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

/**
 * 处理和用户信息相关的请求
 * <ol>
 *     <li>用户登录</li>
 *     <li>用户退出</li>
 *     <li>用户注册</li>
 *     <li>AJAX异步校验用户名是否可用</li>
 *     <li>AJAX异步校验验证码</li>
 *     <li>验证激活码</li>
 * </ol>
 *
 * @author weedien
 * @date 2023/12/10
 */
@WebServlet(name = "UserServlet", urlPatterns = "/userServlet")
public class UserServlet extends BaseServlet {

    private final UserService userService = new UserServiceImpl();

    /**
     * 用户登录处理
     */
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //  获取页面自动登陆选项框的值
        String autoLogin = request.getParameter("autoLogin");
        // 如果用户点击了自动登陆
        if ("1".equals(autoLogin)) {
            // 1.创建一个Cookie用于记住用户的登录名和密码
            Cookie autoLoginCookie = new Cookie("autoLoginCookie", username + "@" + password);
            // 2.设置Cookie的路径为当前路径（即用户登陆）
            autoLoginCookie.setPath(request.getContextPath());
            // 3.设置Cookie的生命周期为一周
            autoLoginCookie.setMaxAge(60 * 60 * 24 * 7);
            // 4.将Cookie发送到浏览器
            response.addCookie(autoLoginCookie);
        } else {
            // 如果用户没有点击自动登陆
            Cookie autoLoginCookie = new Cookie("autoLonginCookie", "");
            autoLoginCookie.setPath(request.getContextPath());
            autoLoginCookie.setMaxAge(0);
            response.addCookie(autoLoginCookie);
        }

        User user = userService.login(username, password);

        if (user != null) {
            request.getSession().setAttribute("loginUser", user);
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        } else {
            request.setAttribute("msg", "用户名或密码不匹配或未激活");
            response.sendRedirect(request.getContextPath() + "/jsp/login.jsp");
        }
    }

    /**
     * 用户退出
     */
    public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getSession().removeAttribute("loginUser");

        Cookie cookie = new Cookie("autoLoginCookie", "");
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        response.sendRedirect(request.getContextPath() + "/");
    }

    /**
     * 用户注册
     */
    public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();

        try {
            // populate 将前端发送来的数据填入user对象中
            UserBeanUtil.populate(user, request.getParameterMap());
        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }

        user.setUid(UUIDUtil.getUUID());
        user.setTelephone(null);
        user.setState(0);

        // 密码加密存储
        user.setPassword(Md5Util.md5(user.getPassword()));

        int status = 0;
        try {
            status = userService.register(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (status > 0) {
            // 注册成功后转到登录页面
            response.sendRedirect(request.getContextPath() + "/jsp/login.jsp");
        } else {
            // 出现问题则转到注册页面
            response.sendRedirect(request.getContextPath() + "/jsp/register.jsp");
        }

    }

    /**
     * AJAX异步校验用户名是否可用
     */
    public void checkUserName(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String username = request.getParameter("username");
        User user = null;

        try {
            user = userService.findByName(username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        boolean isExist = user != null;

        response.getWriter().write("{\"isExist\":" + isExist + "}");

    }

    /**
     * AJAX异步校验验证码
     */
    public void checkValidateCode(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String confirm = request.getParameter("validateCode");

        HttpSession session = request.getSession();
        String word = (String) session.getAttribute("key");
        boolean boole;
        boole = confirm.equals(word);
        response.getWriter().write("{\"boole\":" + boole + "}");

    }

}