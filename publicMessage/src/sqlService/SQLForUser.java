package sqlService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import exceptionBao.BaseException;
import exceptionBao.DaoException;
import javaSystem.Administrator;
import javaSystem.Browser;
import javaSystem.Operator;
import javaSystem.User;
public class SQLForUser extends SQLBase {
	public SQLForUser() {
		
	}
	public User insert(User object) throws BaseException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("insert into user_info (username, password, role) values (?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, object.getName());
			pstmt.setString(2, object.getPassword());
			pstmt.setString(3, object.getRole());
			
			int rt = pstmt.executeUpdate();
			
//			if (rt > 0) {
//				rs = pstmt.getGeneratedKeys();//获取生成的主键
//				if (rs.next()) {
//					object.setName(rs.getInt(1));
//				}
//			}
			return object;
		} catch (SQLException exception) {
			throw new DaoException();
		} finally {
			closeResultSet(rs);
			closeStatement(pstmt);
			closeConnection(conn);
		}
	}


	public User update(User object) throws DaoException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("update user_info set username=?,password=?,role=? where username=?");
			pstmt.setString(1, object.getName());
			pstmt.setString(2, object.getPassword());
			pstmt.setString(3, object.getRole());
			pstmt.setString(4, object.getName());
			int rt = pstmt.executeUpdate();
			if (rt > 0) {
				return object;
			} else {
				return null;
			}
		} catch (SQLException exception) {
			throw new DaoException();
		} finally {
			closeStatement(pstmt);
			closeConnection(conn);
		}
	}

	
	public User delete(User object) throws BaseException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("delete from user_info where username=?");
			pstmt.setString(1, object.getName());
			int rt = pstmt.executeUpdate();
			if (rt > 0) {
				return object;
			} else {
				return null;
			}
		} catch (SQLException exception) {
			throw new DaoException();
		} finally {
			closeStatement(pstmt);
			closeConnection(conn);
		}
	}

	


	
	public List<User> findAllOnes() throws BaseException {//找出数据库中的所有用户
		List<User> users = new ArrayList<User>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from user_info");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				User user;
				String role = rs.getString("role").toLowerCase();
				if (role.indexOf("administrator") > -1) {
					user = new Administrator();
				} else if (role.indexOf("operator") > -1) {
					user = new Operator();
				} else {
					user = new Browser();
				}
				user.setRole(rs.getString("role"));
				user.setName(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				users.add(user);
			}
			return users;
		} catch (SQLException exception) {
			throw new DaoException();
		} finally {
			closeResultSet(rs);
			closeStatement(pstmt);
			closeConnection(conn);
		}
	}

	
	public User findByName(String name) throws BaseException {//按名字查找
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		User user = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from user_info where username=?");
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			if (rs.next()) {

				String role = rs.getString("role").toLowerCase();
				if (role.indexOf("administrator") > -1) {
					user = new Administrator();
				} else if (role.indexOf("operator") > -1) {
					user = new Operator();
				} else {
					user = new Browser();
				}
				user.setName(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setRole(rs.getString("role"));
			}
		} catch (SQLException exception) {
			throw new DaoException();
		} finally {
			closeResultSet(rs);
			closeStatement(pstmt);
			closeConnection(conn);
		}
		return user;
	}
    public User login(String name ,String password)throws SQLException  {
    	try {
    		User user= findByName(name);
    		if(user==null) {
    			return user;
    		}
    		else {
    			if( user.getPassword().equals(password))
    			{
    				return user;
    			}
    			else {
    				return null;
    			}
    		}
    	}
    	catch(BaseException e) {
    		throw new SQLException();
    	}
    }
}















