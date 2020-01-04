package com.tscloud.portal.job;

import com.tscloud.common.framework.Constants;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * 系统操作日志
 */
public class SystemLogJob implements Job {


    private static final Logger log = LogManager.getLogger(SystemLogJob.class);
    /**
     * 系统服务
     */
    private static final String[] SYS_NAMES = new String[]{Constants.RestPathPrefix.GATHER, Constants.RestPathPrefix.ETL, Constants.RestPathPrefix.SYSTEM, Constants.RestPathPrefix.ESB};

    public void execute(JobExecutionContext context) throws JobExecutionException {
    }


}
