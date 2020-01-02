package com.tscloud.resource.utils;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tscloud.common.framework.Constants;
import com.tscloud.common.framework.spring.ServiceBeanContext;

public class DispatchServlet extends HttpServlet{
	private static final long serialVersionUID = -2871793876774691057L;
	public static final Logger log = LoggerFactory.getLogger(DispatchServlet.class);
	//InitReve initReve = new InitReve();
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		//初始化定时任务
//		initSysTask();

		super.init(config);
		try {
			ServiceBeanContext.getInstance().loadContext(Constants.Env.BASE_HOME+"applicationContext.xml");
			//启动代理服务器
			//ProxyServer.startProxy();

			//initReve.init();
		} catch (Exception e) {
			log.error("启动初始化applicationContext.xml失败", e);
		}

	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String className=req.getParameter("className");
		String methodName=req.getParameter("methodName");
		
		try {
			Object obj=ServiceBeanContext.getInstance().getBean(className);
			Method invokeMethod=obj.getClass().getMethod(methodName, 
					new Class[]{HttpServletRequest.class,HttpServletResponse.class});
			
			if(invokeMethod!=null){
				req.setCharacterEncoding("UTF-8");
				invokeMethod.invoke(obj,req,resp);
			} else {
			   throw new Exception("the invokeMethod is null");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



}
