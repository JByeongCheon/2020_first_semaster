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
	
	String ch_password = (String)request.getParameter("passwordcheck");//�л� id
	


if (pwcheck_password.equals(ch_password)){%>
	
	
	
	
<%} %>


<h3>ȸ�� ����</h3>
    <form action="variableTest" method=get>
	     ID : <input type="text" name="id" /><br/>
	        ��й�ȣ : <input type="password" name="pwd" /> <br/>
	        �̸� :  <input type="text"  name="name" /> <br/>
	       
	        �к� : 
              <input type="checkbox" name="dept" value="Computer" /> ��ǻ�Ͱ��к� 
              <input type="checkbox" name="dept" value="Communications" /> ��������к�  
              <input type="checkbox" name="dept" value="Contents" /> IT�������а�  
              <input type="checkbox" name="dept" value="Korean" /> ������а� <br/>
	        
	  
	        �ڱ�Ұ�:<br/>
              <textarea cols="30" rows="10" name="introduction"></textarea> <br/>
        <input type="submit" value="����" />        
    </form>
</body>
</html>