package com.dgit.persistence;


import com.dgit.domain.LoginDTO;
import com.dgit.domain.UserVO;

public interface UserDAO {

  public UserVO login(LoginDTO dto)throws Exception;
  	
}



