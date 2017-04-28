package com.dgit.service;

import com.dgit.domain.*;

public interface UserService {

  public UserVO login(LoginDTO dto) throws Exception;
}
