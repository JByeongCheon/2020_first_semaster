
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DBprint")
public class DBprint extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");//�ѱ�ó��

		PrintWriter out = response.getWriter();
		out.print("<html><head><title>DB���</title></head>");
		
		out.print("<body>");
		request.setCharacterEncoding("UTF-8");//�ѱ�ó��
		
		String jdbc_driver = "com.mysql.cj.jdbc.Driver"; //tomcat�� ����̹� �߰� �� �� !!!!!
		String jdbc_url = "jdbc:mysql://127.0.0.1:3306/dpproject2_member?serverTimezone=UTC";
		String jdbc_user = "root";
		String jdbc_password = "";

			try {
				//����̹� �ε�
				Class.forName(jdbc_driver);
				
				Connection con = DriverManager.getConnection(jdbc_url,jdbc_user,jdbc_password);
				
				//���� ���� statement����
				Statement stmt =  con.createStatement();
				
				DatabaseMetaData dbm = con.getMetaData();
				
				ResultSet rs = stmt.executeQuery("SELECT * from memberbook");
				
				out.print("<h3>DB���</h3>");
				
				while(rs.next()) {
					String id = rs.getString("id");
					String  name = rs.getString("name");
					String  tel = rs.getString("tel");
					String  email = rs.getString("email");
					String  dept = rs.getString("dept");
					String  sex = rs.getString("sex");
					String  birth = rs.getString("birth");
					String  introduce = rs.getString("intro");
					
					out.print("<h3> ID: "+id+", name:"+name+", tel: "+tel+", email: "+email+", dept: "+dept+", sex: "+sex+", birth: "+birth+", introduce: " + introduce+"</h3>");
				
				}
						
				out.println("</body></html>");
				out.close();
				rs.close();
				
				stmt.close();
				con.close();
				}
				
				catch(Exception e) {
					e.printStackTrace();

				}
				
	}
}
	


