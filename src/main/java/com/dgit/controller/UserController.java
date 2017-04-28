package com.dgit.controller;


import javax.inject.Inject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.dgit.domain.*;
import com.dgit.interceptor.LoginInterceptor;
import com.dgit.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
  @Inject
  private UserService service;

  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public void loginGET(@ModelAttribute("dto") LoginDTO dto) {
	  logger.info("loginGET ......");
  }

   @RequestMapping(value = "/loginPost", method = RequestMethod.POST)
   public void loginPOST(LoginDTO dto, HttpSession session, Model model) throws Exception {
	   logger.info("loginPOST ......");
	   UserVO vo = service.login(dto);
	  
	   if (vo == null) {
		   logger.info("loginPOST return ......");
		   return;
	   }
	  
	   model.addAttribute("userVO", vo);  
   }
   
   @RequestMapping(value = "/logout", method = RequestMethod.GET)
   public String loginOutGET( HttpSession session) {
 	  logger.info("logOutGET ......");
 	 UserVO vo = (UserVO)session.getAttribute("login");
 	 
 	 if(vo != null){
 		 logger.info(vo.toString());
 		 session.removeAttribute(LoginInterceptor.LOGIN);
 		 session.invalidate();
 	 }
 	 
 	 return "redirect:/sboard/list";
   }


}
