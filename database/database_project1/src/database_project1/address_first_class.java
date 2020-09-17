//201658073 ����õ
package database_project1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.*;
import java.util.*;
import java.util.ArrayList;

public class address_first_class {	
	
public static void main(String[] args) {
	
	//���ǻ�
	String jdbc_driver = "com.mysql.cj.jdbc.Driver";
	String jdbc_url = "jdbc:mysql://127.0.0.1:3306/dbproject2_member?serverTimezone=UTC";
	
	try {
		//����̹� �ε�
		Class.forName(jdbc_driver);
		
		//����-url,���̵�, ��й�ȣ
		Connection con = DriverManager.getConnection(jdbc_url,"root","");
		
		//���� ���� statement����
		Statement stmt =  con.createStatement();

		DatabaseMetaData dbm = con.getMetaData();
		

		//���̺� �����ϸ� pass ������ ����
		//���� 1)Java ������Ʈ���� Statement�� �̿��Ͽ� addressbook �̶�� table �����
		ResultSet tables = dbm.getTables(null, null, "addressbook", null);
		
		if (!tables.next()) {
		System.out.printf("ddd");
		StringBuilder sb = new StringBuilder();
        String sql = sb.append("create table  addressbook(")
            	.append("id int,")
            	.append("name VARCHAR(45),")
            	.append("tel VARCHAR(45),")
            	.append("email VARCHAR(60),")
            	.append("address VARCHAR(60)")
            	.append(");").toString();
            stmt.execute(sql);
		}
	
		//�÷��� �����ϸ� pass
		//����2)������ ������ addressbook table�� PreparedStatement�� �̿��Ͽ� 5���� ���� ä��� (���� ���Ƿ� ������ ����)
		ResultSet rs = stmt.executeQuery("SELECT * from addressbook");
		
		if (rs.next() == false) {
        int id_arr[] = {365,512,845,745,612};
        String name_arr[] = {"Ǫƾ","������","Ʈ����","�ƺ�","������"};
        String tel_arr[] = {"010-0000-0000","010-1234-5678","010-79851234-0000","010-000-0000","010-1234-9876"};
        String e_arr[] = {"ag@gmail.com","anc@naver.com","gustjq@gmail.com","qudcjs@gmail.com","answpdls@hanmail.com"};
        String add_arr[] = {"��ũ��","���","������","����","����"};
			
        for(int i = 0;i<=4;i++) {
        String stSql = "INSERT INTO addressbook VALUES (?, ?, ?, ?, ?)";
        PreparedStatement st = con.prepareStatement(stSql);
        st.setInt(1, id_arr[i]);
        st.setString(2, name_arr[i]);
        st.setString(3, tel_arr[i]);
        st.setString(4, e_arr[i]);
        st.setString(5, add_arr[i]);
        st.executeUpdate();
        	}
		}
		
		//��ü ���
		//����2)addressbook�� ��� �����͸� Statement�� �̿��ؼ� ��ȸ�Ͽ� eclipse�� console���� �� �� �ֵ��� �ݺ��� �� System.out.printf ����
		rs = stmt.executeQuery("SELECT * from addressbook");
		
		System.out.printf("��ü ���̺� \n" );
		while(rs.next()) {
			int id = rs.getInt("id");
			String  name = rs.getString("name");
			String  tel = rs.getString("tel");
			String  email = rs.getString("email");
			String  address = rs.getString("address");
			System.out.printf("id:  %d, name: %s, tel: %s, email: %s, address: %s"
					+ "\n", id, name, tel, email, address);
		}
		
		//substring�� ���忭���� Ư�� ���ڿ� ��ġ�������� ���ڿ� ����, instr�� Ư�� ���ڿ��� ��ġ ��ȯ �ΰ� �������� ���̹� ���Ϸ� replace
		//����3)PreparedStatement �̿��Ͽ� 5���� ���� email�� �������� @naver.com���� UPDATE ����
		String SQL_UPDATE = "UPDATE addressbook SET email=REPLACE(email, SUBSTRING(email,INSTR(email,'@')+1),?)" ;
		PreparedStatement st1 = con.prepareStatement(SQL_UPDATE);
		st1.setString(1, "naver.com");
		st1.executeUpdate();
		
		//��ü ���
		//����3)addressbook�� ��� �����͸� Statement�� �̿��ؼ� ��ȸ�Ͽ� eclipse�� console���� �� �� �ֵ��� �ݺ��� �� System.out.printf ����
		rs = stmt.executeQuery("SELECT * from addressbook");
		
		System.out.printf("���� ��ȯ \n" );
		while(rs.next()) {
			int id = rs.getInt("id");
			String  name = rs.getString("name");
			String  tel = rs.getString("tel");
			String  email = rs.getString("email");
			String  address = rs.getString("address");
			System.out.printf("id:  %d, name: %s, tel: %s, email: %s, address: %s"
					+ "\n", id, name, tel, email, address);
		}
		
		//id���� ���� 2�� ����
		String SQL_delete = "DELETE FROM addressbook ORDER BY id  ASC LIMIT 2";
		
		stmt.executeUpdate(SQL_delete);
		
		rs = stmt.executeQuery("SELECT * from addressbook");
		System.out.printf("���� ��ȯ(id���� ���� 2��) \n" );
		while(rs.next()) {
			int id = rs.getInt("id");
			String  name = rs.getString("name");
			String  tel = rs.getString("tel");
			String  email = rs.getString("email");
			String  address = rs.getString("address");
			System.out.printf("id:  %d, name: %s, tel: %s, email: %s, address: %s"
					+ "\n", id, name, tel, email, address);
		}
		
		
		
		rs.close();
		stmt.close();
		con.close();}catch(Exception e) {
			e.printStackTrace();
	
		}
	}
}







