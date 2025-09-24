package kr.or.kosa.dao;

import java.sql.*;
import java.util.*;
import java.time.LocalDate;
import java.sql.Date;

import kr.or.kosa.dto.EmpDto;
import kr.or.kosa.utils.ConnectionPoolHelper;

public class EmpDao {
	
	// 전체 사원 수를 조회하는 메서드 추가
	public int getTotalEmpCount() throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM emp";
		int count = 0;
		try {
			conn = ConnectionPoolHelper.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} finally {
			ConnectionPoolHelper.close(rs);
			ConnectionPoolHelper.close(pstmt);
			ConnectionPoolHelper.close(conn);
		}
		return count;
	}
	
	// 전체조회 (페이지네이션 적용)
	public List<EmpDto> getEmpList(int start, int pageSize) throws SQLException {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		// Oracle 데이터베이스에서 페이지네이션을 위한 SQL 쿼리
		String sql = "SELECT * FROM ( " +
				     "    SELECT ROWNUM AS rn, empno, ename, job, mgr, hiredate, sal, comm, deptno " +
				     "    FROM (SELECT * FROM emp ORDER BY empno) " +
				     ") WHERE rn BETWEEN ? AND ?";
		
		List<EmpDto> empList = new ArrayList<>();
		
		try {
			conn = ConnectionPoolHelper.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, start + pageSize - 1);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				EmpDto emp = new EmpDto();
				emp.setEmpno(rs.getInt("empno"));
				emp.setEname(rs.getString("ename"));
				emp.setJob(rs.getString("job"));
				
				int mgr = rs.getInt("mgr");
				if (!rs.wasNull()) {
					emp.setMgr(mgr);
				}
				
				Date hiredate = rs.getDate("hiredate");
				if (hiredate != null) {
					emp.setHiredate(hiredate.toLocalDate());
				}
				
				emp.setSal(rs.getDouble("sal"));
				
				double comm = rs.getDouble("comm");
				if (!rs.wasNull()) {
					emp.setComm(comm);
				}
				
				emp.setDeptno(rs.getInt("deptno"));
				empList.add(emp);
			}
			
		} finally {
			ConnectionPoolHelper.close(rs);
			ConnectionPoolHelper.close(pstmt);
			ConnectionPoolHelper.close(conn);
		}
		
		return empList;
	}
	
	// 기존 getEmpList() 메서드는 제거 또는 다른 이름으로 변경
	// (예: getEmpAllList() 등)
	// getEmpList() 메서드가 오버로딩되어 사용되던 기존 코드는 오류가 발생하지 않으므로
	// 기존 getEmpList() 메서드 위에 아래 코드를 그대로 덮어쓰기 하셔도 무방합니다.

	// 기존 코드는 그대로 유지 (필요에 따라)
	// public List<EmpDto> getEmpList() throws SQLException { ... }
	
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
				
				int mgr = rs.getInt("mgr");
				if (!rs.wasNull()) {
					emp.setMgr(mgr);
				}
				
				Date hiredate = rs.getDate("hiredate");
				if (hiredate != null) {
					emp.setHiredate(hiredate.toLocalDate());
				}
				
				emp.setSal(rs.getDouble("sal"));
				
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
	
	//사원등록
	public int insertEmp(EmpDto emp) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO emp(empno, ename, job, mgr, hiredate, sal, comm, deptno) VALUES(?,?,?,?,?,?,?,?)";
		int result = 0;
		try {
			conn = ConnectionPoolHelper.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, emp.getEmpno());
			pstmt.setString(2, emp.getEname());
			pstmt.setString(3, emp.getJob());
			if (emp.getMgr() == null) {
				pstmt.setNull(4, java.sql.Types.INTEGER);
			} else {
				pstmt.setInt(4, emp.getMgr());
			}
			
			// Java 8 LocalDate를 java.sql.Date로 변환
			if (emp.getHiredate() == null) {
				pstmt.setNull(5, java.sql.Types.DATE);
			} else {
				pstmt.setDate(5, Date.valueOf(emp.getHiredate()));
			}
			
			pstmt.setDouble(6, emp.getSal());
			
			if (emp.getComm() == null) {
				pstmt.setNull(7, java.sql.Types.DOUBLE);
			} else {
				pstmt.setDouble(7, emp.getComm());
			}
			pstmt.setInt(8, emp.getDeptno());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPoolHelper.close(pstmt);
			ConnectionPoolHelper.close(conn);
		}
		return result;
	}
	
	//사원 정보 수정
	public int updateEmp(EmpDto emp) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "UPDATE emp SET ename=?, job=?, mgr=?, hiredate=?, sal=?, comm=?, deptno=? WHERE empno=?";
		try {
			conn = ConnectionPoolHelper.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, emp.getEname());
			pstmt.setString(2, emp.getJob());
			if (emp.getMgr() == null) {
				pstmt.setNull(3, java.sql.Types.INTEGER);
			} else {
				pstmt.setInt(3, emp.getMgr());
			}
			if (emp.getHiredate() == null) {
				pstmt.setNull(4, java.sql.Types.DATE);
			} else {
				pstmt.setDate(4, Date.valueOf(emp.getHiredate()));
			}
			pstmt.setDouble(5, emp.getSal());
			if (emp.getComm() == null) {
				pstmt.setNull(6, java.sql.Types.DOUBLE);
			} else {
				pstmt.setDouble(6, emp.getComm());
			}
			pstmt.setInt(7, emp.getDeptno());
			pstmt.setInt(8, emp.getEmpno());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPoolHelper.close(pstmt);
			ConnectionPoolHelper.close(conn);
		}
		return result;
	}
	
	//사원 정보 삭제
	public int deleteEmp(int empno) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM emp WHERE empno=?";
		int result = 0;
		try {
			conn = ConnectionPoolHelper.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, empno);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPoolHelper.close(pstmt);
			ConnectionPoolHelper.close(conn);
		}
		return result;
	}
	
	//이름으로 사원 검색
	public List<EmpDto> searchEmpByName(String ename) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<EmpDto> empList = new ArrayList<>();
		String sql = "SELECT empno, ename, job, mgr, hiredate, sal, comm, deptno FROM emp WHERE ename LIKE ?";
		try {
			conn = ConnectionPoolHelper.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + ename + "%");
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				EmpDto emp = new EmpDto();
				emp.setEmpno(rs.getInt("empno"));
				emp.setEname(rs.getString("ename"));
				emp.setJob(rs.getString("job"));
				
				int mgr = rs.getInt("mgr");
				if (!rs.wasNull()) {
					emp.setMgr(mgr);
				}
				
				Date hiredate = rs.getDate("hiredate");
				if (hiredate != null) {
					emp.setHiredate(hiredate.toLocalDate());
				}
				
				emp.setSal(rs.getDouble("sal"));
				
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
			if(rs.next() && rs.getInt(1) > 0) {
				exists = true;
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