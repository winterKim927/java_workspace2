package com.edu.shop.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import com.edu.shop.domain.TopCategory;
import com.edu.shop.util.DBManager;

//이 클래스는 오직 TopCategory 테이블에 대해서 CRUD만을 수행할 목적으로 정의한다
public class TopCategoryDAO {
	DBManager manager = DBManager.getInstance();
	
	
	public List selectAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<TopCategory> list = new ArrayList();
		con = manager.getConnection();
		String sql = "select * from topcategory order by topcategory_idx asc";
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				TopCategory topcategory = new TopCategory();
				topcategory.setTopcategory_idx(rs.getInt("topcategory_idx"));
				topcategory.setTopcategory_name(rs.getString("topcategory_name"));
				list.add(topcategory);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			manager.release(pstmt, rs);
		}
		return list;
	}
	
	public int getTopCategoryIdx(String name) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int topcategory_idx = 0;
		String sql = "select topcategory_idx from topcategory";
		sql+= " where topcategory_name = ?";
		
		con = manager.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				topcategory_idx = rs.getInt("topcategory_idx");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return topcategory_idx;
	}
}
