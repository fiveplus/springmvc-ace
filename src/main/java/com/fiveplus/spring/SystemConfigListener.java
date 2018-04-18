package com.fiveplus.spring;

import com.fiveplus.utils.Resource;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.InputStream;
import java.util.Properties;


/**
 * 系统级参数设置
 * @author Mr.Zhang
 *
 */
public class SystemConfigListener implements ServletContextListener {
	private static String contextPath = "web-crm";
	private static Properties sysConfig = new Properties();
	/** 当前应用上下文 */
	private static ServletContext context;
	
	
	public void contextDestroyed(ServletContextEvent sce) {
		sysConfig = null;
	}
	
	
	public void contextInitialized(ServletContextEvent sce) {
		context = sce.getServletContext();
		
		System.out.println("初始化系统服务...");
		// 动态取出当前WEB应用的根路径
		contextPath = context.getContextPath();
		try{
			Resource.contextPath = contextPath;
			//全站注入
			context.setAttribute("contextPath", contextPath);	
			context.setAttribute("URL_IMAGES", contextPath
					+ Resource.URL_IMAGES);
			context.setAttribute("URL_JS", contextPath
					+ Resource.URL_JS);
			context.setAttribute("URL_CSS", contextPath + Resource.URL_CSS);
			context.setAttribute("UPLOAD_CONTEXT",getSysAttribute("upload.context"));

		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("系统服务初始化完成...");
	}
	
	public static Properties getSysConfig(){
		return sysConfig;
	}
	
	public static String getSysAttribute(String key){
		String value = sysConfig.getProperty(key);
		return value;
	}
	
	public static String getContextPath(){
		return contextPath;
	}
	
	public static String getRealPath(String path){
		return context.getRealPath(path);
	}
	
	public static InputStream getResourceAsStream(String path){
		return context.getResourceAsStream(path);
	}
	
	
	
}
