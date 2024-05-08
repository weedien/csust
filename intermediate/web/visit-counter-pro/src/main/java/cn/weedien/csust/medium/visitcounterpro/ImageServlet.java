package cn.weedien.csust.medium.visitcounterpro;

import cn.weedien.csust.medium.visitcounterpro.pojo.Counter;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

@WebServlet(name = "imageServlet", value = "/image")
public class ImageServlet extends HttpServlet {
    private final Font font = new Font("Courier", Font.BOLD, 12);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        // 获取计数器的值
        ServletContext context = getServletContext();
        Counter counter = (Counter) context.getAttribute("counter");

        // 将计数器的值转换为字符串
        String count = String.valueOf(counter.getCount());

        // 以下为生成图片的代码
        response.setContentType("image/jpeg");
        ServletOutputStream out = response.getOutputStream();

        // 创建一个位于缓存中的图像，长为11*len，高为16
        BufferedImage image = new BufferedImage(11 * count.length(), 16, BufferedImage.TYPE_INT_RGB);

        // 获得Graphics画笔
        Graphics g = image.getGraphics();
        g.setColor(Color.black);

        // 画一个黑色的矩形，长为11*len，高为16
        g.fillRect(0, 0, 11 * count.length(), 16);
        g.setColor(Color.white);
        g.setFont(font);

        char c;
        for (int i = 0; i < count.length(); i++) {
            c = count.charAt(i);
            g.drawString(String.valueOf(c), i * 11 + 1, 12); // 写一个白色的数字
            g.drawLine((i + 1) * 11 - 1, 0, (i + 1) * 11 - 1, 16); // 画一个白色的竖线
        }

        // 输出JPEG格式的图片
        ImageIO.write(image, "jpeg", out);
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        doGet(request, response);
    }
}
