package com.tenghu.financial.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenghu.financial.mapper.RegionsMapper;
import com.tenghu.financial.service.IRegionsService;
import com.tenghu.financial.utils.JsonRegionsUtil;

@Service
public class RegionsServiceImpl implements IRegionsService{

	@Autowired
	private RegionsMapper regionsMapper;
	
	@Override
	public String getRegions() {
		return JsonRegionsUtil.getRegionsJson(regionsMapper.queryRegionsList());
	}

}
