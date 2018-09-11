package com.qrot.bookstore.reception.model;

/**
 * 收货地址实体类
 * 
 * @author TanZhiHua 2018年8月4日09点01分
 */
public class Receive {

	private int recvId;
	private int userId;
	private String name;
	private String address;
	private String phone;

	public int getRecvId() {
		return recvId;
	}

	public void setRecvId(int recvId) {
		this.recvId = recvId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
