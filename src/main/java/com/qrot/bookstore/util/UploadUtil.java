package com.qrot.bookstore.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.tomcat.util.codec.binary.Base64;

/**
 * 图片上传相关
 * @author TanZhiHua
 *
 */
public class UploadUtil {
	
	private static long imgId = 0;

	// 接收base64编码，转化为图片存储，返回图片存储路径
	public static String decode(String imgStr, String imgName, String localPath, String servicePath) {
		if (imgStr == null)
			return null;
		
		File file = new File(localPath);
		if(!file.exists() && !file.isDirectory()) {
			file.mkdir();
		}
		// 解码
		// 先处理字符串，去掉base64编码的头，如data:image/png;base64,/gfdgdffgdf
		imgStr = imgStr.replace(imgStr.substring(0, imgStr.indexOf(";base64,")+8), "");
		byte[] data = Base64.decodeBase64(imgStr);

		// 保存路径
		String path = localPath + "/" + imgId + imgName;
		imgId++;
		String newPath = servicePath + "/" + path;
		try {
			OutputStream out = new FileOutputStream(newPath);
			out.write(data);
			out.flush();
			out.close();
			return path;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}
