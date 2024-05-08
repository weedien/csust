package cn.weedien.csust.advanced.T4_webserver_simulation;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Slf4j
public class Client {
    public static void main(String[] args) {
        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
        // String line;
        // while ((line = userInput.readLine()) != null) {
        //     new ClientThread(line).start(); // 为每个用户输入创建一个新线程
        // }
        for (int i = 0; i < 20; i++) {
            new ClientThread("service1").start(); // 为每个用户输入创建一个新线程
        }
    }
}