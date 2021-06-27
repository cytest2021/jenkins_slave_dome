package com.lemon.testcases;

import com.lemon.base.BaseTest;
import com.lemon.data.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

/**
 * @Project: auto_test_framework
 * @Create: 2021-05-22 17:57
 * @Desc：
 **/
public class LoginTestWork extends BaseTest {

    @BeforeMethod
    public void setup(){
        //初始化浏览器
        openBrowser(Constants.TEST_BROWSER);
        //打开登录页面
        driver.get(Constants.LOGIN_URL);
        //设置窗口最大化
        driver.manage().window().maximize();
        //设置隐式等待
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test(priority = 1,description = "密码为空登录")
    public void loginTestFailure(){
        //进行登录操作
        driver.findElement(By.name("phone")).sendKeys(Constants.CORRECT_PHONE);
        driver.findElement(By.name("password")).sendKeys("");
        driver.findElement(By.xpath("//button")).click();
        //断言
        WebElement webElement = driver.findElement(By.className("form-error-info"));
        Assert.assertEquals(webElement.getText(),"请输入密码");
    }

    @Test(priority = 2,dataProvider = "dataProviderDatas",description = "密码输入不正确登录")
    public void loginTestFailure2(String pwd){
        //进行登录操作
        driver.findElement(By.name("phone")).sendKeys(Constants.CORRECT_PHONE);
        driver.findElement(By.name("password")).sendKeys(pwd);
        driver.findElement(By.xpath("//button")).click();
        //断言
        WebDriverWait wait = new WebDriverWait(driver,5);
        WebElement webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("layui-layer-content")));
        Assert.assertEquals(webElement.getText(),"帐号或密码错误!");
    }

    @Test(priority = 3, description = "记住手机号功能验证")
    public void loginTestRemember(){
        //进行登录操作
        driver.findElement(By.name("phone")).sendKeys(Constants.CORRECT_PHONE);
        driver.findElement(By.name("password")).sendKeys(Constants.CORRECT_PASSWORD);
        //点击登录
        driver.findElement(By.xpath("//button")).click();
        //退出登录
        driver.findElement(By.xpath("//a[text()='退出']")).click();
        //进入登录页面
        driver.findElement(By.xpath("//a[text()='登录']")).click();
        //断言
        WebElement webElement = driver.findElement(By.name("phone"));
        Assert.assertEquals(webElement.getAttribute("value"),Constants.CORRECT_PHONE);
    }

    @DataProvider
    public Object[] dataProviderDatas(){
        Object[] datas = {"LEMON123456","lemon1234567","lemon12"," lemon 123456 "};
        return datas;
    }

    @AfterMethod
    public void teardown(){
        //关闭浏览器
        driver.quit();
    }


}
