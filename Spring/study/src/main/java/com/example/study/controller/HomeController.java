package com.example.study.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//@ : 어노스테이션
//ctrl + F11 실행할때 spring 엔진이 class에 있는 어노스테이션을 스캔한다.  
@RestController // 꼭 있어야 읽을수 있다.
public class HomeController {

	/* public : 접근지정자
	 * String : 리턴타입
	 * callHello : 메소드 이름
	 * () : 파라미터
	 * @GetMapping : 주소(자원) 조회
	 * ("/hello") : hello라는 URL 주소가 됨
	 * http://localhost : 내 pc 주소
	 * localhost : 내 IP 주소
	 * IP 입력 접속 vs localhost 입력 접속 중 뭐가 좋을까? 정답 localhost
	 * why? IP는 매번 바뀌기 때문에 localhost라고 입력하는게 더 좋다.
	 * 
	 * 다시 실행할때는 밑에 중지를 눌러야함.
	 */
	@GetMapping("/hello") 
	//메인(studyapplication)으로 가서 ctrl + f11 누르고, 인터넷창 http://localhost:8080/hello 치면 hello,world가 나옴
	//ctrl + shift + o import 활성화하기
	public String callHello() {
		return "Hello, World";
	}
	@GetMapping("/test")
	public Map<String, Object> callTest(){
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("1", "곱창");
		map.put("2", "삼겹살");
		map.put("3", "목살");
		map.put("4", "대창");
		return map;
	}
	
	
	/* public : 접근지정자
	 * List<String> : 리턴 타입
	 * callFood : 메소드 이름
	 * () : 파라미터
	 * /food : URL 주소
	 */
	@GetMapping("/food/kimjinhee")
	public List<String> callFood(){
		List<String> list = new ArrayList<String>();
		list.add("멜론");
		list.add("포도");
		list.add("키위");
		return list;
	/*HTTP 오류
	 * status
	 * 404 : 페이지를 찾을 수 없음. 
	 * 500 : 개발자 실수 (프로그래밍 오류)
	 * 401 : 권한 없음 (권한 없어서 해당 URL에 접속 불가능)
	 * 200 : 성공!
	 */
	
		
	}
}
