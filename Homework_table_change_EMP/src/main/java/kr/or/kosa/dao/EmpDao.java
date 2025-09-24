package kr.or.kosa.dao;

import java.sql.*;
import java.util.*;
import java.time.LocalDate;
import java.sql.Date;

import kr.or.kosa.dto.EmpDto;
import kr.or.kosa.utils.ConnectionPoolHelper;

public class EmpDao {
	
	//전체조회
	public List<EmpDto> getEmpList() throws SQLException {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT empno, ename, job, mgr, hiredate, sal, comm, deptno FROM emp";
		
		conn = ConnectionPoolHelper.getConnection();
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		
		List<EmpDto> empList = new ArrayList<EmpDto>();
		
		while(rs.next()) {
			EmpDto emp = new EmpDto();
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
		
		ConnectionPoolHelper.close(rs);
		ConnectionPoolHelper.close(pstmt);
		ConnectionPoolHelper.close(conn);
		
		return empList;
	}
	
	//조건조회 (empno로 검색)
	public EmpDto getEmpById(int empno) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT empno, ename, job, mgr, hiredate, sal, comm, deptno FROM emp WHERE empno = ?";
		EmpDto emp = null;
		
		try {
			conn = ConnectionPoolHelper.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, empno);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				emp = new EmpDto();
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
			ConnectionPoolHelper.close(rs);
			ConnectionPoolHelper.close(pstmt);
			ConnectionPoolHelper.close(conn);
		}
		
		return emp;
	}
	
	//삽입
	public int insertEmp(EmpDto emp) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
        String sql = "INSERT INTO emp(empno, ename, job, mgr, hiredate, sal, comm, deptno) VALUES(?,?,?,?,?,?,?,?)";
		int resultRow = 0;
		
		try {
			conn = ConnectionPoolHelper.getConnection();
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
			ConnectionPoolHelper.close(pstmt);
			ConnectionPoolHelper.close(conn);
		}
	
		return resultRow;
	}
	
	//수정
	public int updateEmp(EmpDto emp) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE emp SET ename=?, job=?, mgr=?, hiredate=?, sal=?, comm=?, deptno=? WHERE empno=?";
		int resultRow = 0;
		
		try {
			conn = ConnectionPoolHelper.getConnection();
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
			ConnectionPoolHelper.close(pstmt);
			ConnectionPoolHelper.close(conn);
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
			conn = ConnectionPoolHelper.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, empno);
			resultRow = pstmt.executeUpdate();
					  
		} catch (Exception e) {
			System.out.println("예외발생 : " + e.getMessage());
		} finally {
			ConnectionPoolHelper.close(pstmt);
			ConnectionPoolHelper.close(conn);
		}
	
		return resultRow;
	}
	
	// 이름으로 검색 (LIKE 사용)
	public List<EmpDto> searchEmpByName(String name) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT empno, ename, job, mgr, hiredate, sal, comm, deptno FROM emp WHERE ename LIKE ?";
		List<EmpDto> empList = new ArrayList<EmpDto>();
		
		try {
			conn = ConnectionPoolHelper.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + name + "%");
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				EmpDto emp = new EmpDto();
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
			ConnectionPoolHelper.close(rs);
			ConnectionPoolHelper.close(pstmt);
			ConnectionPoolHelper.close(conn);
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
			conn = ConnectionPoolHelper.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, empno);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				exists = rs.getInt(1) > 0;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPoolHelper.close(rs);
			ConnectionPoolHelper.close(pstmt);
			ConnectionPoolHelper.close(conn);
		}
		
		return exists;
	}
}