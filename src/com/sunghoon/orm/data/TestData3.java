package com.sunghoon.orm.data;

import com.sunghoon.orm.persistence.PersistentData;

public class TestData3 extends PersistentData {
    public String a;
    public String b;
    public String c;
    public String d;

    public TestData3() {
        super(TestData3.class);
    }
}
