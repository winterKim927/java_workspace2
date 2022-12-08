package com.edu.shop.client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

//하나의 상품을 표현할 패널
public class ProductItem extends JPanel{
	public ProductItem() {
		setPreferredSize(new Dimension(850,80));
	}
	
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(Color.CYAN);
		g2.fillRect(0, 0, 850, 80);
	}
}
