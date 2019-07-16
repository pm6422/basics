package org.infinity.javabasics.socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * Web服务的基础是套接字（socket），套接字负责监听端口，等待TCP连接，并接受TCP连接。一旦TCP连接被接受，即可从新创建的TCP连接中读取和发送数据
 *
 * 输入测试 http://localhost:9080
 *
 * 优化方式使用多线程处理，参考MultithreadHandleServer
 */
public class SingleThreadServer {

    private static final Logger LOGGER = LoggerFactory.getLogger(SingleThreadServer.class);

    final static String response = "HTTP/1.0 200 OK\r\n" + "Content-type: text/plain\r\n" + "\r\n” +“Hello World\r\n";

    public static void main(String[] args) throws IOException {
        ServerSocket listener = new ServerSocket(7010);// 创建了一个服务端套接字(ServerSocket)，监听7010端口
        try {
            while (true) {// 循环检查这个套接字，查看是否有新的连接
                Socket socket = listener.accept();// 只有一个线程来处理请求，每个请求都必须等待前一个请求处理完成之后才能够被响应
                try {
                    handleRequest(socket);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            listener.close();
        }
    }

    public static void handleRequest(Socket socket) throws IOException {
        // Read the input stream, and return “200 OK”
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            LOGGER.info(in.readLine());
            OutputStream out = socket.getOutputStream();
            out.write(response.getBytes(StandardCharsets.UTF_8));
        } finally {
            socket.close();
        }
    }

}
