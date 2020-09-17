//201658073 전병천
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
	
	//편의상
	String jdbc_driver = "com.mysql.cj.jdbc.Driver";
	String jdbc_url = "jdbc:mysql://127.0.0.1:3306/dbproject2_member?serverTimezone=UTC";
	
	try {
		//드라이버 로딩
		Class.forName(jdbc_driver);
		
		//연결-url,아이디, 비밀번호
		Connection con = DriverManager.getConnection(jdbc_url,"root","");
		
		//쿼리 위한 statement새성
		Statement stmt =  con.createStatement();

		DatabaseMetaData dbm = con.getMetaData();
		

		//테이블 존재하면 pass 없으면 생성
		//과제 1)Java 프로젝트에서 Statement를 이용하여 addressbook 이라는 table 만들기
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
	
		//컬럼값 존재하면 pass
		//과제2)위에서 생성한 addressbook table에 PreparedStatement를 이용하여 5개의 열을 채우기 (본인 임의로 데이터 생성)
		ResultSet rs = stmt.executeQuery("SELECT * from addressbook");
		
		if (rs.next() == false) {
        int id_arr[] = {365,512,845,745,612};
        String name_arr[] = {"푸틴","김정은","트럼프","아베","문제인"};
        String tel_arr[] = {"010-0000-0000","010-1234-5678","010-79851234-0000","010-000-0000","010-1234-9876"};
        String e_arr[] = {"ag@gmail.com","anc@naver.com","gustjq@gmail.com","qudcjs@gmail.com","answpdls@hanmail.com"};
        String add_arr[] = {"모스크바","평양","워싱턴","도쿄","서울"};
			
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
		
		//전체 출력
		//과제2)addressbook의 모든 데이터를 Statement를 이용해서 조회하여 eclipse의 console에서 볼 수 있도록 반복문 및 System.out.printf 구현
		rs = stmt.executeQuery("SELECT * from addressbook");
		
		System.out.printf("전체 테이블 \n" );
		while(rs.next()) {
			int id = rs.getInt("id");
			String  name = rs.getString("name");
			String  tel = rs.getString("tel");
			String  email = rs.getString("email");
			String  address = rs.getString("address");
			System.out.printf("id:  %d, name: %s, tel: %s, email: %s, address: %s"
					+ "\n", id, name, tel, email, address);
		}
		
		//substring는 문장열에서 특정 문자열 위치에서부터 문자열 시작, instr은 특정 문자열의 위치 반환 두개 응용으로 네이버 메일로 replace
		//과제3)PreparedStatement 이용하여 5개의 열의 email의 도메인을 @naver.com으로 UPDATE 수행
		String SQL_UPDATE = "UPDATE addressbook SET email=REPLACE(email, SUBSTRING(email,INSTR(email,'@')+1),?)" ;
		PreparedStatement st1 = con.prepareStatement(SQL_UPDATE);
		st1.setString(1, "naver.com");
		st1.executeUpdate();
		
		//전체 출력
		//과제3)addressbook의 모든 데이터를 Statement를 이용해서 조회하여 eclipse의 console에서 볼 수 있도록 반복문 및 System.out.printf 구현
		rs = stmt.executeQuery("SELECT * from addressbook");
		
		System.out.printf("메일 변환 \n" );
		while(rs.next()) {
			int id = rs.getInt("id");
			String  name = rs.getString("name");
			String  tel = rs.getString("tel");
			String  email = rs.getString("email");
			String  address = rs.getString("address");
			System.out.printf("id:  %d, name: %s, tel: %s, email: %s, address: %s"
					+ "\n", id, name, tel, email, address);
		}
		
		//id기준 하위 2개 삭제
		String SQL_delete = "DELETE FROM addressbook ORDER BY id  ASC LIMIT 2";
		
		stmt.executeUpdate(SQL_delete);
		
		rs = stmt.executeQuery("SELECT * from addressbook");
		System.out.printf("삭제 변환(id기준 하위 2개) \n" );
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







