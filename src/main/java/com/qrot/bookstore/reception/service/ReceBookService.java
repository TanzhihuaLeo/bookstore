package com.qrot.bookstore.reception.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qrot.bookstore.reception.mapper.ReceBookMapper;
import com.qrot.bookstore.reception.model.Book;

/**
 * 
 * @author TanZhiHua 2018年8月6日10点51分
 */
@Service
public class ReceBookService {

	@Autowired
	ReceBookMapper bookMapper;

	/**
	 * 显示所有书籍信息
	 * 
	 * @return
	 */
	public List<Book> show() {
		return bookMapper.show();
	}

	/**
	 * 按时间排序
	 * 
	 * @return
	 */
	public List<Book> timeSelectSort() {
		return bookMapper.timeSelectSort();
	}

	/**
	 * 按销量排序
	 * 
	 * @return
	 */
	public List<Book> volumeSelectSort() {
		return bookMapper.volumeSelectSort();
	}

	/**
	 * 按评分排序
	 * 
	 * @return
	 */
	public List<Book> scoreSelectSort() {
		return bookMapper.scoreSelectSort();
	}

	/**
	 * 搜索特定书籍的详细信息
	 * 
	 * @param bookId
	 * @return
	 */
	public Book showBookDetailInfo(int bookId) {
		return bookMapper.showBookDetailInfo(bookId);
	}

	/**
	 * 分类显示
	 * 
	 * @param kind
	 * @param offset
	 * @return
	 */
	public List<Book> kindShow(String kind, int offset) {
		return bookMapper.kindShow(kind, offset);
	}

	/**
	 * 模糊查询搜索书籍
	 * 
	 * @param price
	 * @param score
	 * @param name
	 * @param author
	 * @param publish
	 * @param kind
	 * @param publTime
	 * @return
	 */
	public List<Book> selectBook(String keyWord) {
		return bookMapper.selectBook(keyWord);
	}

}
