package com.dgit.service;

import java.util.Date;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import com.dgit.domain.*;
import com.dgit.persistence.UserDAO;

@Service
public class UserServiceImpl implements UserService {

  @Inject
  private UserDAO dao;

  @Override
  public UserVO login(LoginDTO dto) throws Exception {

    return dao.login(dto);
  }
  
}
