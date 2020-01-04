/**
 * SimpleCORSFilter.java	  V1.0   2019年11月14日 下午3:36:10
 *
 * Copyright 2019 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.tscloud.portal.utils;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class SpringCORSFilter implements Filter{  
	   @Override  
	    public void destroy() {  
	          
	    }  
	  
	    @Override  
	    public void doFilter(ServletRequest req, ServletResponse res,  
	            FilterChain chain) throws IOException, ServletException {  
	            HttpServletResponse response = (HttpServletResponse) res;  
	            response.setHeader("Access-Control-Allow-Origin", "*");  
	            response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");  
	            response.setHeader("Access-Control-Max-Age", "3600");  
	            response.setHeader("Access-Control-Allow-Headers", "x-requested-with");  
	            chain.doFilter(req, res);  
	          
	    }  
	  
	    @Override  
	    public void init(FilterConfig arg0) throws ServletException {  
	          
	    }  
}
