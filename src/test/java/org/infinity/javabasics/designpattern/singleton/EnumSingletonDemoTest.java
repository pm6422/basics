package org.infinity.javabasics.designpattern.singleton;

import org.junit.Test;

public class EnumSingletonDemoTest {

    @Test
    public void test() {
        EnumSingletonDemo.INSTANCE.meth();
    }
}
