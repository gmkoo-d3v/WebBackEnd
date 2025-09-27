package kr.or.kosa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import java.util.List;
import java.sql.Date;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import kr.or.kosa.dto.empDto;
import kr.or.kosa.utils.connectionpoolhelper;

public class empDao {

	
	
	
	//전체조회
	public List<empDto> getEmpList() throws SQLException{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql =  "SELECT empno, ename, job, mgr, hiredate, sal, comm, deptno FROM emp";
		
		conn = connectionpoolhelper.getConnection();
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		
		List<empDto> empList = new ArrayList<empDto>();
		while(rs.next()) {
			empDto emp = new empDto();
			emp.setEmpno(rs.getInt("empno"));
			emp.setEname(rs.getString("ename"));
			emp.setJob(rs.getString("job"));
			
			int mgr = rs.getInt("mgr");
			if(!rs.wasNull()) {
				emp.setMgr(mgr);
			}
			
			Date hiredate = rs.getDate("hiredate");
			if(hiredate != null) {
				emp.setHiredate(hiredate.toLocalDate());
			}
						
			double comm = rs.getDouble("comm");
			if(!rs.wasNull()) {
				emp.setComm(comm);
			}
			emp.setSal(rs.getDouble("sal"));
			
			
			emp.setDeptno(rs.getInt("deptno"));
			empList.add(emp);
		}
		connectionpoolhelper.close(rs);
		connectionpoolhelper.close(pstmt);
		connectionpoolhelper.close(conn);
		return empList;
	}
	
	//조건조회
	public empDto getEmpById(int empno) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT empno, ename, job, mgr, hiredate, sal, comm, deptno FROM emp WHERE empno = ?";
		empDto emp = null;
		
		try {
			conn = connectionpoolhelper.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, empno);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				emp = new empDto();
				emp.setEmpno(rs.getInt("empno"));
				emp.setEname(rs.getString("ename"));
				emp.setJob(rs.getString("job"));
				
				// mgr은 null 가능
				int mgr = rs.getInt("mgr");
				if (!rs.wasNull()) {
					emp.setMgr(mgr);
				}
				
				// hiredate 처리
				Date hiredate = rs.getDate("hiredate");
				if (hiredate != null) {
					emp.setHiredate(hiredate.toLocalDate());
				}
				
				emp.setSal(rs.getDouble("sal"));
				
				// comm은 null 가능
				double comm = rs.getDouble("comm");
				if (!rs.wasNull()) {
					emp.setComm(comm);
				}
				
				emp.setDeptno(rs.getInt("deptno"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionpoolhelper.close(rs);
			connectionpoolhelper.close(pstmt);
			connectionpoolhelper.close(conn);
		}
		
		return emp;
	}
		

	//삽입
	public int insertEmp(empDto emp) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
        String sql = "INSERT INTO emp(empno, ename, job, mgr, hiredate, sal, comm, deptno) VALUES(?,?,?,?,?,?,?,?)";
		int resultRow = 0;
		
		try {
			conn = connectionpoolhelper.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, emp.getEmpno());
			pstmt.setString(2, emp.getEname());
			pstmt.setString(3, emp.getJob());
			
			// mgr null 처리
			if(emp.getMgr() != null) {
				pstmt.setInt(4, emp.getMgr());
			} else {
				pstmt.setNull(4, Types.INTEGER);
			}
			
			// hiredate null 처리
			if(emp.getHiredate() != null) {
				pstmt.setDate(5, java.sql.Date.valueOf(emp.getHiredate()));
			} else {
				pstmt.setNull(5, Types.DATE);
			}
			
			pstmt.setDouble(6, emp.getSal());
			
			// comm null 처리
			if(emp.getComm() != null) {
				pstmt.setDouble(7, emp.getComm());
			} else {
				pstmt.setNull(7, Types.DOUBLE);
			}
			
			pstmt.setInt(8, emp.getDeptno());
			
			resultRow = pstmt.executeUpdate();
					  
		} catch (Exception e) {
			System.out.println("예외발생 : " + e.getMessage());
		} finally {
			connectionpoolhelper.close(pstmt);
			connectionpoolhelper.close(conn);
		}
	
		return resultRow;
	}
	
	//수정
	public int updateEmp(empDto emp) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE emp SET ename=?, job=?, mgr=?, hiredate=?, sal=?, comm=?, deptno=? WHERE empno=?";
		int resultRow = 0;
		
		try {
			conn = connectionpoolhelper.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, emp.getEname());
			pstmt.setString(2, emp.getJob());
			
			// mgr null 처리
			if(emp.getMgr() != null) {
				pstmt.setInt(3, emp.getMgr());
			} else {
				pstmt.setNull(3, Types.INTEGER);
			}
			
			// hiredate null 처리
			if(emp.getHiredate() != null) {
				pstmt.setDate(4, java.sql.Date.valueOf(emp.getHiredate()));
			} else {
				pstmt.setNull(4, Types.DATE);
			}
			
			pstmt.setDouble(5, emp.getSal());
			
			// comm null 처리
			if(emp.getComm() != null) {
				pstmt.setDouble(6, emp.getComm());
			} else {
				pstmt.setNull(6, Types.DOUBLE);
			}
			
			pstmt.setInt(7, emp.getDeptno());
			pstmt.setInt(8, emp.getEmpno());
			
			resultRow = pstmt.executeUpdate();
					  
		} catch (Exception e) {
			System.out.println("예외발생 : " + e.getMessage());
		} finally {
			connectionpoolhelper.close(pstmt);
			connectionpoolhelper.close(conn);
		}
	
		return resultRow;
	}
	
	//삭제
	public int deleteEmp(int empno) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM emp WHERE empno=?";
		int resultRow = 0;
		
		try {
			conn = connectionpoolhelper.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, empno);
			resultRow = pstmt.executeUpdate();
					  
		} catch (Exception e) {
			System.out.println("예외발생 : " + e.getMessage());
		} finally {
			connectionpoolhelper.close(pstmt);
			connectionpoolhelper.close(conn);
		}
	
		return resultRow;
	}
	
	// 이름으로 검색 (LIKE 사용)
	public List<empDto> searchEmpByName(String name) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT empno, ename, job, mgr, hiredate, sal, comm, deptno FROM emp WHERE ename LIKE ?";
		List<empDto> empList = new ArrayList<empDto>();
		
		try {
			conn = connectionpoolhelper.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + name + "%");
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				empDto emp = new empDto();
				emp.setEmpno(rs.getInt("empno"));
				emp.setEname(rs.getString("ename"));
				emp.setJob(rs.getString("job"));
				
				// mgr은 null 가능
				int mgr = rs.getInt("mgr");
				if (!rs.wasNull()) {
					emp.setMgr(mgr);
				}
				
				// hiredate 처리
				Date hiredate = rs.getDate("hiredate");
				if (hiredate != null) {
					emp.setHiredate(hiredate.toLocalDate());
				}
				
				emp.setSal(rs.getDouble("sal"));
				
				// comm은 null 가능
				double comm = rs.getDouble("comm");
				if (!rs.wasNull()) {
					emp.setComm(comm);
				}
				
				emp.setDeptno(rs.getInt("deptno"));
				empList.add(emp);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionpoolhelper.close(rs);
			connectionpoolhelper.close(pstmt);
			connectionpoolhelper.close(conn);
		}
		
		return empList;
	}
	
	//empno 존재 여부 확인
	public boolean isCheckByEmpno(int empno) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM emp WHERE empno = ?";
		boolean exists = false;
		
		try {
			conn = connectionpoolhelper.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, empno);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				exists = rs.getInt(1) > 0;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionpoolhelper.close(rs);
			connectionpoolhelper.close(pstmt);
			connectionpoolhelper.close(conn);
		}
		
		return exists;
	}
}