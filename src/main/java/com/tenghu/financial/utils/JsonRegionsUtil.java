package com.tenghu.financial.utils;

import java.util.ArrayList;
import java.util.List;

import com.tenghu.financial.model.Regions;

/**
 * 地区JSON数据封装工具类
 * @author Arvin_Li
 *
 */
public class JsonRegionsUtil {
	private JsonRegionsUtil(){}
	
	/**
	 * 获取地区JSON数据
	 * @param regionsList
	 * @return
	 */
	public static String getRegionsJson(List<Regions> regionsList){
		//创建StringBuffer
		StringBuffer regionsJson=new StringBuffer("[");
		//创建存放省份地区集合
		List<Regions> provinceList=new ArrayList<Regions>();
		//遍历地区集合
		for (Regions regions : regionsList) {
			if(regions.getLevel()==0){
				provinceList.add(regions);
			}
		}
		
		//转为JSON数据
		for(int i=0;i<provinceList.size();i++){
			Regions regions=provinceList.get(i);
			regionsJson.append("{");
			regionsJson.append("\"id\":\"").append(regions.getCode()).append("\",");
			regionsJson.append("\"name\":\"").append(regions.getName()).append("\",");
			regionsJson.append("\"p_code\":\"").append(regions.getpCode()).append("\",");
			regionsJson.append("\"children\":[").append(getChildRegions(regions, regionsList)).append("]");
			regionsJson.append("}");
			if(i!=provinceList.size()-1){
				regionsJson.append(",");
			}
		}
		regionsJson.append("]");
		return regionsJson.toString();
	}
	
	/**
	 * 获取下级地区地区
	 * @param regions
	 * @param regionsList
	 * @return
	 */
	private static String getChildRegions(Regions regions,List<Regions> regionsList){
		StringBuffer childRegions=new StringBuffer();
		List<Regions> childRegionsList=new ArrayList<Regions>();
		for(Regions reg:regionsList){
			
			if(reg.getpCode().equalsIgnoreCase(regions.getCode())){
				childRegionsList.add(reg);
			}
		}
		//转为JSON数据
		for(int i=0;i<childRegionsList.size();i++){
			Regions reg=childRegionsList.get(i);
			childRegions.append("{");
			childRegions.append("\"id\":\"").append(reg.getCode()).append("\",");
			childRegions.append("\"name\":\"").append(reg.getName()).append("\",");
			childRegions.append("\"p_code\":\"").append(reg.getpCode()).append("\",");
			childRegions.append("\"children\":[").append(getChildRegions(reg, regionsList)).append("]");
			childRegions.append("}");
			if(i!=childRegionsList.size()-1){
				childRegions.append(",");
			}
		}
		return childRegions.toString();
	}
}
