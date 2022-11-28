package tablemodel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.table.AbstractTableModel;

public class DeptModel extends AbstractTableModel {
	String[] column;
	String[][] data;
	AppMain2 appMain2;
	public DeptModel(AppMain2 appMain2) {
		this.appMain2 = appMain2;
		select();
		
	}
	public void select() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from dept order by deptno asc";
		try {
			pstmt = appMain2.con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = pstmt.executeQuery();
			rs.last();
			int row = rs.getRow();
			int col = rs.getMetaData().getColumnCount();
			column = new String[col];
			data = new String[row][col];
			for(int i = 0; i<col; i++) {
				column[i] = rs.getMetaData().getColumnName(i+1);
			}
			rs.first();
			for (int i = 0; i < data.length; i++) {
				for (int j = 0; j < column.length; j++) {
					data[i][j] = rs.getObject(j+1)+"";
				}
				rs.next();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			appMain2.release(pstmt, rs);
		}
		
	}
	
	public int getRowCount() {
		return data.length;
	}

	public int getColumnCount() {
		return column.length;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		return data[rowIndex][columnIndex];
	}
	
	public String getColumnName(int col) {
		return column[col];
	}

}
