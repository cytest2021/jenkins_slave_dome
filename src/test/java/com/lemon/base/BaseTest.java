package com.lemon.base;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;


import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @Project: auto_test_framework
 * @Create: 2021-05-22 17:46
 * @Desc： 父类方法-提供给用例继承
 **/
public class BaseTest {
    Logger logger = Logger.getLogger(BaseTest.class);

    //定义一个公共的静态driver，优化代码中的driver
    public static WebDriver driver;

    /** 多态：父类接收子类对象
     * 统一浏览器封装
     * @param browserName 浏览器名称
     */
    public void openBrowser(String browserName){
        //equalsIgnoreCase  这个String的api意思是忽略大小写
        if ("chrome".equalsIgnoreCase(browserName)){
            //执行打开chrome的代码
            System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
//            ChromeOptions chromeOptions = new ChromeOptions();
//            chromeOptions.addArguments("--headless");
//            driver = new ChromeDriver(chromeOptions);
            driver = new ChromeDriver();
            logger.info("打开【chrome】浏览器");
        }else if ("firefox".equals(browserName)){
            //执行打开firefox的代码
            System.setProperty("webdriver.gecko.driver","src/test/resources/geckodriver.exe");
            driver = new FirefoxDriver();
            logger.info("打开【firefox】浏览器");
        }else if ("ie".equals(browserName)){
            //执行打开ie的代码
            //取消IE安全设置（忽略IE的Protected Mode的设置）
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
            //忽略掉浏览器缩放设置问题
            capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
            System.setProperty("webdriver.ie.driver","src/test/resources/IEDriverServer.exe");
            driver = new InternetExplorerDriver(capabilities);
            logger.info("打开【IE】浏览器");
        }else {
            //System.out.println("浏览器不支持，请确认浏览器名称");
            logger.info("浏览器不支持，请确认浏览器名称");
        }
    }

    /**
     * 访问网址的二次封装
     * @param url 访问网址
     */
    public void toUrl(String url){
        driver.get(url);
        logger.info("打开【"+url+"】网址");
    }

    /**
     * 最大化窗口
     */
    public void maxWindow(){
        driver.manage().window().maximize();
        logger.info("浏览器窗口最大化");
    }

    /**
     * 设置隐式等待
     * @param timeout
     */
    public void setImplicitlyWait(int timeout){
        driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
        logger.info("设置隐式等待时间【"+timeout+"】秒");
    }

    /**
     * 获取当前页面url
     * @return
     */
    public String getCurrentPageUrl(){
        String url = driver.getCurrentUrl();
        logger.info("获取当前页面url地址【"+url+"】");
        return url;
    }

    /**
     * 关闭浏览器
     */
    public void closeBrowser(){
        driver.quit();
        logger.info("关闭浏览器");
    }

    /**
     * 刷新页面
     */
    public void refreshPage(){
        driver.navigate().refresh();
        logger.info("刷新页面");
    }

    /**
     * 生成File文件截图的封装
     * @param picPath   截图文件需要保存的路径
     */
    public static void getScreenshotAsFile(String picPath){
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        //1、file对象
        File srcFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File descFile = new File(picPath);
        try{
            FileUtils.copyFile(srcFile,descFile);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * 生成字节数组截图的封装
     */
    public static byte[] getScreenshotAsByte(){
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        //2、字节数组
        byte[] screenshot = takesScreenshot.getScreenshotAs(OutputType.BYTES);
        return screenshot;
    }
}
