package com.qrot.bookstore.reception.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qrot.bookstore.reception.model.User;

/**
 * 反射、注解：动态代理、cglib(字节码生成)
 * 
 * @author TanZhiHua 2018年8月4日09点47分
 */
@Mapper
public interface ReceUserMapper {

	/**
	 * 显示所有用户
	 * 
	 * @return
	 */
	@Select("select * from v_show_all_user")
	@Results(value = { 
			@Result(column = "user_id", property = "id"), 
			@Result(column = "user_name", property = "name"),
			@Result(column = "user_password", property = "password"),
			@Result(column = "user_head", property = "userHead"), 
			@Result(column = "user_sex", property = "sex"),
			@Result(column = "user_phone", property = "phone"), 
			@Result(column = "user_email", property = "email"), 
	})
	List<User> show();

	/**
	 * 用户登录
	 * 
	 * @param name
	 * @return
	 */
	@Select("select * from v_show_all_user where user_name=#{name} and user_password=#{password}")
	@Results(value = { 
			@Result(column = "user_id", property = "id"), 
			@Result(column = "user_name", property = "name"),
			@Result(column = "user_password", property = "password"),
			@Result(column = "user_head", property = "userHead"), 
			@Result(column = "user_sex", property = "sex"),
			@Result(column = "user_phone", property = "phone"), 
			@Result(column = "user_email", property = "email"), 
	})
	User load(
			@Param("name") String name, 
			@Param("password") String password);
	
	
	/**
	 * 用户注册
	 * @param user
	 */
	@Insert("insert into user(user_name,user_password,user_head) values(#{name}, #{password}, #{userHead})")
	@Results(value = { 
			@Result(column = "user_id", property = "id"), 
			@Result(column = "user_name", property = "name"),
			@Result(column = "user_password", property = "password"),
			@Result(column = "user_head", property = "userHead"), 
			@Result(column = "user_sex", property = "sex"),
			@Result(column = "user_phone", property = "phone"), 
			@Result(column = "user_email", property = "email"), 
	})
	void create(User user);
	
	/**
	 * 用户注册
	 * @param user
	 */
	@Insert("insert into user_info(user_id,user_sex,user_phone,user_email) "
			+ "values((last_insert_id()) ,#{sex}, #{phone}, #{email})")
	@Results(value = { 
			@Result(column = "user_id", property = "id"), 
			@Result(column = "user_name", property = "name"),
			@Result(column = "user_password", property = "password"),
			@Result(column = "user_head", property = "userHead"), 
			@Result(column = "user_sex", property = "sex"),
			@Result(column = "user_phone", property = "phone"), 
			@Result(column = "user_email", property = "email"), 
	})
	void createInfo(User user);
	
	/**
	 * 更新 密码
	 * @param user
	 */
	@Update("update user set user_password=#{password} where user_id=#{id}")
	void updateUser(User user);
	/**
	 * 更新性别、电话、邮箱
	 * @param user
	 */
	@Update("update user_info set user_sex=#{sex},user_phone=#{phone},user_email=#{email} where user_id=#{id}")
	void updateUserInfo(User user);

	
	/**
	 * 修改用户信息
	 * @param user
	 */
	@Update("update user_info set user_sex=#{sex},user_phone=#{phone},user_email=#{email} where user_id=#{userId}")
	void updateInfo(User user);


}
