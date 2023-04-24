package com.gdj59.bookmall.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AdminInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		String user_grade = (String) session.getAttribute("grade");
		if (user_grade == null || !user_grade.equals("admin")) {
			
			response.setCharacterEncoding("EUC-KR");
			PrintWriter writer = response.getWriter();
		    writer.println("<script type='text/javascript'>");
		    writer.println("alert('관리자 권한이 필요합니다');");
		    writer.println("history.back();");
		    writer.println("</script>");
		    writer.flush();
		    writer.close();
		    response.sendRedirect("..");
			return false;
		} else
			return true;
	}

}