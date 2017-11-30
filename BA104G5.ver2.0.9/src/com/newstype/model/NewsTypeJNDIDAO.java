package com.newstype.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.news.model.NewsVO;

public class NewsTypeJNDIDAO implements NewsTypeDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA104G5");
		} catch (NamingException ne) {
			ne.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO News_Type (NType_No, News_Type) VALUES(?, ?)";
	private static final String GET_ONE_STMT = "SELECT NType_No, News_Type FROM News_Type WHERE NType_No = ?";
	private static final String GET_ALL_STMT = "SELECT NType_No, News_Type FROM News_Type ORDER BY NType_No";
	private static final String GET_News_ByNtype_No_STMT = "SELECT News_No, NType_No, News_Title, News_Content, News_Photo, News_State, to_char(News_Date, 'yyyy-mm-dd')News_Date, EMP_NO FROM News WHERE NType_No= ? ORDER BY News_No";

	// 新增
	@Override
	public void insert(NewsTypeVO newstypeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			con.setAutoCommit(false);

			pstmt.setString(1, newstypeVO.getNtype_no());
			pstmt.setString(2, newstypeVO.getNews_type());

			int rowCount = pstmt.executeUpdate();
			con.commit();
			con.setAutoCommit(true);
			System.out.println("NewsType_INSERT_STMT: " + rowCount + "筆");

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	// 查以ntype_no
	@Override
	public NewsTypeVO findByPrimaryKey(String ntype_no) {
		NewsTypeVO newstypevo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, ntype_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				newstypevo = new NewsTypeVO();
				newstypevo.setNtype_no(rs.getString("NType_No"));
				newstypevo.setNews_type(rs.getString("News_Type"));
			}

			System.out.println("NewsType_findByPrimaryKey");

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException re) {
					re.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

		return newstypevo;
	}

	// 查全部
	@Override
	public List<NewsTypeVO> getAll() {
		List<NewsTypeVO> newstypelist = new ArrayList<NewsTypeVO>();
		NewsTypeVO newstypevo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				newstypevo = new NewsTypeVO();
				newstypevo.setNtype_no(rs.getString("NType_No"));
				newstypevo.setNews_type(rs.getString("News_Type"));
				newstypelist.add(newstypevo);
			}

			ResultSet rs2 = pstmt.executeQuery("SELECT COUNT(*) AS count FROM News_Type");
			rs2.next();
			int len = rs2.getInt("count");
			System.out.println("NewsType_findAll: " + len + "筆[SQL指令]");
			rs2.close();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException re) {
					re.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return newstypelist;
	}

	// 查找對應的新聞種類編號
	@Override
	public Set<NewsVO> getNewsByNtype_no(String ntype_no) {
		Set<NewsVO> set = new LinkedHashSet<NewsVO>();
		NewsVO newsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_News_ByNtype_No_STMT);
			pstmt.setString(1, ntype_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				newsVO = new NewsVO();
				newsVO.setNews_no(rs.getString("News_No"));
				newsVO.setNews_title(rs.getString("News_Title"));
				newsVO.setNews_content(rs.getString("News_Content"));
				newsVO.setNews_photo(rs.getBytes("News_Photo"));
				newsVO.setNews_state(rs.getString("News_State"));
				newsVO.setNews_date(rs.getDate("News_Date"));
				newsVO.setEmp_no(rs.getString("EMP_NO"));
				newsVO.setNtype_no(rs.getString("NType_No"));
				set.add(newsVO);
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return set;
	}

}