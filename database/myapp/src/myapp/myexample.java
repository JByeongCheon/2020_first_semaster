package myapp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class myexample
 */

public class myexample extends HttpServlet {
	public void init(ServletConfig config) throws ServletException{
		System.out.println("init ����");
	}

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
		
		System.out.println("service ����");
		
		response.setContentType("text/html;charset=UTF-8");//�ѱ�ó��
		
		PrintWriter out = response.getWriter();
		out.print("<html><head><title>Test</title></head>");
		out.print("<body><h1>service ����</h1></body>");
		
		doGet(request,response);
		
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");//�ѱ�ó��
		
		PrintWriter out = resp.getWriter();
		out.print("<body><h1>doGet ����</h1></body>");
		out.print("</html>");
		out.close();
	}


	
	public void destroy() {
		
	}

}
