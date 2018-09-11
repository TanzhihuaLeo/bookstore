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

import com.qrot.bookstore.reception.model.Cart;
import com.qrot.bookstore.reception.service.ReceCartService;

/**
 * 
 * @author TanZhiHua
 *2018年8月6日08点42分
 */
@Controller
@RequestMapping("/cart")
@CrossOrigin
public class ReceCartController {
	
	@Autowired
	ReceCartService cartService;
	
	/**
	 * 显示所有购物车信息
	 * @return
	 */
	@GetMapping
	@ResponseBody
	public List<Cart> show() {
		
		return cartService.show();
	}
	
	/**
	 * 显示某个用户的所有购物车信息
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	@ResponseBody
	public List<Cart> select(@PathVariable int id) {
		
		return cartService.select(id);
	}

	/**
	 * 添加购物车
	 * @param cart
	 */
	@PostMapping("/create")
	@ResponseBody
	public void create(@RequestBody Cart cart) {
		cartService.create(cart);
	}
	
	/**
	 * 修改购物车订单项
	 * @param cart
	 */
	@PostMapping("/update")
	@ResponseBody
	public void update(@RequestBody Cart cart) {
		cartService.update(cart);
	}
	
	/**
	 * 删除单个购物车订单项
	 * @param cart
	 */
	@PostMapping("/delete")
	@ResponseBody
	public void delete(@RequestBody Cart cart) {
		cartService.delete(cart);
	}
	
	/**
	 * 清空该用户购物车
	 * @param userId
	 */
	@PostMapping("/empty")
	@ResponseBody
	public void empty(@RequestBody int userId) {
		cartService.empty(userId);
	}
}
