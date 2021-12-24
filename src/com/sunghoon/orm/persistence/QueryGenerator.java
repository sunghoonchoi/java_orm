package com.sunghoon.orm.persistence;

import java.lang.reflect.Field;
import java.util.Arrays;

public class QueryGenerator {

    public static String makeDdl(Object object) throws PersistenceException {

        if((object instanceof PersistentData) == true) {
            System.out.println("Persis data");
        } else {
            throw new PersistenceException();
        }

//        PersistentData perst = PersistentData.class.cast(object);
//        if(perst.getPrimaryKeyCount() == 0) {
//            throw new PersistenceException();
//        }

        Field[] fields = object.getClass().getDeclaredFields();
        String className = object.getClass().getSimpleName();

        StringBuffer ddl = new StringBuffer();
//        StringBuffer pKPhase = new StringBuffer();
//        pKPhase.append("PRIMARY KEY(");

        ddl.append("CREATE TABLE ");
        ddl.append(className);
        ddl.append(" (");
        for(int i=0; i<fields.length; i++) {
            ddl.append(fields[i].getName());
            //if this column is one of the primary keys
            System.out.println("Type = " + fields[i].getGenericType().getTypeName());
            String sqlType = QueryGenerator.getSQLType(fields[i].getGenericType().getTypeName());
            System.out.println("SQL Type = " + sqlType);
            ddl.append(" ");
            ddl.append(sqlType);
            ddl.append(", ");
        }

//        pKPhase.deleteCharAt(pKPhase.length()-1);
//        pKPhase.append(")");

        ddl.append(");");
        return ddl.toString();
    }

    public static String makeInsertPreparedStatement(Object object) {
        Field[] fields = object.getClass().getDeclaredFields();
        String tableName = object.getClass().getSimpleName();
        int numberOfArgument = fields.length;

        StringBuffer dml = new StringBuffer();

        dml.append("INSERT INTO ");
        dml.append(tableName);
        dml.append(" VALUES (");

        for(int i=0; i<numberOfArgument; i++) {
            dml.append("?");
            if(i != numberOfArgument-1) {
                dml.append(",");
            }
        }
        dml.append(");");

        return dml.toString();
    }

    public static String makeInsert(Object object) {

//        insert into user values (3, 'kimkc', 32, 'Daejeon');
        Field[] fields = object.getClass().getDeclaredFields();
        String className = object.getClass().getSimpleName();
        StringBuffer dml = new StringBuffer();

        dml.append("INSERT INTO ");
        dml.append(className);
        dml.append(" VALUES (");

        for(int i=0; i<fields.length; i++) {

            Object value = null;
            fields[i].setAccessible(true);
            try {
                value = fields[i].get(object);
            } catch(IllegalAccessException ex) {
                ex.printStackTrace();
            }
            String sqlType = QueryGenerator.getSQLType(fields[i].getGenericType().getTypeName());
            if(sqlType.equals("TEXT") == true) {
                dml.append("'");
            }
            dml.append(value);
            if(sqlType.equals("TEXT") == true) {
                dml.append("'");
            }
            if(i != fields.length -1) {
                dml.append(", ");
            }
        }
        dml.append(");");

        return dml.toString();
    }


    //REAL, NULL
    public static String getSQLType(String inputType) {
        String sqlType = null;

        if(inputType.equals("java.lang.String")) {
            sqlType = "TEXT";
        } else if(inputType.equals("int")) {
            sqlType = "INTEGER";
        } else if(inputType.equals("java.sql.Timestamp")) {
            sqlType = "TEXT";
        } else if(inputType.equals("byte[]")) {
            sqlType = "BLOB";
        }

        return sqlType;
    }
}
