package tablemodel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.table.AbstractTableModel;
	
public class EmpModel extends AbstractTableModel{
	//TableModel이 보유한 메서드들은 JTable이 호출하여 화면에 반영한다
	AppMain2 appMain2;
	String[] column = {"EMPNO","ENAME","JOB","MGR","HIREDATE","SAL","COMM","DEPTNO"};
	String[][] data;
	public EmpModel(AppMain2 appMain2) {
		this.appMain2 = appMain2;
		select();
		
	}
	//EMP의 레코드 가져오기
	public void select() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from emp order by empno asc";
		try {
			pstmt = appMain2.con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = pstmt.executeQuery();
			rs.last();
			int total = rs.getRow();
			data = new String[total][column.length];
			rs.first();
			for (int i = 0; i < data.length; i++) {
				for (int j = 0; j < data[0].length; j++) {
					data[i][j] = rs.getObject(j+1)+"";
				}
				rs.next();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			appMain2.release(pstmt,rs);
		}
	}
	
	public int getRowCount() {
		return data.length;
	}

	public int getColumnCount() {
		return column.length;
	}

	public Object getValueAt(int row, int col) {
		System.out.println("getValueAt("+row+","+col+" ) 호출 ");
		return data[row][col];
	}
	
	public String getColumnName(int col) {
		return column[col];
	}
}
