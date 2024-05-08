package cn.weedien.csust.advanced.T4_webserver_simulation;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

@Slf4j
public class ServerThread implements Runnable {
    private final Socket socket;

    private final String clientName;

    public ServerThread(Socket socket) {
        this.socket = socket;
        this.clientName = socket.getInetAddress() + ":" + socket.getPort();
    }

    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            String line = in.readLine(); // 读取客户端发送的字符串
            log.info("客户端 {} 请求: {}", clientName, line);

            Service service = ServiceFactory.getService(line); // 根据字符串构建服务对象
            if (service != null) {
                String response = service.doService(); // 执行服务并获取响应
                out.println(response);
            } else {
                out.println("未知服务");
            }

            socket.close();
        } catch (IOException e) {
            log.error("服务器异常", e);
        }
    }
}