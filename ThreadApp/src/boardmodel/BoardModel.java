package boardmodel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.table.AbstractTableModel;

public class BoardModel extends AbstractTableModel{
	BoardMain main;
	String[] column;
	String[][] data;
	int rows = 0;
	int columns = 0;
	public BoardModel(BoardMain main) {
		this.main = main;
		select();
	}
	
	public void select() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from board order by board_id desc";
		try {
			pstmt = main.con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = pstmt.executeQuery();
			rs.last();
			rows = rs.getRow();
			columns = rs.getMetaData().getColumnCount();
			column = new String[columns];
			data = new String[rows][columns];
			for (int i = 0; i < column.length; i++) {
				column[i] = rs.getMetaData().getColumnName(i+1);
			}
			rs.first();
			for (int i = 0; i < data.length; i++) {
				for (int j = 0; j < data[i].length; j++) {
					data[i][j] = rs.getString(j+1);
				}
				rs.next();
			}
			System.out.println(data[0][3].toString());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			main.release(pstmt, rs);
		}
		
	}

	
	//레코드 한건 넣기
	public int insert(String title, String writer, String content) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql ="insert into board(board_id, title, writer, content)";
		sql+=" values(seq_board.nextval, '"+title+"', '"+writer+"', '"+content+"')";
		try {
			pstmt = main.con.prepareStatement(sql);
			result = pstmt.executeUpdate();
			if(result !=0) {
				System.out.println("인서트문 잘 들어갔어 임마");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			main.release(pstmt);
		}
		return result;
	}
	
	// 레코드 1건 삭제하기
	// 이 메서드를 호출하는 자는 board_id를 넘겨야한다!!
	public int delete(int board_id) {
		PreparedStatement pstmt = null;
		int result=0;
		String sql = "delete from board where board_id="+board_id;
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
