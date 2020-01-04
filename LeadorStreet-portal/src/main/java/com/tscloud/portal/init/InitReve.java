package com.tscloud.portal.init;

import com.google.common.collect.Maps;
import com.tscloud.portal.utils.StreetXmlConfig;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Map;


public class InitReve {

    private static final Logger log = LogManager.getLogger(InitReve.class);

    public static Map<String, String> dicts = Maps.newHashMap();

    public void init() {
        initDataGrid();

        initSysTask();
    }


    private void initSysTask() {

    }
    private void initDataGrid() {

    }
}
