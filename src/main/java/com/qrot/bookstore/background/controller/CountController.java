package com.qrot.bookstore.background.controller;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.qrot.bookstore.background.model.KindCount;
import com.qrot.bookstore.background.model.VComment;
import com.qrot.bookstore.background.model.VItemBook;
import com.qrot.bookstore.background.service.BackCountService;

/**
 * 控制器，包括统计，查看评论和推荐设置
 * 
 * @author TanZhiHua
 *
 */
@RestController
@RequestMapping("api/1")
@CrossOrigin
public class CountController {

	@Autowired
	BackCountService service;
	
	/**
	 * 统计某书籍的评论条数
	 * 
	 * @param bookID
	 * @return
	 */
	@GetMapping("/len/getComments")
	@ResponseBody
	public int getCommentsLen(@RequestParam(name="id", required=true) int bookID) {
		return service.getCommentsLen(bookID);
	}

	/**
	 * 查询某书籍的评论
	 * 
	 * @param bookID
	 * @param len
	 * @param offset
	 * @return
	 */
	@GetMapping("/getComments")
	@ResponseBody
	public List<VComment> getComments(
			@RequestParam(name="id", required=true) int bookID, 
			@RequestParam(name="l", defaultValue="6",required=false) int len, 
			@RequestParam(name="o", defaultValue="0",required=false) int offset){
		return service.getComments(bookID, len, offset);
	}
	
	/**
	 * 统计某年每月的订单量
	 * @param time
	 * @return
	 */
	@GetMapping("/countOrderByMonth")
	@ResponseBody
	public List<Long> countOrderByMonth(@RequestParam int time){
		return service.countOrderByMonth(time);
	}
	
	/**
	 * 统计某年每季度的订单量
	 * @param time
	 * @return
	 */
	@GetMapping("/countOrderByQuarter")
	@ResponseBody
	public List<Long> countOrderByQuarter(@RequestParam int time){
		return service.countOrderByQuarter(time);
	}
	
	/**
	 * 统计每月的销售额
	 * @param time
	 * @return
	 */
	@GetMapping("/countMoneyByMonth")
	@ResponseBody
	public List<BigDecimal> countMoneyByMonth(@RequestParam int time){
		return service.countMoneyByMonth(time);
	}
	
	/**
	 * 统计每季度的销售额
	 * @param time
	 * @return
	 */
	@GetMapping("/countMoneyByQuarter")
	@ResponseBody
	public List<BigDecimal> countMoneyByQuarter(@RequestParam int time){
		return service.countMoneyByQuarter(time);
	}
	
	/**
	 * 统计每月的总销量
	 * @param time
	 * @return
	 */
	@GetMapping("/countVolumeByMonth")
	@ResponseBody
	public List<Long> countVolumeByMonth(@RequestParam int time){
		return service.countVolumeByMonth(time);
	}
	
	/**
	 * 统计每季度的总销量
	 * @param time
	 * @return
	 */
	@GetMapping("countVolumeByQuarter")
	@ResponseBody
	public List<Long> countVolumeByQuarter(@RequestParam int time){
		return service.countVolumeByQuarter(time);
	}

	/**
	 * 统计某年某月各类书籍的销量
	 * @param year
	 * @param week
	 * @return
	 */
	@GetMapping("countKindVolumeByMonth")
	@ResponseBody
	public List<KindCount> countKindVolumeByMonth(@RequestParam int year, @RequestParam int month){
		return service.countKindVolumeByMonth(year, month);
	}

	/**
	 * 统计某年某周各类书籍的销量
	 * @param year
	 * @param week
	 * @return
	 */
	@GetMapping("countKindVolumeByWeek")
	@ResponseBody
	public List<KindCount> countKindVolumeByWeek(@RequestParam int year, @RequestParam int week){
		return service.countKindVolumeByWeek(year, week);
	}

	/**
	 * 查询某年某月销量前十的书籍
	 * @param year
	 * @param month
	 * @return
	 */
	@GetMapping("selectTopVolumeByMonth")
	@ResponseBody
	public List<VItemBook> selectTopVolumeByMonth(@RequestParam int year, @RequestParam int month){
		return service.selectTopVolumeByMonth(year, month);
	}

	/**
	 * 查询某年某月销量前十的书籍
	 * @param year
	 * @param week
	 * @return
	 */
	@GetMapping("selectTopVolumeByWeek")
	@ResponseBody
	public List<VItemBook> selectTopVolumeByWeek(@RequestParam int year, @RequestParam int week){
		return service.selectTopVolumeByWeek(year, week);
	}
}
