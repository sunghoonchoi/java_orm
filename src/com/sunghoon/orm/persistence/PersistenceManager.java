package com.sunghoon.orm.persistence;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.sql.*;

public class PersistenceManager {
    private static PersistenceManager gPersistenceManager;

    private static final String JDBC_NAME = "org.sqlite.JDBC";
    private static final String DB_FILE_NAME = "/Users/dam808/db.sqlite";

    private static Connection connection = null;

    private PersistenceManager() {
    }

//    public static boolean checkTableExist() {
//        SELECT name FROM sqlite_master WHERE type='table' AND name='table_name';
//    }

    public static PersistenceManager getInstance() {
        if(gPersistenceManager == null) {
            gPersistenceManager = new PersistenceManager();

            try {
                Class.forName(JDBC_NAME);
                connection = DriverManager.getConnection("jdbc:sqlite:" + DB_FILE_NAME);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return gPersistenceManager;
    }

    private static void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private byte[] convertToBytes(Object object) throws IOException {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutputStream out = new ObjectOutputStream(bos)) {
            out.writeObject(object);
            return bos.toByteArray();
        }
    }

    public void addObject(Object object) {
        String ddlQueryString = QueryGenerator.makeDdl(object);
        System.out.println("query = " + ddlQueryString);
        runQuery(ddlQueryString);

//        String insertQueryString = QueryGenerator.makeInsertPreparedStatement(object);
//
//        try (PreparedStatement pstmt = connection.prepareStatement(insertQueryString))
//        {
//            Field[] fields = object.getClass().getDeclaredFields();
//            for(int i=0; i<fields.length; i++) {
//                Object value = fields[i].get(object);
//                String dataType = fields[i].getGenericType().getTypeName();
//                if(QueryGenerator.getSQLType(dataType).equals("TEXT")) {
//                    pstmt.setString(i+1, String.valueOf(value));
//                } else if(QueryGenerator.getSQLType(dataType).equals("INTEGER")) {
//                    pstmt.setInt(i+1, (Integer)value);
//                } else if(QueryGenerator.getSQLType(dataType).equals("BLOB")) {
//                    byte[] bytes = convertToBytes(value);
//                    pstmt.setBytes(i+1, bytes);
//                }
//            }
//            pstmt.execute();
//        } catch(Exception ex) {
//            ex.printStackTrace();
//        }
    }

    public void runQuery(String query) {
        try {
            Statement stat = connection.createStatement();
            stat.executeQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        finally {
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (Exception e) {
//                }
//            }
//        }
    }

    public void testFunction() {
        try {
            Statement stat = connection.createStatement();
            ResultSet rs = stat.executeQuery("SELECT ID, Name FROM User");
            while (rs.next()) {
                String id = rs.getString("ID");
                String name = rs.getString("Name");
                System.out.println(id + " " + name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                }
            }
        }
    }
}
