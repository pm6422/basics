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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class FixedWorkQueueThreadPoolServer {
    private static final Logger LOGGER = LoggerFactory.getLogger(MultithreadHandleServer.class);

    final static String response = "HTTP/1.0 200 OK\r\n" + "Content-type: text/plain\r\n" + "\r\n” +“Hello World\r\n";

    public static void main(String[] args) throws IOException {
        ServerSocket listener = new ServerSocket(7030);
        ExecutorService threadPool = newBoundedFixedThreadPool(4, 16);
        try {
            while (true) {
                Socket socket = listener.accept();// 通过创建新的线程，主线程可以继续接受新的TCP连接，且这些信求可以并行的处理
                threadPool.submit(new HandleRequestRunnable(socket));
            }
        } finally {
            listener.close();
        }
    }

    static ExecutorService newBoundedFixedThreadPool(int nThreads, int capacity) {
        return new ThreadPoolExecutor(nThreads, nThreads,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(capacity),
                new ThreadPoolExecutor.DiscardPolicy());
    }

    static class HandleRequestRunnable implements Runnable {
        final Socket socket;

        public HandleRequestRunnable(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                handleRequest(socket);
                LOGGER.debug("Handled by thread: {}", Thread.currentThread().getName());
            } catch (IOException e) {
                e.printStackTrace();
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
}
