package com.fiveplus.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.util.Properties;

public class PropertiesUtils {
	
	private Properties props;
	private URI uri;
	
	private String fileName = "/application.properties";
	
	public PropertiesUtils(){
		readProperties(fileName);
	}
	
	public PropertiesUtils(String fileName){
		readProperties(fileName);
	}
	
	private void readProperties(String fileName){
		try{
			props = new Properties();
			InputStream fis = getClass().getResourceAsStream(fileName);
			props.load(fis);
			uri = this.getClass().getResource(fileName).toURI();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void writeProperties(String key, String value) {    
        try {    
        	OutputStream fos = new FileOutputStream(new File(uri));    
            props.setProperty(key, value);    
            // 将此 Properties 表中的属性列表（键和元素对）写入输出流    
            props.store(fos, "『comments』Update key：" + key);    
        } catch (Exception e) {    
        e.printStackTrace();  
        }    
    }  
	
	public String getProperty(String key){    
        return props.getProperty(key).trim();    
    }  
	
	public static void main(String[] args) {
		PropertiesUtils pu = new PropertiesUtils("/application.properties");
		System.out.println(pu.getProperty("init.pass"));
	}
}
