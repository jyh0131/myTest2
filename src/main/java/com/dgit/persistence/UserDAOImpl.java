package com.dgit.persistence;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.dgit.domain.LoginDTO;
import com.dgit.domain.UserVO;

@Repository
public class UserDAOImpl implements UserDAO {
	
	@Inject
	private SqlSession session;
	
	private static String namespace ="com.dgit.mapper.UserMapper";	

	@Override
	public UserVO login(LoginDTO dto) throws Exception {
		
		return session.selectOne(namespace +".login", dto);
	}
}









