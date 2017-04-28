package com.dgit.ex01;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dgit.domain.ReplyVO;
import com.dgit.persistence.ReplyDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class ReplyDAOTest {
	private static final Logger logger = LoggerFactory.getLogger(ReplyDAOTest.class);
	
	@Autowired
	private ReplyDAO dao;
	
	//@Test
	public void testCreate() throws Exception{
		ReplyVO vo = new ReplyVO();
		vo.setBno(2000);
		vo.setReplyer("user01");
		vo.setReplytext("댓글을 달아봅니다.2");
		dao.create(vo);	
		logger.info("insert");
	}
	
	//@Test
	public void testList() throws Exception{
		logger.info("list");
		logger.info(dao.list(2000).toString());		
	}
	
	//@Test
	public void testUpdate() throws Exception{
		logger.info("update");
		ReplyVO vo = new ReplyVO();
		vo.setRno(1);
		vo.setReplyer("user00");
		vo.setReplytext("댓글을 수정합니다.");
		dao.update(vo);
		logger.info(dao.list(2000).toString());		
	}
	
	@Test
	public void testDelete() throws Exception{
		logger.info("Delete");	
		dao.delete(1);
		logger.info(dao.list(2000).toString());		
	}
	
}






