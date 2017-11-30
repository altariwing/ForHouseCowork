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

import com.news.model.NewsVO;

public class NewsTypeJDBCDAO implements NewsTypeDAO_interface {

	private static final String driver = "oracle.jdbc.driver.OracleDriver";
	private static final String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String userid = "BA104G5";
	private static final String password = "ba104g5";

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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(INSERT_STMT);

			con.setAutoCommit(false);

			pstmt.setString(1, newstypeVO.getNtype_no());
			pstmt.setString(2, newstypeVO.getNews_type());

			int rowCount = pstmt.executeUpdate();
			con.commit();
			con.setAutoCommit(true);
			System.out.println("NewsType_INSERT_STMT: " + rowCount + "筆");

		} catch (ClassNotFoundException ce) {
			throw new RuntimeException("Couldn't load database driver. " + ce.getMessage());
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, ntype_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				newstypevo = new NewsTypeVO();
				newstypevo.setNtype_no(rs.getString("NType_No"));
				newstypevo.setNews_type(rs.getString("News_Type"));
			}

			System.out.println("NewsType_findByPrimaryKey");

		} catch (ClassNotFoundException ce) {
			throw new RuntimeException("Couldn't load database driver. " + ce.getMessage());
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
		List<NewsTypeVO> newstypeList = new ArrayList<NewsTypeVO>();
		NewsTypeVO newstypevo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(GET_ALL_STMT);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				newstypevo = new NewsTypeVO();
				newstypevo.setNtype_no(rs.getString("NType_No"));
				newstypevo.setNews_type(rs.getString("News_Type"));
				newstypeList.add(newstypevo);
			}

			ResultSet rs2 = pstmt.executeQuery("SELECT COUNT(*) AS count FROM News_Type");
			rs2.next();
			int len = rs2.getInt("count");
			System.out.println("NewsType_findAll: " + len + "筆[SQL指令]");
			rs2.close();

		} catch (ClassNotFoundException ce) {
			throw new RuntimeException("Couldn't load database driver. " + ce.getMessage());
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

		return newstypeList;
	}
	
	//查找對應的新聞種類編號
	@Override
	public Set<NewsVO> getNewsByNtype_no(String ntype_no) {
		Set<NewsVO> set = new LinkedHashSet<NewsVO>();
		NewsVO newsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
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

		} catch (ClassNotFoundException ce) {
			throw new RuntimeException("Couldn't load database driver. " + ce.getMessage());
		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
			se.printStackTrace();
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

	// 測試
	public static void main(String[] args) {
		NewsTypeJDBCDAO dao = new NewsTypeJDBCDAO();

		// 新增
		// NewsTypeVO vo1 = new NewsTypeVO();
		// vo1.setNtype_no("PP101");
		// vo1.setN_type("輕鬆小品");
		// dao.insert(vo1);
		// System.out.println("------------------------------------");

		// 查單一
		NewsTypeVO vo2 = dao.findByPrimaryKey("HN001");
		System.out.println(vo2.getNtype_no() + ",");
		System.out.println(vo2.getNews_type() + ",");
		System.out.println("------------------------------------");

		// 查全部
//		List<NewsTypeVO> vo3 = dao.getAll();
//		for (NewsTypeVO anewstype : vo3) {
//			System.out.println(anewstype.getNtype_no() + ",");
//			System.out.println(anewstype.getNews_type() + ",");
//			System.out.println();
//		}
//		System.out.println("---------------------------------------");
		//專查外來
//		Set<NewsVO> set = dao.getNewsByNtype_no("LK002");
//		for(NewsVO anews: set){
//			System.out.println(anews.getNews_no() + ",");
//			System.out.println(anews.getNews_title() + ",");
//			System.out.println(anews.getNews_content() + ",");
//			System.out.println(anews.getNews_photo() + ",");
//			System.out.println(anews.getNews_state() + ",");
//			System.out.println(anews.getNews_date() + ",");
//			System.out.println(anews.getEmp_no() + ",");
//			System.out.println(anews.getNtype_no());
//			System.out.println();
//		}
		
		
	}

}
