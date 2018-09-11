package com.qrot.bookstore.background.mapper;

import java.math.BigDecimal;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.qrot.bookstore.background.model.KindCount;
import com.qrot.bookstore.background.model.VComment;
import com.qrot.bookstore.background.model.VItemBook;

/**
 * 进行报表统计的工作及查看
 * 
 * @author TanZhiHua
 *
 */
@Mapper
public interface CountMapper {

	/**
	 * 统计某书籍的评论条数
	 * 
	 * @param bookID
	 * @return
	 */
	@Select("select count(comm_id) from v_comment where book_id = #{bookID}")
	int getCommentsLen(@Param("bookID") int bookID);
	
	/**
	 *  查询某书籍的评论
	 * 
	 * @param bookID
	 * @param len
	 * @param offset
	 * @return
	 */
	@Select("select * from v_comment where book_id = #{bookID} "
			+ "order by comm_time desc limit #{len} offset #{offset}")
	@Results(value = {
			@Result(column="comm_id", property="commID"),
			@Result(column="comm_score", property="commScore"),
			@Result(column="comm_content", property="commContent"),
			@Result(column="comm_time", property="commTime"),
			@Result(column="book_id", property="bookID"),
			@Result(column="order_id", property="orderID"),
			@Result(column="user_id", property="userID"),
			@Result(column="user_name", property="userName"),
	})
	List<VComment> getComments(
			@Param("bookID") int bookID, 
			@Param("len") int len, 
			@Param("offset") int offset);
	
	
	/**
	 * 统计某年每月的订单量
	 * @param time
	 * @return
	 */
	@Select("select count(order_id) number from orders where Year(order_time) =#{time} "
			+ "group by Month(order_time)")
	List<Long> countOrderByMonth(@Param("time") int time);
	
	/**
	 * 统计某年每季度的订单量
	 * @param time
	 * @return
	 */
	@Select("select count(order_id) number from orders where Year(order_time) =#{time} "
			+ "group by Month(order_time)")
	List<Long> countOrderByQuarter(@Param("time") int time);
	
	
	/**
	 * 统计每月的销售额
	 * @param time
	 * @return
	 */
	@Select("select sum(order_total) money from orders where Year(order_time) =#{time} "
			+ "group by Month(order_time)")
	List<BigDecimal> countMoneyByMonth(@Param("time") int time);
	
	/**
	 * 统计每季度的销售额
	 * @param time
	 * @return
	 */
	@Select("select sum(order_total) money from orders where Year(order_time) =#{time} "
			+ "group by Quarter(order_time)")
	List<BigDecimal> countMoneyByQuarter(@Param("time") int time);
	
	
	/**
	 * 统计每月的总销量
	 * @param time
	 * @return
	 */
	@Select("select sum(item_quantity) volume from v_item_book where Year(order_time) =#{time} "
			+ "group by Month(order_time)")
	List<Long> countVolumeByMonth(@Param("time") int time);
	/**
	 * 统计每月的总销量
	 * @param time
	 * @return
	 */
	@Select("select sum(item_quantity) volume from v_item_book where Year(order_time) =#{time} "
			+ "group by Quarter(order_time)")
	List<Long> countVolumeByQuarter(@Param("time") int time);
	
	/**
	 * 统计某年某月各类书籍的销量
	 * @param year
	 * @param month
	 * @return
	 */
	@Select("select book_kind,sum(total_volume) volume from v_volume_count "
			+ "where Year(order_time)=#{year} and Month(order_time)=#{month} "
			+ "group by book_kind")
	@Results(id="kindVolume", value= {
			@Result(column="book_kind",property="bookKind"),
			@Result(column="volume",property="volume")
	})
	List<KindCount> countKindVolumeByMonth(@Param("year") int year,@Param("month") int month);
	
	/**
	 * 统计某年某周各类书籍的销量
	 * @param year
	 * @param week
	 * @return
	 */
	@Select("select book_kind,sum(total_volume) volume from v_volume_count "
			+ "where Year(order_time)=#{year} and Week(order_time)=#{week} "
			+ "group by book_kind")
	@ResultMap("kindVolume")
	List<KindCount> countKindVolumeByWeek(@Param("year") int year,@Param("week") int week);
	
	/**
	 * 查询某年某月销量前十的书籍
	 * @param year
	 * @param month
	 * @return
	 */
	@Select("select book_id,book_name,book_author,book_publish,book_kind,sum(item_quantity) total_vlolume "
			+ "from v_item_book where Year(order_time)=#{year} and Month(order_time)=#{month} "
			+ "group by book_id order by total_vlolume desc,book_id limit 10")
	@Results(id="topVolume", value= {
			@Result(column="book_id",property="bookID"),
			@Result(column="book_name",property="bookName"),
			@Result(column="book_author",property="bookAuthor"),
			@Result(column="book_publish",property="bookPublish"),
			@Result(column="book_kind",property="bookKind"),
			@Result(column="total_vlolume",property="itemQuantity"),
	})
	List<VItemBook> selectTopVolumeByMonth(@Param("year") int year,@Param("month") int month);
	
	/**
	 * 查询某年某周销量前十的书籍
	 * @param year
	 * @param week
	 * @return
	 */
	@Select("select book_id,book_name,book_author,book_publish,book_kind,sum(item_quantity) total_vlolume "
			+ "from v_item_book where Year(order_time)=#{year} and Week(order_time)=#{week} "
			+ "group by book_id order by total_vlolume desc,book_id limit 10")
	@ResultMap("topVolume")
	List<VItemBook> selectTopVolumeByWeek(@Param("year") int year,@Param("week") int week);
}
