import com.sunghoon.orm.data.TestData2;
import com.sunghoon.orm.data.TestData3;
import com.sunghoon.orm.persistence.PersistenceManager;
import com.sunghoon.orm.data.TestData1;
import java.sql.Timestamp;
import java.util.Date;

import java.nio.charset.StandardCharsets;

public class Main {

    public static void main(String[] args) {
        PersistenceManager persistenceManager = PersistenceManager.getInstance();
//        persistenceManager.testFunction();

//        TestData1 testData1 = new TestData1();
//
//        testData1.setAge(43);
//        testData1.setBinary("choi sung hoon".getBytes(StandardCharsets.UTF_8));
//        testData1.setName("choi choi choi");
//        Date date = new Date();
//        testData1.setTime(new Timestamp(date.getTime()));
//
//        TestData2 testData2 = new TestData2();
//        testData2.bcType = "1";
//        testData2.bcValue = "2";

        TestData3 testData3 = new TestData3();
        testData3.a = "1";
        testData3.b = "2";
        testData3.c = "3";
        testData3.d = "4";
        testData3.setPrimaryKeyIndex(0);
        testData3.setPrimaryKeyIndex(1);
        System.out.println("primary key phase " + testData3.getPrimaryKeySQLPhase());
        persistenceManager.addObject(testData3);
    }
}