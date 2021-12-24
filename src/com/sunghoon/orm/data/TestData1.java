package com.sunghoon.orm.data;

import com.sunghoon.orm.persistence.PersistentData;

import java.sql.Timestamp;

public class TestData1 extends PersistentData {
    public String name;
    public int age;
    public Timestamp time;
    public byte[] binary;

    public TestData1() {
        super(TestData1.class);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public byte[] getBinary() {
        return binary;
    }

    public void setBinary(byte[] binary) {
        this.binary = binary;
    }
}
