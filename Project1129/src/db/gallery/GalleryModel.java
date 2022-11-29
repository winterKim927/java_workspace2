package db.gallery;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.table.AbstractTableModel;

//Gallery 테이블과 관련된 CRUD를 담당하고, JTable에게 정보를 제공해주는 객체
public class GalleryModel extends AbstractTableModel {
	String column[] = {"gallery_id", "title","writer","content","filename","regdate"};
	String data[][];
	GalleryMain main;
	public GalleryModel(GalleryMain main) {
		this.main = main;
		selectAll();
	}
	
	//모든 데이터 가져오기
	public void selectAll() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int row = 0;
		int col = 0;
		String sql = "select * from gallery order by gallery_id desc";
		try {
			pstmt = main.con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = pstmt.executeQuery();
			rs.last();
			row = rs.getRow();
			col = rs.getMetaData().getColumnCount();
			data = new String[row][col];
			rs.first();
			for (int i = 0; i < data.length; i++) {
				for (int j = 0; j < data[i].length; j++) {
					data[i][j] = rs.getString(j+1);
				}
				rs.next();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			main.release(pstmt, rs);
		}
	}
	
	//한 건 가져오기
	public void select() {
		
	}
	
	//insert
	public int insert(String title, String writer, String content, String filename) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "insert into gallery(gallery_id, title, writer, content, filename)";
		sql+= " values(seq_gallery.nextval, '"+title+"', '"+writer+"', '"+content+"','"+filename+"')";
		//select seq_gallery.currval from dual;
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
	
	//update
	public int update(String title, String writer, String content, int gallery_id) {
		PreparedStatement pstmt = null;
		int result=0;
		String sql = "update gallery set title = '"+title+"', writer = '"+writer+"', content = '"+content+"'";
		sql+= " where gallery_id ="+gallery_id;
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
	
	//delete
	public int delete(int gallery_id) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "delete gallery where gallery_id="+gallery_id;
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
	

	public int getRowCount() {
		return data.length;
	}

	public int getColumnCount() {
		return column.length;
	}

	public Object getValueAt(int row, int col) {
		return data[row][col];
	}
	
	public String getColumnName(int col) {
		return column[col];
	}
}
