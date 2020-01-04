package com.tscloud.portal.utils;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 一个反射调用的工具类
 *
 * 暂时做简单的封装，等程序调通后再
 *
 * 对其进行重构，当你看到此工具类的这段换尚未
 *
 * 消失，请email ： chenlu@ccuu.me
 *
 *  Created by chl on 2015/4/25.
 */
public class ReflectUtils {

    private static final Logger log = LogManager.getLogger(ReflectUtils.class);

    public static Object invokeMethod(Method method,Object obj,Object...args) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        return method.invoke(obj, args);
    }

    /**
     * 反射调用指定@class类的指定方法
     * @param param 参数
     * @param methodName 方法名称
     * @param clazz 类
     * @return 结果集
     */
    public static Object invokeQuery(String param, String methodName, Class clazz) {
        Object obj = null;
        try {
            obj = invokeMethod(clazz.getMethod(methodName,String.class),clazz.newInstance(),param);
        } catch (Exception e) {
            log.error("reflect invoke fail,{methodName:" + methodName + ",clazz:" + clazz + "},param:" + param + "", e);
        }
        return obj;
    }



    /**
     * 反射调用指定@class类的指定方法
     * @param methodName 方法名称
     * @param clazz 类
     * @param params 参数
     * @return 结果集
     */
    public static Object invokeQuery(String methodName, Class clazz,Object... params) {
        Object obj = null;
        try {
            Class[] args = new Class[params.length];
            for (int i = 0; i < params.length; i++) {
                args[i] = params[i].getClass();
            }

            Method method = clazz.getMethod(methodName,args);
            obj = invokeMethod(method,clazz.newInstance(), params);
        } catch (Exception e) {
            log.error("reflect invoke fail,{methodName:" + methodName + ",clazz:" + clazz + "},params:" + params.toString() + "", e);
        }
        return obj;
    }
}
