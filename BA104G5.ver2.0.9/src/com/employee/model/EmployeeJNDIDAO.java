package com.employee.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class EmployeeJNDIDAO implements EmployeeDAO_interface {

	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA104G5");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT = "INSERT INTO EMPLOYEE(EMP_NO, EMP_ID, EMP_PSW, EMP_NAME) "
			+ "VALUES('EM'||(LPAD(to_char(EMP_SEQ.NEXTVAL),8,'0')), ?, ?, ?)";
	private static final String UPDATE = "UPDATE EMPLOYEE set EMP_ID=?, EMP_PSW=?, EMP_NAME=?,Emp_photo=?, EMP_STATE=?"
			+ "where emp_no = ?";
	private static final String DELETE = "DELETE FROM EMPLOYEE where EMP_NO = ?";
	private static final String FIND_BY_PK = "SELECT * FROM EMPLOYEE WHERE EMP_NO = ?";
	private static final String FIND_BY_ID = "SELECT * FROM EMPLOYEE WHERE EMP_ID = ?";
	private static final String GET_ALL = "SELECT * FROM EMPLOYEE";
	private static final String GET_ID_COUNT = "SELECT COUNT(*) FROM EMPLOYEE WHERE EMP_ID = ?";

	@Override
	public void insert(EmployeeVO EmployeeVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT);

			pstmt.setString(1, EmployeeVO.getEmp_id());
			pstmt.setString(2, EmployeeVO.getEmp_psw());
			pstmt.setString(3, EmployeeVO.getEmp_name());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		} // end finally

	}

	@Override
	public void update(EmployeeVO EmployeeVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, EmployeeVO.getEmp_id());
			pstmt.setString(2, EmployeeVO.getEmp_psw());
			pstmt.setString(3, EmployeeVO.getEmp_name());
			pstmt.setBytes(4, EmployeeVO.getEmp_photo());
			pstmt.setString(5, EmployeeVO.getEmp_state());
			pstmt.setString(6, EmployeeVO.getEmp_no());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		} // end finally

	}

	@Override
	public void delete(String emp_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, emp_no);

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());

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
		} // end finally

	}

	@Override
	public EmployeeVO findByPK(String emp_no) {
		EmployeeVO employeeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_PK);

			pstmt.setString(1, emp_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				employeeVO = new EmployeeVO();
				employeeVO.setEmp_no(rs.getString("emp_no"));
				employeeVO.setEmp_id(rs.getString("emp_id"));
				employeeVO.setEmp_psw(rs.getString("emp_psw"));
				employeeVO.setEmp_name(rs.getString("emp_name"));
				employeeVO.setEmp_lstlog(rs.getTimestamp("emp_lstlog"));
				employeeVO.setEmp_badlog(rs.getTimestamp("emp_badlog"));
				employeeVO.setEmp_badlogtry(rs.getInt("emp_badlogtry"));
				employeeVO.setEmp_photo(rs.getBytes("emp_photo"));
				employeeVO.setEmp_state(rs.getString("emp_state"));
				employeeVO.setEmp_newdate(rs.getTimestamp("emp_newdate"));
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());

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
		} // end finally

		return employeeVO;
	}

	@Override
	public EmployeeVO findByID(String emp_id) {
		EmployeeVO employeeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_ID);

			pstmt.setString(1, emp_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				employeeVO = new EmployeeVO();
				employeeVO.setEmp_no(rs.getString("emp_no"));
				employeeVO.setEmp_id(rs.getString("emp_id"));
				employeeVO.setEmp_psw(rs.getString("emp_psw"));
				employeeVO.setEmp_name(rs.getString("emp_name"));
				employeeVO.setEmp_lstlog(rs.getTimestamp("emp_lstlog"));
				employeeVO.setEmp_badlog(rs.getTimestamp("emp_badlog"));
				employeeVO.setEmp_badlogtry(rs.getInt("emp_badlogtry"));
				employeeVO.setEmp_photo(rs.getBytes("emp_photo"));
				employeeVO.setEmp_state(rs.getString("emp_state"));
				employeeVO.setEmp_newdate(rs.getTimestamp("emp_newdate"));
			}

		} // Handle any SQL errors
		catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());

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
		} // end finally

		return employeeVO;

	}

	@Override
	public List<EmployeeVO> getAll() {

		List<EmployeeVO> list = new ArrayList<EmployeeVO>();
		EmployeeVO employeeVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// EmployeeVO �]�٬� Domain objects
				employeeVO = new EmployeeVO();
				employeeVO.setEmp_no(rs.getString("emp_no"));
				employeeVO.setEmp_id(rs.getString("emp_id"));
				employeeVO.setEmp_psw(rs.getString("emp_psw"));
				employeeVO.setEmp_name(rs.getString("emp_name"));
				employeeVO.setEmp_lstlog(rs.getTimestamp("emp_lstlog"));
				employeeVO.setEmp_badlog(rs.getTimestamp("emp_badlog"));
				employeeVO.setEmp_badlogtry(rs.getInt("emp_badlogtry"));
				employeeVO.setEmp_photo(rs.getBytes("emp_photo"));
				employeeVO.setEmp_state(rs.getString("emp_state"));
				employeeVO.setEmp_newdate(rs.getTimestamp("emp_newdate"));

				list.add(employeeVO); // Store the row in the list
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		} // end finally

		return list;
	}

	@Override
	public Integer getCountByID(String emp_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Integer idCount = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ID_COUNT);

			pstmt.setString(1, emp_id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				idCount = new Integer(rs.getInt(1));
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

		return idCount;
	}

}
