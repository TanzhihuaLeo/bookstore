package com.qrot.bookstore.reception.mapper;

import java.util.List;


import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qrot.bookstore.reception.model.Comment;
import com.qrot.bookstore.reception.model.Item;
import com.qrot.bookstore.reception.model.Orders;

/**
 * 
 * @author TanZhiHua
 *2018年8月11日09点26分
 */
@Mapper
public interface ReceOrdersMapper {
	
	/**
	 * 向订单表添加用户订单
	 */
	@Insert("insert into orders(order_total,user_id,order_state,order_name,order_address,order_phone,order_time) "
			+ "values (#{total}, #{userId}, #{state}, #{name}, #{address}, #{phone}, #{time})")
	void subOrder(Orders order);
	
	/**
	 * 向订单项表添加书籍数据
	 * @param item
	 */
	@Insert("insert into item values ((last_insert_id()), #{bookId}, #{quantity}, #{price})")
	void createItem(Item item);
	
	/**
	 * 书籍销量增加
	 * @param item
	 */
	@Update("update book set book_volume=(book_volume+(#{quantity})) where book_id=#{bookId}")
	void updateBookVolume(Item item);
	
	/**
	 * 书籍库存减少
	 * @param item
	 */
	@Update("update book set book_storage=(book_storage-(#{quantity})) where book_id=#{bookId}")
	void updateBookStorage(Item item);
	
	/**
	 * 查询用户订单
	 * @param userId
	 * @return
	 */

	@Select("select * from orders where user_id=#{userId} and order_state !='4'")

	@Results(value = { 
			@Result(column = "order_id", property = "orderId"), 
			@Result(column = "order_total", property = "total"),
			@Result(column = "user_id", property = "userId"),
			@Result(column = "order_state", property = "state"), 
			@Result(column = "order_name", property = "name"),
			@Result(column = "order_address", property = "address"), 
			@Result(column = "order_phone", property = "phone"),
			@Result(column = "order_time", property = "time"), 
			@Result(
					property="itemList",
					column="order_id",
					javaType=List.class,
					many=@Many(select="findItemById")
					)
	})
	List<Orders> selectUserOrder(int userId);
	
	/**
	 * 获得特定订单信息
	 * @param id
	 * @return
	 */
	@Select("select * from item where order_id=#{orderId}")
	@Results(value = { 
//			@Result(column = "order_id", property = "orderId"), 
//			@Result(column = "book_id", property = "bookId"),
			@Result(column = "item_quantity", property = "quantity"),
//			@Result(column = "item_price", property = "price"), 
	})
	List<Item> findItemById(int orderId);
	
	/**
	 * 删除订单
	 * @param orderId
	 */
	@Update("update orders set order_state = '4' where order_id = #{orderId}")
	void deleteOrder(int orderId);

}
