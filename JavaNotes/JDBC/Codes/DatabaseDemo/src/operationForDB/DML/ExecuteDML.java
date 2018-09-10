package operationForDB.DML;


import operationForDB.DBUtil;

import java.sql.Connection;
import java.sql.Statement;

public class ExecuteDML {
    public void executeSql(String sql){
        try {
            Connection conn= DBUtil.getConn();
            Statement statement=conn.createStatement();
            int result= statement.executeUpdate(sql);
            System.out.println("创建结果:"+result);
            DBUtil.close(statement,conn);
        }catch (Exception e){
            /**
             * Establishing SSL connection without server's identity verification is not recommended
             * 出现的异常错误（是Mysql数据库的SSL连接问题，提示警告不建议使用没有带服务器身份验证的SSL连接）
             * 在数据库连接的url中添加useSSL=false
             */
            e.printStackTrace();
            System.exit(0);
        }


    }

    public static void main(String[] args) {
        ExecuteDML ed=new ExecuteDML();
        /**
         * 日常增删改操作
         */
        String insertsql="insert into create_test(testname,testdesc) values('aa','aa');";
        String deletesql="delete from create_test where testname='aa';";
        String updatesql="update create_test set testdesc='bb' where testname='aa'";
        ed.executeSql(updatesql);
    }
}
