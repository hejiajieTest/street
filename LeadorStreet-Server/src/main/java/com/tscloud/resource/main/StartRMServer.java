package com.tscloud.resource.main;


import com.tscloud.resource.utils.StartJetty;

public class StartRMServer {
	public static void main(String[] args) throws Exception {
		//start jetty  server
		StartJetty.getInstance().startJetty();
	}
}
