package org.infinity.javabasics.concurrency;

public class VolatileTest {

    int a = 0;
    int b = 0;

    public void set() {
        a = 1;
        b = 1;
    }

    public void loop() {
        while (b == 0) continue;
        if (a == 1) {
            System.out.println("i'm here");
        } else {
            System.out.println("what's wrong");
        }
    }
}