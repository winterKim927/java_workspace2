package com.edu.shop.admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class AdminMainMine extends JFrame{
	//서쪽영역
	JPanel p_west;
	JComboBox<String> box_top, box_sub;
	JTextField t_name, t_brand, t_price;
	JPanel preview; //이미지 미리보기 영역 145 145
	JTextField t_url; //이미지 url 입력할 필드
	JButton bt_preview;
	JButton bt_regist;
	
	//센터영역
	JPanel p_center, p_search; //북쪽과 센터로 구분되어질 패널
	JComboBox<String> box_category; //검색 구분
	JTextField t_keyword; //검색어
	JButton bt_search; // 검색 버튼
	
	JTable table;
	JScrollPane scroll;
	
	//동쪽영역
	JPanel p_east;
	JComboBox<String> box_top2, box_sub2;
	JTextField t_name2, t_brand2, t_price2;
	JPanel preview2;
	JTextField t_url2;
	JButton bt_preview2;
	JButton bt_edit, bt_del;
	
	public AdminMainMine() {
		
		//서쪽
		p_west = new JPanel();
		box_top = new JComboBox<String>();
		box_sub = new JComboBox<String>();
		t_name = new JTextField();
		t_brand = new JTextField();
		t_price = new JTextField();
		preview = new JPanel();
		t_url = new JTextField();
		bt_preview = new JButton("미리보기");
		bt_regist = new JButton("등록");
		
		p_west.setPreferredSize(new Dimension(150, 500));
		
		Dimension d = new Dimension(140,20);
		box_top.setPreferredSize(d);
		box_sub.setPreferredSize(d);
		t_name.setPreferredSize(d);
		t_brand.setPreferredSize(d);
		t_price.setPreferredSize(d);
		preview.setPreferredSize(new Dimension(145,145));
		preview.setBackground(Color.yellow);
		t_url.setPreferredSize(d);
		
		p_west.add(box_top);
		p_west.add(box_sub);
		p_west.add(t_name);
		p_west.add(t_brand);
		p_west.add(t_price);
		p_west.add(preview);
		p_west.add(t_url);
		p_west.add(bt_preview);
		p_west.add(bt_regist);
		
		//북쪽
		p_center = new JPanel();
		p_center.setLayout(new BorderLayout());
		p_search = new JPanel();
		box_category = new JComboBox<String>();
		t_keyword = new JTextField(30);
		bt_search = new JButton("검색");

		p_search.add(box_category);
		p_search.add(t_keyword);
		p_search.add(bt_search);
		
		//센터
		table = new JTable(7,6);
		scroll = new JScrollPane(table);
		
		p_center.add(p_search, BorderLayout.NORTH);
		p_center.add(scroll);
		
		//동쪽
		p_east = new JPanel();
		box_top2 = new JComboBox<String>();
		box_sub2 = new JComboBox<String>();
		t_name2 = new JTextField();
		t_brand2 = new JTextField();
		t_price2 = new JTextField();
		preview2 = new JPanel();
		t_url2 = new JTextField();
		bt_preview2 = new JButton("미리보기");
		bt_edit = new JButton("수정");
		bt_del = new JButton("삭제");
		
		p_east.setPreferredSize(new Dimension(150, 500));
		
		box_top2.setPreferredSize(d);
		box_sub2.setPreferredSize(d);
		t_name2.setPreferredSize(d);
		t_brand2.setPreferredSize(d);
		t_price2.setPreferredSize(d);
		preview2.setPreferredSize(new Dimension(145,145));
		preview2.setBackground(Color.yellow);
		t_url2.setPreferredSize(d);
		
		p_east.add(box_top2);
		p_east.add(box_sub2);
		p_east.add(t_name2);
		p_east.add(t_brand2);
		p_east.add(t_price2);
		p_east.add(preview2);
		p_east.add(t_url2);
		p_east.add(bt_preview2);
		p_east.add(bt_edit);
		p_east.add(bt_del);
		
		add(p_west, BorderLayout.WEST);
		add(p_center);
		add(p_east, BorderLayout.EAST);
		
		setSize(900,500);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	public static void main(String[] args) {
		new AdminMainMine();
	}
}
