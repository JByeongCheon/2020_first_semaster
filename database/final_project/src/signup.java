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

@WebServlet("/signup1")
public class signup extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");//한글처리

		PrintWriter out = response.getWriter();
		out.print("<html><head><title>입력 테스트</title></head>");
		
		out.print("<body>");
		request.setCharacterEncoding("UTF-8");//한글처리
		
		String id = request.getParameter("id");	
		String password = request.getParameter("password");	
		String name = request.getParameter("name");	
		String department = request.getParameter("department");	
		String introduce = request.getParameter("introduce");	
	
		String jdbc_driver = "com.mysql.cj.jdbc.Driver";
		String jdbc_url = "jdbc:mysql://127.0.0.1:3306/final_db?serverTimezone=UTC";
		String jdbc_user = "root";
		String jdbc_password = "";
		

			try {
				//드라이버 로딩
				Class.forName(jdbc_driver);
				
				
				Connection con = DriverManager.getConnection(jdbc_url,jdbc_user,jdbc_password);
				
				Statement stmt =  con.createStatement();
				
				DatabaseMetaData dbm = con.getMetaData();
				
				ResultSet tables = dbm.getTables(null, null, "final_db", null); //테이블 memberbook 존재 확인

				if (tables.next()) { //테이블이 있으면 값만 삽입
					
				String check = "SELECT COUNT(*) FROM final_db where id ='"+id+ "'"; //id 조건 검색
				ResultSet rs_check = stmt.executeQuery(check);		
				rs_check.next();
				String Countrow_check = rs_check.getString(1);//중복값 존재시 1
				
					if(!Countrow_check.equals("0")  ) { //중복값 있으면 실행
				
						    out.print("<h2>이미 있는 ID입니다.</h2>");
							out.println("</body></html>");
							out.close();

						}

					else { //아이디가 같은 값이 없으면 실행, 일반 적인 data삽입
			
						ResultSet rs = stmt.executeQuery("SELECT * from final_db");
						rs = stmt.executeQuery("SELECT * from final_db");
				
						String stSql = "INSERT INTO final_db VALUES (?, ?, ?, ?,?)"; //이거 말고 다른건 왜 안되냐...
						PreparedStatement st = con.prepareStatement(stSql);
		        
						st.setString(1,id);
						st.setString(2, password);
						st.setString(3,name);
						st.setString(4, department);
						st.setString(5, introduce);
				        st.executeUpdate();
				        
				        out.print("<h3>저장 성공</h3>");
						out.print("<h3> ID:"+id+"</h3>");
						out.print("<h3> name:"+password+"</h3>");
						out.println("</body></html>");
						out.close();
						
						rs.close();
						}
					}
				
				else { //테이블 없으면 테이블 생성하고 값 삽입
					StringBuilder sb = new StringBuilder();
			        String sql = sb.append("create table final_db(")
			            	.append("id VARCHAR(45),")
			            	.append("password VARCHAR(45),")
			            	.append("name VARCHAR(45),")
			            	.append("department VARCHAR(45),")
			            	.append("introduce VARCHAR(200)")
			            	.append(");").toString();
			        	System.out.printf("seuccess");
			            stmt.execute(sql);
			            
			            
			            ResultSet rs = stmt.executeQuery("SELECT * from final_db");
			        	
						String stSql = "INSERT INTO final_db VALUES (?, ?, ?, ?, ?)";
				        PreparedStatement st = con.prepareStatement(stSql); //sql문 전달
				        
				        st.setString(1,id);
				        st.setString(2, password);
				        st.setString(3, name);
				        st.setString(4, department);
				        st.setString(5, introduce);
				        st.executeUpdate(); //업데이트
				        
				        out.print("<h3>저장 성공</h3>");
						out.print("<h3> ID:"+id+"</h3>");
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