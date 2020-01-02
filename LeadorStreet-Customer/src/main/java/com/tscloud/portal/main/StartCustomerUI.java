package com.tscloud.portal.main;


import com.tscloud.portal.utils.StartJetty;

/**
 * 启动GIS-ManagerUI
 */
public class StartCustomerUI {
	public static void main(String[] args) throws Exception {
		StartJetty.getInstance().startJetty();
	}	
}
