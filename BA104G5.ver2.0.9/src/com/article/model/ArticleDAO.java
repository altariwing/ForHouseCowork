package com.article.model;

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

public class ArticleDAO implements ArticleDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA104G5");
		} catch (NamingException ne) {
			ne.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO Article (Article_No, Rtr_No, Article_body, Post_date, Article_State) VALUES('ART'||LPAD(TO_CHAR(SEQ_ART.NEXTVAL), 7, '0'), ?, ?, ?, ?)";
	private static final String UPDATE_STMT = "UPDATE Article SET Rtr_No=?, Article_body=?, Post_date=?, Article_State=? WHERE Article_No = ?";
	private static final String GET_ONE_STMT = "SELECT Article_No, Rtr_No, Article_body, to_char(Post_Date, 'yyyy-mm-dd hh:mi:ss')Post_Date, Update_date, Article_State FROM Article WHERE Article_No = ?";
	private static final String GET_ALL_STMT = "SELECT Article_No, Rtr_No, Article_body, to_char(Post_Date, 'yyyy-mm-dd hh:mi:ss')Post_Date, Update_date, Article_State FROM Article ORDER BY Article_No";

	private static final String GET_ALL_BY_TIME = "SELECT * FROM Article ORDER BY Post_Date DESC";// 查詢發布時間排序

	// 新增
	@Override
	public void insert(ArticleVO articleVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			con.setAutoCommit(false);

			pstmt.setString(1, articleVO.getRtr_no());
			pstmt.setString(2, articleVO.getArticle_body());
			pstmt.setTimestamp(3, articleVO.getPost_date());
			pstmt.setString(4, articleVO.getArticle_state());

			pstmt.executeUpdate();
			con.commit();
			con.setAutoCommit(true);

		} catch (SQLException se) {
			se.printStackTrace();
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
	}// 新增結束

	// 修改
	@Override
	public void update(ArticleVO articleVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);

			con.setAutoCommit(false);

			pstmt.setString(1, articleVO.getRtr_no());
			pstmt.setString(2, articleVO.getArticle_body());
			pstmt.setTimestamp(3, articleVO.getPost_date());
			pstmt.setString(4, articleVO.getArticle_state());
			pstmt.setString(5, articleVO.getArticle_no());

			pstmt.executeUpdate();
			con.commit();
			con.setAutoCommit(true);

		} catch (SQLException se) {
			se.printStackTrace();
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
	}// 修改結束

	// 查單一
	@Override
	public ArticleVO findByPrimaryKey(String article_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArticleVO articlevo = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, article_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				articlevo = new ArticleVO();
				articlevo.setArticle_no(rs.getString("Article_no"));
				articlevo.setRtr_no(rs.getString("Rtr_no"));
				articlevo.setPost_date(rs.getTimestamp("Post_date"));
				articlevo.setArticle_body(rs.getString("Article_body"));
				articlevo.setArticle_state(rs.getString("Article_State"));
				articlevo.setUpdate_date(rs.getDate("Update_Date"));
			}

		} catch (SQLException se) {
			se.printStackTrace();
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
		return articlevo;
	}// 查單一結束

	// 查全部結束
	@Override
	public List<ArticleVO> getAll() {
		List<ArticleVO> list = new ArrayList<ArticleVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArticleVO articlevo = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				articlevo = new ArticleVO();
				articlevo.setArticle_no(rs.getString("Article_no"));
				articlevo.setRtr_no(rs.getString("Rtr_no"));
				articlevo.setPost_date(rs.getTimestamp("Post_date"));
				articlevo.setArticle_body(rs.getString("Article_body"));
				articlevo.setArticle_state(rs.getString("Article_State"));
				articlevo.setUpdate_date(rs.getDate("Update_Date"));
				list.add(articlevo);
			}

		} catch (SQLException se) {
			se.printStackTrace();
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
		return list;
	}// 查全部結束

	// 發布時間
	@Override
	public List<ArticleVO> getAllByTime() {
		List<ArticleVO> list = new ArrayList<ArticleVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArticleVO articlevo = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_BY_TIME);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				articlevo = new ArticleVO();
				articlevo.setArticle_no(rs.getString("Article_no"));
				articlevo.setRtr_no(rs.getString("Rtr_no"));
				articlevo.setPost_date(rs.getTimestamp("Post_date"));
				articlevo.setArticle_body(rs.getString("Article_body"));
				articlevo.setArticle_state(rs.getString("Article_State"));
				articlevo.setUpdate_date(rs.getDate("Update_Date"));
				list.add(articlevo);
			}

		} catch (SQLException se) {
			se.printStackTrace();
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
		return list;
	}// 發布時間結束
}