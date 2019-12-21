package sqlService;

import java.sql.*;
//�������ӶϿ����ݿ�
public abstract class SQLBase {
     final String driverName="com.mysql.cj.jdbc.Driver";               // �������ݿ�������
     final String url="jdbc:mysql://localhost:3306/document?serverTimezone=UTC&characterEncoding=utf-8" ;       // �������ݿ��URL
     final String user="root";                                      // ���ݿ��û�
     final String password="147896325";
     protected Connection getConnection() throws SQLException//�����ݿ⽨������
     {
     	try {
 			Class.forName(driverName);
 		} catch (ClassNotFoundException e) {
 			e.printStackTrace();
 			
 		}
         return DriverManager.getConnection(url, user, password);
     }
     
     protected  void closeConnection(Connection conn)//�ر�����
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
