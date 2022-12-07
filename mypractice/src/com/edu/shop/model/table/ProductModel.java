package com.edu.shop.model.table;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.edu.shop.admin.AdminMain;
import com.edu.shop.domain.Product;
import com.edu.shop.model.repository.ProductDAO;

public class ProductModel extends AbstractTableModel{
	AdminMain main;
	List<Product> list = new ArrayList();
	String[] colName = {"카테고리번호","카테고리이름","상품번호","상품이름","브랜드","가격","이미지"};
	public ProductModel(AdminMain main) {
		this.main = main;
		getProductList();
	}
	
	public void getProductList() {
		list = main.productDAO.selectAll();
	}
	
	public int getRowCount() {
		return list.size();
	}

	public int getColumnCount() {
		return 7;
	}

	public Object getValueAt(int row, int col) {
		Product product = list.get(row);
		String value = null;
		switch(col) {
		case 0 : value = product.getSubCategory().getSubCategory_idx()+""; break;
		case 1 : value = product.getSubCategory().getSubCategory_name()+""; break;
		case 2 : value = product.getProduct_idx()+""; break;
		case 3 : value = product.getProduct_name(); break;
		case 4 : value = product.getBrand(); break;
		case 5 : value = product.getPrice()+""; break;
		case 6 : value = product.getFilename(); break;
		}
		return value;
	}
	
	public String getColumnName(int col) {
		return colName[col];
	}
}
