package com.tenghu.financial.test.mapper;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.tenghu.financial.mapper.RegionsMapper;
import com.tenghu.financial.test.BasicTest;
import com.tenghu.financial.utils.JsonRegionsUtil;

public class RegionsMapperTest extends BasicTest{
	@Autowired
	private RegionsMapper regionsMapper;
	
	@Test
	public void test(){
		System.out.println(JsonRegionsUtil.getRegionsJson(regionsMapper.queryRegionsList()));
	}
}
