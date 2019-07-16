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


public class MultithreadHandleServer {

    private static final Logger LOGGER = LoggerFactory.getLogger(MultithreadHandleServer.class);

    final static String response = "HTTP/1.0 200 OK\r\n" + "Content-type: text/plain\r\n" + "\r\n” +“Hello World\r\n";

    public static void main(String[] args) throws IOException {
        ServerSocket listener = new ServerSocket(7020);
        try {
            while (true) {
                Socket socket = listener.accept();
                new Thread(new HandleRequestRunnable(socket)).start();
            }
        } finally {
            listener.close();
        }
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


