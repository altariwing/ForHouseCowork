package com.news.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class NewsDAO implements NewsDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA104G5");
		} catch (NamingException ne) {
			ne.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO News (News_No, NType_No, News_Title, News_Content, News_Photo, News_State, EMP_NO) VALUES('NS'||LPAD(TO_CHAR(seq_news.NEXTVAL), 8, '0'), ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_STMT = "UPDATE News SET NType_No=?, News_Title=?, News_Content=?, News_Photo=?, News_State=?, EMP_NO=? WHERE News_No = ?";
	private static final String GET_ONE_STMT = "SELECT News_No, NType_No, News_Title, News_Content, News_Photo, News_State, to_char(News_Date, 'yyyy-mm-dd')News_Date, EMP_NO FROM News WHERE News_No = ?";
	private static final String GET_ALL_STMT = "SELECT News_No, NType_No, News_Title, News_Content, News_Photo, News_State, to_char(News_Date, 'yyyy-mm-dd')News_Date, EMP_NO FROM News ORDER BY News_No";
	private static final String GET_ALL_BY_TIME = "SELECT * FROM News ORDER BY News_Date DESC";// 查詢全部照時間排序

	// 新增
	@Override
	public void insert(NewsVO newsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			con.setAutoCommit(false);

			pstmt.setString(1, newsVO.getNtype_no());
			pstmt.setString(2, newsVO.getNews_title());
			pstmt.setString(3, newsVO.getNews_content());
			pstmt.setBytes(4, newsVO.getNews_photo());
			pstmt.setString(5, newsVO.getNews_state());
			pstmt.setString(6, newsVO.getEmp_no());

			pstmt.executeUpdate();
			con.commit();
			con.setAutoCommit(true);

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

	// 修改
	@Override
	public void update(NewsVO newsVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);

			con.setAutoCommit(false);

			pstmt.setString(1, newsVO.getNtype_no());
			pstmt.setString(2, newsVO.getNews_title());
			pstmt.setString(3, newsVO.getNews_content());
			pstmt.setBytes(4, newsVO.getNews_photo());
			pstmt.setString(5, newsVO.getNews_state());
			pstmt.setString(6, newsVO.getEmp_no());
			pstmt.setString(7, newsVO.getNews_no());

			pstmt.executeUpdate();
			con.commit();
			con.setAutoCommit(true);

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

	// 查單一
	@Override
	public NewsVO findByPrimaryKey(String news_no) {

		NewsVO newsvo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, news_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				newsvo = new NewsVO();
				newsvo.setNews_no(rs.getString("News_No"));
				newsvo.setNtype_no(rs.getString("NType_No"));
				newsvo.setNews_title(rs.getString("News_Title"));
				newsvo.setNews_content(rs.getString("News_Content"));
				newsvo.setNews_photo(rs.getBytes("News_Photo"));
				newsvo.setNews_state(rs.getString("News_State"));
				newsvo.setNews_date(rs.getDate("News_Date"));
				newsvo.setEmp_no(rs.getString("EMP_No"));
			}

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
		return newsvo;
	}

	// 查全部
	@Override
	public List<NewsVO> getAll() {
		List<NewsVO> newsList = new ArrayList<NewsVO>();
		NewsVO newsvo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				newsvo = new NewsVO();
				newsvo.setNews_no(rs.getString("News_No"));
				newsvo.setNtype_no(rs.getString("NType_No"));
				newsvo.setNews_title(rs.getString("News_Title"));
				newsvo.setNews_content(rs.getString("News_Content"));
				newsvo.setNews_photo(rs.getBytes("News_Photo"));
				newsvo.setNews_state(rs.getString("News_State"));
				newsvo.setNews_date(rs.getDate("News_Date"));
				newsvo.setEmp_no(rs.getString("EMP_No"));
				newsList.add(newsvo);
			}

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
		return newsList;
	}

	// 搜尋條件依照發布時間
	@Override
	public List<NewsVO> getAllByTime() {

		List<NewsVO> newsList = new ArrayList<NewsVO>();
		NewsVO newsvo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_BY_TIME);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				newsvo = new NewsVO();
				newsvo.setNews_no(rs.getString("News_No"));
				newsvo.setNtype_no(rs.getString("NType_No"));
				newsvo.setNews_title(rs.getString("News_Title"));
				newsvo.setNews_content(rs.getString("News_Content"));
				newsvo.setNews_photo(rs.getBytes("News_Photo"));
				newsvo.setNews_state(rs.getString("News_State"));
				newsvo.setNews_date(rs.getDate("News_Date"));
				newsvo.setEmp_no(rs.getString("EMP_No"));
				newsList.add(newsvo);
			}

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
		return newsList;
	}// 搜尋條件依照發布時間結束
}
