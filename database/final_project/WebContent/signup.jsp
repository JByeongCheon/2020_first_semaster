<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<h3>DBP �л�����</h3>
<hr>
<form action="signup1" method="post">

����� ID : <input type="text" size="15" value="" name="id"><br>
��� ��ȣ : <input type="password" size="15" value="" name="password"><br>
           
�̸� : <input type="text" size="15" value="" name="name"><br>
�Ұ� : <input type="text" size="15" value="" name="introduce"><br>
<label class="hidden">�а�</label>
						<select name="department">
							<option  value="default">��ü</option>
							<option  value="computer">��ǻ�Ͱ��к�</option>
							<option  value="information">��������к�</option>
							<option  value="itcontents">IT�������а�</option>
							<option  value="korean">������а�</option>							
						</select> 
<br>
<input type="submit" value="����" onclick='this.form.action="signup1";'>
</form>
</body>
</html>

