package myapp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(urlPatterns="/my2")

public class myexample2 extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");//한글처리

		PrintWriter out = response.getWriter();
		out.print("<html><head><title>입력 테스트</title></head>");
		
		out.print("<body><h1>출력 성공</h1>");
		request.setCharacterEncoding("UTF-8");//한글처리 단 body
		
		String id = request.getParameter("id");	
		String password = request.getParameter("pwd");	
		String name = request.getParameter("name");	
		String tel = request.getParameter("tel");	
		String email = request.getParameter("email");	
		String dept = request.getParameter("dept");	
		String sex = request.getParameter("gender");	
		String birth = request.getParameter("birth");	
		String introduce = request.getParameter("introduction");	
		
		out.print("<h3> ID:"+id+"</h3>");
		out.print("<h3> password:"+password+"</h3>");
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

}
