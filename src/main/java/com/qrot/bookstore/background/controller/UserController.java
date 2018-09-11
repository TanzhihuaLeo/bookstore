package com.qrot.bookstore.background.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.qrot.bookstore.background.model.User;
import com.qrot.bookstore.reception.service.ReceUserService;

/**
 * 控制器
 * 
 * @author TanZhiHua
 *
 */
@RestController
@RequestMapping("/api/1")
@CrossOrigin
public class UserController {
	
	@Autowired
	ReceUserService userService;
	
	@PostMapping("/login")
	@ResponseBody
	public String login(@RequestBody User u) {
		com.qrot.bookstore.reception.model.User user = userService.load(u.getName(), u.getPassword());
		
		if("admin".equals(u.getName())) {
				return "OK";
		}
		
		else
			return "用户名或密码错误";
		
	}
	
}
