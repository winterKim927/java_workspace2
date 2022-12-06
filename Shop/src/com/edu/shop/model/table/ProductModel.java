package com.edu.shop.model.table;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.edu.shop.admin.AdminMain;
import com.edu.shop.domain.Product;

public class ProductModel extends AbstractTableModel{
	AdminMain main;
	List<Product> productList;
	String[] colName = {"하위IDX","하위카테고리","상품IDX","이름","브랜드","가격","이미지파일"};
	public ProductModel(AdminMain main) {
		this.main = main;
		getProductList();
	}
	
	public void getProductList() {
		productList = main.productDAO.selectAll();
	}
	
	@Override
	public int getRowCount() {
		return productList.size();
	}

	@Override
	public int getColumnCount() {
		return 7;
	}

	@Override
	public Object getValueAt(int row, int col) {
		Product product =productList.get(row);
		String value = null;
		switch(col) {
			case 0 : value = product.getSubcategory().getSubcategory_idx() + ""; break;
			case 1 : value = product.getSubcategory().getSubcategory_name(); break;
			case 2 : value = product.getProduct_idx()+""; break;
			case 3 : value = product.getProduct_name(); break;
			case 4 : value = product.getBrand(); break;
			case 5 : value = product.getPrice()+""; break;
			case 6 : value = product.getFilename(); break;
		}
		return value;
	}
	@Override
	public String getColumnName(int column) {
		return colName[column];
	}
}
