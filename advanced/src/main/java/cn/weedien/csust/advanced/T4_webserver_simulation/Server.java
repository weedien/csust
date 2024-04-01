package cn.weedien.csust.advanced.T4_webserver_simulation;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class Server {
    private static final int THREAD_POOL_SIZE = 10;
    private static final ExecutorService executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8888)) {
            log.info("服务器启动,等待客户端连接...");

            while (true) {
                Socket socket = serverSocket.accept(); // 接受客户端连接
                // 将客户端请求提交给线程池处理
                executorService.execute(new ServerThread(socket));
            }
        } catch (IOException e) {
            log.error("服务器异常", e);
        }
    }
}
