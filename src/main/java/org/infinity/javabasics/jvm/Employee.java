package org.infinity.javabasics.jvm;

public class Employee {

    private String id; // 雇员的标识号码

    private String name; // 雇员姓名

    private String department; // 该雇员所在部门

    private String Phone; // 该雇员联系电话

    private int salary; // 该雇员薪资

    private String origin; // 该雇员信息的来源

// 构造方法

    public Employee(String id) {
        this.id = id;
        getDataFromlnfoCenter();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    // 到数据库中取得雇员信息

    private void getDataFromlnfoCenter() {
    }

}