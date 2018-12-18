package org.infinity.javabasics.concurrency;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

/**
 * 管道通信
 */
public class PipedReaderWriterDemo {

    private static final Logger LOGGER = LoggerFactory.getLogger(PipedReaderWriterDemo.class);

    public static void main(String[] args) throws IOException {
        //面向于字符 PipedInputStream 面向于字节
        PipedWriter writer = new PipedWriter();
        PipedReader reader = new PipedReader();

        //输入输出流建立连接
        writer.connect(reader);

        Thread writerThread = new Thread(() -> {
            LOGGER.info("Running " + Thread.currentThread().getName());
            try {
                for (int i = 0; i < 10; i++) {
                    writer.write(i + "");
                    Thread.sleep(10);
                }
            } catch (Exception e) {
            } finally {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, "WriterThread");
        Thread readerThread = new Thread(() -> {
            LOGGER.info("Running " + Thread.currentThread().getName());
            int msg = 0;
            try {
                while ((msg = reader.read()) != -1) {
                    LOGGER.info("Read message {}", (char) msg);
                }

            } catch (Exception e) {
            }
        }, "ReaderThread");
        writerThread.start();
        readerThread.start();
    }
}
