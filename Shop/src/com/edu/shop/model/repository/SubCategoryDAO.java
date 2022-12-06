package com.edu.shop.model.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.edu.shop.domain.SubCategory;
import com.edu.shop.domain.TopCategory;
import com.edu.shop.util.DBManager;

//이 클래스는 오직 SubCategory 테이블에 대한 CRUD만을 담당
public class SubCategoryDAO {
	DBManager manager = DBManager.getInstance();
	
	public List selectByTopCategory(int topcategory_idx) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<SubCategory> list = new ArrayList();
		con = manager.getConnection();
		String sql = "select * from subcategory where topcategory_idx = ? order by subcategory_idx asc";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, topcategory_idx);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				SubCategory subcategory = new SubCategory();
				subcategory.setSubcategory_idx(rs.getInt("subcategory_idx"));
				subcategory.setTopcategory_idx(topcategory_idx);
				subcategory.setSubcategory_name(rs.getString("subcategory_name"));
				list.add(subcategory);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			manager.release(pstmt, rs);
		}
		return list;
	}
}
