package org.infinity.javabasics.io;

public class ShadowCloneDemo {
    public static void main(String[] args) throws CloneNotSupportedException {
        Dog dog = new Dog();
        dog.name = "旺财";
        dog.age = 5;
        // 克隆
        Dog dog3 = (Dog) dog.clone();
        dog3.name = "小白";
        dog3.age = 2;
        System.out.println(dog);
        System.out.println(dog3);
    }
}

class Dog implements Cloneable {
    public String name;
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