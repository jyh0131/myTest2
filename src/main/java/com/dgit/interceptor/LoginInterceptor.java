package com.dgit.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


public class LoginInterceptor extends HandlerInterceptorAdapter {

	public static final String LOGIN = "login";
	private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		logger.info("preHandle......");
		
		//session안의 login정보 삭제
		HttpSession session = request.getSession();
		
		if(session.getAttribute(LOGIN) != null){
			logger.info("clear login data preHandle");
			session.removeAttribute(LOGIN);
		}
		
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		logger.info("postHandle......");
		
		HttpSession session = request.getSession();
		
		Object userVO = modelAndView.getModel().get("userVO");
		
		if(userVO !=null){
			logger.info("new Login Success");
			session.setAttribute(LOGIN, userVO);
			Object dest = session.getAttribute("dest");
			String path = dest!= null ? (String) dest: request.getContextPath();
			response.sendRedirect(path);//home
		}
		
		//가입되지 않은 회원이면, loginPost에서는 list로 간다.
		
	}
}













