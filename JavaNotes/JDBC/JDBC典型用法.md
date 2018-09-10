# JDBC典型用法
## 一. 常用接口API
###### DriverManager:用于管理JDBC驱动的服务类。主要用来获取Connection对象
- public static Connection getConnection(String url,String user, String password) throws SQLException --用来获取对应数据库的连接
###### Connection: 数据库连接对象，每一个Connection代表一个物理连接回话，访问数据库是必须获得的。
- Statement createStatement() throws SQLException;
-  PreparedStatement prepareStatement(String sql) throws SQLException; --返回预编译的Statement对象
-  CallableStatement prepareCall(String sql) throws SQLException; --返回CallableStatement对象，用于存储过程。

###### Statement：用于执行SQL查询。可以用于执行DDl，DCl,DMl,也可以用来SQL查询。sql查询时候返回的是结果集
- ResultSet executeQuery(String sql) throws SQLException;--用于查询语句，用于返回结果集，只能用于查询
- int executeUpdate(String sql) throws SQLException; --用于执行DML语句，返回影响的行数，也可以DDL语句，返回0
- boolean execute(String sql) throws SQLException; --用于执行任何sql语句，如果执行结果有结果集，则返回true；如果执行后第一个结果为受影响行数或者没有结果则返回false。

###### PreparedStatement:预编译的Statement对象。可以预编译SQl语句（带有参数那种），后面只需要改参数即可，避免每次都去编译SQL语句，性能更好，每次执行只需要传入参数即可。同样有Statement默认的三个方法，但是不用接受SQL。还有个很好的作用，防止SQL注入。
字符串了，已经编译好了，只需要传入参数即可。
- void setXxx(int parameterIndex,Xxx value)：不同的传入参数，用不同的方法。前一个参数是指定位置的参数

###### ResultSet：结果集对象
- close():释放结果集
- boolean first():将结果集的记录指针定位到上一行。如果指针指向的记录有效，则返回true。
- void beforeFirst():将指针定位到首行之前，也就是初始状态。
- boolean previous():将指针定位到上一行，如果记录有效，则返回true
- boolean next(): 将指移动到下一行，如果记录有效则返回true
- boolean last(): 将指针移动到最后一行
- 指针到了指定行时候，可以通过getXxx(int columnindex) 或者 getXxx(String columnlabel)方法获取当前行的指定列的值，前一个根据索引，后者根据列名获取。还有一个getObject或者对应列的对象


## 二. JDBC步骤
1. 反射加载驱动
2. 获取数据库连接
3. 执行sql语句
4. 操作结果集


```
package connectMysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectMysqlDemo {
    public static void main(String[] args) throws Exception{
        //1.反射加载驱动
        Class.forName("com.mysql.jdbc.Driver");
        try{
            //2.获取数据库连接
            Connection conn= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/database","root","password");
            //3.创建statement对象
            Statement stmt=conn.createStatement();

            //执行sql语句
            /**
             * 1.execute()可以执行任何sql返回一个boolean值(如果第一个结果是relultset则返回true,如果是一个update，或者没有结果则返回false)
             * 2.executeQuery()执行select语句，返回查询到的结果集
             * 3.executeUpdate()用于执行DML语句，返回一个整数，代表被这个语句影响的记录数目
             */
            ResultSet rs=stmt.executeQuery("SELECT * from pet");
            while(rs.next()){
                System.out.println(rs.getInt(1)+"\t"+rs.getInt(2)+"\t"+rs.getString(3));
            }
        }catch (Exception e) {
            System.exit(0);
        }


    }
}

```
