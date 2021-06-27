package com.lemon.pages;

import com.lemon.base.BasePage;
import com.lemon.base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @Project: auto_test_framework
 * @Create: 2021-05-22 22:38
 * @Desc：
 **/
public class IndexPage extends BasePage {
    //退出按钮
    By logoutBy = By.linkText("退出");

    public boolean isQuitDisplay(){
        return isElementVisible(logoutBy,"首页_退出按钮");
    }
}
