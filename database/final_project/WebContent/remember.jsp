<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>


<%
String pwcheck_password = request.getParameter("passwordcheck");

Cookie[] cookies = request.getCookies();

	Cookie c = cookies[0];
	String pass_check = (String)c.getValue();
	
	String ch_password = (String)request.getParameter("passwordcheck");//학생 id
	


if (pwcheck_password.equals(ch_password)){%>
	
	
	
	
<%} %>


<h3>회원 정보</h3>
    <form action="variableTest" method=get>
	     ID : <input type="text" name="id" /><br/>
	        비밀번호 : <input type="password" name="pwd" /> <br/>
	        이름 :  <input type="text"  name="name" /> <br/>
	       
	        학부 : 
              <input type="checkbox" name="dept" value="Computer" /> 컴퓨터공학부 
              <input type="checkbox" name="dept" value="Communications" /> 정보통신학부  
              <input type="checkbox" name="dept" value="Contents" /> IT콘텐츠학과  
              <input type="checkbox" name="dept" value="Korean" /> 국어국문학과 <br/>
	        
	  
	        자기소개:<br/>
              <textarea cols="30" rows="10" name="introduction"></textarea> <br/>
        <input type="submit" value="전송" />        
    </form>
</body>
</html>