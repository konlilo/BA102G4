package com.Course_time.model;

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

import com.Course.model.CourseVO;
import com.Course_list.model.Course_listDAO;
import com.place.model.PlaceVO;

public class Course_timeDAO implements Course_timeDAO_interface {
	private static DataSource ds = null;
	private static final String INSERT_STMT = "INSERT INTO course_time (ct_no,crs_no,p_no,crs_date,deadline,crs_time,price,limit,class_num,status) VALUES (? || lpad(course_time_sq.NEXTVAL,6,'0'), ?, ?, ?, ?, ?, ?, ?, ?, default)";
	private static final String GET_ALL_STMT = "SELECT * FROM course_time ct join course c ON ct.crs_no = c.crs_no left outer join place p  ON ct.p_no = p.p_no where c.c_acc = ? AND ct.status = 1";
	private static final String GET_ALL_OPEN_STMT = "SELECT * FROM course_time ct join course c ON ct.crs_no = c.crs_no left outer join place p  ON ct.p_no = p.p_no where c.c_acc = ? AND ct.status = 2 AND ct.deadline < sysdate AND ct.crs_date > sysdate";
	private static final String GET_ALL_RECORD_STMT = "SELECT * FROM course_time ct join course c ON ct.crs_no = c.crs_no left outer join place p  ON ct.p_no = p.p_no where c.c_acc = ? AND ct.status = 2 AND ct.crs_date < sysdate";
	private static final String GET_ONE_STMT = "SELECT * FROM course_time  where ct_no = ? AND status = 1";
	private static final String DELETE = "DELETE FROM course_time  where ct_no = ?";
	private static final String UPDATE = "UPDATE course_time  set p_no=?, crs_date=?, deadline=?, crs_time=?, price=? where ct_no = ?";

	
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA102G4DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void insert(Course_timeVO course_timeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, course_timeVO.getCourseVO().getCategory());
			pstmt.setString(2, course_timeVO.getCourseVO().getCrs_no());
			pstmt.setString(3, course_timeVO.getP_no());
			pstmt.setDate(4, course_timeVO.getCrs_date());
			pstmt.setDate(5, course_timeVO.getDeadline());
			pstmt.setInt(6, course_timeVO.getCrs_time());
			pstmt.setString(7, course_timeVO.getPrice());
			pstmt.setString(8, course_timeVO.getLimit());
			pstmt.setString(9, course_timeVO.getClass_num());


			pstmt.executeUpdate();

			// Handle any driver errors
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
		}

	}

	@Override
	public void update(Course_timeVO course_timeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, course_timeVO.getP_no());
			pstmt.setDate(2, course_timeVO.getCrs_date());
			pstmt.setDate(3, course_timeVO.getDeadline());
			pstmt.setInt(4, course_timeVO.getCrs_time());
			pstmt.setString(5, course_timeVO.getPrice());
			pstmt.setString(6, course_timeVO.getCt_no());
			
			pstmt.executeUpdate();

			// Handle any driver errors
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
		}

	}

	@Override
	public void delete(String ct_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, ct_no);

			pstmt.executeUpdate();

			// Handle any driver errors
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
		}

	}

	@Override
	public Course_timeVO findByPK(String ct_no) {
		Course_timeVO course_timeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, ct_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				course_timeVO = new Course_timeVO();
				course_timeVO.setCt_no(rs.getString("ct_no"));
				course_timeVO.setCrs_no(rs.getString("crs_no"));
				course_timeVO.setP_no(rs.getString("p_no"));
				course_timeVO.setCrs_date(rs.getDate("crs_date"));
				course_timeVO.setDeadline(rs.getDate("deadline"));
				course_timeVO.setCrs_time(rs.getInt("crs_time"));
				course_timeVO.setPrice(rs.getString("price"));
				course_timeVO.setLimit(rs.getString("limit"));
				course_timeVO.setClass_num(rs.getString("class_num"));
				course_timeVO.setStatus(rs.getInt("status"));
			}

			// Handle any driver errors
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
		}
		return course_timeVO;
	}

	@Override
	public List<Course_timeVO> getAll(String c_acc) {
		List<Course_timeVO> list = new ArrayList<Course_timeVO>();
		Course_timeVO course_timeVO = null;
		CourseVO courseVO = null;
		PlaceVO placeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Course_listDAO course_listDAO = new Course_listDAO();

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			pstmt.setString(1, c_acc);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				course_timeVO = new Course_timeVO();
				courseVO = new CourseVO();
				placeVO = new PlaceVO();
				course_timeVO.setCt_no(rs.getString("ct_no"));
				course_timeVO.setCrs_no(rs.getString("crs_no"));
				course_timeVO.setP_no(rs.getString("p_no"));
				course_timeVO.setCrs_date(rs.getDate("crs_date"));
				course_timeVO.setDeadline(rs.getDate("deadline"));
				course_timeVO.setCrs_time(rs.getInt("crs_time"));
				course_timeVO.setPrice(rs.getString("price"));
				course_timeVO.setLimit(rs.getString("limit"));
				course_timeVO.setClass_num(rs.getString("class_num"));
				course_timeVO.setStatus(rs.getInt("status"));
				courseVO.setCrs_name(rs.getString("crs_name"));
				courseVO.setCategory(rs.getString("category"));
				placeVO.setP_name((rs.getString("p_name")==null)?"無":rs.getString("p_name"));
				placeVO.setP_no(rs.getString("p_no"));
				course_timeVO.setCourseVO(courseVO);
				course_timeVO.setPlaceVO(placeVO);
				course_timeVO.setCount(course_listDAO.count(rs.getString("ct_no")));
				list.add(course_timeVO); // Store the row in the list
			}

			// Handle any driver errors
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
		}
		return list;
	}

	@Override
	public List<Course_timeVO> getAllOpen(String c_acc) {
		List<Course_timeVO> list = new ArrayList<Course_timeVO>();
		Course_timeVO course_timeVO = null;
		CourseVO courseVO = null;
		PlaceVO placeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Course_listDAO course_listDAO = new Course_listDAO();

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_OPEN_STMT);
			pstmt.setString(1, c_acc);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				course_timeVO = new Course_timeVO();
				courseVO = new CourseVO();
				placeVO = new PlaceVO();
				course_timeVO.setCt_no(rs.getString("ct_no"));
				course_timeVO.setCrs_no(rs.getString("crs_no"));
				course_timeVO.setP_no(rs.getString("p_no"));
				course_timeVO.setCrs_date(rs.getDate("crs_date"));
				course_timeVO.setDeadline(rs.getDate("deadline"));
				course_timeVO.setCrs_time(rs.getInt("crs_time"));
				course_timeVO.setPrice(rs.getString("price"));
				course_timeVO.setLimit(rs.getString("limit"));
				course_timeVO.setClass_num(rs.getString("class_num"));
				course_timeVO.setStatus(rs.getInt("status"));
				courseVO.setCrs_name(rs.getString("crs_name"));
				courseVO.setCategory(rs.getString("category"));
				placeVO.setP_name((rs.getString("p_name")==null)?"無":rs.getString("p_name"));
				placeVO.setP_no(rs.getString("p_no"));
				course_timeVO.setCourseVO(courseVO);
				course_timeVO.setPlaceVO(placeVO);
				course_timeVO.setCount(course_listDAO.count(rs.getString("ct_no")));
				list.add(course_timeVO); // Store the row in the list
			}

			// Handle any driver errors
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
		}
		return list;
	}

	@Override
	public List<Course_timeVO> getAllRecord(String c_acc) {
		List<Course_timeVO> list = new ArrayList<Course_timeVO>();
		Course_timeVO course_timeVO = null;
		CourseVO courseVO = null;
		PlaceVO placeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Course_listDAO course_listDAO = new Course_listDAO();

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_RECORD_STMT);
			pstmt.setString(1, c_acc);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				course_timeVO = new Course_timeVO();
				courseVO = new CourseVO();
				placeVO = new PlaceVO();
				course_timeVO.setCt_no(rs.getString("ct_no"));
				course_timeVO.setCrs_no(rs.getString("crs_no"));
				course_timeVO.setP_no(rs.getString("p_no"));
				course_timeVO.setCrs_date(rs.getDate("crs_date"));
				course_timeVO.setDeadline(rs.getDate("deadline"));
				course_timeVO.setCrs_time(rs.getInt("crs_time"));
				course_timeVO.setPrice(rs.getString("price"));
				course_timeVO.setLimit(rs.getString("limit"));
				course_timeVO.setClass_num(rs.getString("class_num"));
				course_timeVO.setStatus(rs.getInt("status"));
				courseVO.setCrs_name(rs.getString("crs_name"));
				courseVO.setCategory(rs.getString("category"));
				placeVO.setP_name((rs.getString("p_name")==null)?"無":rs.getString("p_name"));
				placeVO.setP_no(rs.getString("p_no"));
				course_timeVO.setCourseVO(courseVO);
				course_timeVO.setPlaceVO(placeVO);
				course_timeVO.setCount(course_listDAO.count(rs.getString("ct_no")));
				list.add(course_timeVO); // Store the row in the list
			}

			// Handle any driver errors
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
		}
		return list;
	}

}