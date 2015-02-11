package com.tenghu.financial.test.mapper;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.tenghu.financial.mapper.AuthorityMapper;
import com.tenghu.financial.model.Authority;
import com.tenghu.financial.model.page.PageBean;
import com.tenghu.financial.test.BasicTest;
import com.tenghu.financial.utils.JsonMenuUtil;

public class AuthorityMapperTest extends BasicTest{

		@Autowired
		private AuthorityMapper authorityMapper;
		
		/**
		 * 测试查询所有的权限
		 */
		@Test
		public void testQueryAllAuthority(){
			List<Authority> authorityList=authorityMapper.queryAllAuthority();
			for (Authority authority : authorityList) {
				for (Authority childAuth : authority.getChildList()) {
					System.out.println(authority.getAuthName()+"\t"+childAuth.getAuthName());
				}
			}
		}
		
		/**
		 * 测试根据权限级别获取权限
		 */
		@Test
		public void testQueryAuthorityByLevel(){
			List<Authority> authorityList=authorityMapper.queryAuthorityByLevel(1);
			for (Authority authority : authorityList) {
				for (Authority childAuth : authority.getChildList()) {
					System.out.println(authority.getAuthName()+"\t"+childAuth.getAuthName());
				}
			}
		}
		
		@Test
		public void testQueryAuthorityByIds(){
			String[] ids={"1","2","3","4","5","6","7","8","9","10","11","12","13","14"};
			List<Authority> authorityList=authorityMapper.queryAuthorityByIds(ids);
			/*for (Authority authority : authorityList) {
				System.out.println(authority.getParentAuth()+"\t"+authority.getAuthId()+"\t"+authority.getAuthName());
			}*/
			System.out.println(JsonMenuUtil.getJsonMenu(authorityList));
		}
		
		@Test
		public void testQueryAuthorityPage(){
			PageBean<Authority> pageBean=new PageBean<Authority>();
			pageBean.setCurrentPage(1);
			
			List<Authority> authList=authorityMapper.queryAuthorityPage(pageBean);
			System.out.println(authList.size());
		}
}
