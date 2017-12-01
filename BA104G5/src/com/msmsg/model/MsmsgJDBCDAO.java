package com.msmsg.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MsmsgJDBCDAO implements MsmsgDAO_interface{
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "BA104G5";
	String passwd = "ba104g5";

	private static final String INSERT_STMT = 
		"INSERT INTO msmsg (mem_no,slr_no,ms_msg) VALUES ( ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT mem_no,slr_no,ms_msg,ms_time FROM msmsg order by mem_no";
	private static final String GET_ONE_STMT = 
		"SELECT mem_no,slr_no,ms_msg,ms_time FROM msmsg where mem_no = ? and slr_no = ?";
//	private static final String DELETE = 
//		"DELETE FROM msmsg where mem_no = ? and slr_no = ?";
	private static final String UPDATE = 
		"UPDATE msmsg set ms_msg=?,ms_time=CURRENT_TIMESTAMP where mem_no = ? and slr_no = ?";

	
	
	
	
	@Override
	public void insert(MsmsgVO msmsgVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, msmsgVO.getMem_no());
			pstmt.setString(2, msmsgVO.getSlr_no());
			pstmt.setString(3, msmsgVO.getMs_msg());
			
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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

	@Override
	public void update(MsmsgVO msmsgVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, msmsgVO.getMs_msg());
			pstmt.setString(2, msmsgVO.getMem_no());
			pstmt.setString(3, msmsgVO.getSlr_no());
			
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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

	@Override
	public void delete(String mem_no, String slr_no) {

		
	}

	@Override
	public MsmsgVO findByPrimaryKey(String mem_no, String slr_no) {

		MsmsgVO msmsgVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1,mem_no);
			pstmt.setString(2, slr_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// msmsgVo 也稱為 Domain objects
				msmsgVO = new MsmsgVO();
				msmsgVO.setMem_no(rs.getString("mem_no"));
				msmsgVO.setSlr_no(rs.getString("slr_no"));
				msmsgVO.setMs_msg(rs.getString("ms_msg"));
				msmsgVO.setMs_time(rs.getTimestamp("ms_time"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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
		return msmsgVO;
	}

	@Override
	public List<MsmsgVO> getAll() {
		List<MsmsgVO> list = new ArrayList<MsmsgVO>();
		MsmsgVO msmsgVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// msmsgVO 也稱為 Domain objects
				msmsgVO = new MsmsgVO();
				msmsgVO.setMem_no(rs.getString("mem_no"));
				msmsgVO.setSlr_no(rs.getString("slr_no"));
				msmsgVO.setMs_msg(rs.getString("ms_msg"));
				msmsgVO.setMs_time(rs.getTimestamp("ms_time"));
	
				list.add(msmsgVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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
		return list;
	}
	public static void main(String[] args) {

		MsmsgJDBCDAO dao = new MsmsgJDBCDAO();
		SimpleDateFormat sdfromat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		// 新增
//		MsmsgVO msmsgVO1 = new MsmsgVO();
//		msmsgVO1.setMem_no("MB00000001");
//		msmsgVO1.setSlr_no("SL00000002");
//		msmsgVO1.setMs_msg("運用到一些到現在還是搞不太懂的socket, 以及多執行緒 Multi-Thread, 和client與server的概念,  我的寫法是, "
//				+ "讓使用者可以設定, (1)發送對象的ip位置, (2)發送對象的port號, (3)所要接收的port號, "
//				+ "要注意使用者A發送的port號必須和使用者B接收的port號一致, 所以呢使用者B發送的port號必須和使用者A接收的port號一致");
//		
//		dao.insert(msmsgVO1);
//		System.out.println("新增成功");
		
		// 修改
//		MsmsgVO msmsgVO2 = new MsmsgVO();
//		msmsgVO2.setMem_no("MB00000001");
//		msmsgVO2.setSlr_no("SL00000002");
//		msmsgVO2.setMs_msg("每 個請求來到Web容器，Web容器會為其分配一條執行緒來專門負責該請求，直到回應完成前，該執行緒都不會被釋放回容器。執行緒會耗用系統資源，若有些請 求需要長時間處理（例如長時間運算、等待某個資源），就會長時間佔用執行緒，若這類的請求很多，許多執行緒都被長時間佔用，對於系統就會是個效能負擔，甚 至造成應用程式的效能瓶頸。"
//				+ "基本上一些需長時間處理的請求，通常客戶端也較不在乎請求後要有立即的回應，若可以，讓這類請求先釋放容器分配給該請求的執行緒，讓容器可以有機會將執行緒資源分配給其它的請求，可以減輕系統負擔。原先釋放了容器所分配執行緒的請求，其回應將被延後，直到處理完成（例如長時間運算完成、所需資源已獲得）再行對客戶端的回應。"
//				+ "在Servlet 3.0中，在ServletRequest上提供了startAsync()方法："
//				+ "AsyncContext startAsync() throws java.lang.IllegalStateException;"
//				+ "AsyncContext startAsync(ServletRequest servletRequest,ServletResponse servletResponse)"
//				+ "throws java.lang.IllegalStateException "
//				+ "這兩個方法都會傳回AsyncContext介面的實作物件，前者會直接利用原有的請求與回應物件來建立AsyncContext，後者可以讓你傳入自己建立的請求、回應包裹物件。在呼叫了startAsync()方法取得AsyncContext物件之後，這次的回應會被延後，並釋放容器所分配的執行緒。"
//				+ "你可以透過AsyncContext的getRequest()、getResponse()方法取得請求、回應物件，此次對客戶端的回應將暫緩至呼叫AsyncContext的complete()方法或dispatch()為止，前者表示回應完成，後者表示將回應調派給指定的URL。"
//				+ "若要能呼叫ServletRequest的startAsync()使用AsyncContext，你的Servlet必須能支援非同步處理，如果使用@WebServlet來標註，則可以設定其asyncSupported為true。例如："
//				+ "@WebServlet(urlPatterns = '/some.do', asyncSupported = true)"
//						+ "public class AsyncServlet extends HttpServlet {"
//						+ "...如果使用web.xml設定Servlet，則可以設定<async-supported>標籤為true：..."
//						+ "<servlet> "
//						+ "    <servlet-name>AsyncServlet</servlet-name> "
//						+ "    <servlet-class>cc.openhome.AsyncServlet</servlet-class> "
//						+ "    <async-supported>true</async-supported> "
//						+ "</servlet> ..."
//						+ "如果Servlet將會非同步處理，若其前端有過濾器，則過濾器亦需標示其支援非同步處理，如果使用@WebFilter，同樣是可以設定其asyncSupported為true。例如："
//						+ "@WebFilter(urlPatterns = '/some.do', asyncSupported = true) "
//								+ "public class AsyncFilter implements Filter{..."
//								+ "如果使用web.xml設定過濾器，則可以設定<async-supported>標籤為true：..."
//								+ "<filter> "
//								+ "    <filter-name>AsyncFilter</filter-name> "
//								+ "    <filter-class>cc.openhome.AsyncFilter</filter-class> "
//								+ "    <async-supported>true</async-supported> "
//								+ "</filter> ..."
//								+ "底下示範一個非同步處理的例子，對於進來的請求，Servlet會取得其AsyncContext，並釋放容器所分配的執行緒，回應被延後，對於這些被延後回應的請求，建立一個Runnable的物件，並將其排入一個執行緒池（Thread pool），執行緒池的執行緒數量是固定的，讓這些必須長時間處理的請求，在這些有限數量的執行緒中完成，而不用每次請求都佔用容器所分配的執行緒。 ");
//		dao.update(msmsgVO2);
//		System.out.println("更新成功");
		// 刪除
//		dao.delete(" ");

		// 查詢		
//		MsmsgVO msmsgVO3 = dao.findByPrimaryKey("MB00000001" , "SL00000002");
//		System.out.println(msmsgVO3.getMem_no() + ",");
//		System.out.println(msmsgVO3.getSlr_no() + ",");
//		System.out.println(msmsgVO3.getMs_msg() + ",");
//		System.out.println(sdfromat.format(msmsgVO3.getMs_time()) + ",");
//		System.out.println("---------------------");
//
//		// 查詢
		List<MsmsgVO> list = dao.getAll();
		for (MsmsgVO aMsmsg : list) {
			System.out.println(aMsmsg.getMem_no() + ",");
			System.out.println(aMsmsg.getSlr_no() + ",");
			System.out.println(aMsmsg.getMs_msg() + ",");
			System.out.println(sdfromat.format(aMsmsg.getMs_time()) + ",");
			System.out.println("---------------------");
			System.out.println();
		}
	}
	
}
