package org.infinity.javabasics.jvm;

/**
 * jstat命令可以查看到回收信息以及输出的日志
 */
public class FinalizeDemo {

    private static Block holder = null;

    public static void main(String[] args) throws Exception {
        holder = new Block();
        holder = null;
        System.gc();
        //System.in.read();
    }

    static class Block {
        byte[] _200M = new byte[200 * 1024 * 1024];

        @Override
        protected void finalize() throws Throwable {
            System.out.println("invoke finalize");
        }
    }
}