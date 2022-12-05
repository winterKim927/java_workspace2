package com.edu.shop.admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import com.edu.shop.domain.SubCategory;
import com.edu.shop.domain.TopCategory;
import com.edu.shop.model.SubCategoryDAO;
import com.edu.shop.model.TopCategoryDAO;
import com.edu.shop.util.DBManager;

public class AdminMain extends JFrame{
	TopCategoryDAO topCategoryDAO;
	SubCategoryDAO subCategoryDAO;
	DBManager dbManager = DBManager.getInstance();
	
	//서쪽 영역 
	JPanel p_west;
	JComboBox<String> box_top; //상위 카테고리 
 	JComboBox<String> box_sub; //하위 카테고리 
	JTextField t_name; //상품명
	JTextField t_brand; //브랜드명 
	JTextField t_price; //가격 
	JPanel preview; //이미지 미리보기 영역 145 x 145
	JTextField t_url;//이미지의 웹 주소
	JButton bt_preview; //이미지 미리보기 버튼
	JButton bt_regist; // 등록버튼
	
	//센터영역 
	JPanel p_center;//북쪽과 센터로 구분되어질 패널
	JPanel p_search;//검색기능이 올려질 패널 
	JComboBox<String> box_category; // 검색 구분
	JTextField t_keyword;//검색어
	JButton bt_search; //검색 버튼
	JTable table;
	JScrollPane scroll;
	
	//동쪽 영역 
	JPanel p_east;
	JComboBox<String> box_top2; //상위 카테고리 
	JComboBox<String> box_sub2; //하위 카테고리 
	JTextField t_name2; //상품명
	JTextField t_brand2; //브랜드명 
	JTextField t_price2; //가격 
	JPanel preview2; //이미지 미리보기 영역
	JTextField t_url2;//이미지의 웹 주소
	JButton bt_preview2; //
	JButton bt_edit; //
	JButton bt_del; //
	
	public AdminMain() {
		topCategoryDAO = new TopCategoryDAO();
		subCategoryDAO = new SubCategoryDAO();
		
		//서쪽
		p_west = new JPanel();
		box_top = new JComboBox<String>();
		box_sub = new JComboBox<String>();
		t_name = new JTextField();
		t_brand = new JTextField();
		t_price = new JTextField();
		preview = new JPanel();
		t_url = new JTextField();
		bt_preview = new JButton("가져오기");
		bt_regist = new JButton("등록");
		
		p_west.setPreferredSize(new Dimension(150, 500));
		Dimension d = new Dimension(140, 25);
		box_top.setPreferredSize(d);
		box_sub.setPreferredSize(d);
		t_name.setPreferredSize(d);
		t_brand.setPreferredSize(d);
		t_price.setPreferredSize(d);
		preview.setPreferredSize(new Dimension(140, 140));
		preview.setBackground(Color.BLACK);
		t_url.setPreferredSize(d);
		bt_preview.setPreferredSize(d);
		
		p_west.add(box_top);
		p_west.add(box_sub);
		p_west.add(t_name);
		p_west.add(t_brand);
		p_west.add(t_price);
		p_west.add(preview);
		p_west.add(t_url);
		p_west.add(bt_preview);
		p_west.add(bt_regist);
		
		//센터영역
		p_center = new JPanel();
		
		p_search = new JPanel();
		box_category = new JComboBox<String>();
		t_keyword = new JTextField(25);
		bt_search = new JButton("검색");
		p_search.add(box_category);
		p_search.add(t_keyword);
		p_search.add(bt_search);
		
		table = new JTable(7,6);
		scroll = new JScrollPane(table);
		
		p_center.setLayout(new BorderLayout());
		p_center.add(p_search, BorderLayout.NORTH);
		p_center.add(scroll);
		
		
		//동쪽 
		p_east = new JPanel();
		box_top2 = new JComboBox<String>();
		box_sub2 = new JComboBox<String>();
		t_name2 = new JTextField();
		t_brand2 = new JTextField();
		t_price2 = new JTextField();
		preview2 = new JPanel() {
			
		};
		t_url2 = new JTextField();
		bt_preview2 = new JButton("가져오기");
		bt_edit = new JButton("수정");
		bt_del = new JButton("삭제");
		
		p_east.setPreferredSize(new Dimension(150, 500));
		Dimension d2 = new Dimension(140, 25);
		box_top2.setPreferredSize(d2);
		box_sub2.setPreferredSize(d2);
		t_name2.setPreferredSize(d2);
		t_brand2.setPreferredSize(d2);
		t_price2.setPreferredSize(d2);
		preview2.setPreferredSize(new Dimension(140, 140));
		preview2.setBackground(Color.BLACK);
		t_url2.setPreferredSize(d2);
		bt_preview2.setPreferredSize(d);
		
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
		
		getTopList();
		
		setSize(900,500);
		setVisible(true);
		setLocationRelativeTo(null);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dbManager.release(dbManager.getConnection());
				System.exit(0);
			}
		});
		box_top.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED) {
					getSubList(e);
				}
			}
		});
	}
	
	public void getTopList() {
		List<TopCategory> topList = topCategoryDAO.selectAll();
		box_top.addItem("카테고리 선택");
		for(TopCategory topCategory : topList) {
			box_top.addItem(topCategory.getTopcategory_name());
		}
	}
	
	public void getSubList(ItemEvent e) {
		box_sub.removeAllItems();
		int topcategory_idx = topCategoryDAO.getTopCategoryIdx(e.getItem().toString());
		List<SubCategory> subList = subCategoryDAO.selectByTopCategory(topcategory_idx);
		for(SubCategory subCategory : subList) {
			box_sub.addItem(subCategory.getSubcategory_name());
		}
	}
	
	
	public static void main(String[] args) {
		new AdminMain();
	}
}














