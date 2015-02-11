package com.tenghu.financial.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tenghu.financial.service.IAccountServer;
import com.tenghu.financial.service.IUsersService;

/**
 * 统计管理控制器
 * @author Arvin_Li
 *
 */
@Controller
@RequestMapping(value="/statistics")
public class StatisticsController {
	
	@Autowired
	private IAccountServer accountServer;
	@Autowired
	private IUsersService usersService;
	
	/**
	 * 年统计
	 * @param paramters
	 * @return
	 */
	@RequestMapping(value="/year/{year}/{status}/{typeId}")
	@ResponseBody
	public List<Map<String, Object>> yearStatistics(@PathVariable("year") String year,@PathVariable("status") int status,@PathVariable("typeId") int typeId){
		Map<String, Object> paramters=new HashMap<String, Object>();
		paramters.put("year", year);
		paramters.put("status", status);
		paramters.put("typeId", typeId);
		paramters.put("user", usersService.getCurrentUsers().getuId());
		return accountServer.queryYearStatistics(paramters);
	}
	
	/**
	 * 月统计
	 * @param year
	 * @param status
	 * @return
	 */
	@RequestMapping(value="/month/{month}/{status}/{typeId}")
	@ResponseBody
	public List<Map<String, Object>> monthStatistics(@PathVariable("month") String month,@PathVariable("status") int status,@PathVariable("typeId") int typeId){
		Map<String, Object> paramters=new HashMap<String, Object>();
		paramters.put("month", month);
		paramters.put("status", status);
		paramters.put("typeId", typeId);
		paramters.put("user", usersService.getCurrentUsers().getuId());
		return accountServer.queryMonthStatistics(paramters);
	}
	
	/**
	 * 分类统计
	 * @param year
	 * @param status
	 * @return
	 */
	@RequestMapping(value="/type/{month}/{status}")
	@ResponseBody
	public List<Map<String, Object>> typeStatistics(@PathVariable("month") String month,@PathVariable("status") int status){
		Map<String, Object> paramters=new HashMap<String, Object>();
		paramters.put("month", month);
		paramters.put("status", status);
		paramters.put("user", usersService.getCurrentUsers().getuId());
		return accountServer.queryTypeStatistics(paramters);
	}
}
