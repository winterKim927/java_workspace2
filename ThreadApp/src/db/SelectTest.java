package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//오라클에 접속하여 emp 테이블의 레코드를 가져와보자!
public class SelectTest {
		//throws란? 해당 예외를 개발자가 처리하기 싫을 때, 이 예외가 발생된 메서드를 호출한, 
		//호출자에게 예외처리를 전가시킨 것 throws ClassNotFoundException
	public static void main(String[] args) {
		/* Java DataBase Connectivity : 자바기반의 데이터버스 연동기술
		  	=> java.sql 패키지에서 지원함
		  	
			JDBC 기반의 DB연동절차
			1) 드라이버 로드(사용하고자 하는 DBMS 제품에 맞는)
				java 언어가 해당 DB 제품을 핸들링하기 위한 라이브러리
			2) 접속
			3) 쿼리 실행 
			4) DB 관련 자원 해제(스트림과 DB는 개발자가 반드시 닫아야함)
		*/
		Connection con = null; //java.sql 패키지
		PreparedStatement pstmt = null; //쿼리수행 객체
		ResultSet rs = null; //java.sql 결과표를 표현한 객체
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); //인스턴스를 올리는 것이 아니라, static 영역으로 지정한 드라이버 클래스를 load 한다.
			String url="jdbc:oracle:thin:@localhost:1521:XE";
			String user="java";
			String pass="1234";
			
			con = DriverManager.getConnection(url, user, pass);
			if(con!=null) {
				System.out.println("접속성공");
			} else {
				System.out.println("접속실패");
			}
			
			String sql="select * from emp order by empno";
			pstmt =con.prepareStatement(sql); //쿼리 수행 객체 생성
			rs = pstmt.executeQuery();
			
			//rs의 레코드를 사용하기 위해서는 커서를 이동시켜야하는데
			//rs가 생성된 초기에는, 그 어떤 레코드도 가리키지 않고 제일 위로 올라와있다
			while (rs.next()) {
				int empno = rs.getInt("empno");
				String ename = rs.getString("ename");
				String job = rs.getString("job");
				System.out.println(empno + "\t" + ename + "\t" + job);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
