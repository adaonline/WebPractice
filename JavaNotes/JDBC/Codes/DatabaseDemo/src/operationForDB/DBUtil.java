package operationForDB;

import java.sql.*;

public class DBUtil {
    private static String driver="com.mysql.jdbc.Driver";
    private static String url="jdbc:mysql://127.0.0.1:3306/database?useSSL=false";
    private static String user="root";
    private static String password="password";

    /**
     * 获取连接
     * @return
     * @throws Exception
     */
    public static Connection getConn() throws Exception{
        Class.forName(driver);
        Connection conn = DriverManager.getConnection(url, user, password);
        return conn;
    }
    /**
     * 关闭连接
     * @param cstmt
     * @param conn
     * @throws Exception
     */
    public static void close(Statement cstmt, Connection conn) throws Exception{
        if(cstmt!=null){
            cstmt.close();
            if(conn!=null){
                conn.close();
            }
        }
    }


}
