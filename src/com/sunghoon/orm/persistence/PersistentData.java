package com.sunghoon.orm.persistence;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;

import static com.sunghoon.orm.constant.GlobalConst.MAX_TABLE_COLUMN_SIZE;

public class PersistentData {

    private ArrayList primaryKeyList;
    private Object object;

    public PersistentData(Object object) {
        primaryKeyList = new ArrayList();
        this.object = object;
    }

    public void setPrimaryKeyIndex(int index) {

        if(primaryKeyList.contains(index)) {
            throw new PersistenceException();
        } else {
            primaryKeyList.add(index);
        }
    }

    public ArrayList getPrimaryKeyIndexes() {
        return primaryKeyList;
    }

    public String getPrimaryKeySQLPhase() {
        Field[] fields = object.getClass().getDeclaredFields();

        StringBuffer pKPhase = new StringBuffer();
        pKPhase.append("PRIMARY KEY(");

        for(int i=0; i<primaryKeyList.size(); i++) {
            pKPhase.append("'");
            try {
                pKPhase.append(fields[i].get(object));
            } catch(IllegalAccessException ex) {
                throw new PersistenceException();
            }
            pKPhase.append("'");
            pKPhase.append(",");
        }
        pKPhase.deleteCharAt(pKPhase.length()-1);
        pKPhase.append(")");

        return pKPhase.toString();
    }
}
