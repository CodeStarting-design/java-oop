package sqlService;
import exceptionBao.*;
import 多线程综合实验.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLForDoc extends SQLBase  {
	public SQLForDoc() {
		
	}
	public Doc insert(Doc object) throws BaseException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(
					"insert into doc_info (Id, creator, timestamp, description, filename) values (?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, object.getID());
			pstmt.setString(2, object.getCreator());
			pstmt.setTimestamp(3, object.getTimestamp());
			pstmt.setString(4, object.getDescription());
			pstmt.setString(5, object.getFilename());
			
			int rt = pstmt.executeUpdate();
//			if (rt > 0) {
//				rs = pstmt.getGeneratedKeys();
//				if (rs.next()) {
//					object.setId(rs.getInt(1));
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


//	public Doc update(Doc object) throws BaseException {
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		try {
//			conn = getConnection();
//			pstmt = conn.prepareStatement(
//					"update doc_info set ID=?,creator=?,timestamp=?,description=?,filename=?where Id=?");
//			pstmt.setString(1, object.getID());
//			pstmt.setString(2, object.getCreator());
//			pstmt.setTimestamp(3, object.getTimestamp());
//			pstmt.setString(4, object.getDescription());
//			pstmt.setString(5, object.getFilename());
//			int rt = pstmt.executeUpdate();
//			if (rt > 0) {
//				return object;
//			} else {
//				return null;
//			}
//		} catch (SQLException exception) {
//			throw new DaoException();
//		} finally {
//			closeStatement(pstmt);
//			closeConnection(conn);
//		}
//	}

	
	public Doc delete(Doc object) throws BaseException {//根据文件ID删除
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("delete from doc_info where Id=?");
			pstmt.setString(1, object.getID());
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

	
	public Doc findById(String id) throws BaseException {
		Connection conn = null;
		PreparedStatement pstmt = null, pstmt1 = null;
		ResultSet rs = null, rs1 = null;

		Doc document = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from doc_info where Id=?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				document = new Doc();
				document.setID(rs.getString("ID"));
				document.setCreator(rs.getString("creator"));
				document.setFilename(rs.getString("filename"));
				document.setTimestamp(rs.getTimestamp("timestamp"));
				document.setDescription(rs.getString("description"));				
//				pstmt1 = conn.prepareStatement("select * from user_info where user_id=?");
//				pstmt1.setLong(1, rs.getLong("document_user_id"));
//				rs1 = pstmt1.executeQuery();
//				if (rs1.next()) {
//					User user;
//					String role = rs1.getString("role").toLowerCase();
//					if (role.indexOf("administrator") > -1) {
//						user = new Administrator();
//					} else if (role.indexOf("operator") > -1) {
//						user = new Operator();
//					} else {
//						user = new Browser();
//					}
//					user.setName(rs1.getString("username"));
//					user.setPassword(rs1.getString("password"));
//					user.setId(rs1.getLong("user_id"));
//					document.setUser(user);
//				}
			}
		} catch (SQLException exception) {
			throw new DaoException();
		} finally {
			closeResultSet(rs);
			closeStatement(pstmt);
			closeResultSet(rs1);
			closeStatement(pstmt1);
			closeConnection(conn);
		}
		return document;
	}

	
	public List<Doc> findAllOnes() throws BaseException {//列出所有文件
		List<Doc> documents = new ArrayList<Doc>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from doc_info");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String document_id = rs.getString("Id");
				Doc document = this.findById(document_id);
				documents.add(document);
			}
			return documents;
		} catch (SQLException exception) {
			throw new DaoException();
		} finally {
			closeResultSet(rs);
			closeStatement(pstmt);
			closeConnection(conn);
		}
	}

	
//	public Document findBySn(String sn) throws BaseException {
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		Document document = null;
//		try {
//			conn = getConnection();
//			pstmt = conn.prepareStatement("select * from document_info where sn=?");
//			pstmt.setString(1, sn);
//			rs = pstmt.executeQuery();
//			if (rs.next()) {
//				long document_id = rs.getLong("document_id");
//				document = this.findById(document_id);
//			}
//		} catch (SQLException exception) {
//			throw new DaoException();
//		} finally {
//			closeResultSet(rs);
//			closeStatement(pstmt);
//			closeConnection(conn);
//		}
//		return document;
//	}

}
