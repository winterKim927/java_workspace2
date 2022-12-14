package page;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//자바언어에서 DBMS를 연결해본다
public class OracleTest {
	public static void main(String[] args) {
		Connection con=null;
		PreparedStatement pstmt=null;
		
		//알맞는 드라이버를 메모리에 로드하자!!
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); //지정한 문자열의 경로의 클래스를 메모리에 로드시킨다
			System.out.println("드라이버 로드 성공");
		} catch (ClassNotFoundException e1) {
			System.out.println("드라이버가 존재하지 않습니다");
			e1.printStackTrace();
		} 
		
		//켜져있는 오라클 서버에 접속해보자
		
		//접속 url은 정해져있는 규칙을 따라야한다
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String user = "java";
		String pass = "1234";
		try {
			//접속 시도 후, 성공일 경우만 Connection 객체가 반환된다
			//따라서 Connection 객체가 null이라면 접속 실패
			con = DriverManager.getConnection(url, user, pass);
			if(con == null) {
				System.out.println("접속실패");
			} else {
				System.out.println("접속성공");
			}
			//접속을 성공했으니, insert문 실행해보자
			//쿼리문을 실행시키기 위해선 쿼리 담당 객체를 사용해야한다
			//이 때 사용되는 객체명은 PreparedStatement이다
			String sql = "insert into emp2 (ename, job, sal) values('xman', 'none', '4000')";
			pstmt = con.prepareStatement(sql);
			//쿼리실행
			if(pstmt.executeUpdate()>0) System.out.println("성공");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//db관련된 모든 자원 해제
			if(pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if(con != null)
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
}
