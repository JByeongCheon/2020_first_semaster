import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.*;

@WebServlet("/check_login")
public class check_login extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");//�ѱ�ó��

		PrintWriter out = response.getWriter();
		out.print("<html><head><title>�Է� �׽�Ʈ</title></head>");
		
		out.print("<body>");
		request.setCharacterEncoding("UTF-8");//�ѱ�ó��
		
		String id = request.getParameter("id");	
		String password = request.getParameter("password");	
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

				
				
				if (tables.next()) { 
					
					String check = "SELECT COUNT(*) FROM final_db where id ='"+id+"'AND password ='" + password + "'"; //id,name ���� �˻�
					ResultSet rs_check = stmt.executeQuery(check);		
					rs_check.next();
					String Countrow_check = rs_check.getString(1);//�ߺ��� ����� 1
					HttpSession session = request.getSession();
				
					if(!Countrow_check.equals("0")  ) { //�ߺ��� ������ ���� //�� �α��� ���� �� 
							    
							String va = "SELECT * FROM final_db ;"; //db�� �ִ� ������ ���� ��������
							ResultSet list_val = stmt.executeQuery(va);	
							int z = 0;
								
							while(list_val.next()) {
								String list_id_val = list_val.getString("id");
								String list_name_val = list_val.getString("name");
								String list_password_val = list_val.getString("password");
								String list_department_val = list_val.getString("department");
								String list_introduce_val = list_val.getString("introduce");
								session.setAttribute("id"+z, list_id_val);
								session.setAttribute("name"+z, list_name_val);
								session.setAttribute("department"+z, list_department_val);
								session.setAttribute("introduce"+z, list_introduce_val);
								session.setAttribute("password"+z, list_password_val);
								z++;		
								 
							}
							session.setAttribute("count", z);
							response.sendRedirect("list_jsp.jsp");

							out.close();
					}
						
						
					else { //���̵� ���� ���� ������ ����,

				        out.print("<h3>�α��� ����</h3>");
  
						out.println("</body></html>");
						out.close();
						
						}
					
			}}
				
			catch(Exception e) {
				e.printStackTrace();

			}	
	
	}
}
	
