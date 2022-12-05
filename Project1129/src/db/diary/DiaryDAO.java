package db.diary;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DiaryDAO {
	DBManager dbManager = DBManager.getInstance();
	
	public int insert(Diary diary) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "insert into diary(diary_idx, yy, mm, dd, content, icon)";
		sql+="values(seq_diary.nextval, ?,?,?,?,?)";
		try {
			pstmt = dbManager.getConnection().prepareStatement(sql);
			pstmt.setInt(1, diary.getYy());
			pstmt.setInt(2, diary.getMm());
			pstmt.setInt(3, diary.getDd());
			pstmt.setString(4, diary.getContent());
			pstmt.setString(5, diary.getIcon());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.release(pstmt);
		}
		return result;
	}
	
	//해당 월에 등록된 다이어리 가져오기
	public List selectAll(int yy, int mm) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Diary> list = new ArrayList<Diary>();
		String sql="select * from diary where yy=? and ";
		sql+="mm=? order by diary_idx asc";
		try {
			pstmt = dbManager.getConnection().prepareStatement(sql);
			pstmt.setInt(1, yy);
			pstmt.setInt(2, mm);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Diary diary = new Diary();
				diary.setDiary_idx(rs.getInt("diary_idx"));
				diary.setYy(rs.getInt("yy"));
				diary.setMm(rs.getInt("mm"));
				diary.setDd(rs.getInt("dd"));
				diary.setContent(rs.getString("content"));
				diary.setIcon(rs.getString("icon"));
				list.add(diary);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.release(pstmt, rs);
		}
		return list;
	}
}
