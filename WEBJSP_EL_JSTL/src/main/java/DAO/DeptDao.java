package DAO;

import java.util.ArrayList;
import java.util.List;

import DTO.Dept;

public class DeptDao {
	
	public List<Dept> getDeptList(){
		// db 연결 select ... 결과 집합 : 4건 ( 부서 4개)
		List<Dept> list = new ArrayList<Dept>();
		
		list.add(new Dept(10,"AA","AAA"));
		list.add(new Dept(20,"BB","BBB"));
		list.add(new Dept(30,"CC","CCC"));
		list.add(new Dept(40,"DD","DDD"));
		
		return list;
		
	}
}
