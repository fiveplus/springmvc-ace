package com.fiveplus.utils;

import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 1.传入字段
 * 2.判断次数
 * 3.拼装order
 * @author hack
 *
 */
public class OrderUtils {
	private static final String ASC = "ASC";
	private static final String DESC = "DESC";
	
	
	public static String getOrder(String order,HttpSession session){
		if(order == null || order.equals("")) return null;
		Map<String,String> map = (Map<String,String>)session.getAttribute("orders");
		if(map == null) map = new LinkedHashMap<String,String>();
		boolean flag = true;
		for(Map.Entry<String, String> m : map.entrySet()){
			if(m.getKey().equals(order)){
				if(m.getValue().equals(ASC)){
					m.setValue(DESC);
				}else{
					map.remove(m.getKey());
				}
				break;
			}else{
				flag = false;
			}
		}
		
		if(!flag || map.size() == 0) map.put(order, ASC);
		
		String str = "";
		for(Map.Entry<String, String> m : map.entrySet()){
			str += m.getKey() + " " + m.getValue() + ","; 
		}
		str = str.substring(0,str.length()-1);
		session.setAttribute("orders", map);
		return str;
	}
	
	
	
	
}
