package com.dgit.ex01;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dgit.domain.BoardVO;
import com.dgit.domain.Criteria;
import com.dgit.persistence.BoardDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class BoardDAOTest {
	private static final Logger logger = LoggerFactory.getLogger(BoardDAOTest.class);

	@Autowired
	private BoardDAO dao;

	//@Test
	public void testCreate() throws Exception{
		BoardVO vo = new BoardVO();
		vo.setTitle("새 타이틀");
		vo.setContent("새로운 글을 넣습니다.");
		vo.setWriter("user00");
		dao.create(vo);
	}

	//@Test
	public void testRead() throws Exception{
		dao.read(1);		
	}	

	//@Test
	public void testUpdate() throws Exception {
		BoardVO board = new BoardVO();
		board.setBno(1);
		board.setTitle("수정된 글입니다.");
		board.setContent("수정 테스트 ");
		dao.update(board);
	}

	//@Test
	public void testDelete() throws Exception{
		dao.delete(2);
	}

	//@Test
	public void testListAll() throws Exception {
		dao.listAll();
	}
	
	//@Test
	public void testListPage() throws Exception{
		int page = 3;
		List<BoardVO> list = dao.listPage(page);
		for(BoardVO vo : list){
			logger.info(vo.getBno() + ":" + vo.getTitle());
		}
	}
	
	
	@Test
	public void testListCriteria() throws Exception{
		Criteria cri = new Criteria();
		cri.setPage(3);
		cri.setPerPageNum(30);
		
		List<BoardVO> list = dao.listCriteria(cri);
		for(BoardVO vo : list){
			logger.info(vo.getBno() + ":" + vo.getTitle());
		}
	}

}













