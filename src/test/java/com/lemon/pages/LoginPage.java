package com.lemon.pages;

import com.lemon.base.BasePage;
import com.lemon.data.Constants;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @Project: auto_test_framework
 * @Create: 2021-05-22 21:19
 * @Desc：页面元素定位
 **/
public class LoginPage extends BasePage {
    //属性--元素定位信息（元素定位方式+表达式）
    //手机号码输入框
    By phoneBy = By.name("phone");
    //密码输入框
    By passwordBy = By.name("password");
    //登录按钮
    By loginBy = By.xpath("//button[text()='登录']");
    //页面中间提示
    By centerTipsBy = By.className("layui-layer-content");
    //电话号码输入框错误提示语
    By phoneErrorTipsBy = By.className("form-error-info");

    /**
     * 登录柠檬班前台
     * @param phone
     * @param password
     */
    public void login(String phone, String password){
        //driver.findElement(phoneBy).sendKeys(phone);
        type(phoneBy,"登录页面_电话输入框",phone);
        //driver.findElement(passwordBy).sendKeys(password);
        type(passwordBy,"登录页面_密码输入框",password);
        //driver.findElement(loginBy).click();
        click(loginBy,"登录页面_登录按钮");
    }

    /**
     * 获取中间提示信息
     * @return
     */
    public String centerTips(){
        return getText(centerTipsBy,"登录页面_中间提示信息");
    }

    /**
     * 获取手机号码错误提示信息
     * @return
     */
    public String phoneErrorTips(){
        return getText(phoneErrorTipsBy,"登录页面_手机号码错误提示信息");
    }

}
