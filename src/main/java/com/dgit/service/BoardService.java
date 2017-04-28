package com.dgit.service;

import java.util.List;

import com.dgit.domain.BoardVO;
import com.dgit.domain.Criteria;
import com.dgit.domain.SearchCriteria;

public interface BoardService {
	public void regist(BoardVO board) throws Exception;
	public BoardVO read(Integer bno) throws Exception;
	public void modify(BoardVO board) throws Exception;
	public void remove(Integer bno )throws Exception;
	public List<BoardVO> listAll() throws Exception;	
	public List<BoardVO> listCriteria(Criteria cri) throws Exception;
	
	public int countPaging() throws Exception;
	
	public void updateViewCount(int bno) throws Exception;
	
	public List<BoardVO> listSearch(SearchCriteria cri) throws Exception;
	public int listSearchCount(SearchCriteria cri) throws Exception; 
}
