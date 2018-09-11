package com.qrot.bookstore.reception.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qrot.bookstore.reception.model.User;
import com.qrot.bookstore.reception.service.ReceUserService;

/**
 * 控制器依赖业务逻辑
 * 
 * @author TanZhiHua 2018年8月4日09点25分
 */

@Controller
// 控制器的 URL
@RequestMapping("/user")
@CrossOrigin
public class ReceUserController {

	@Autowired
	ReceUserService userService;
	
	/**
	 * 显示所有用户
	 * @return
	 */
	@GetMapping
	@ResponseBody
	public List<User> show() {
		
		return userService.show();
	}

	/**
	 * 用户登录
	 * @param name
	 * @param password
	 * @return
	 */
	@GetMapping("/load")
	@ResponseBody
	public User load(
			@RequestParam(name="n", required=false) String name,
			@RequestParam(name="p", required=false) String password) {
		User user = userService.load(name, password);
		
		if(user == null) {
			return null;
		}
		
		return user;
	}
	
	@PostMapping("/update")
	@ResponseBody
	public void update(@RequestBody User user) {
		userService.update(user);
		System.out.println("ok");
		System.out.println(user.getId());
	}
	
	@PostMapping
	@ResponseBody
	public void create(@RequestBody User user) {
		
		userService.create(user);
		
	}

}