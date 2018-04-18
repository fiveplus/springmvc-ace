package com.fiveplus.filter;


import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

public class RoleAuthorizationFilter extends AuthorizationFilter {
	private static final Logger log = LoggerFactory.getLogger(RoleAuthorizationFilter.class); 
	
	protected boolean onAccessDenied(ServletRequest req,
			ServletResponse res, Object mappedValue) throws Exception {
	
		HttpServletRequest request = (HttpServletRequest) req;  
        HttpServletResponse response = (HttpServletResponse) res; 
        Subject subject = getSubject(request, response);
        if(subject.getPrincipal() == null){
        	//判断是否为ajax
        	if("XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"))){
        		//没有登录无权
        		response.setCharacterEncoding("UTF-8");
        		PrintWriter out = response.getWriter();
        		out.print("{code:4,msg:'请先登录。'}");
        		out.flush();
    			out.close();
        	}else{
        		saveRequestAndRedirectToLogin(request, response);
        	}
        }else{
        	if("XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"))){
        		//登录无权
        		response.setCharacterEncoding("UTF-8");
        		PrintWriter out = response.getWriter();
        		out.print("{code:4,msg:'没有授权。'}");
        	}else{
        		String unauthorizedUrl = getUnauthorizedUrl();
        		if (StringUtils.hasText(unauthorizedUrl)) {  
                    WebUtils.issueRedirect(request, response, unauthorizedUrl);  
                } else {  
                    WebUtils.toHttp(response).sendError(401);  
                }  
        	}
        }
        return false;
	}
	
	public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)  
            throws IOException {  
  
        Subject subject = getSubject(request, response);  
        String[] rolesArray = (String[]) mappedValue;  
  
        if (rolesArray == null || rolesArray.length == 0) {  
            // no roles specified, so nothing to check - allow access.  
            return true;  
        }  
  
        Set<String> roles = CollectionUtils.asSet(rolesArray);  
        for (String role : roles) {  
            if (subject.hasRole(role)) {  
                return true;  
            }  
        }  
        return false;  
    }  

	

}
