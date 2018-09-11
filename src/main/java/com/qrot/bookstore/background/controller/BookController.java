package com.qrot.bookstore.background.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.qrot.bookstore.background.model.Book;
import com.qrot.bookstore.background.service.BackBookService;

/**
 * 
 * @author TanZhiHua
 *
 */
@RestController
@RequestMapping("/api/1")
@CrossOrigin
public class BookController {

	@Autowired
	BackBookService service;
	
	/**
	 * 新增书籍
	 * @param book
	 * @return
	 */
	@PostMapping(path="/addBook")
	public String add(@RequestBody Book book) {
		
		service.addBook(book);
		//返回状态码
		return "{msg: 'ok'}";
	}
	
	/**
	 * 上传书籍
	 * @param img
	 * @param imgName
	 * @return
	 */
	@PostMapping(path="/uploadImg",consumes="application/json",produces="application/json")
	@ResponseBody
	public String upload(@RequestBody String img, @RequestParam String imgName){
		String msg = service.setCover(img,imgName);
		if(msg==null) {
			return "{msg:'上传失败！'}";
		}
		return msg;
	}
	
	/**
	 * 修改书籍信息
	 * @param bookid
	 * @param book
	 * @return
	 */
	@PutMapping(path="/updateBook",consumes="application/json",produces="application/json")
	public String updateBook(@RequestBody Book book) {
		service.updateBook(book);
		return "{msg: 'ok'}";
	}
	
	/**
	 * 书籍上架
	 * @param id
	 * @return
	 */
	@PutMapping(path="/recoverBook/{id}",consumes="application/json",produces="application/json")
	public String recoverBook(@PathVariable int id) {
		service.recoverBook(id);
		return "{msg:'ok'}";
	}
	
	/**
	 * 书籍下架
	 * @param bookid
	 * @return
	 */
	@PutMapping(path="/removeBook/{bookid}",consumes="application/json",produces="application/json")
	public String removeBook(@PathVariable int bookid) {
		service.removeBook(bookid);
		return "{msg:'ok'}";
	}
	
	/**
	 * 通过bookid查询书籍信息
	 * 
	 * @param bookid
	 * @return
	 */
	@GetMapping(path="/getBook/{bookid}")
	@ResponseBody
	public Book getOneBook(@PathVariable int bookid) {
		return service.getBookByID(bookid);
	}
	
	/**
	 * 统计全部书籍数量
	 * @return
	 */
	@GetMapping(path="/len/getAllBook")
	@ResponseBody
	public int getAllBookLen() {
		return service.getAllBookLen();
	}
	
	/**
	 * 获得指定哪些行的书籍信息
	 * 
	 * @param len
	 * @param offset
	 * @return
	 */
	@GetMapping(path="/getAllBook")
	@ResponseBody
	public List<Book> getAllBook(
			@RequestParam(name="l", defaultValue="5",required=false) int len,
			@RequestParam(name="o", defaultValue="0", required=false) int offset){
		return service.getAllBook(len, offset);
	}
	
	/**
	 * 统计指定类别的书籍数量
	 * @return
	 */
	@GetMapping(path="/len/kindBook")
	@ResponseBody
	public int getKindBookLen(@RequestParam(name="k", defaultValue="''", required=true) String kind) {
		return service.getKindBookLen(kind);
	}
	
	/**
	 * 获得某个类别的书籍信息
	 * @param kind  类别
	 * @param len   长度
	 * @param offset 偏移量
	 * @return
	 */
	@GetMapping(path="/kindBook")
	@ResponseBody
	public List<Book> getKindBook(
			@RequestParam(name="k", defaultValue="''", required=true) String kind,
			@RequestParam(name="l", defaultValue="5",required=false) int len,
			@RequestParam(name="o", defaultValue="0", required=false) int offset){
		return service.getKindBook(kind, len, offset);
	}
	
	/**
	 * 得到书籍的所有分类
	 * @return
	 */
	@GetMapping(path="/getKind")
	@ResponseBody
	public List<String> getkind(){
		return service.getkind();
	}
	
	/**
	 * 统计书籍模糊查询结果数量
	 * @param text
	 * @return
	 */
	@GetMapping(path="/len/selectBook")
	@ResponseBody
	public int getSelectBookLen(@RequestParam(name="t", defaultValue="''", required=true) String text) {
		return service.getSelectBookLen(text);
	}
	
	/**
	 * 模糊查询
	 * @param text
	 * @param len
	 * @param offset
	 * @return
	 */
	@GetMapping(path="/selectBook")
	@ResponseBody
	public List<Book> selectBook(
			@RequestParam(name="t", defaultValue="''",required=true) String text,
			@RequestParam(name="l", defaultValue="5",required=false) int len,
			@RequestParam(name="o", defaultValue="0", required=false) int offset){
		return service.selectBook(text, len, offset);
	}
	
	/**
	 * 统计未上架书籍数量
	 * @return
	 */
	@GetMapping(path="/len/deleteBook")
	@ResponseBody
	public int getDeleteBookLen() {
		return service.getDeleteBookLen();
	}
	
	/**
	 * 查询未上架书籍
	 * @param offset
	 * @return
	 */
	@GetMapping(path="/getDeleteBook")
	@ResponseBody
	public List<Book> getDeleteBook(
			@RequestParam(name="l", defaultValue="5",required=false) int len,
			@RequestParam(name="o", defaultValue="0", required=false) int offset){
		return service.getDeleteBook(len, offset);
	}
	
	/**
	 * 统计已上架书籍数量
	 * @return
	 */
	@GetMapping(path="/len/sellingBook")
	@ResponseBody
	public int getSellingBookLen() {
		return service.getSellingBookLen();
	}
	
	/**
	 * 查询已上架书籍
	 * @param offset
	 * @return
	 */
	@GetMapping(path="/getSellingBook")
	@ResponseBody
	public List<Book> getSellingBook(
			@RequestParam(name="l", defaultValue="5",required=false) int len,
			@RequestParam(name="o", defaultValue="0", required=false) int offset){
		return service.getSellingBook(len, offset);
	}

	/**
	 * 统计库存低于某值的已上架书籍数量
	 * @param low
	 * @return
	 */
	@GetMapping(path="/len/lowerStorage")
	@ResponseBody
	public int getLowerStorageLen(@RequestParam(name="lo", defaultValue="5",required=true) int low) {
		return service.getLowerStorageLen(low);
	}
	
	/**
	 * 查询库存低于某值的书籍
	 * @param low
	 * @param len
	 * @param offset
	 * @return
	 */
	@GetMapping(path="/getLowerStorage")
	@ResponseBody
	public List<Book> getLowerStorage(
			@RequestParam(name="lo", defaultValue="5",required=true) int low,
			@RequestParam(name="l", defaultValue="5",required=false) int len,
			@RequestParam(name="o", defaultValue="0", required=false) int offset){
		return service.getLowerStorage(low, len, offset);
	}
}
