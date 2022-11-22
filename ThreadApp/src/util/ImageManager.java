package util;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class ImageManager {
	//이미지에 대한 경로를 전달하면 이미지 객체를 반환하는 메서드 정의
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
}
