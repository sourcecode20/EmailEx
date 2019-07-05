package com.example.emailex.model;

public class Users {
    private String address;
    private String age;
    private String mobile;
    private String name;

    public Users() {


    }

    public Users(String address, String age, String mobile, String name) {
        this.address = address;
        this.age = age;
        this.mobile = mobile;
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Users{" +
                "address='" + address + '\'' +
                ", age='" + age + '\'' +
                ", mobile='" + mobile + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
