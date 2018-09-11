package com.qrot.bookstore.background.service;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qrot.bookstore.background.mapper.CountMapper;
import com.qrot.bookstore.background.model.KindCount;
import com.qrot.bookstore.background.model.VComment;
import com.qrot.bookstore.background.model.VItemBook;

/**
 * 业务逻辑，包括统计，查看评论和推荐设置
 * 
 * @author TanZhiHua
 *
 */
@Service
public class BackCountService {

	@Autowired
	CountMapper countMapper;
	
	/**
	 * 统计某书籍的评论条数
	 * 
	 * @param bookID
	 * @return
	 */
	public int getCommentsLen(int bookID) {
		return countMapper.getCommentsLen(bookID);
	}

	/**
	 * 查询某书籍的评论
	 * 
	 * @param bookID
	 * @param len
	 * @param offset
	 * @return
	 */
	public List<VComment> getComments(int bookID, int len, int offset){
		return countMapper.getComments(bookID, len, offset);
	}
	
	/**
	 * 统计某年每月的订单量
	 * @param time
	 * @return
	 */
	public List<Long> countOrderByMonth(int time){
		return countMapper.countOrderByMonth(time);
	}
	
	/**
	 * 统计某年每季度的订单量
	 * @param time
	 * @return
	 */
	public List<Long> countOrderByQuarter(int time){
		return countMapper.countOrderByMonth(time);
	}
	
	/**
	 * 统计每月的销售额
	 * @param time
	 * @return
	 */
	public List<BigDecimal> countMoneyByMonth(int time){
		return countMapper.countMoneyByMonth(time);
	}
	
	/**
	 * 统计每季度的销售额
	 * @param time
	 * @return
	 */
	public List<BigDecimal> countMoneyByQuarter(int time){
		return countMapper.countMoneyByQuarter(time);
	}
	
	/**
	 * 统计每月的总销量
	 * @param time
	 * @return
	 */
	public List<Long> countVolumeByMonth(int time){
		return countMapper.countVolumeByMonth(time);
	}
	
	/**
	 * 统计每季度的总销量
	 * @param time
	 * @return
	 */
	public List<Long> countVolumeByQuarter(int time){
		return countMapper.countVolumeByQuarter(time);
	}

	/**
	 * 统计某年某月各类书籍的销量
	 * @param year
	 * @param week
	 * @return
	 */
	public List<KindCount> countKindVolumeByMonth(int year, int month){
		return countMapper.countKindVolumeByMonth(year, month);
	}

	/**
	 * 统计某年某周各类书籍的销量
	 * @param year
	 * @param week
	 * @return
	 */
	public List<KindCount> countKindVolumeByWeek(int year, int week){
		return countMapper.countKindVolumeByWeek(year, week);
	}

	/**
	 * 查询某年某月销量前十的书籍
	 * @param year
	 * @param month
	 * @return
	 */
	public List<VItemBook> selectTopVolumeByMonth(int year, int month){
		return countMapper.selectTopVolumeByMonth(year, month);
	}

	/**
	 * 查询某年某月销量前十的书籍
	 * @param year
	 * @param week
	 * @return
	 */
	public List<VItemBook> selectTopVolumeByWeek(int year, int week){
		return countMapper.selectTopVolumeByWeek(year, week);
	}
}
