package connectMysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectMysqlDemo {
    public static void main(String[] args) throws Exception{
        Connection conn=null;
        //1.反射加载驱动
        Class.forName("com.mysql.jdbc.Driver");
        try{
            //2.获取数据库连接
            conn= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/database?useSSL=false","root","password");
            //3.创建statement对象
            Statement stmt=conn.createStatement();

            //执行sql语句
            /**
             * 1.execute()可以执行任何sql返回一个boolean值(如果第一个结果是relultset则返回true,如果是一个update，或者没有结果则返回false)
             * 2.executeQuery()执行select语句，返回查询到的结果集
             * 3.executeUpdate()用于执行DML语句，返回一个整数，代表被这个语句影响的记录数目
             */
            ResultSet rs=stmt.executeQuery("SELECT * from create_test");
            while(rs.next()){
                System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try{
                if(conn!=null){
                    conn.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }


    }
}