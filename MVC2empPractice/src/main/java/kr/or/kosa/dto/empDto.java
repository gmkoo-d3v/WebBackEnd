package kr.or.kosa.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class empDto {
	private int empno;
	private String ename;
	private String job;
	private Integer mgr;
	private LocalDate hiredate;
	private double sal;
	private Double comm;
	private int deptno;


	public String getFormattedHiredate() {
	    return hiredate != null ? hiredate.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일")) : "";
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