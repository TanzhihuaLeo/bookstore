package com.qrot.bookstore.reception.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qrot.bookstore.reception.model.Cart;

/**
 * 
 * @author TanZhiHua
 *2018年8月6日08点39分
 */
@Mapper
public interface ReceCartMapper {
	
	/**
	 * 显示所有购物车信息
	 * @return
	 */
	@Select("select * from v_show_all_cart")
	@Results(value = { 
			@Result(column = "user_id", property = "userId"), 
			@Result(column = "book_id", property = "bookId"),
			@Result(column = "cart_quantity", property = "quantity"),
			@Result(column = "book_name", property = "bookName"),
			@Result(column = "book_cover", property = "bookCover"), 
	})
	List<Cart> show();
	
	/**
	 * 查询某个用户购物车
	 * @param id
	 * @return
	 */
	@Select("select * from v_show_all_cart where user_id=#{id}")
	@Results(value = { 
			@Result(column = "user_id", property = "userId"), 
			@Result(column = "book_id", property = "bookId"),
			@Result(column = "cart_quantity", property = "quantity"),
			@Result(column = "book_price", property = "price"),
			@Result(column = "book_name", property = "bookName"),
			@Result(column = "book_cover", property = "bookCover"), 
	})
	List<Cart> select(int id);
	
	
	/**
	 * 添加购物车
	 * @param cart
	 * @return
	 */
	@Insert("insert into cart values(#{userId},#{bookId},#{quantity})")
	void create(Cart cart);
	
	/**
	 * 修改购物车订单项数量
	 * @param cart
	 * @return
	 */
	@Update("update cart set cart_quantity=#{quantity} where book_id=#{bookId} and user_id=#{userId}")
	Cart update(Cart cart);
	
	/**
	 * 删除单个购物车订单项
	 * @param cart
	 */
	@Delete("delete from cart where user_id=#{userId} and book_id=#{bookId}")
	void delete(Cart cart);
	
	/**
	 * 清空该用户购物车
	 * @param userId
	 */
	@Delete("delete from cart where user_id=#{userId}")
	void empty(int userId);
}
