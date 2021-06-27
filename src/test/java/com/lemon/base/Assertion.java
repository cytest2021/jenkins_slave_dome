package com.lemon.base;

import org.apache.log4j.Logger;
import org.testng.Assert;

/**
 * @Project: auto_test_framework
 * @Create: 2021-05-23 21:31
 * @Desc：
 **/
public class Assertion {
    static Logger logger = Logger.getLogger(Assertion.class);

    /**
     * TestNG的assertTrue方法二次封装，集成日志
     * @param condition 表达式
     */
     public static void myAssertTrue(boolean condition){
        try{
            Assert.assertTrue(condition);
        }catch(AssertionError e){
            logger.error("断言【"+condition+"】失败");
            logger.error(e);
            //还要向testng测试框架抛出异常，不然有异常用例也不会显示失败
            throw e;
        }
        logger.info("断言【"+condition+"】是否为真");
     }

    /**
     * 断言实际值与期望值的二次封装
     * @param actual
     * @param expected
     */
     public static void myAssertEquals(int actual, int expected){
        try{
            Assert.assertEquals(actual,expected);
        }catch(AssertionError e){
            logger.error("断言实际值【"+actual+"】与期望值【"+expected+"】失败");
            logger.error(e);
            //还要向testng测试框架抛出异常，不然有异常用例也不会显示失败
            throw e;
        }
        logger.info("断言实际值【"+actual+"】与期望值【"+expected+"】");
     }

    /**
     * 断言实际值与期望值的二次封装
     * @param actual
     * @param expected
     */
    public static void myAssertEquals(String actual, String expected){
        try{
            Assert.assertEquals(actual,expected);
        }catch(AssertionError e){
            logger.error("断言实际值【"+actual+"】与期望值【"+expected+"】失败");
            logger.error(e);
            //还要向testng测试框架抛出异常，不然有异常用例也不会显示失败
            throw e;
        }
        logger.info("断言实际值【"+actual+"】与期望值【"+expected+"】");
    }

    /**
     * 断言实际值与期望值的二次封装
     * @param actual
     * @param expected
     */
    public static void myAssertEquals(double actual, double expected){
        try{
            Assert.assertEquals(actual,expected);
        }catch(AssertionError e){
            logger.error("断言实际值【"+actual+"】与期望值【"+expected+"】失败");
            logger.error(e);
            //还要向testng测试框架抛出异常，不然有异常用例也不会显示失败
            throw e;
        }
        logger.info("断言实际值【"+actual+"】与期望值【"+expected+"】");
    }
}
