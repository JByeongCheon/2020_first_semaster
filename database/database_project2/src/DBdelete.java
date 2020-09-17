
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/DBdelete")
public class DBdelete extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html;charset=UTF-8");//한글처리
		out.print("<html><head><title>DB삭제</title></head>");
		
		out.print("<body>");
		request.setCharacterEncoding("UTF-8");//한글처리
		
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
				
				
				
				String stSql = "TRUNCATE TABLE memberbook";
				PreparedStatement st = con.prepareStatement(stSql);
		        st.executeUpdate();
		        
		        out.print("<h3>table DELETE</h3></body></html>");
	}
			catch(Exception e) {
				e.printStackTrace();

			}
	}

}
