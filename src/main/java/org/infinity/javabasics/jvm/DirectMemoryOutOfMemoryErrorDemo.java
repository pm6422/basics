package org.infinity.javabasics.jvm;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * 不敢尝试效果，担心电脑硬盘被写满
 * <p>
 * 据说会产生下面的错误
 * Exception in thread "main" java.lang.OutOfMemoryError: Direct buffer memory
 * at java.nio.Bits.reserveMemory(Bits.java:658)
 * at java.nio.DirectByteBuffer.<init>(DirectByteBuffer.java:123)
 */
public class DirectMemoryOutOfMemoryErrorDemo {
    public static void main(String[] args) {
        List list = new ArrayList();
        while (true) {
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024 * 2048);
            list.add(byteBuffer);
        }
    }
}
