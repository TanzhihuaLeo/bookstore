package com.qrot.bookstore.reception.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qrot.bookstore.reception.mapper.ReceUserMapper;
import com.qrot.bookstore.reception.model.User;

/**
 * 业务逻辑：依赖数据访问操作，自动注入各个实体类的 Mapper
 * @author TanZhiHua
 *2018年8月4日09点28分
 */
@Service
public class ReceUserService {
	
	@Autowired
	ReceUserMapper usermapper;
	
	/**
	 * 显示所有用户
	 * @return
	 */
	public List<User> show(){
		return usermapper.show();
	}
	
	/**
	 * 用户登录
	 * @param name
	 * @return
	 */
	public User load(String name, String password) {
		return usermapper.load(name, password);
	}
	/**
	 * 修改用户信息
	 * @param user
	 */
	@Transactional
	public void update(User user) {
		usermapper.updateUser(user);
		usermapper.updateUserInfo(user);
	}
	
	/**
	 * 用户注册
	 * @Transactional 要么成功，要么回滚
	 * @param user
	 */
	@Transactional
	public void create(User user) {
		usermapper.create(user);
		usermapper.createInfo(user);
		
	}

}
