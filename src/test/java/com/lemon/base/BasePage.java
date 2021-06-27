package com.lemon.base;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @Project: auto_test_framework
 * @Create: 2021-05-23 14:45
 * @Desc：
 **/
public class BasePage {
    Logger logger = Logger.getLogger(BasePage.class);

    /**
     * 点击元素的二次封装
     * @param by
     * @param desc
     */
    public void click(By by, String desc){
        try {
            BaseTest.driver.findElement(by).click();
        }catch (Exception e){
            logger.error("元素定位异常【"+desc+"】");
            logger.error(e);
            //还要向testng测试框架抛出异常，不然有异常用例也不会显示失败
            throw e;
        }
        logger.info("点击了元素【"+desc+"】");
    }

    /**
     * 输入操作的二次封装
     * @param by
     * @param desc
     * @param data
     */
    public void type(By by, String desc, String data){
        try{
            BaseTest.driver.findElement(by).sendKeys(data);
        }catch(Exception e){
            logger.error("元素定位异常【"+desc+"】");
            logger.error(e);
            //还要向testng测试框架抛出异常，不然有异常用例也不会显示失败
            throw e;
        }
        logger.info("往元素【"+desc+"】输入数据【"+data+"】");
    }

    /**
     * 获取文本值的二次封装
     * @param by
     * @return
     */
    public String getText(By by, String desc){
        WebElement webElement = null;
        try{
            WebDriverWait wait = new WebDriverWait(BaseTest.driver,5);
            webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        }catch(Exception e){
            logger.error("元素定位异常【"+desc+"】");
            logger.error(e);
            //还要向testng测试框架抛出异常，不然有异常用例也不会显示失败
            throw e;
        }
        logger.info("获取元素【"+desc+"】的文本【"+webElement.getText()+"】");
        return webElement.getText();
    }

    /**
     * 获取元素属性值
     * @param by
     * @param desc
     * @param attributeName
     * @return
     */
    public String getAttributeValue(By by, String desc, String attributeName){
        WebElement webElement = null;
        try{
            webElement = BaseTest.driver.findElement(by);
        }catch(Exception e){
            logger.error("元素定位异常【"+desc+"】");
            logger.error(e);
            //还要向testng测试框架抛出异常，不然有异常用例也不会显示失败
            throw e;
        }
        logger.info("获取元素【"+desc+"】的【"+attributeName+"】属性的值【"+webElement.getAttribute(attributeName)+"】");
        return webElement.getAttribute(attributeName);
    }

    /**
     * 判断元素是否可见公共封装
     * @param by
     * @param desc
     * @return
     */
    public boolean isElementVisible(By by, String desc){
        try{
            BaseTest.driver.findElement(by).isDisplayed();
        }catch(Exception e){
            logger.error("判断元素【"+desc+"】可见异常");
            logger.error(e);
            throw e;
        }
        logger.info("判断元素【"+desc+"】可见正常");
        return true;
    }
}
