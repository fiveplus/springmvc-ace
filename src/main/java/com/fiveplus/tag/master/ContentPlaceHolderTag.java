package com.fiveplus.tag.master;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class ContentPlaceHolderTag extends TagSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public int doEndTag() throws JspException {
		JspWriter out = pageContext.getOut();
		Object obj = this.pageContext.getRequest().getAttribute(this.getId());
		try{
			if(obj != null){
				out.write(obj.toString());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}
	
}
