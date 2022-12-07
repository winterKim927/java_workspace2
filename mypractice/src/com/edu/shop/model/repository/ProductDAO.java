package com.edu.shop.model.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.edu.shop.domain.Product;
import com.edu.shop.domain.SubCategory;
import com.edu.shop.util.DBManager;

public class ProductDAO {
	DBManager manager = DBManager.getInstance();
	
	public List selectAll() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Product> list = new ArrayList();
		//String sql = "select product_idx, subcategory_idx, product_name, brand, price, filename from product";
		String sql = "select s.subcategory_idx, s.subcategory_name, product_idx, product_name, brand, price, filename"
				+ " from subcategory s, product d"
				+ " where s.subcategory_idx = d.subcategory_idx";
		try {
			pstmt = manager.getConnection().prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Product product = new Product();
				SubCategory sub = new SubCategory();
				product.setSubCategory(sub);
				product.getSubCategory().setSubCategory_idx(rs.getInt(1));
				product.getSubCategory().setSubCategory_name(rs.getString(2));
				product.setProduct_idx(rs.getInt(3));
				product.setProduct_name(rs.getString(4));
				product.setBrand(rs.getString(5));
				product.setPrice(rs.getInt(6));
				product.setFilename(rs.getString(7));
				list.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			manager.release(pstmt, rs);
		}
		return list;
	}
}
