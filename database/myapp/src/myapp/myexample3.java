package myapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns="/my3")
public class myexample3 extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");//한글처리
		request.setCharacterEncoding("UTF-8");
		
		String value_origin = request.getParameter("value"); //입력 숫자 요청
		int value = 0; //계산 시작값
		value = Integer.parseInt(value_origin); //v를 요청한 값으로 int로 받는다.
		String operator_origin = request.getParameter("operator"); //op를 기호 값으로

		ServletContext application = request.getServletContext();
		
		HttpSession session = request.getSession();
		
		Cookie[] cookies = request.getCookies();
		
		
		if(operator_origin.equals("=")) { //기호가 =면
			//application 계산
			int result_application = 0; //결과 값 초기화
			
			int x_application = (int) application.getAttribute("value"); //application에서 값 가져옴
			int y_application = value;
			String operator_application = (String)application.getAttribute("operate");
			
			if(operator_application.equals("+")) {
				result_application = x_application + y_application; 
			}
			else {
				result_application = x_application - y_application;
			}
			
			response.getWriter().printf("result_application is %d\n<br>", result_application);
			
			//session 계산
			int result_session = 0;
			
			int x_session = (int) session.getAttribute("value");//session에서 값 가져옴
			int y_session = value;
			String operator_session = (String)session.getAttribute("operate");
			
			if(operator_session.equals("+")) {
				result_session = x_session + y_session; 
			}
			else {
				result_session = x_session - y_session;
			}
			
			response.getWriter().printf("result_session is %d\n<br>", result_session);
			
			//cookie 계산
			int x_cookie = 0;
			int result_cookie = 0;
			String operator_cookie = ""; //기호값 초기화

			for(Cookie c : cookies) { //쿠키에 저장 된 값들을 c로 사용
				if(c.getName().equals("value")) { //키가 value면  int값으로 값 호출
					x_cookie = Integer.parseInt(c.getValue());					
				}	
				if(c.getName().equals("op")) { //키가 op면 기호를 가져옴
					operator_cookie = c.getValue(); //문자로 기호값 				
				}	
			}
			
			int y = value; //y값 입력 후 기호 계산 없었으므로
			
			if(operator_cookie.equals("+"))
				result_cookie = x_cookie + y;
			else
				result_cookie = x_cookie - y;
					
				response.getWriter().printf("result_cookie is %d\n", result_cookie);
			}			
		else{ 
			
			application.setAttribute( "value", value ); // 어플리케이션에 저장
			application.setAttribute( "operate", operator_origin );
			
			session.setAttribute("value", value); //session에 저장
			session.setAttribute("operate", operator_origin);
			session.setMaxInactiveInterval(600);//600초 동안 세션 유지
			
			
			Cookie valueCookie = new Cookie("value", String.valueOf(value)); //값쿠키에 들어온 숫자를 값으로
			Cookie opCookie = new Cookie("op", operator_origin); // 기호 쿠키에 들어온 키호를 값으로
			valueCookie.setMaxAge(60*60); //쿠키 지속시간 1시간
			response.addCookie(valueCookie); //쿠키를 웹 브라우저로 보낸다.
			response.addCookie(opCookie);
			
			response.sendRedirect("Calculator2.html");//html로 복귀
			
		}	
		
	}	
}
