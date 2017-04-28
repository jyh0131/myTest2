package com.dgit.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dgit.domain.BoardVO;

@RestController
@RequestMapping("/sample/*")
public class SampleController {

	@RequestMapping("hello")
	public String sayHello(){
		return "Hello World";
	}
	
	@RequestMapping("boardVO")
	public BoardVO sendBoardVO(){
		BoardVO vo = new BoardVO();
		vo.setBno(1);
		vo.setTitle("title");
		vo.setContent("내용입니다.");
		vo.setViewcnt(1);
		vo.setWriter("작성자");
		vo.setRegdate(new Date());
		
		return vo;
	}
	
	@RequestMapping("sendMap")
	public Map<Integer,BoardVO> sendMap(){
		
		Map<Integer,BoardVO> map = new HashMap<>();
		
		for(int i = 0; i< 10; i++){
			BoardVO vo = new BoardVO();
			vo.setBno(1);
			vo.setTitle("title");
			vo.setContent("내용입니다.");
			vo.setViewcnt(1);
			vo.setWriter("작성자");
			vo.setRegdate(new Date());
			
			map.put(i, vo);
		}
		
		return map;
	}
	
	
	@RequestMapping("sendErrorAuth")	
	public ResponseEntity<Void> sendListAuth(){
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);//400 error
	}
	
	@RequestMapping("sendErrorNot")
	public ResponseEntity<List<BoardVO>> sendListNot(){
		List<BoardVO> list = new ArrayList<>();
		
		for(int i = 0; i < 10; i++){
			BoardVO vo = new BoardVO();
			vo.setBno(1);
			vo.setTitle("title");
			vo.setContent("내용입니다.");
			vo.setViewcnt(1);
			vo.setWriter("작성자");
			vo.setRegdate(new Date());
			
			list.add(vo);
		}
		
		return new ResponseEntity<List<BoardVO>>(list, HttpStatus.NOT_FOUND);//404 error
		
	}
	
	
}







