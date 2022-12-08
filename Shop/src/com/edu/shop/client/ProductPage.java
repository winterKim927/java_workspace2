package com.edu.shop.client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

//상품을 출력해주는 페이지
public class ProductPage extends Page{
	JPanel wrapper; //상품 아이템들을 포함할 패널
	JScrollPane scroll;
	List<ProductItem> itemList;
	public ProductPage() {
		wrapper = new JPanel();
		wrapper.setPreferredSize(new Dimension(850,1200));
		itemList = new ArrayList<ProductItem>();
		for (int i = 0; i < 10; i++) {
			ProductItem item = new ProductItem();
			wrapper.add(item);
			itemList.add(item);
		}
		
		scroll = new JScrollPane(wrapper);
		scroll.setPreferredSize(new Dimension(850,400));
		
		add(scroll);
		
		setBackground(Color.orange);
	}
}
