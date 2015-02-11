package com.tenghu.financial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tenghu.financial.service.IRegionsService;

/**
 * 地区控制器
 * @author Arvin_Li
 *
 */
@Controller
@RequestMapping(value="/regions")
public class RegionsController {
	@Autowired
	private IRegionsService regionsService;
	
	@RequestMapping(value="/data")
	@ResponseBody
	public String getRegions(){
		return regionsService.getRegions();
	}
}
