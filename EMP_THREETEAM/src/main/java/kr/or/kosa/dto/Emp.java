package kr.or.kosa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Emp {
	private String ename;
	private int empno;
	private String job;
	private Integer mgr;
	private String hiredate;
	private int sal;
	private Integer comm;
	private int deptno;
}