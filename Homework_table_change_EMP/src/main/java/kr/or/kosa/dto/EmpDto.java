package kr.or.kosa.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmpDto {
    private int empno;         // 사원번호 (Primary Key)
    private String ename;      // 사원명
    private String job;        // 직무
    private Integer mgr;       // 관리자번호 (null 가능)
    private LocalDate hiredate; // 입사일 (null 가능)
    private double sal;        // 급여
    private Double comm;       // 커미션 (null 가능)
    private int deptno;        // 부서번호

    // 포맷된 입사일 반환 (JSP에서 사용)
    public String getFormattedHiredate() {
        if (hiredate != null) {
            return hiredate.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일"));
        }
        return null;
    }
    
    // 간단한 날짜 포맷 반환
    public String getSimpleHiredate() {
        if (hiredate != null) {
            return hiredate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
        return null;
    }

    @Override
    public String toString() {
        return "사번:" + empno +
                ", 이름:" + ename +
                ", 직무:" + job +
                ", 관리자:" + (mgr != null ? mgr : "없음") +
                ", 입사일:" + (hiredate != null ? hiredate : "없음") +
                ", 급여:" + sal +
                ", 커미션:" + (comm != null ? comm : "없음") +
                ", 부서번호:" + deptno;
    }
}