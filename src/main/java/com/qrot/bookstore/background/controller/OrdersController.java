package com.qrot.bookstore.background.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.qrot.bookstore.background.model.Orders;
import com.qrot.bookstore.background.model.VItemBook;
import com.qrot.bookstore.background.service.BackOrderService;

/**
 * 
 * @author TanZhiHua
 *
 */
@RequestMapping("/api/1")
@RestController
@CrossOrigin
public class OrdersController {

	@Autowired
	BackOrderService service;
	
	/**
	 * 查询某个订单信息
	 * @param id
	 * @return
	 */
	@GetMapping(path="/getOrder/{id}",consumes="application/json",produces="application/json")
	@ResponseBody
	public Orders loadOrder(@PathVariable int id) {
		return service.loadOrder(id);
	}
	
	/**
	 * 修改订单状态
	 * @param order
	 * @return
	 */
	@PutMapping(path="/updateOrder")
	public String updateOrder(@RequestParam int id) {
		service.updateOrder(id);
		return "{msg: 'ok'}";
	}
	
	/**
	 * 得到某订单的所有项，只含订单项的信息
	 * @param id
	 * @return
	 */
	@GetMapping(path="/getItems/{id}",consumes="application/json",produces="application/json")
	@ResponseBody
	public List<VItemBook> getItems(@PathVariable int id){
		return service.getItems(id);
	}
	
	/**
	 * 通过orderID查询订单信息，包括订单信息和订单项信息
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(path="/getOrderItem/byID/{id}")
	@ResponseBody
	public List<Orders> getOrderByID(@PathVariable int id){
		return service.getOrderByID(id);
	}
	
	/**
	 * 统计某时间段内的订单量
	 * 
	 * @param start 起始时间
	 * @param end   终止时间
	 * @return
	 */
	@GetMapping(path="/getOrderLen/byTime")
	@ResponseBody
	public int getLenByTime(
			@RequestParam(name="s", defaultValue="1970-01-01",required=false) String start,
			@RequestParam(name="e",required=false) String end) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date sta = null;
		Date en = null;
		try {
			sta = sdf.parse(start);
			if(end==null) {
				en = new Date();
			}else {
				en=sdf.parse(end);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return service.getLenByTime(sta, en);
	}
	
	/**
	 * 通过时间查询订单，包括订单信息和订单项信息
	 * 
	 * @param start 起始时间
	 * @param end   终止时间
	 * @param len   分页长度长度
	 * @param offset偏移量
	 * @return
	 */
	@GetMapping(path="/getOrderItem/byTime")
	@ResponseBody
	public List<Orders> getOrderByTime(
			@RequestParam(name="s", defaultValue="1970-01-01",required=false) String start,
			@RequestParam(name="e",required=false) String end,
			@RequestParam(name="l", defaultValue="6",required=false) int len,
			@RequestParam(name="o", defaultValue="0", required=false) int offset){
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date sta = null;
		Date en = null;
		try {
			sta = sdf.parse(start);
			if(end==null) {
				en = new Date();
			}else {
				en=sdf.parse(end);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return service.getOrderByTime(sta, en, len, offset);
	}
	
	/**
	 * 统计某个状态的订单量
	 * 
	 * @param state  订单状态
	 * @return
	 */
	@GetMapping(path="/getOrderLen/byState")
	@ResponseBody
	public int getLenByState(
			@RequestParam(name="s", required=false) char state) {
		return service.getLenByState(state);
	}
	
	/**
	 * 通过订单状态查询订单，包括订单信息和订单项信息
	 * 
	 * @param state
	 * @param len  长度
	 * @param offset  偏移量
	 * @return
	 */
	@GetMapping(path="/getOrderItem/byState")
	@ResponseBody
	public List<Orders> getOrderByState(
			@RequestParam(name="s", defaultValue="1",required=false) char state,
			@RequestParam(name="l", defaultValue="5",required=false) int len,
			@RequestParam(name="o", defaultValue="0", required=false) int offset){
		return service.getOrderByState(state, len, offset);
	}
	
	/**
	 * 统计某个时间段内某个状态的订单量
	 * 
	 * @param 起始时间
	 * @param end   终止时间
	 * @param state 订单状态
	 * @return
	 */
	@GetMapping(path="/getOrderLen/byTimeState")
	@ResponseBody
	public int getLenByTimeAndState(
			@RequestParam(name="st", defaultValue="1970-01-01",required=false) String start,
			@RequestParam(name="e",required=false) String end,
			@RequestParam(name="se", defaultValue="1",required=false) char state) {
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date sta = null;
		Date en = null;
		try {
			sta = sdf.parse(start);
			if(end==null) {
				en = new Date();
			}else {
				en=sdf.parse(end);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return service.getLenByTimeAndState(sta, en,state);
	}
	
	
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
	@GetMapping(path="/getOrderItem/byTimeState")
	@ResponseBody
	public List<Orders> getOrderByTimeAndState(
			@RequestParam(name="st", defaultValue="1970-01-01",required=false) String start,
			@RequestParam(name="e",required=false) String end,
			@RequestParam(name="se", defaultValue="3",required=false) char state,
			@RequestParam(name="l", defaultValue="5",required=false) int len,
			@RequestParam(name="o", defaultValue="0", required=false) int offset){
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date sta = null;
		Date en = null;
		try {
			sta = sdf.parse(start);
			if(end==null) {
				en = new Date();
			}else {
				en=sdf.parse(end);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return service.getOrderByTimeAndState(sta, en, state, len, offset);
	}
}
