package myapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns="/my3")
public class myexample3 extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");//�ѱ�ó��
		request.setCharacterEncoding("UTF-8");
		
		String value_origin = request.getParameter("value"); //�Է� ���� ��û
		int value = 0; //��� ���۰�
		value = Integer.parseInt(value_origin); //v�� ��û�� ������ int�� �޴´�.
		String operator_origin = request.getParameter("operator"); //op�� ��ȣ ������

		ServletContext application = request.getServletContext();
		
		HttpSession session = request.getSession();
		
		Cookie[] cookies = request.getCookies();
		
		
		if(operator_origin.equals("=")) { //��ȣ�� =��
			//application ���
			int result_application = 0; //��� �� �ʱ�ȭ
			
			int x_application = (int) application.getAttribute("value"); //application���� �� ������
			int y_application = value;
			String operator_application = (String)application.getAttribute("operate");
			
			if(operator_application.equals("+")) {
				result_application = x_application + y_application; 
			}
			else {
				result_application = x_application - y_application;
			}
			
			response.getWriter().printf("result_application is %d\n<br>", result_application);
			
			//session ���
			int result_session = 0;
			
			int x_session = (int) session.getAttribute("value");//session���� �� ������
			int y_session = value;
			String operator_session = (String)session.getAttribute("operate");
			
			if(operator_session.equals("+")) {
				result_session = x_session + y_session; 
			}
			else {
				result_session = x_session - y_session;
			}
			
			response.getWriter().printf("result_session is %d\n<br>", result_session);
			
			//cookie ���
			int x_cookie = 0;
			int result_cookie = 0;
			String operator_cookie = ""; //��ȣ�� �ʱ�ȭ

			for(Cookie c : cookies) { //��Ű�� ���� �� ������ c�� ���
				if(c.getName().equals("value")) { //Ű�� value��  int������ �� ȣ��
					x_cookie = Integer.parseInt(c.getValue());					
				}	
				if(c.getName().equals("op")) { //Ű�� op�� ��ȣ�� ������
					operator_cookie = c.getValue(); //���ڷ� ��ȣ�� 				
				}	
			}
			
			int y = value; //y�� �Է� �� ��ȣ ��� �������Ƿ�
			
			if(operator_cookie.equals("+"))
				result_cookie = x_cookie + y;
			else
				result_cookie = x_cookie - y;
					
				response.getWriter().printf("result_cookie is %d\n", result_cookie);
			}			
		else{ 
			
			application.setAttribute( "value", value ); // ���ø����̼ǿ� ����
			application.setAttribute( "operate", operator_origin );
			
			session.setAttribute("value", value); //session�� ����
			session.setAttribute("operate", operator_origin);
			session.setMaxInactiveInterval(600);//600�� ���� ���� ����
			
			
			Cookie valueCookie = new Cookie("value", String.valueOf(value)); //����Ű�� ���� ���ڸ� ������
			Cookie opCookie = new Cookie("op", operator_origin); // ��ȣ ��Ű�� ���� Űȣ�� ������
			valueCookie.setMaxAge(60*60); //��Ű ���ӽð� 1�ð�
			response.addCookie(valueCookie); //��Ű�� �� �������� ������.
			response.addCookie(opCookie);
			
			response.sendRedirect("Calculator2.html");//html�� ����
			
		}	
		
	}	
}
