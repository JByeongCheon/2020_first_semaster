<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>


<%@ page import ="java.io.IOException"%>;
<%@ page import ="javax.script.ScriptEngine"%>;
<%@ page import ="javax.script.ScriptEngineManager"%>;
<%@ page import ="javax.script.ScriptException"%>;
<%@ page import ="javax.servlet.ServletContext"%>;
<%@ page import ="javax.servlet.ServletException"%>;
<%@ page import ="javax.servlet.annotation.WebServlet"%>;
<%@ page import ="javax.servlet.http.Cookie"%>;
<%@ page import ="javax.servlet.http.HttpServlet"%>;
<%@ page import ="javax.servlet.http.HttpServletRequest"%>;
<%@ page import ="javax.servlet.http.HttpServletResponse"%>;
<%@ page import ="javax.servlet.http.HttpSession"%>;

<%
	
	
		// 전달된 POST parameter 파싱
		String value = req.getParameter("value");
		String operator = req.getParameter("operator");
		
		// Cookie 파싱 
		String exp = "";
		Cookie[] cookies = req.getCookies();
		
		if(cookies!=null) {
			for(Cookie c : cookies) {
				if(c.getName().equals("exp")) {
					exp = c.getValue();
					break;
				}			
			}
		}
		
		if(operator!=null && operator.equals("=")) {
			// "=" 연산자일 때	-> 계산
			ScriptEngine engine = new ScriptEngineManager().getEngineByName("JavaScript");
			try {
				exp = String.valueOf(engine.eval(exp));
			} catch (ScriptException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		} else {
			// 문자열에 add
			exp += (value==null)?"":value;
			exp += (operator==null)?"":operator;			
		}
		
		// Cookie 생성 및 추가
		Cookie cookie = new Cookie("exp", exp);
		
		if(operator!=null && operator.equals("C")) {
			cookie.setMaxAge(0);
		}
		
		request.setAttribute("exp",exp);
		
		// /calpage로 redirect
		RequestDispatcher rd = request.getRequestDispatchar("jspcalpage.jsp");
			
	

%>
<body>

</body>
</html>