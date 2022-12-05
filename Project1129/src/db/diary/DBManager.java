package db.diary;
/*
데이터베이스 접속과 해제 등은 디자인 영역이 아니기 때문에
만일 JFrame을 상속받는 클래스 내에 두면 재사용성도 떨어지고
디자인과 로직이 뒤섞여 유지보수성도 떨어진다.. 앞으로는 DB 관련 코드는
무조건 물리적인 파일로 분리시켜 개발한다

이 클래스의 역할
1) 오라클에 접속 -> Connection 보유 및 공유
2) 공유한 Connection을 release
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;


public class DBManager {
	private static DBManager instance;
	private String driver, url, user, pass;
	private Connection connection;
	
	private DBManager() {
		loadConfig();
		connect();
	}
	
	public static DBManager getInstance() {
		if(instance==null) {
			instance = new DBManager();
		}
		return instance;
	}
	
	//db 연결 정보 가져오기
	public void loadConfig() {
		FileInputStream fis = null;
		URL path = this.getClass().getClassLoader().getResource("res/db/db.properties");
		Properties props;
		try {
			File file = new File(path.toURI());
			fis = new FileInputStream(file);
			props = new Properties();
			props.load(fis);
			driver = props.getProperty("driver");
			url = props.getProperty("url");
			user = props.getProperty("user");
			pass = props.getProperty("pass");
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	//오라클 접속
	public void connect() {
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, user, pass);
			if(connection!=null) {
				System.out.println("접속완료");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() {
		return connection;
	}
	
	public void release(Connection connection) {
		if(connection!=null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public void release(PreparedStatement pstmt) {
		if(pstmt!=null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public void release(PreparedStatement pstmt, ResultSet rs) {
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
	}
	
	//외부에서 connection을 가져갈 수 있도록 해주자
	
	
	
//	public static void main(String[] args) {
//		new DBManager();
//	}
}
