package com.qrot.bookstore.background.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qrot.bookstore.background.mapper.OrdersMapper;
import com.qrot.bookstore.background.model.Orders;
import com.qrot.bookstore.background.model.VItemBook;

/**
 * 关于订单的业务逻辑：依赖数据访问操作
 * @author TanZhiHua
 *
 */
@Service
public class BackOrderService {

	@Autowired
	OrdersMapper ordersMapper;
	
	/**
	 * 查询某个订单的信息
	 * @param id
	 */
	public Orders loadOrder(int id) {
		return ordersMapper.loadOrder(id);
	}
	
	/**
	 * 更新订单状态
	 * @param order
	 */
	public void updateOrder(int id) {
		ordersMapper.updateOrder(id);
	}
	
	/**
	 * 得到某订单的所有项，只含订单项的信息
	 * @param orderID
	 * @return
	 */
	public List<VItemBook> getItems(int orderID){
		return ordersMapper.getItems(orderID);
	}
	
	/**
	 * 通过orderID查询订单信息，包括订单信息和订单项信息
	 * 
	 * @param orderID
	 * @return
	 */
	public List<Orders> getOrderByID(int orderID){
		return ordersMapper.getOrderByID(orderID);
	}
	
	/**
	 * 统计某时间段内的订单量
	 * 
	 * @param start 起始时间
	 * @param end   终止时间
	 * @return
	 */
	public int getLenByTime(Date start,Date end) {
		return ordersMapper.getLenByTime(start, end);
	}
	
	/**
	 * 通过时间查询订单，包括订单信息和订单项信息
	 * 
	 * @param start 起始时间
	 * @param end   终止时间
	 * @param len   分页长度长度
	 * @param offset偏移量
	 * @return
	 */
	public List<Orders> getOrderByTime(Date start,Date end,int len,int offset){
		return ordersMapper.getOrderByTime(start, end, len, offset);
	}
	
	/**
	 * 统计某个状态的订单量
	 * 
	 * @param state  订单状态
	 * @return
	 */
	public int getLenByState(char state) {
		return ordersMapper.getLenByState(state);
	}
	
	/**
	 * 通过订单状态查询订单，包括订单信息和订单项信息
	 * 
	 * @param state
	 * @param len  长度
	 * @param offset  偏移量
	 * @return
	 */
	public List<Orders> getOrderByState(char state,int len,int offset){
		return ordersMapper.getOrderByState(state, len, offset);
	}
	
	/**
	 * 统计某个时间段内某个状态的订单量
	 * 
	 * @param 起始时间
	 * @param end   终止时间
	 * @param state 订单状态
	 * @return
	 */
	public int getLenByTimeAndState(Date start,Date end,char state) {
		return ordersMapper.getLenByTimeAndState(start, end, state);
	}
	
	/**
	 * 通过时间和订单状态查询订单，包括订单信息和订单项信息
	 * 
	 * @param start 起始时间
	 * @param end   终止时间
	 * @param state 订单状态
	 * @param len   分页长度长度
	 * @param offset偏移量
	 * @return
	 */
	public List<Orders> getOrderByTimeAndState(Date start,Date end,char state,int len,int offset){
		return ordersMapper.getOrderByTimeAndState(start, end, state, len, offset);
	}
}
