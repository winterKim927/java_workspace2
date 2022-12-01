package util;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ImageManager {
	//이미지배열에 대한 경로를 전달하면 이미지 객체 배열을 반환하는 메서드 정의
	public Image[] createImages(String[] imgName) {
		Class myClass=this.getClass();
		Image[] images = new Image[imgName.length];
		
		for (int i = 0; i < imgName.length; i++) {
			URL url = myClass.getClassLoader().getResource(imgName[i]); //자원의 위치를 의미, 패키지 경로도 처리 가능
			try {
				images[i] = ImageIO.read(url);
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
		return images;
	}
	
	//이미지의 경로, 원하는 너비, 높이를 입력받아 스케일링된 이미지를 사용한 아이콘을 반환하는 메서드
	public ImageIcon getIcon(String path, int width, int height) {
		Class myClass = this.getClass();
		URL url = myClass.getClassLoader().getResource(path);
		Image img = null;
		try {
			img = ImageIO.read(url);
			img = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		} catch (IOException e) {
			e.printStackTrace();
		}
		ImageIcon icon = new ImageIcon(img);
		
		return icon;
	}
	
	//이미지에 대한 경로를 전달하면 이미지 객체를 반환하는 메서드 정의
	public Image getImage(String path, int width, int height) {
		Class myClass=this.getClass();
		URL url = myClass.getClassLoader().getResource(path);
		Image image = null;
		try {
			image = ImageIO.read(url);
			image = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return image;
	}
}
