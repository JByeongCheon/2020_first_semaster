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
		response.setContentType("text/html;charset=UTF-8");//�ѱ�ó��

		PrintWriter out = response.getWriter();
		out.print("<html><head><title>�Է� �׽�Ʈ</title></head>");
		
		out.print("<body>");
		request.setCharacterEncoding("UTF-8");//�ѱ�ó��
		
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
				//����̹� �ε�
				Class.forName(jdbc_driver);
				
				
				Connection con = DriverManager.getConnection(jdbc_url,jdbc_user,jdbc_password);
				
				Statement stmt =  con.createStatement();
				
				DatabaseMetaData dbm = con.getMetaData();
				
				ResultSet tables = dbm.getTables(null, null, "final_db", null); //���̺� memberbook ���� Ȯ��

				if (tables.next()) { //���̺��� ������ ���� ����
					
				String check = "SELECT COUNT(*) FROM final_db where id ='"+id+ "'"; //id ���� �˻�
				ResultSet rs_check = stmt.executeQuery(check);		
				rs_check.next();
				String Countrow_check = rs_check.getString(1);//�ߺ��� ����� 1
				
					if(!Countrow_check.equals("0")  ) { //�ߺ��� ������ ����
				
						    out.print("<h2>�̹� �ִ� ID�Դϴ�.</h2>");
							out.println("</body></html>");
							out.close();

						}

					else { //���̵� ���� ���� ������ ����, �Ϲ� ���� data����
			
						ResultSet rs = stmt.executeQuery("SELECT * from final_db");
						rs = stmt.executeQuery("SELECT * from final_db");
				
						String stSql = "INSERT INTO final_db VALUES (?, ?, ?, ?,?)"; //�̰� ���� �ٸ��� �� �ȵǳ�...
						PreparedStatement st = con.prepareStatement(stSql);
		        
						st.setString(1,id);
						st.setString(2, password);
						st.setString(3,name);
						st.setString(4, department);
						st.setString(5, introduce);
				        st.executeUpdate();
				        
				        out.print("<h3>���� ����</h3>");
						out.print("<h3> ID:"+id+"</h3>");
						out.print("<h3> name:"+password+"</h3>");
						out.println("</body></html>");
						out.close();
						
						rs.close();
						}
					}
				
				else { //���̺� ������ ���̺� �����ϰ� �� ����
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
				        PreparedStatement st = con.prepareStatement(stSql); //sql�� ����
				        
				        st.setString(1,id);
				        st.setString(2, password);
				        st.setString(3, name);
				        st.setString(4, department);
				        st.setString(5, introduce);
				        st.executeUpdate(); //������Ʈ
				        
				        out.print("<h3>���� ����</h3>");
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