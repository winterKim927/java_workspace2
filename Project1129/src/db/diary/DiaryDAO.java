package db.diary;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DiaryDAO {
	DBManager dbManager = DBManager.getInstance();
	
	public int insert(Diary diary) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "insert into diary(diary_idx, yy, mm, dd, content, icon)";
		sql+="values(seq_diary.nextval, ?,?,?,?,?)";
		try {
			pstmt = dbManager.getConnection().prepareStatement(sql);
//			pstmt.setInt(1, result);
//			pstmt.setInt(2, result);
//			pstmt.setInt(3, result);
//			pstmt.setString(4, result);
//			pstmt.setString(5, result);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
