package com.edu.shop.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ClientMain extends JFrame{
	JPanel p_north; //메뉴버튼이 올 패널
	List<MenuButton> menuList;
	MenuButton m_login, m_join, m_cart;
	JPanel container; //화면 전환에 사용될 루트 컨테이너
	
	Page[] pageList = new Page[4];
	
	public ClientMain() {
		p_north = new JPanel();
		menuList = new ArrayList();
		
		createMenu();
		//로그인, 회원가입, 장바구니
		m_login = new MenuButton("로그인");
		m_join = new MenuButton("회원가입");
		m_cart = new MenuButton("장바구니");
		p_north.add(m_login);
		p_north.add(m_join);
		p_north.add(m_cart);
		
		container = new JPanel(); //모든 페이지를 부착할 패널
		container.setBackground(Color.gray);
		pageList[0] = new ProductPage();
		pageList[1] = new LoginPage();
		pageList[2] = new JoinPage();
		pageList[3] = new CartPage();
		for (int i = 0; i < pageList.length; i++) {
			container.add(pageList[i]);
		}
		
		
		add(p_north, BorderLayout.NORTH);
		add(container);
		
		setSize(900,500);
		setVisible(true);
		setLocationRelativeTo(null);
		
		showHide(0);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
	
	public void createMenu() {
		String[] menuName = {"상의","하의","액세서리","신발"};
		for (int i = 0; i < 4; i++) {
			menuList.add(new MenuButton(menuName[i]));
			p_north.add(menuList.get(i));
		}
	}
	
	public void showHide(int n) {
		for (int i = 0; i < pageList.length; i++) {
			if(i == n) {
				pageList[i].setVisible(true);
			} else {
				pageList[i].setVisible(false);
			}
		}
	}
	
	public static void main(String[] args) {
		new ClientMain();
	}
}
