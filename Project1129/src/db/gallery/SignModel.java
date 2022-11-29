package db.gallery;

import java.sql.PreparedStatement;
import java.sql.SQLException;

//싸인 좌표 테이블은 dot에 대한 crud를 담당할 객체
public class SignModel {
	GalleryMain main;
	public SignModel(GalleryMain main) {
		this.main = main;
	}
	public int insert(int x, int y, int gallery_id) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "insert into dot(dot_id, x, y, gallery_id)";
		sql+= " values(seq_dot.nextval, "+x+", "+y+", "+gallery_id+")";
		try {
			pstmt = main.con.prepareStatement(sql);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			main.release(pstmt);
		}
		return result;
	}
}
