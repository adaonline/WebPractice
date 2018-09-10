package StatementTest.CallableStatement;

import operationForDB.DBUtil;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;

public class CallableStatementTest {
    /**
     * 存储过程1
        create PROCEDURE getall()
        BEGIN
        SELECT * from create_test;
        end;

        create PROCEDURE add_s(a int,b int,out sum int)
        BEGIN
        set sum=a+b;
        END;
     */
    public void callProByparam(){
        try {
            Connection conn= DBUtil.getConn();
            CallableStatement callableStatement=conn.prepareCall("{call add_s(?,?,?)}");
            callableStatement.setInt(1,1);
            callableStatement.setInt(2,2);
            //注册参数
            callableStatement.registerOutParameter(3, Types.INTEGER);
            //执行存储过程
            callableStatement.execute();
            System.out.println("执行后的结果："+callableStatement.getInt(3));
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void callPro(){
        try {
            Connection conn= DBUtil.getConn();
            CallableStatement callableStatement=conn.prepareCall("{call getall()}");

            //执行存储过程
            ResultSet resultSet=callableStatement.executeQuery();
            while (resultSet.next()){
                System.out.println(resultSet.getInt(1)+"\t"+resultSet.getString(2)+"\t"+resultSet.getString(3));
            }
            //System.out.println("执行后的结果："+callableStatement.getInt(3));
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        CallableStatementTest test=new CallableStatementTest();
        System.out.println("####执行带参数存储过程：");
        test.callProByparam();
        System.out.println("####执行无参数存储过程：");
        test.callPro();
    }

}
