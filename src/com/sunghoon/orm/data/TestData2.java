package com.sunghoon.orm.data;

import com.sunghoon.orm.persistence.PersistentData;

import java.sql.Timestamp;

public class TestData2 extends PersistentData {
    public String bcType;
    public String bcValue;

    public TestData2() {
        super(TestData2.class);
    }
}
