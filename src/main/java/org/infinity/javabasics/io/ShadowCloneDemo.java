package org.infinity.javabasics.io;

public class ShadowCloneDemo {
    public static void main(String[] args) throws CloneNotSupportedException {
        Dog dog1 = new Dog();
        dog1.name = "旺财";
        dog1.age = 5;

        Dog dog2 = (Dog) dog1.clone(); // 调用clone()方法实现克隆
        dog2.name = "小白";
        dog2.age = 2;
        System.out.println(dog1);
        System.out.println(dog2);
    }
}

/**
 * 注意：必须实现Cloneable接口，否则无法调用clone()方法
 */
class Dog implements Cloneable {
    public String name;// 注意String类型的字段值也会克隆复制
    public int    age;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}