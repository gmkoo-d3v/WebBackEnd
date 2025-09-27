package kr.or.kosa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import kr.or.kosa.dto.Emp;
import kr.or.kosa.utils.ConnectionPoolHelper;

public class EmpDao {

    // 전체조회 (hiredate는 문자열로 통일)
    public List<Emp> getEmpList() throws SQLException {

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql =
            "select ename, empno, job, mgr, to_char(hiredate,'YYYY-MM-DD') as hiredate, sal, comm, deptno from emp";

        conn = ConnectionPoolHelper.getConnection();
        pstmt = conn.prepareStatement(sql);
        rs = pstmt.executeQuery();

        List<Emp> empList = new ArrayList<>();

        while (rs.next()) {
            Emp emp = new Emp();
            emp.setEname(rs.getString("ename"));
            emp.setEmpno(rs.getInt("empno"));
            emp.setJob(rs.getString("job"));
            // NULL 보존
            emp.setMgr(rs.getObject("mgr", Integer.class));
            emp.setHiredate(rs.getString("hiredate"));
            emp.setSal(rs.getInt("sal"));
            emp.setComm(rs.getObject("comm", Integer.class));
            emp.setDeptno(rs.getInt("deptno"));

            empList.add(emp);
        }

        ConnectionPoolHelper.close(rs);
        ConnectionPoolHelper.close(pstmt);
        ConnectionPoolHelper.close(conn);

        return empList;
    }

    // 단건조회 (ename 기준)
    public Emp getEmpListById(String ename) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql =
            "select ename, empno, job, mgr, to_char(hiredate,'YYYY-MM-DD') as hiredate, sal, comm, deptno " +
            "from emp where ename=?";
        Emp emp = new Emp();
        try {
            conn = ConnectionPoolHelper.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, ename);

            rs = pstmt.executeQuery();
            if (rs.next()) {
                emp.setEname(rs.getString("ename"));
                emp.setEmpno(rs.getInt("empno"));
                emp.setJob(rs.getString("job"));
                emp.setMgr(rs.getObject("mgr", Integer.class));   // NULL 보존
                emp.setHiredate(rs.getString("hiredate"));
                emp.setSal(rs.getInt("sal"));
                emp.setComm(rs.getObject("comm", Integer.class)); // NULL 보존
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

    // 삽입
    public int insertEmp(Emp emp) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql =
            "insert into emp(ename, empno, job, mgr, hiredate, sal, comm, deptno) " +
            "values(?,?,?,?,TO_DATE(?,'YYYY-MM-DD'),?,?,?)";
        int resultRow = 0;

        try {
            conn = ConnectionPoolHelper.getConnection();
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, emp.getEname());
            pstmt.setInt(2, emp.getEmpno());
            pstmt.setString(3, emp.getJob());

            // mgr (nullable)
            if (emp.getMgr() == null) pstmt.setNull(4, Types.INTEGER);
            else pstmt.setInt(4, emp.getMgr());

            pstmt.setString(5, emp.getHiredate());
            pstmt.setInt(6, emp.getSal());

            // comm (nullable)
            if (emp.getComm() == null) pstmt.setNull(7, Types.INTEGER);
            else pstmt.setInt(7, emp.getComm());

            pstmt.setInt(8, emp.getDeptno());

            resultRow = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionPoolHelper.close(pstmt);
            ConnectionPoolHelper.close(conn);
        }

        return resultRow;
    }

    // 수정
    public int updateEmp(Emp emp) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql =
            "update emp " +
            "   set empno=?, job=?, mgr=?, hiredate=TO_DATE(?,'YYYY-MM-DD'), sal=?, comm=?, deptno=? " +
            " where ename=?";
        int resultRow = 0;

        try {
            conn = ConnectionPoolHelper.getConnection();
            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, emp.getEmpno());
            pstmt.setString(2, emp.getJob());

            // mgr (nullable)
            if (emp.getMgr() == null) pstmt.setNull(3, Types.INTEGER);
            else pstmt.setInt(3, emp.getMgr());

            pstmt.setString(4, emp.getHiredate());
            pstmt.setInt(5, emp.getSal());

            // comm (nullable)
            if (emp.getComm() == null) pstmt.setNull(6, Types.INTEGER);
            else pstmt.setInt(6, emp.getComm());

            pstmt.setInt(7, emp.getDeptno());
            pstmt.setString(8, emp.getEname());

            resultRow = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionPoolHelper.close(pstmt);
            ConnectionPoolHelper.close(conn);
        }

        return resultRow;
    }

    // 삭제
    public int deleteEmp(String ename) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "delete from emp where ename=?";
        int resultRow = 0;

        try {
            conn = ConnectionPoolHelper.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, ename);
            resultRow = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionPoolHelper.close(pstmt);
            ConnectionPoolHelper.close(conn);
        }

        return resultRow;
    }

    public Emp idSearchByEmail(String job) { return null; }
    public boolean isCheckByName(String ename) { return false; }
}
