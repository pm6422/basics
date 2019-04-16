package org.infinity.javabasics.basic;

import java.util.Objects;

public class HashCodeAndEqualsDemo1 {

    public static void main(String[] args) {
        // 新建2个相同内容的Person对象，
        // 再用equals比较它们是否相等
        Person p1 = new Person("eee", 100);
        Person p2 = new Person("eee", 100);
        Person p3 = new Person("aaa", 200);
        System.out.printf("p1.equals(p2): %s; p1 hashcode(%d) p2 hashcode(%d)\n", p1.equals(p2), p1.hashCode(), p2.hashCode());
        System.out.printf("p1.equals(p3): %s; p1 hashcode(%d) p3 hashcode(%d)\n", p1.equals(p3), p1.hashCode(), p3.hashCode());
    }

    private static class Person {
        int    age;
        String name;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String toString() {
            return name + " - " + age;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Person person = (Person) o;
            return age == person.age &&
                    Objects.equals(name, person.name);
        }

        @Override
        public int hashCode() {
            // Object类中的hashCode是返回对象在内存中地址转换成的一个int值（可以就当做地址看）。所以如果没有重写hashCode方法，任何对象的hashCode都是不相等的。
            return super.hashCode();
        }
    }
}
