package com.qrot.bookstore.background.model;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 *   评论实体类
 * @author TanZhiHua
 *
 */
public class VComment {

	private int commID;
	private double commScore;
	private String commContent;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Timestamp commTime;
	private int bookID;
	private int orderID;
	private int userID;
	private String userName;
	
	public VComment() {}

	public int getCommID() {
		return commID;
	}

	public void setCommID(int commID) {
		this.commID = commID;
	}

	public double getCommScore() {
		return commScore;
	}

	public void setCommScore(double commScore) {
		this.commScore = commScore;
	}

	public String getCommContent() {
		return commContent;
	}

	public void setCommContent(String commContent) {
		this.commContent = commContent;
	}

	public Timestamp getCommTime() {
		return commTime;
	}

	public void setCommTime(Timestamp commTime) {
		this.commTime = commTime;
	}

	public int getBookID() {
		return bookID;
	}

	public void setBookID(int bookID) {
		this.bookID = bookID;
	}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
		
}
