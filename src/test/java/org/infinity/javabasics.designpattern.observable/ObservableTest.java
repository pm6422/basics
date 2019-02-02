package org.infinity.javabasics.designpattern.observable;


import org.junit.Test;

public class ObservableTest {
    @Test
    public void test() {
        Observable o = new ObservableThread<>(() -> {
            int a = 0;
            for (int i = 0; i < 10; i++) {
                a += i;
            }
            return a;
        });
        o.start();
    }
}