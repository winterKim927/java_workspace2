package com.edu.shop.model.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.edu.shop.domain.TopCategory;
import com.edu.shop.util.DBManager;

public class TopCategoryDAO {
	DBManager manager = DBManager.getInstance();
	
	public List selectAll() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<TopCategory> list = new ArrayList();
		String sql = "select * from topcategory order by topcategory_idx";
		try {
			pstmt = manager.getConnection().prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				TopCategory topcategory = new TopCategory();
				topcategory.setTopCategory_idx(rs.getInt("topcategory_idx"));
				topcategory.setTopCategory_name(rs.getString("topcategory_name"));
				list.add(topcategory);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			manager.release(pstmt, rs);
		}
		return list;
	}
	
	public int getTopIdx(String topcategory_name) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		System.out.println(topcategory_name);
		String sql = "select topcategory_idx from topcategory where topcategory_name = ?";
		try {
			pstmt = manager.getConnection().prepareStatement(sql);
			pstmt.setString(1, topcategory_name);
			rs = pstmt.executeQuery();
			if(rs.next())
			result = rs.getInt("topcategory_idx");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return result;
	}
}
