<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	
	Member member = new Member();
	//member.setName("kim")
	
	<c:set var="member" value="<%=new Member() %>"/>
	<c:set target="${member }" property="name" value="Yeun"/>
	<c:out value="${member.name }"/>
	
	
	
	<%
	ArrayList<Member> members = new ArrayList<Member>(); %>
	members.add(new Member(1,"kyung","123"));
	members.add(new Member(2,"kyfung","1253"));%>
	%>
	
	for(int i = 1; i<members.size(); i++){ %>
		<%-members.get(i),getName() %>
		<%} 

	</c:forEach[var>
		<c:set var="member" value="<%=members%>" />
	<c:forEach var="member" items = "<%=members %>">
		${member.id }${member.name }${member.pw }<br>
	</c:forEach>
	
	<%
	for(int i=2;i<10;i++){
		for(int j=1;j<10;j++){ 	
			out.print("결과: ");	 
			out.print(i+"*"+j+"="+(i*j));  %> <br>
		<%}%> <br>
	<%} %>
</body>
</html>