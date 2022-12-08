package com.edu.shopclient.client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.edu.shopclient.domain.Product;

//하나의 상품을 표현할 패널
public class ProductItem extends JPanel{
	Image image;
	String path = "D:\\java_workspace2\\data\\shop\\product\\";
	Product product;
	
	public ProductItem(Product product) {
		this.product = product;
		setPreferredSize(new Dimension(850,80));
		createImage();
	}
	
	public void createImage() {
		File file = new File(path+product.getFilename());
		try {
			image = ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(Color.white);
		g2.fillRect(0, 0, 850, 80);
		
		//그림
		g2.drawImage(image, 20, 10, 80, 60, null);
		
		//글씨 (하위, 상품명, 브랜드, 가격)
		g2.setColor(Color.DARK_GRAY);
		g2.drawString(product.getSubcategory().getSubcategory_name(), 120, 15);
		g2.drawString(product.getProduct_name(), 120, 32);
		g2.drawString(product.getBrand(), 120, 49);
		g2.drawString("가격 : " + product.getPrice(), 120, 66);
	}
}
