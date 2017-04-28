package com.dgit.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;

public class UploadFileUtils {
	private static final Logger logger = 
		      LoggerFactory.getLogger(UploadFileUtils.class);
	
	public static String makeThumbnail(String uploadPath, String filename) throws IOException{
		String thumbnailName = "";
		
		//원본 이미지 가져오기
		BufferedImage sourceImg = ImageIO.read(new File(uploadPath, filename));
		
		//썹네일 이미지 데이타 만들기, 썸네일 이미지의 높이를 뒤의 100px로 동일하게 만들음.
		BufferedImage destImg  = Scalr.resize(sourceImg,
												Scalr.Method.AUTOMATIC, 
												Scalr.Mode.FIT_TO_HEIGHT,
												100);
		//c:/zzz/upload/2017/04/24/s_salksjdfljasdf.jpg
		thumbnailName = uploadPath +"/" + "s_" + filename;
		
		File newFile = new File(thumbnailName);
		String format = filename.substring(filename.lastIndexOf(".")+1);//확장자 찾기
		
		//Thumbnail 경로/파일 이름에 resizing된 이미지를 넣는다.
	    ImageIO.write(destImg, format.toUpperCase(), newFile);
		
		return "s_" + filename;
	}
	
	//upload하면서, thumbnail 이미지도 만들도록 함.
	public static String uploadFile(String uploadPath, //c:/zzz/upload 
						            String originalName,  //aaa.png
						            byte[] fileData) //file data
						            		throws Exception{		
		// ---------------
		// Upload 부분 
		// ---------------
		UUID uid = UUID.randomUUID();//고유한 키 이름
		String savedName = uid.toString() + "_" + originalName;
		
		//한 폴더에 저장할 수 있는 용량이 제한되어 있으므로, 날짜별로 업로드가 되도록 처리한다.
		String savedPath = calcPath(uploadPath); //최종 저장될 날짜 폴더 리턴해줌
		
		File target = new File(uploadPath+savedPath, savedName);//외부 경로에 파일 생성
		FileCopyUtils.copy(fileData, target);//file upload됨	
		
		// ---------------
	    // Thumbnail부분 
	    // ---------------
		String thumFile = UploadFileUtils.makeThumbnail(uploadPath+savedPath, savedName);
		
		//thumbnail이미지 경로 return		
		return savedPath+"/"+thumFile;
	}
	
	private static String calcPath(String uploadPath){
		Calendar cal = Calendar.getInstance();    
		
		String yearPath = "/"+cal.get(Calendar.YEAR);		
		String monthPath = String.format("%s/%02d", yearPath, cal.get(Calendar.MONTH) + 1);			
		String datePath = String.format("%s/%02d", monthPath, cal.get(Calendar.DATE));
		
		makeDir(uploadPath, yearPath, monthPath, datePath);
		
		return datePath;//최종 파일이 저장될 경로. uploadPath는 빠져있음.
	}
	
	public static void makeDir(String uploadPath, String... paths){
		
		for (String path : paths) {
			File dirPath = new File(uploadPath + path);
			
			if(!dirPath.exists()){
				dirPath.mkdir();
			}
		}	
	}
	
}











