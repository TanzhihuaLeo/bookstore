package com.qrot.bookstore.background.model;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
 * @author TanZhiHua
 *
 */
public class Book {

	private int bookid;
	private String isbn;
	private String bookname;
	private String author;
	private String publish;
	private String kind;
	private String summary;
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date publtime;
	private String cover;
	private BigDecimal price;
	private int storage;
	private int volume;
	private double score;
	private char delete;
	
	public Book() {}

	public int getBookid() {
		return bookid;
	}

	public void setBookid(int bookid) {
		this.bookid = bookid;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getBookname() {
		return bookname;
	}

	public void setBookname(String bookname) {
		this.bookname = bookname;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublish() {
		return publish;
	}

	public void setPublish(String publish) {
		this.publish = publish;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Date getPubltime() {
		return publtime;
	}

	public void setPubltime(Date publtime) {
		this.publtime = publtime;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getStorage() {
		return storage;
	}

	public void setStorage(int storage) {
		this.storage = storage;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public char getDelete() {
		return delete;
	}

	public void setDelete(char delete) {
		this.delete = delete;
	}
	
}
