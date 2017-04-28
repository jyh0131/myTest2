package com.dgit.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthInterceptor extends HandlerInterceptorAdapter{

	private static final Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		logger.info("preHandle.......");
		
		//session에 들어있는 login정보를 받아서, 있으면 그대로 진행. 없으면 로그인화면으로 이동
		HttpSession session = request.getSession();
		
		Object login = session.getAttribute(LoginInterceptor.LOGIN);
		
		if(login == null){//로그인이 안됐네.
			logger.info("go login");
			saveDest(request);
			response.sendRedirect(request.getContextPath()+"/user/login");
			return false;
		}
		
		return true;
	}
	
	//login 화면으로 이동 전에 원래 가고자 하는 path를 저장해 뒀다가 로그인이 되면 그 곳으로 이동되도록 한다.
	  private void saveDest(HttpServletRequest req) {

	    String uri = req.getRequestURI();
	    String query = req.getQueryString();

	    if(query == null || query.equals("null")) {
	        query = "";
	    } else {
	        query = "?" + query;
	    }

	    if (req.getMethod().equals("GET")) {
	      logger.info("dest: " + (uri + query));
	      //매개변수가 있다면 매개변수값까지 모두 기억해둔다.
	      req.getSession().setAttribute("dest", uri + query);
	    }
	  }
}






