package db.diary;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Cell extends JPanel {
	String title;
	String content;
	int fontSize,x,y;
	public Cell(String title, String content, int fontSize, int x, int y) {
		this.title = title;
		this.content = content;
		this.fontSize = fontSize;
		this.x = x;
		this.y = y;
   }
}