<%@page import="java.util.ArrayList"%>
<%@ page import="java.net.URLEncoder" %>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
    <style>    
        #visual .content-container{	
            height:inherit;
            display:flex; 
            align-items: center;
        }
    </style>
</head>
<%request.setCharacterEncoding("euc-kr"); %>
<%
ArrayList id_ar = new ArrayList();
ArrayList name_ar = new ArrayList();


String id;
String name;

Integer count = (Integer)session.getAttribute("count");

int count2 = 0;
session = request.getSession();
for(int i=0;i<count;i++){ %>
	<%if(session.getAttribute("id"+i) != null){ %>
		
		<%id = (String)session.getAttribute("id"+i); %>
		<%name = (String)session.getAttribute("name"+i); %>
		
		<%id_ar.add(id); 
		name_ar.add(name); 
		count2 ++;
		%>
		
	<%}%>
	<%}%>
	


<body>
    <!-- --------------------------- <body> --------------------------------------- -->
	<div id="body">
		<div class="content-container clearfix">
		<main class="main">
			<h2 class="main title">DBP 학생 목록</h2>
			
			<div class="notice margin-top">				
				<table>
					<thead>
						<tr>
							<th>번호</th>
							<th>ID</th>
							<th>&nbsp;이름</th>
						</tr>
					</thead>
					<tbody>
					<%for(int i=0;i<count2;i++){ %>
					
				
					<tr>
						<td>1</td>
						<td><a href="detail.jsp?select=<%=id_ar.get(i)%>"><%=id_ar.get(i) %></a></td>
						
						<td>&nbsp;&nbsp; <%=name_ar.get(i) %></td>												
					</tr>
				
					<%}%>
							
					
					</tbody>
				</table>
			</div>
			
			<div class="search-form margin-top first align-right">
			<br>	
				<form action="list_jsp_depart.jsp" method=get>
					<fieldset>
						<legend class="hidden">학생 분류</legend>
						<label class="hidden">학과</label>
						<select name="department_search">
							<option  value="default">전체</option>
							<option  value="computer">컴퓨터공학부</option>
							<option  value="information">정보통신학부</option>
							<option  value="itcontents">IT콘텐츠학과</option>
							<option  value="korean">국어국문학과</option>							
						</select> 
						<input type="submit" value="검색" action="list_jsp_depart.jsp"/>
					</fieldset>
				</form>
			</div>
		</main>
		
			
		</div>
	</div>

    </body>
    
</html>