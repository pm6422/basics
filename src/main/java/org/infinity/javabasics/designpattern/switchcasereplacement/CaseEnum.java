package org.infinity.javabasics.designpattern.switchcasereplacement;

public enum CaseEnum {
    A("A"),
    B("B"),
    C("C");

    private String type;

    CaseEnum(String type) {
        this.type = type;
    }
    //省略type的getter setter方法
}