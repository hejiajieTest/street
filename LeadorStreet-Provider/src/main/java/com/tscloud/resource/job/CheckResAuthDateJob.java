//package com.tscloud.resource.job;
//
//import com.tscloud.common.framework.spring.ServiceBeanContext;
//import com.tscloud.domain.resource.entity.ResAuth;
//import com.tscloud.domain.resource.service.IResAuthService;
//import org.apache.log4j.LogManager;
//import org.apache.log4j.Logger;
//import org.quartz.Job;
//import org.quartz.JobExecutionContext;
//import org.quartz.JobExecutionException;
//
//import java.util.List;
//
///**
// * 检查授权资源效期
// * Created by shm on 2015/10/29.
// */
//public class CheckResAuthDateJob implements Job {
//
//    private IResAuthService resAuthService;
//
//    public CheckResAuthDateJob() {
//
//        resAuthService = (IResAuthService) ServiceBeanContext.getInstance().getBean("resAuthService");
//    }
//
//    private static final Logger log = LogManager.getLogger(CheckResAuthDateJob.class);
//
//    public void execute(JobExecutionContext context) throws JobExecutionException {
//
//        try {
//            //查询所有已过期的授权资源
//            List<ResAuth> overResAuths = resAuthService.findOverRes();
//
//            //设置禁用
//            if (!overResAuths.isEmpty() && null != overResAuths) {
//                for (ResAuth ra : overResAuths) {
//                    ra.setStatus("1");
//                    resAuthService.update(ra);
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
