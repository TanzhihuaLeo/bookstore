package com.qrot.bookstore.background.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qrot.bookstore.background.mapper.BookMapper;
import com.qrot.bookstore.background.model.Book;
import com.qrot.bookstore.util.ImgSrcUtil;
import com.qrot.bookstore.util.UploadUtil;

/**
 * 关于书籍的业务逻辑：依赖数据访问操作
 * @author TanZhiHua
 *
 */
@Service
public class BackBookService {

	@Autowired
	BookMapper bookMapper;
	
	/**
	 * 新增书籍
	 * @param book
	 */
	public void addBook(Book book) {
		bookMapper.addBookInfo(book);
		bookMapper.addBook(book);
	}
	
	public String setCover(String img, String imgName) {
		String localPath = "/img";
		String servicePath = "src/main/resources/static";
		return UploadUtil.decode(img, imgName, localPath, servicePath);
		
	}
	
	/**
	 * 修改书籍信息
	 * @param book
	 */
	public void updateBook(Book book) {
		bookMapper.updateBookInfo(book);
		bookMapper.updateBook(book);
	}
	
	/**
	 * 书籍上架
	 * @param bookid
	 */
	public void recoverBook(int bookid) {
		bookMapper.recoverBook(bookid);
	}
	
	/**
	 * 书籍下架
	 * @param bookid
	 */
	public void removeBook(int bookid) {
		bookMapper.removeBook(bookid);
	}
	
	/**
	 * 得到某一本书的信息信息
	 * @param bookid
	 * @return
	 */
	public Book getBookByID(int bookid) {
		return ImgSrcUtil.getFullPath(bookMapper.getBookByID(bookid));
	}
	
	/**
	 * 统计全部书籍数量
	 * @return
	 */
	public int getAllBookLen() {
		return bookMapper.getAllBookLen();
	}
	
	/**
	 * 获得指定行的书籍信息
	 * 
	 * @param len  分页长度
	 * @param offset
	 * @return
	 */
	public List<Book> getAllBook(int len,int offset){
		return ImgSrcUtil.getFullPath(bookMapper.getAllBook(len, offset));
	}
	
	/**
	 * 统计指定类别的书籍数量
	 * @return
	 */
	public int getKindBookLen(String kind) {
		return bookMapper.getKindBookLen(kind);
	}
	
	/**
	 * 获得某个类别的某些行的书籍信息
	 * @param kind  类别
	 * @param len  分页长度
	 * @param offset 偏移量
	 * @return
	 */
	public List<Book> getKindBook(String kind,int len,int offset){
		return ImgSrcUtil.getFullPath(bookMapper.getKindBook(kind, len,offset));
	}
	
	/**
	 * 得到书籍的所有分类
	 * @return
	 */
	public List<String> getkind(){
		return bookMapper.getkind();
	}
	
	/**
	 * 统计书籍模糊查询结果数量
	 * @param text
	 * @return
	 */
	public int getSelectBookLen(String text) {
		return bookMapper.getSelectBookLen(text);
	}
	
	/**
	 * 对书籍进行模糊查询
	 * @param text
	 * @param offset
	 * @return
	 */
	public List<Book> selectBook(String text, int len, int offset){
		return ImgSrcUtil.getFullPath(bookMapper.selectBook(text, len, offset));
	}
	
	/**
	 * 统计未上架书籍数量
	 * @return
	 */
	public int getDeleteBookLen() {
		return bookMapper.getDeleteBookLen();
	}
	
	/**
	 * 查询未上架书籍
	 * @param offset
	 * @return
	 */
	public List<Book> getDeleteBook(int len, int offset){
		return ImgSrcUtil.getFullPath(bookMapper.getDeleteBook(len, offset));
	}
	
	/**
	 * 统计已上架书籍数量
	 * @return
	 */
	public int getSellingBookLen() {
		return bookMapper.getSellingBookLen();
	}
	
	/**
	 * 查询已上架书籍
	 * @param offset
	 * @return
	 */
	public List<Book> getSellingBook(int len, int offset){
		return ImgSrcUtil.getFullPath(bookMapper.getSellingBook(len, offset));
	}
	
	/**
	 * 统计库存低于某值的已上架书籍数量
	 * @param low
	 * @return
	 */
	public int getLowerStorageLen(int low) {
		return bookMapper.getLowerStorageLen(low);
	}
	
	/**
	 * 查询库存低于某值的书籍
	 * @param low
	 * @param len
	 * @param offset
	 * @return
	 */
	public List<Book> getLowerStorage(int low, int len, int offset){
		return ImgSrcUtil.getFullPath(bookMapper.getLowerStorage(low, len, offset));
	}
}
