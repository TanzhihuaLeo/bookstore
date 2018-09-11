package com.qrot.bookstore.util;

import java.util.List;

import com.qrot.bookstore.background.model.Book;

/**
 * 图片上传相关
 * @author TanZhiHua
 *
 */
public class ImgSrcUtil {
	
	public static Book getFullPath(Book b) {
		b.setCover("http://localhost:8080/" + b.getCover());
		return b;
	}
	
	public static List<Book> getFullPath(List<Book> books){
		for(int i=0; i < books.size(); i++) {
			books.get(i).setCover("http://localhost:8080/" +books.get(i).getCover());
		}
		return books;
	}

}
