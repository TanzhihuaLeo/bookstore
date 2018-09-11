package com.qrot.bookstore.reception.model;

import java.math.BigDecimal;

/**
 * 订单项实体类
 * 
 * @author TanZhiHua 2018年8月4日09点01分
 */
public class Item {

	int orderId;
	int bookId;
	int quantity;
	BigDecimal price;

	public int getItemId() {
		return orderId;
	}

	public void setItemId(int orderId) {
		this.orderId = orderId;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
