package page;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MySQLTest {

	public static void main(String[] args) {
		//MySQL용 드라이버를 로드하자
		try {
			Class.forName("com.mysql.jdbc.Driver");// static 영역으로 원본코드 로드
			System.out.println("드라이버 로드 성공");
			
			//접속 시도
			String url = "jdbc:mysql://localhost:3306/java";
			String user = "root";
			String pass = "1234";
			
			Connection con = DriverManager.getConnection(url, user, pass);
			if(con!=null) {System.out.println("접속성공");}
			
			String sql = "insert into emp2(empno, ename) values(2834, 'winter')";
			PreparedStatement pstmt = con.prepareStatement(sql);
			int result = pstmt.executeUpdate();
			if(result > 0) System.out.println("쿼리성공");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("드라이버 로드 실패");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
		}
	}

}
