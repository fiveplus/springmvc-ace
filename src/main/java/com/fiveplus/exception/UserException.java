package com.fiveplus.exception;

import org.aspectj.lang.JoinPoint;

import javax.servlet.http.HttpServletRequest;

public class UserException {
	public void doThrowing(JoinPoint jp,Throwable ex){
		HttpServletRequest request = SysContent.getRequest();
		request.setAttribute("error", ex.getMessage());
		request.setAttribute("errorDetail", jp.getTarget().getClass().getName()+"."+jp.getSignature().getName());
		//HttpSession session = SysContent.getSession();
		//session.setAttribute("session", "test session");
	}
}
