package com.example.study.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.study.mapper.EmpMapper;
import com.example.study.vo.DeptVO;
import com.example.study.vo.EmpVO;

import ch.qos.logback.core.recovery.ResilientSyslogOutputStream;

//API 서버
//@RestController는 결과값을 json으로 보여준다. 
@RestController
@CrossOrigin
public class EmpController {
	
	//@Autowired Spring에서 객체를 관리(생성과 소멸을 담당하겠다.) 해주겠다.
	@Autowired
	EmpMapper empMapper;
	
	/*
	 * @ : 어노스테이션 
	 * Get : 조회하다
	 * Mapping : URL을 매핑(연결)하다.
	 * emp : URL주소
	 */
	@GetMapping("/emp")
	public List<EmpVO> callAllEmp(){
		List<EmpVO> list = new ArrayList<EmpVO>();
		
		EmpVO smith = new EmpVO(); //vo 클래스 생성
		smith.setEmpno(7369);
		smith.setEname("SMITH");
		smith.setJob("CLERK");
		smith.setHiredate("1980-12-17");
		smith.setSal(800);
		list.add(smith); //list 에 스미스 추가
		
		//list에 알렌 추가
		EmpVO allen = new EmpVO(); // vo 클래스 생성
		allen.setEmpno(7499);
		allen.setEname("ALLEN");
		allen.setJob("SALESMAN");
		allen.setHiredate("1981-02-20");
		allen.setSal(1600);
		list.add(allen);
		
		return list;
	}
		@GetMapping("/emp/{empno}")
		public EmpVO callEmpNo(@PathVariable int empno) {
			
			List<EmpVO> list = new ArrayList<EmpVO>();
			EmpVO smith = new EmpVO(); //vo 클래스 생성
			smith.setEmpno(7369);
			smith.setEname("SMITH");
			smith.setJob("CLERK");
			smith.setHiredate("1980-12-17");
			smith.setSal(800);
			list.add(smith); //list 에 스미스 추가
			
			//list에 알렌 추가
			EmpVO allen = new EmpVO(); // vo 클래스 생성
			allen.setEmpno(7499);
			allen.setEname("ALLEN");
			allen.setJob("SALESMAN");
			allen.setHiredate("1981-02-20");
			allen.setSal(1600);
			list.add(allen);
			
			System.out.println("사원번호 => "+empno);
			
			for(int i=0; i<list.size(); i++) {
				int x = list.get(i).getEmpno(); //list add한 deptno를 불러온다. 초보-ctrl + 스페이스바로 찾을경우 오타날 확률이 줄어듬..
				if(x == empno) { //x가 url로 받아온 empno랑 같다면!
					return list.get(i); //부서번호 일치 empno class return!
				}
			}
			//ArrayList, HashMap 공부
			return null;
		}
		
	
	
	@GetMapping("/dept")
	public List<DeptVO> callDeptAll(){
		
		List<DeptVO> list = new ArrayList<DeptVO>();
		DeptVO dept1 = new DeptVO();
		dept1.setDeptno(10);
		dept1.setDname("ACCOUNTING");
		dept1.setLoc("NEW YORK");
		list.add(dept1);
		
		DeptVO dept2 = new DeptVO();
		dept2.setDeptno(20);
		dept2.setDname("RESEARCH");
		dept2.setLoc("DALLAS");
		list.add(dept2);
		
		return list;
	}
	
	//@PathVariable을 이용해서 url에 있는 데이터를 받아 올 수 있다.
	//ex)localhost:8080/dept/300 => 300이라는 데이터를 파라미터로 받는다. 숫자 300은 postman에서 확인차 아무거나 썼기때문에 신경쓸 필요없다.
	@GetMapping("/dept/{deptno}")
	public DeptVO callDeptNo(@PathVariable int deptno) {
		
		List<DeptVO> list = new ArrayList<DeptVO>();
		DeptVO dept1 = new DeptVO();
		dept1.setDeptno(10);
		dept1.setDname("ACCOUNTING");
		dept1.setLoc("NEW YORK");
		list.add(dept1);
		
		DeptVO dept2 = new DeptVO();
		dept2.setDeptno(20);
		dept2.setDname("RESEARCH");
		dept2.setLoc("DALLAS");
		list.add(dept2);
		
		System.out.println("부서번호 => "+deptno);
		
		for(int i=0; i<list.size(); i++) {
			int x = list.get(i).getDeptno(); //list add한 deptno를 불러온다.
			if(x == deptno) { //x가 url로 받아온 deptno랑 같다면!
				return list.get(i); //부서번호 일치 deptno class return!
			}
		}
		//ArrayList, HashMap 공부
		return null;
	}
	
	@GetMapping("/emp/sal/{money}")
	public String callEmpSal(@PathVariable String money) {
		return money;
	}
	/*
	 * ? : 쿼리 스트링
		@PathVariable처럼 url에 값을 넘겨주는 방법 중 하나. 
	 */
	//ex)/leaderboards/tier?region=kr
	@GetMapping("/leaderboards/tier")
	public String queryStringTest(@RequestParam String region, @RequestParam String page) {
		return region+","+page;
	}
	//ex)board/search?writer=brian&page=10
	@GetMapping("/board/search")
	public String queryStringTest2(@RequestParam String writer, @RequestParam String page) {
		return writer+","+page;
	}
	
	/*
	 * url(controller)
 		값을 하나 or 둘 넘길 때 path 사용
 		여러 값을 넘길 때 queryString 사용
	 */
	
	@GetMapping("/db/emp")
	public List<EmpVO> callDbEmp(){
		
		return empMapper.selectEmp();
	}
	
	//path 방법으로 데이터 받아오기
	//사원번호가 7521번을 조회하면 급여와 입사날짜를 안 보이게 해주세요!
	@GetMapping("db/emp/{empno}")
	public EmpVO callEmpOne(@PathVariable int empno) {
		System.out.println("사원번호:"+empno);
		EmpVO vo = empMapper.selectEmpOne(empno);
		if(empno == 7521) {
			vo.setSal(0);
			vo.setHiredate(null);
		}
		return vo;
	}
	
	//원하는 부서 조회하기.
	@GetMapping("db/dept/{deptno}")
	public DeptVO callDbdeptOne(@PathVariable int deptno) {
		System.out.println("부서 번호:"+deptno);
		if(deptno ==10 || deptno == 20) { //10번, 20번 부서번호는 조회하지못하게 해주세요!
		//return은 더이상 아래 코드를 출력하지 않는다.
			return null;
		}
		return empMapper.selectDeptOne(deptno);		
	}
	
	
	
}
