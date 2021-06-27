package com.lemon.testcases;

import com.lemon.base.Assertion;
import com.lemon.base.BaseTest;
import com.lemon.data.Constants;
import com.lemon.pages.IndexPage;
import com.lemon.pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

/**
 * @Project: auto_test_framework
 * @Create: 2021-05-18 23:56
 * @Desc：
 **/
public class LoginTest extends BaseTest {

    @BeforeMethod
    public void setup(){
        //前置
        //1、指定测试的浏览器
        openBrowser(Constants.TEST_BROWSER);
        //2、测试的网站
        toUrl(Constants.LOGIN_URL);
        //3、最大化窗口
        maxWindow();
        //4、设置全局隐式等待
        setImplicitlyWait(5);
    }

    @Test(priority = 1)
    public void loginTestSuccess(){
        //正确账号密码登录
        //输入账号、密码，点击登录
        LoginPage loginPage = new LoginPage();
        loginPage.login(Constants.CORRECT_PHONE,Constants.CORRECT_PASSWORD);
        //断言
        //1、根据元素出现（根据退出按钮出现）
        IndexPage indexPage = new IndexPage();
        //Assert.assertTrue(indexPage.isQuitDisplay(driver));
        Assertion.myAssertTrue(indexPage.isQuitDisplay());
        //2、页面的变化（url发生了变化）
        //Assert.assertEquals(currentUrl,"http://8.129.91.152:8765/Index/index");
        Assertion.myAssertEquals(getCurrentPageUrl(),"http://8.129.91.152:8765/Index/index");
    }

    @Test(priority = 2)
    public void loginTestFailure1(){
        //错误的账号密码登录
        //输入账号、密码，点击登录
        LoginPage loginPage = new LoginPage();
        loginPage.login("15859019266","123456");
        //断言
        //根据页面提示信息
        Assertion.myAssertEquals(loginPage.centerTips(),"此账号没有经过授权，请联系管理员!");
    }

//    @Test(priority = 3)
//    public void loginTestFailure2(){
//        //错误的手机号码登录
//        //输入账号、密码，点击登录
//        driver.findElement(By.name("phone")).sendKeys("");
//        driver.findElement(By.name("password")).sendKeys("Ab%12345");
//        driver.findElement(By.xpath("//button[text()='登录']")).click();
//        //断言
//        WebElement webElement = driver.findElement(By.className("form-error-info"));
//        Assert.assertEquals(webElement.getText(),"请输入手机号");
//    }
//
//    @Test(priority = 4)
//    public void loginTestFailure3(){
//        //错误的手机号码登录
//        //输入账号、密码，点击登录
//        driver.findElement(By.name("phone")).sendKeys("1585901925");
//        driver.findElement(By.name("password")).sendKeys("Ab%12345");
//        driver.findElement(By.xpath("//button[text()='登录']")).click();
//        //断言
//        WebElement webElement = driver.findElement(By.className("form-error-info"));
//        Assert.assertEquals(webElement.getText(),"请输入正确的手机号");
//    }

    @Test(priority = 3, dataProvider = "loginTestFailureDatas")
    public void loginTestFailure2(String phone,String pwd,String tips){
        //调用LoginPage中的login方法
        LoginPage loginPage = new LoginPage();
        loginPage.login(phone,pwd);
        //断言
        Assertion.myAssertEquals(loginPage.phoneErrorTips(),tips);
    }

    @DataProvider
    //只要有@DataProvider就是数据驱动
    public Object[][] loginTestFailureDatas(){
        Object [][] datas = {{"","Ab%12345","请输入手机号"},
                {"1585901925","Ab%12345","请输入正确的手机号"}};
        //也可以读取excel，读取数据库
        return datas;
    }

    @AfterMethod
    public void teardown(){
        //后置
        //关闭浏览器
        closeBrowser();
    }
}
