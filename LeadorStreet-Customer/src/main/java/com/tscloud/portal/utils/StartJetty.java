package com.tscloud.portal.utils;

import com.tscloud.common.framework.config.ConfigHelper;
import com.tscloud.common.utils.DateUtil;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

import java.net.InetAddress;

public class StartJetty {
	private static StartJetty sj = null;
	private static boolean status = false;
	private StartJetty() {
	}

	public static StartJetty getInstance() {
		if (sj == null) {
			sj = new StartJetty();
		}
		return sj;
	}

	public static boolean getStatus() {
		return status;
	}

	public void startJetty() {
		Server server = new Server(Integer.parseInt(ConfigHelper.getJettyParameter("server.port")));
		WebAppContext webapp = new WebAppContext();
		webapp.setContextPath(ConfigHelper.getJettyParameter("server.name"));
		webapp.setResourceBase(ConfigHelper.getJettyParameter("server.resource"));
		webapp.setDescriptor(ConfigHelper.getJettyParameter("server.resource")+"/"+ConfigHelper.getJettyParameter("server.descriptor"));
		webapp.setInitParameter( "org.eclipse.jetty.servlet.Default.useFileMappedBuffer", "false");
		webapp.setParentLoaderPriority(true);
		webapp.setClassLoader(Thread.currentThread().getContextClassLoader());
		server.setHandler(webapp);
		try {
			System.out.println("===========================================================");
			System.out.println("[" + DateUtil.getDateTime()+ "] PortalGis UI is starting......");
			server.start();
			System.out.println("[" + DateUtil.getDateTime()+ "] PortalGis UI has been started.");
			System.out.println("[" + DateUtil.getDateTime()+ "] PortalGis UI visit: http://" + InetAddress.getLocalHost().getHostAddress() + ":"+ ConfigHelper.getJettyParameter("server.port")+ ConfigHelper.getJettyParameter("server.name"));
			System.out.println("[" + DateUtil.getDateTime()+ "] PortalGis UI Start successfully.");
			System.out.println("===========================================================");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
