package com.edu.shopclient.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

//쇼핑몰의 메뉴 기능 담당
public class MenuButton extends JButton implements ActionListener{
	ClientMain main;
	int pageNum;
	String title;
	
	public MenuButton(ClientMain main, int pageNum, String title) {
		super(title);
		this.main = main;
		this.pageNum = pageNum;
		this.title = title;
		addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		main.showHide(pageNum);
		if(pageNum == main.PRODUCTPAGE) {
			ProductPage p = (ProductPage) main.pageList[0];
			p.getProductList(title);
			main.container.updateUI();
		}
	}
}
