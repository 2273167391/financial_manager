package com.tenghu.financial.utils;

import java.util.ArrayList;
import java.util.List;

import com.tenghu.financial.model.Authority;

/**
 * Json格式菜单工具类
 * @author Arvin_Li
 *
 */
public class JsonMenuUtil {
	private JsonMenuUtil(){}
	
	/**
	 * 获取JSON格式菜单
	 * @param authorityList 权限集合
	 * @return
	 */
	public static String getJsonMenu(List<Authority> authorityList){
		//创建StringBuffer
		StringBuffer jsonMenu=new StringBuffer("[");
		//声明存放父菜单集合
		List<Authority> parentAuthorityList=new ArrayList<Authority>();
		//遍历所有菜单集合
		for (Authority authority : authorityList) {
			if(authority.getLevel()==2){
				parentAuthorityList.add(authority);
			}
		}
		
		//遍历父菜单集合拼接为JSON数据
		for (int i=0;i<parentAuthorityList.size();i++) {
			Authority authority=parentAuthorityList.get(i);
			jsonMenu.append("{");
			jsonMenu.append("\"id\":").append(authority.getAuthId()).append(",");
			jsonMenu.append("\"name\":\"").append(authority.getAuthName()).append("\",");
			jsonMenu.append("\"url\":\"").append(authority.getUrl()).append("\",");
			jsonMenu.append("\"parentId\":").append(authority.getParentAuth().getAuthId()).append(",");
			jsonMenu.append("\"children\":[").append(getChildMenus(authorityList,authority)).append("]");
			jsonMenu.append("}");
			if(parentAuthorityList.size()-1!=i){
				jsonMenu.append(",");
			}
		}
		jsonMenu.append("]");
		return jsonMenu.toString();
	}
	
	/**
	 * 获取子菜单
	 * @param authorityList 所有菜单集合
	 * @param authority 父菜单对象
	 * @return
	 */
	private static String getChildMenus(List<Authority> authorityList,Authority authority){
		//创建StringBuffer
		StringBuffer childMenu=new StringBuffer();
		//创建存放子菜单集合
		List<Authority> childAuthorityList=new ArrayList<Authority>();
		//遍历所有菜单
		for(int i=0;i<authorityList.size();i++){
			Authority childAuth=authorityList.get(i);
			if(null==childAuth.getParentAuth()) continue;
			if(authority.getAuthId()==childAuth.getParentAuth().getAuthId()){
				childAuthorityList.add(childAuth);
			}
		}
		//遍历子菜单
		for(int i=0;i<childAuthorityList.size();i++){
			Authority childAuth=childAuthorityList.get(i);
			childMenu.append("{");
			childMenu.append("\"id\":").append(childAuth.getAuthId()).append(",");
			childMenu.append("\"name\":\"").append(childAuth.getAuthName()).append("\",");
			childMenu.append("\"url\":\"").append(childAuth.getUrl()).append("\",");
			childMenu.append("\"parentId\":").append(childAuth.getParentAuth().getAuthId()).append(",");
			childMenu.append("\"children\":[").append(getChildMenus(authorityList,childAuth)).append("]");
			childMenu.append("}");
			if(childAuthorityList.size()-1!=i){
				childMenu.append(",");
			}
		}
		return childMenu.toString();
	}
}
