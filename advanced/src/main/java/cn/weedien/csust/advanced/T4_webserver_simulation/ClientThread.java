package cn.weedien.csust.advanced.T4_webserver_simulation;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

@Slf4j
public class ClientThread extends Thread {
    private final String request;

    public ClientThread(String request) {
        this.request = request;
    }

    public void run() {
        try (Socket socket = new Socket("localhost", 8888); // 连接服务器
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            out.println(request); // 将用户输入发送给服务器
            log.info("服务器响应: {}", in.readLine()); // 读取服务器响应

        } catch (IOException e) {
            log.error("客户端异常", e);
        }
    }
}