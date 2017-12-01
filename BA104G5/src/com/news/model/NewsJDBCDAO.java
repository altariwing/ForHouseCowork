package com.news.model;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.realtor.model.RealtorVO;

public class NewsJDBCDAO implements NewsDAO_interface {
	private static final String driver = "oracle.jdbc.driver.OracleDriver";
	private static final String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String userid = "BA104G5";
	private static final String password = "ba104g5";

	private static final String INSERT_STMT = "INSERT INTO News (News_No, NType_No, News_Title, News_Content, News_Photo, News_State, EMP_NO) VALUES('NS'||LPAD(TO_CHAR(seq_news.NEXTVAL), 8, '0'), ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_STMT = "UPDATE News SET NType_No=?, News_Title=?, News_Content=?, News_Photo=?, News_State=?, EMP_NO=? WHERE News_No = ?";
	private static final String GET_ONE_STMT = "SELECT News_No, NType_No, News_Title, News_Content, News_Photo, News_State, to_char(News_Date, 'yyyy-mm-dd')News_Date, EMP_NO FROM News WHERE News_No = ?";
	private static final String GET_ALL_STMT = "SELECT News_No, NType_No, News_Title, News_Content, News_Photo, News_State, to_char(News_Date, 'yyyy-mm-dd')News_Date, EMP_NO FROM News WHERE News_State='公告中' ORDER BY News_No";

	// 依照新增時間做排序(只取前三筆)
	private static final String GET_ALL_BY_TIME = "SELECT * FROM (SELECT * FROM News ORDER BY News_Date DESC) WHERE rownum<=3 and News_State='公告中'";

	// 專門塞照片內文用
	private static final String UPDATE_FOR_PHOTO = "UPDATE News SET News_PHOTO=?, News_content=? WHERE News_NO=?";

	// 新增
	@Override
	public void insert(NewsVO newsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
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
	}// 新增結束

	// 修改
	@Override
	public void update(NewsVO newsVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
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

	// 查單一
	@Override
	public NewsVO findByPrimaryKey(String news_no) {

		NewsVO newsvo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
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
			System.out.println("News_findByPrimaryKey");

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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
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
		return newsList;
	}// 搜尋條件依照發布時間結束

	// 專門塞照片內文用
	@Override
	public void updatePhoto(NewsVO newsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(UPDATE_FOR_PHOTO);

			con.setAutoCommit(false);

			pstmt.setBytes(1, newsVO.getNews_photo());
			pstmt.setString(2, newsVO.getNews_content());
			pstmt.setString(3, newsVO.getNews_no());

			pstmt.executeUpdate();
			con.commit();
			con.setAutoCommit(true);

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

	}// 專門塞照片內文用結束

	// 測試
	public static void main(String[] args) throws IOException {

		NewsJDBCDAO dao = new NewsJDBCDAO();

		// 修改照片、內容
		NewsVO vo = new NewsVO();
		FileInputStream in = null;
		String reader = null;
		byte[] newspic = null;
		String news_no = null;
		for (int i = 1; i <= 12; i++) {
			if (i <= 9) {
				in = new FileInputStream("WebContent/images/newsphoto/news" + i + ".jpg");
				reader = getLongString("WebContent/txt/news_txt/news" + i + ".txt");
				news_no = "NS0000000" + i;
				newspic = new byte[in.available()];
				in.read(newspic);
			} else {
				in = new FileInputStream("WebContent/images/newsphoto/news" + i + ".jpg");
				reader = getLongString("WebContent/txt/news_txt/news" + i + ".txt");
				news_no = "NS000000" + i;
				newspic = new byte[in.available()];
				in.read(newspic);

			}
			vo.setNews_photo(newspic);
			vo.setNews_content(reader);
			vo.setNews_no(news_no);
			dao.updatePhoto(vo);
			in.close();
		}

		// 修改
		// NewsVO vo2 = new NewsVO();
		// vo2.setNtype_no("HN001");
		// vo2.setNews_title("cooper");
		// vo2.setNews_content("cooper");
		// byte[] pic2 =
		// getPictureByteArray("WebContent/images/newsphoto/HN001.jpg");
		// vo2.setNews_photo(pic2);
		// vo2.setNews_state("公告中");
		// vo2.setEmp_no("EM00000001");
		// vo2.setNews_no("NS00002000");
		// dao.update(vo2);
		// System.out.println("---------------------------------");

		// 查單一
		// NewsVO vo3 = dao.findByPrimaryKey("NS00002000");
		// System.out.println(vo3.getNews_no());
		// System.out.println(vo3.getNtype_no());
		// System.out.println(vo3.getNews_title());
		// System.out.println(vo3.getNews_content());
		// System.out.println(vo3.getNews_photo());
		// System.out.println(vo3.getNews_state());
		// System.out.println(vo3.getEmp_no());
		// System.out.println("---------------------------------");

		// 查全部
		// List<NewsVO> newsList = dao.getAll();
		// for (NewsVO anews : newsList) {
		// System.out.println(anews.getNews_no());
		// System.out.println(anews.getNtype_no());
		// System.out.println(anews.getNews_title());
		// System.out.println(anews.getNews_content());
		// System.out.println(anews.getNews_photo());
		// System.out.println(anews.getNews_state());
		// System.out.println(anews.getNews_date());
		// System.out.println(anews.getEmp_no());
		// System.out.println();
		// }

		// 查全部依時間排序
//		List<NewsVO> newsList = dao.getAllByTime();
//		for (NewsVO anews : newsList) {
//			System.out.println(anews.getNews_no());
//			System.out.println(anews.getNtype_no());
//			System.out.println(anews.getNews_title());
//			System.out.println(anews.getNews_content());
//			System.out.println(anews.getNews_photo());
//			System.out.println(anews.getNews_state());
//			System.out.println(anews.getNews_date());
//			System.out.println(anews.getEmp_no());
//			System.out.println();
//		}
	}// 測試結束
 
	// 上傳照片的方法
	public static byte[] getPictureByteArray(String path) throws IOException {
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[8192];
		int i;
		while ((i = fis.read(buffer)) != -1) {
			baos.write(buffer, 0, i);
		}
		baos.close();
		fis.close();
		return baos.toByteArray();
	}

	// 使用String 上傳用
	public static String getLongString(String path) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(path));
		StringBuilder sb = new StringBuilder(); // StringBuffer is thread-safe!
		String str;
		while ((str = br.readLine()) != null) {
			sb.append(str);
			sb.append("\n");
		}
		br.close();

		return sb.toString();
	}

}
