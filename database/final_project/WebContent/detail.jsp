<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>

<% 
String infor = request.getParameter("select");//학생 id
%>

<%
ArrayList id_ar = new ArrayList();
ArrayList name_ar = new ArrayList();
ArrayList department_ar = new ArrayList();
ArrayList introduce_ar = new ArrayList();
ArrayList password_ar = new ArrayList();

String id;
String name;
String department;
String introduce;
String password;

Integer count = (Integer)session.getAttribute("count");
String department_list = request.getParameter("department_search");%>

<% 
int count2 = 0;



// 전체 쿠키 삭제하기
Cookie[] cookies_del = request.getCookies() ;
 
if(cookies_del != null){
    for(int i=0; i < cookies_del.length; i++){
         
        
        cookies_del[i].setMaxAge(0) ;
         
      
        response.addCookie(cookies_del[i]) ;
    }
}
%>
<% 
for(int i=0;i<count;i++){ 

	if(session.getAttribute("id"+i).equals(infor)){ %>		
		
			<%id = (String)session.getAttribute("id"+i); %>
			<%name = (String)session.getAttribute("name"+i); %>
			<%department = (String)session.getAttribute("department"+i); %>
			<%introduce = (String)session.getAttribute("introduce"+i); %>
			<%password = (String)session.getAttribute("password"+i); %>
			<%id_ar.add(id); %>
			<% name_ar.add(name); 
			department_ar.add(department); 
			introduce_ar.add(introduce); 
			password_ar.add(password); 
			count2 ++;
			%>
		<%} %>
	<%}%>
	<%String id_check = (String)id_ar.get(0); %>
	
	<%String password_check = (String)password_ar.get(0); %>
	<%Cookie password_cookie = new Cookie("password-check",password_check); %>
	
	<% response.addCookie(password_cookie); %>


<body>
	<form action="pwcheck.jsp" method=post>
	
    <div id="body">
		<div class="content-container clearfix">
		<main class="main">
			<h2 class="main title"> 학생 정보</h2>
			
			<div class="notice margin-top">				
				
					<tr>
					
						<td>아이디: <%=id_ar.get(0) %></a></td>
						<br>
						<td>이름: <%=name_ar.get(0) %></a></td>
						<br>
						<td>학과: <%=department_ar.get(0) %></td>		
						<br>		
						<td>자기소개: <%=introduce_ar.get(0) %></td>									
					</tr>
					
			</div>
			
			<div class="search-form margin-top first align-right">
			<input type="submit" value="목록" onclick='this.form.action="list_jsp.jsp"' />
			<input type="submit" value="수정"/>
			</div>
			</form>
		</main>
		
			
		</div>
	</div>

</body>
</html>