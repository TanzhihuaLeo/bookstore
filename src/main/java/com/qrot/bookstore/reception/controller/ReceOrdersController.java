package com.qrot.bookstore.reception.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qrot.bookstore.reception.model.Orders;
import com.qrot.bookstore.reception.service.ReceOrdersService;

/**
 * 
 * @author TanZhiHua 2018年8月11日09点26分
 */
@Controller
@RequestMapping("/orders")
@CrossOrigin
public class ReceOrdersController {

	@Autowired
	ReceOrdersService ordersService;

	/**
	 * 创建订单
	 * @param order
	 */
	@PostMapping("/create")
	@ResponseBody
	public void create(@RequestBody Orders order) {

		ordersService.subOrder(order);

	}
	
	/**
	 * 删除订单
	 * @param orderId
	 */
	@PostMapping("/delete")
	@ResponseBody
	public String delete(@RequestBody Orders order) {
		
		ordersService.deleteOrder(order.getOrderId());
		
		return "msg : ok";
		
	}
	
	/**
	 * 获取用户订单
	 * @param userId
	 * @return
	 */
	@GetMapping("/{userId}")
	@ResponseBody
	public List<Orders> selectUserOrder(@PathVariable int userId) {
		return ordersService.selectUserOrder(userId);
	}

}
