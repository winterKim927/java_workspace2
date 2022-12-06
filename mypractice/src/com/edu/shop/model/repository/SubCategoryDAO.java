package com.edu.shop.model.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.edu.shop.domain.SubCategory;
import com.edu.shop.util.DBManager;

public class SubCategoryDAO {
	DBManager manager = DBManager.getInstance();
	
	public List getSelectedSub(int topcategory_idx) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<SubCategory> list = new ArrayList();
		String sql = "select * from subcategory where topcategory_idx = ? order by subcategory_idx asc";
		try {
			pstmt = manager.getConnection().prepareStatement(sql);
			pstmt.setInt(1, topcategory_idx);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				SubCategory subcat = new SubCategory();
				subcat.setSubCategory_idx(rs.getInt(1));
				subcat.setTopCategory_idx(rs.getInt(2));
				subcat.setSubCategory_name(rs.getString(3));
				list.add(subcat);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			manager.release(pstmt, rs);
		}
		return list;
	}
}
