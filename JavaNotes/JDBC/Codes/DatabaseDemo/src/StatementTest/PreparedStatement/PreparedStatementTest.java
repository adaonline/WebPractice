package StatementTest.PreparedStatement;

import operationForDB.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
//预编译应该更快一些
public class PreparedStatementTest {
    public void insertUseStatement(){
        long start=System.currentTimeMillis();
        try {
            Connection conn= DBUtil.getConn();
            Statement statement=conn.createStatement();
            for(int i=0;i<100;i++){
                statement.executeUpdate("insert into create_test values(null,'name"+i+"','desc"+i+"');");
            }
            System.out.println("使用statement耗时："+(System.currentTimeMillis()-start));
            DBUtil.close(statement,conn);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void insertUsePrePare(){
        long start=System.currentTimeMillis();
        try {
            Connection conn= DBUtil.getConn();
            PreparedStatement preparedStatement=conn.prepareStatement("insert into create_test values(null,?,'desc')");
            for(int i=0;i<100;i++){
                preparedStatement.setString(1,"name"+i);
                preparedStatement.executeUpdate();
            }
            System.out.println("使用PreparedStatement耗时："+(System.currentTimeMillis()-start));
            DBUtil.close(preparedStatement,conn);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        PreparedStatementTest preparedTest=new PreparedStatementTest();
        preparedTest.insertUseStatement();
        preparedTest.insertUsePrePare();
    }
}
