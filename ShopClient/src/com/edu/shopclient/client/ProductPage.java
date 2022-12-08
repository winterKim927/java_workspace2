package com.edu.shopclient.client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.edu.shopclient.domain.Product;
import com.edu.shopclient.model.repository.ProductDAO;

//상품을 출력해주는 페이지
public class ProductPage extends Page{
	JPanel wrapper; //상품 아이템들을 포함할 패널
	JScrollPane scroll;
	List<ProductItem> itemList;
	ProductDAO productDAO;
	
	public ProductPage() {
		productDAO = new ProductDAO();
		wrapper = new JPanel();
		wrapper.setPreferredSize(new Dimension(850,1200));
		itemList = new ArrayList<ProductItem>();
		
		//getProductList();
		
		scroll = new JScrollPane(wrapper);
		scroll.setPreferredSize(new Dimension(850,400));
		
		add(scroll);
		
		setBackground(Color.orange);
	}
	
	public void getProductList(String topcategory_name) {
		List<Product> productList = productDAO.selectAll(topcategory_name);
		itemList.removeAll(itemList);
		wrapper.removeAll(); //지우고 다시 붙이자
		for (int i = 0; i < productList.size(); i++) {
			Product product = productList.get(i);
			ProductItem item = new ProductItem(product);
			wrapper.add(item);
			itemList.add(item);
		}
	}
}
