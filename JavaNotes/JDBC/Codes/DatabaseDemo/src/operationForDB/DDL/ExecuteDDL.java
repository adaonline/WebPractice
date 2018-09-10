package operationForDB.DDL;


import operationForDB.DBUtil;

import java.sql.Connection;
import java.sql.Statement;

public class ExecuteDDL {

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
        ExecuteDDL ed=new ExecuteDDL();
        //创建语句不要有数据库的关键字，例如name，desc等
        String sql="create table create_test" +"(id int auto_increment primary key," +"testname varchar(255)," +"testdesc text);";
        //String sql="drop table create_test;";
        ed.executeSql(sql);
        System.out.println(sql);
        System.out.println("执行成功");
    }
}
