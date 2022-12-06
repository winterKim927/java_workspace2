package com.edu.shop.model.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.edu.shop.admin.AdminMain;
import com.edu.shop.domain.Product;
import com.edu.shop.domain.SubCategory;
import com.edu.shop.util.DBManager;

//Product 테이블에 대한 CRUD를 수행할 객체
public class ProductDAO {
	DBManager manager = DBManager.getInstance();
	
	public int insert(Product product) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "insert into product(product_idx, subcategory_idx, product_name, brand, price, filename)";
		sql+= " values(seq_product.nextval, ?,?,?,?,?)";
		try {
			pstmt = manager.getConnection().prepareStatement(sql);
			pstmt.setInt(1, product.getSubcategory().getSubcategory_idx());
			pstmt.setString(2, product.getProduct_name());
			pstmt.setString(3, product.getBrand());
			pstmt.setInt(4, product.getPrice());
			pstmt.setString(5, product.getFilename());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			manager.release(pstmt);
		}
		return result;
	}
	
	public List selectAll() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Product> list = new ArrayList<Product>();
		//String sql = "SELECT s.subcategory_idx, subcategory_name, product_idx, product_name, brand, price, filename"
		//		+ " FROM SUBCATEGORY s , PRODUCT p WHERE s.SUBCATEGORY_IDX = p.SUBCATEGORY_IDX";
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT s.subcategory_idx as subcategory_idx, subcategory_name,");
		sb.append("product_idx, product_name, brand, price, filename");
		sb.append(" FROM SUBCATEGORY s , PRODUCT p");
		sb.append(" WHERE s.SUBCATEGORY_IDX = p.SUBCATEGORY_IDX");
		try {
			pstmt = manager.getConnection().prepareStatement(sb.toString());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Product product = new Product();
				SubCategory subCategory = new SubCategory();
				product.setSubcategory(subCategory);
				subCategory.setSubcategory_idx(rs.getInt("subcategory_idx")); 
				subCategory.setSubcategory_name(rs.getString("subcategory_name"));
				product.setProduct_idx(rs.getInt("product_idx"));
				product.setProduct_name(rs.getString("product_name"));
				product.setBrand(rs.getString("brand"));
				product.setPrice(rs.getInt("price"));
				product.setFilename(rs.getString("filename"));
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
