package sqlService;

import java.sql.*;
//用于连接断开数据库
public abstract class SQLBase {
     final String driverName="com.mysql.cj.jdbc.Driver";               // 加载数据库驱动类
     final String url="jdbc:mysql://localhost:3306/document?serverTimezone=UTC&characterEncoding=utf-8" ;       // 声明数据库的URL
     final String user="root";                                      // 数据库用户
     final String password="147896325";
     protected Connection getConnection() throws SQLException//与数据库建立连接
     {
     	try {
 			Class.forName(driverName);
 		} catch (ClassNotFoundException e) {
 			e.printStackTrace();
 			
 		}
         return DriverManager.getConnection(url, user, password);
     }
     
     protected  void closeConnection(Connection conn)//关闭链接
     {
         if (conn != null)
         {
             try
             {
                 conn.close();
             }
             catch (SQLException e)
             {
            	 e.printStackTrace();
      			
             }
             conn = null;
         }
     }

     protected  void closeResultSet(ResultSet rs) 
     {
         if (rs != null)
         {
             try
             {
                 rs.close();
             }
             catch (SQLException e)
             {
            	 e.printStackTrace();
             }
             rs = null;
         }
     }

     protected   void closeStatement(Statement st) 
     {
         if (st != null)
         {
             try
             {
                 st.close();
             }
             catch (SQLException e)
             {
            	 e.printStackTrace();
             }
             st = null;
         }
     }
}
