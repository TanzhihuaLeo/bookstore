package com.qrot.bookstore.background.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qrot.bookstore.background.model.Orders;
import com.qrot.bookstore.background.model.VItemBook;

/**
 * orderMapper
 * @author TanZhiHua
 *
 */
@Mapper
public interface OrdersMapper {

	/**
	 * 查询某个订单的信息
	 * @param orderID
	 * @return
	 */
	@Select("select * from orders where order_id=#{orderID}")
	@Results(value= {
			@Result(column="order_id",property="orderID"),
			@Result(column="order_total",property="orderTotal"),
			@Result(column="user_id",property="userID"),
			@Result(column="order_state",property="orderState"),
			@Result(column="order_name",property="orderName"),
			@Result(column="order_address",property="orderAddress"),
			@Result(column="order_phone",property="orderPhone"),
			@Result(column="order_time",property="orderTime"),
	})
	Orders loadOrder(int orderID);
	
	/**
	 * 修改订单状态
	 * @param order
	 */
	@Update("update orders set order_state=2 where order_id=#{id}")
	void updateOrder(@Param("id") int id);
	
	/**
	 * 得到某订单的所有项，只含订单项的信息
	 * @param orderID
	 * @return
	 */
	@Select("select * from v_item_book where order_id=#{orderID}")
	@Results(value= {
			@Result(column="order_id",property="orderID"),
			@Result(column="book_id",property="bookID"),
			@Result(column="book_name",property="bookName"),
			@Result(column="book_cover",property="bookCover"),
			@Result(column="book_author",property="bookAuthor"),
			@Result(column="book_publish",property="bookPublish"),
			@Result(column="book_kind",property="bookKind"),
			@Result(column="item_quantity",property="itemQuantity"),
			@Result(column="item_price",property="itemPrice"),
			@Result(column="subtotal",property="itemSubtotal"),
			@Result(column="order_time",property="orderTime"),
	})
	List<VItemBook> getItems(int orderID);
	
	/**
	 * 通过orderID查询订单信息，包括订单信息和订单项信息
	 * @param orderID
	 * @return
	 */
	@Select("select * from orders where order_id=#{orderID}")
	@Results(id="orderItem", value= {
			@Result(column="order_id",property="orderID"),
			@Result(column="order_total",property="orderTotal"),
			@Result(column="user_id",property="userID"),
			@Result(column="order_state",property="orderState"),
			@Result(column="order_name",property="orderName"),
			@Result(column="order_address",property="orderAddress"),
			@Result(column="order_phone",property="orderPhone"),
			@Result(column="order_time",property="orderTime"),
			@Result(
					property = "itemList",
					javaType=List.class,
					column="order_id",  
					many=@Many(select="getItems"))
	})
	List<Orders> getOrderByID(int orderID);
	
	/**
	 * 统计某时间段内的订单量
	 * 
	 * @param start 起始时间
	 * @param end   终止时间
	 * @return
	 */
	@Select("select count(order_id) from orders where Date(order_time) between #{start} and #{end}")
	int getLenByTime(
			@Param("start")Date start,
			@Param("end")Date end);
	
	/**
	 * 通过时间查询订单，包括订单信息和订单项信息
	 * 
	 * @param start 起始时间
	 * @param end   终止时间
	 * @param len   分页长度长度
	 * @param offset偏移量
	 * @return
	 */
	@Select("select * from orders where Date(order_time) between #{start} and #{end}"
			+ "order by order_time desc limit #{len} offset #{offset}")
	@ResultMap("orderItem")
	List<Orders> getOrderByTime(
			@Param("start")Date start,
			@Param("end")Date end,
			@Param("len")int len,
			@Param("offset")int offset);
	
	/**
	 * 统计某个状态的订单量
	 * 
	 * @param state  订单状态
	 * @return
	 */
	@Select("select count(order_id) from orders where order_state=#{state} ")
	int getLenByState(@Param("state")char state);
	
	/**
	 * 通过订单状态查询订单，包括订单信息和订单项信息
	 * @param state 订单状态
	 * @param len  长度
	 * @param offset  偏移量
	 * @return
	 */
	@Select("select * from orders where order_state=#{state} "
			+ "order by order_time limit #{len} offset #{offset}")
	@ResultMap("orderItem")
	List<Orders> getOrderByState(
			@Param("state")char state,
			@Param("len")int len,
			@Param("offset")int offset);
	
	
	/**
	 * 统计某个时间段内某个状态的订单量
	 * 
	 * @param 起始时间
	 * @param end   终止时间
	 * @param state 订单状态
	 * @return
	 */
	@Select("select count(order_id) from orders where (Date(order_time) between #{start} and #{end})"
			+ "and order_state=#{state}")
	int getLenByTimeAndState(
			@Param("start")Date start,
			@Param("end")Date end,
			@Param("state")char state);
	
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
	@Select("select * from orders where (Date(order_time) between #{start} and #{end})"
			+ "and order_state=#{state}"
			+ "order by order_time limit #{len} offset #{offset}")
	@ResultMap("orderItem")
	List<Orders> getOrderByTimeAndState(
			@Param("start")Date start,
			@Param("end")Date end,
			@Param("state")char state,
			@Param("len")int len,
			@Param("offset")int offset);
}
