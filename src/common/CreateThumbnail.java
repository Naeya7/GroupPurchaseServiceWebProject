package common;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.media.jai.JAI;
import javax.media.jai.RenderedOp;

public class CreateThumbnail {
	
	//셋팅 변수
	int size = 2;
	String thumbnailPrepix = "th_";
	
	//싱글톤 패턴
	public static CreateThumbnail session = new CreateThumbnail();
	
	//세션 가져오기
	public static CreateThumbnail getSession() {
		return session;
	}
	
	//썸네일 생성 - return 썸네일 이름 
	public String create(String imagePath, String imgName, String thumbImagePath ) {
		
		//이미지 경로 및 이름
		String imgPathAndName = imagePath + "\\" + imgName;
		
		//원본 이미지 읽기
		RenderedOp op = JAI.create("fileload", imgPathAndName);
		
		//원본 이미지 버퍼 생성
		BufferedImage imgBuffer =  op.getAsBufferedImage();
		
		//사이즈 설정
		int width = imgBuffer.getWidth() / size;
		int height = imgBuffer.getHeight() / size;
			
		//저장될 파일이름생성
		String thumbName = thumbnailPrepix + imgName;
		String thumbPathAndName = thumbImagePath + "\\" + thumbName;
		
		//버퍼만들기_썸네일
		BufferedImage thumbBuffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
		//그래픽 생성
		Graphics g = thumbBuffer.getGraphics();
		g.drawImage(imgBuffer, 0, 0, width, height, null);
		
		
		try {			
			
			//썸네일 생성(확장자에 해당하는 것만 그림)
			ImageIO.write(thumbBuffer, "png", new File(thumbPathAndName));
			ImageIO.write(thumbBuffer, "jpg", new File(thumbPathAndName));
			ImageIO.write(thumbBuffer, "jfif", new File(thumbPathAndName));
			ImageIO.write(thumbBuffer, "gif", new File(thumbPathAndName));	
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//썸네일 이름 리턴
		return thumbName;
	}
}
