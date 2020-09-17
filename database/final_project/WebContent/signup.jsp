<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<h3>DBP 학생관리</h3>
<hr>
<form action="signup1" method="post">

사용자 ID : <input type="text" size="15" value="" name="id"><br>
비밀 번호 : <input type="password" size="15" value="" name="password"><br>
           
이름 : <input type="text" size="15" value="" name="name"><br>
소개 : <input type="text" size="15" value="" name="introduce"><br>
<label class="hidden">학과</label>
						<select name="department">
							<option  value="default">전체</option>
							<option  value="computer">컴퓨터공학부</option>
							<option  value="information">정보통신학부</option>
							<option  value="itcontents">IT콘텐츠학과</option>
							<option  value="korean">국어국문학과</option>							
						</select> 
<br>
<input type="submit" value="가입" onclick='this.form.action="signup1";'>
</form>
</body>
</html>

