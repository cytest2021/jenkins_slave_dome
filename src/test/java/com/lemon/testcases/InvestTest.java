package com.lemon.testcases;

import com.lemon.base.Assertion;
import com.lemon.base.BaseTest;
import com.lemon.data.Constants;
import com.lemon.pages.InvestPage;
import com.lemon.pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * @Project: auto_test_framework
 * @Create: 2021-05-22 23:33
 * @Desc：
 **/
public class InvestTest extends BaseTest {

    String loadTitle;

    @BeforeMethod
    public void setup(){
        //投资的前置
        //1、打开浏览器
        openBrowser("chrome");
        //窗口最大化
        maxWindow();
        //设置隐式等待
        setImplicitlyWait(10);
        //2、准备一个可投标的项目
        loadTitle = addBid(driver);
        //3、访问地址
        toUrl(Constants.LOGIN_URL);
        //4、投资人登录成功
        LoginPage loginPage = new LoginPage();
        loginPage.login(Constants.CORRECT_PHONE,Constants.CORRECT_PASSWORD);
        //5、账户金额足够？
        /*
        测试前的数据准备问题？
        1）通过UI界面准备测试数据  缺点：速度慢  优点：直观
        2）调用接口方式    缺点：需要接口可用   优点：速度快
        3）数据库方式     缺点：需要清楚业务逻辑 优点：最推荐的方法
         */
    }
    @Test
    public void investTestSuccess() throws InterruptedException {
        //进入投标窗口
        InvestPage investPage = new InvestPage();
        investPage.intoInvestWindow(loadTitle);
        //获取投标前账户剩余金额
        double beforeLeaveAmount = investPage.getLeaveAmount();
        //投标
        investPage.investBid("2000");
        //断言
        //1、根据投资成功文本信息断言
        Thread.sleep(1000);
        Assertion.myAssertEquals(investPage.getBidSuccessText(),"投标成功！");
        //2、投资金额减少
        investPage.closeBidSuccessPop();
        //刷新页面
        refreshPage();
        double allAmount = investPage.getAllAmount();
        double amountToBeInvest = investPage.getAmountToBeInvest();
        Assertion.myAssertEquals(allAmount-amountToBeInvest,2000);
        //3、可用账户余额减少
        double afterLeaveAmount = investPage.getLeaveAmount();
        Assertion.myAssertEquals(beforeLeaveAmount-afterLeaveAmount,2000);
    }
    @AfterMethod
    public void teardown(){
        closeBrowser();
    }

    public static String addBid(WebDriver driver) {
        //打开前程贷后台
        driver.get("http://8.129.91.152:8765//Admin/Index/login.html");
        //输入账号、密码、验证码，登录
        driver.findElement(By.xpath("//input[@placeholder='账号']")).sendKeys("lemon7");
        driver.findElement(By.xpath("//input[@placeholder='密码']")).sendKeys("lemonbest");
        driver.findElement(By.xpath("//input[@placeholder='验证码']")).sendKeys("hapi");
        driver.findElement(By.xpath("//*[text()='登陆后台']")).click();

        //找到贷款管理
        driver.findElement(By.xpath("//div[@id='_easyui_tree_15']/span[4]")).click();
        //点击加标
        driver.switchTo().frame("mainFrame");
        driver.findElement(By.xpath("//*[text()='加标']")).click();

        //输入借款人号码
        driver.findElement(By.xpath("//*[@placeholder='输入手机号码进行查找']")).sendKeys("13323234444");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //选中借款人
        driver.findElement(By.xpath("//*[@id='datagrid-row-r2-2-0']/td[1]/div")).click();
        //输入贷款标题
        String title = "小阳" + System.currentTimeMillis()/1000;
        driver.findElement(By.xpath("//*[@id='add_loan']/div[1]/div[2]/div[1]/div/table/tbody/tr[3]/td[2]/input")).sendKeys(title);
        //输入年利息率
        driver.findElement(By.xpath("//*[@id='add_loan']/div[1]/div[2]/div[1]/div/table/tbody/tr[4]/td[2]/input")).sendKeys("5");
        //输入借款期限
        driver.findElement(By.xpath("//*[@id='add_loan']/div[1]/div[2]/div[1]/div/table/tbody/tr[7]/td[2]/input")).sendKeys("12");
        //输入借款额度
        driver.findElement(By.xpath("//*[@id='add_loan']/div[1]/div[2]/div[1]/div/table/tbody/tr[8]/td[2]/input")).sendKeys("100000");
        //输入竞标期限
        driver.findElement(By.xpath("//*[@id='add_loan']/div[1]/div[2]/div[1]/div/table/tbody/tr[15]/td[2]/input")).sendKeys("10");
        //切换到风控评测表单
        driver.findElement(By.xpath("//span[text()='风控评测']")).click();
        //输入评估价值
        driver.findElement(By.xpath("//*[@id='add_loan']/div[1]/div[2]/div[2]/div/table/tbody/tr[2]/td[2]/input")).sendKeys("100000");
        //切换到项目录入表单
        driver.findElement(By.xpath("//span[text()='项目录入']")).click();
        //输入籍贯
        driver.findElement(By.xpath("//*[@id='add_loan']/div[1]/div[2]/div[3]/div/table/tbody/tr[1]/td[2]/input")).sendKeys("广东");
        //输入职业
        driver.findElement(By.xpath("//*[@id='add_loan']/div[1]/div[2]/div[3]/div/table/tbody/tr[2]/td[2]/input")).sendKeys("公务员");
        //输入年龄
        driver.findElement(By.xpath("//*[@id='add_loan']/div[1]/div[2]/div[3]/div/table/tbody/tr[3]/td[2]/input")).sendKeys("24");
        //点击提交
        driver.findElement(By.xpath("//span[text()='提交']")).click();

        //新增数据的xpath地址
        String path = "//div[text()='" + title +"']";
        //进行三次审核
        for (int i=1; i <= 3; i++){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //选中新增的数据
            driver.findElement(By.xpath(path)).click();
            //点击审核按钮
            driver.findElement(By.xpath("//span[text()='审核']")).click();
            //点击审核通过
            driver.findElement(By.xpath("//span[text()='审核通过']")).click();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return title;
    }
}
