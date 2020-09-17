
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.*;


@WebServlet("/member")
public class member extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");//한글처리

		PrintWriter out = response.getWriter();
		out.print("<html><head><title>입력 테스트</title></head>");
		
		out.print("<body>");
		request.setCharacterEncoding("UTF-8");//한글처리
		
		String id = request.getParameter("id");	
		String password = request.getParameter("pwd");	
		String name = request.getParameter("name");	
		String tel = request.getParameter("tel");	
		String email = request.getParameter("email");	
		String dept = request.getParameter("dept");	
		String sex = request.getParameter("gender");	
		String birth = request.getParameter("birth");	
		String introduce = request.getParameter("introduction");	

		String jdbc_driver = "com.mysql.cj.jdbc.Driver"; //tomcat에 드라이버 추가 할 것 !!!!!
		String jdbc_url = "jdbc:mysql://127.0.0.1:3306/dpproject2_member?serverTimezone=UTC";
		String jdbc_user = "root";
		String jdbc_password = "";

			try {
				//드라이버 로딩
				Class.forName(jdbc_driver);
				
				Connection con = DriverManager.getConnection(jdbc_url,jdbc_user,jdbc_password);
				
				//쿼리 위한 statement새성
				Statement stmt =  con.createStatement();
				
				DatabaseMetaData dbm = con.getMetaData();
				
				ResultSet tables = dbm.getTables(null, null, "memberbook", null); //테이블 memberbook 존재 확인

				if (tables.next()) { //테이블이 있으면 값만 삽입
					
				String check = "SELECT COUNT(*) FROM memberbook where id ='"+id+"'AND name ='" + name + "'"; //id,name 조건 검색
				ResultSet rs_check = stmt.executeQuery(check);		
				rs_check.next();
				String Countrow_check = rs_check.getString(1);//중복값 존재시 1
				
					if(!Countrow_check.equals("0")  ) { //중복값 있으면 실행
						
						String check_password = "SELECT COUNT(*) FROM memberbook where id ='"+id+"'AND name ='" + name + "'AND password ='"
								+password+"'";//셋다 일치 조건 조건 검색
						ResultSet rs_checkpassword = stmt.executeQuery(check_password);		
						rs_checkpassword.next();
						String Countrow_checkpassword = rs_checkpassword.getString(1);//중복값 존재시 1
						
						if(!Countrow_checkpassword.equals("0")) {//이름, id, password 같을 시 업데이트 실행
							String SQL_UPDATE = "UPDATE memberbook SET tel = ?, email = ?, dept = ?, sex = ?, birth = ?, intro = ? where "+
							"id = '" + id + "'AND name ='" + name + "'AND password ='"+password+"'"; //3가지 조건이 같은 행의 update
							  
							PreparedStatement st1 = con.prepareStatement(SQL_UPDATE);
							st1.setString(1, tel);
						    st1.setString(2, email);
						    st1.setString(3, dept);
						    st1.setString(4, sex);
						    st1.setString(5, birth);
						    st1.setString(6, introduce);
						    st1.executeUpdate();
						    
						    out.print("<h2>update success</h2>");
						    out.print("<h3> ID:"+id+"</h3>");
							out.print("<h3> name:"+name+"</h3>");
							out.print("<h3> tel:"+tel+"</h3>");
							out.print("<h3> email:"+email+"</h3>");
							out.print("<h3> dept:"+dept+"</h3>");
							out.print("<h3> sex:"+sex+"</h3>");
							out.print("<h3> birth:"+birth+"</h3>");
							out.print("<h3> introduce:"+introduce+"</h3>");
							out.println("</body></html>");
							out.close();

						}
					
						else {//아이디와 이름이 같아도 password다르면 안됨
							out.print("<h2>WRONG PASSWORD</h2></body></html>");
						}
					
					}
				
					else { //이름과 아이디가 같은 값이 없으면 실행, 일반 적인 data삽입
			
						ResultSet rs = stmt.executeQuery("SELECT * from memberbook");
						rs = stmt.executeQuery("SELECT * from memberbook");
				
						String stSql = "INSERT INTO memberbook VALUES (?, ?, ?, ?, ?,?, ?, ?, ?)"; //이거 말고 다른건 왜 안되냐...
						PreparedStatement st = con.prepareStatement(stSql);
		        
						st.setString(1,id);
						st.setString(2, password);
				        st.setString(3, name);
				        st.setString(4, tel);
				        st.setString(5, email);
				        st.setString(6, dept);
				        st.setString(7, sex);
				        st.setString(8, birth);
				        st.setString(9, introduce);
				        st.executeUpdate();
				        
				        out.print("<h3>저장 성공</h3>");
						out.print("<h3> ID:"+id+"</h3>");
						out.print("<h3> name:"+name+"</h3>");
						out.print("<h3> tel:"+tel+"</h3>");
						out.print("<h3> email:"+email+"</h3>");
						out.print("<h3> dept:"+dept+"</h3>");
						out.print("<h3> sex:"+sex+"</h3>");
						out.print("<h3> birth:"+birth+"</h3>");
						out.print("<h3> introduce:"+introduce+"</h3>");
						out.println("</body></html>");
						out.close();
						
						rs.close();
						}
					}
				
				else { //테이블 없으면 테이블 생성하고 값 삽입
					StringBuilder sb = new StringBuilder();
			        String sql = sb.append("create table memberbook(")
			            	.append("id VARCHAR(45),")
			            	.append("password VARCHAR(45),")
			            	.append("name VARCHAR(45),")
			            	.append("tel VARCHAR(60),")
			            	.append("email VARCHAR(60),")
			            	.append("dept VARCHAR(60),")
			            	.append("sex VARCHAR(60),")
			            	.append("birth VARCHAR(60),")
			            	.append("intro VARCHAR(300)")
			            	.append(");").toString();
			        	System.out.printf("seuccess");
			            stmt.execute(sql);
			            
			            
			            ResultSet rs = stmt.executeQuery("SELECT * from memberbook");
			        	
						String stSql = "INSERT INTO memberbook VALUES (?, ?, ?, ?, ?,?, ?, ?, ?)"; //이거 말고 다른건 왜 안되냐...
				        PreparedStatement st = con.prepareStatement(stSql); //sql문 전달
				        
				        st.setString(1,id);
				        st.setString(2, password);
				        st.setString(3, name);
				        st.setString(4, tel);
				        st.setString(5, email);
				        st.setString(6, dept);
				        st.setString(7, sex);
				        st.setString(8, birth);
				        st.setString(9, introduce);
				        st.executeUpdate(); //업데이트
				        
				        out.print("<h3>저장 성공</h3>");
						out.print("<h3> ID:"+id+"</h3>");
						out.print("<h3> name:"+name+"</h3>");
						out.print("<h3> tel:"+tel+"</h3>");
						out.print("<h3> email:"+email+"</h3>");
						out.print("<h3> dept:"+dept+"</h3>");
						out.print("<h3> sex:"+sex+"</h3>");
						out.print("<h3> birth:"+birth+"</h3>");
						out.print("<h3> introduce:"+introduce+"</h3>");
						out.println("</body></html>");
						out.close();
						
						rs.close();}
				
						stmt.close();
						con.close();
				}
			catch(Exception e) {
				e.printStackTrace();

			}	
	}	
	
}
