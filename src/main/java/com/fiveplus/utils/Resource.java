package com.fiveplus.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 系统静态资源
 * @author hack
 *
 */
public class Resource {
	
	public static String contextPath = "/web-crm";
	public final static String URL_IMAGES = "/common/images/";
	public final static String URL_CSS = "/common/css/";
	public final static String URL_JS = "/common/js/";
	public final static String CURRENT_SYSTEM = "/admin";
	
	public static final String Y = "Y";
	public static final String D = "D";
	public static final String N = "N";

	public static String image(String file){
		StringBuffer imgBf = new StringBuffer(contextPath + URL_IMAGES);
		imgBf.append(file);
		return imgBf.toString();
	}
	
	public static String js(String file){
		StringBuffer jsBf = new StringBuffer(contextPath + URL_JS);
		jsBf.append(file);
		return jsBf.toString();
	}
	
	public static String css(String file) {
		StringBuffer jsBf = new StringBuffer(contextPath + URL_CSS);
		jsBf.append(file);
		return jsBf.toString();
	}
	
	public static String curSystem(){
		return CURRENT_SYSTEM;
	}
	
	public static String boot(){
		return contextPath;
	}
}
