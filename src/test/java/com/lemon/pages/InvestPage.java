package com.lemon.pages;

import com.lemon.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @Project: auto_test_framework
 * @Create: 2021-05-23 00:13
 * @Desc：
 **/
public class InvestPage extends BasePage {
    //投标金额元素
    By investAmountBy = By.xpath("//div[@class='clearfix left']/input");
    //点击投标元素
    By investClickBy = By.xpath("//button[text()='投标']");
    //获取投标成功信息元素
    By bidSuccessTextBy = By.xpath("//div[@class='layui-layer-content']//div[@class='capital_font1 note']");
    //关闭投标成功弹窗元素
    By closeBidSuccessPopBy = By.xpath("//div[@class='layui-layer-content']//img");
    //项目所有金额
    By allAmountBy = By.xpath("//div[@class='left fs-16 money_overplus']//div[1]/span[2]");
    //项目可投金额元素
    By amountToBeInvestBy = By.xpath("//div[@class='left fs-16 money_overplus']/div[2]/span[2]");
    //账户可用余额
    By leaveAmountBy = By.xpath("//div[@class='clearfix left']//input");

    /**
     * 投标
     * @param amount
     */
    public void investBid(String amount){
        //输入投标金额
        //driver.findElement(investAmountBy).sendKeys(amount);
        type(investAmountBy,"投标页面_投标金额",amount);
        //点击投标
        //driver.findElement(investClickBy).click();
        click(investClickBy,"投标页面_确认投标按钮");
    }

    /**
     * 进入投标弹窗页面
     * @param loadTitle
     */
    public void intoInvestWindow(String loadTitle){
        //点击抢投标按钮
        //driver.findElement(By.xpath("//span[contains(text(),'" + loadTitle + "')]/parent::div/parent::a/following-sibling::div//a")).click();
        By bidBy = By.xpath("//span[contains(text(),'" + loadTitle + "')]/parent::div/parent::a/following-sibling::div//a");
        click(bidBy,"投标页面_投标按钮");
    }

    /**
     * 获取投标成功提示信息
     * @return
     */
    public String getBidSuccessText(){
        return getText(bidSuccessTextBy,"投标页面_投标成功");
    }

    /**
     * 关闭投标成功弹窗
     */
    public void closeBidSuccessPop(){
        click(closeBidSuccessPopBy,"投标页面_关闭投标成功弹窗");
    }

    /**
     * 获取项目总金额
     * @return
     */
    public double getAllAmount(){
        String amount = getText(allAmountBy, "投标页面_项目总金额");
        return Double.parseDouble(amount)*10000;
    }

    /**
     * 获取项目可投金额
     * @return Double
     */
    public double getAmountToBeInvest(){
        String amount = getText(amountToBeInvestBy,"投标页面_可投金额");
        //字符串转换为浮点型，因为项目的可投金额单位是万
        return Double.parseDouble(amount)*10000;
    }

    /**
     * 获取账户可用余额
     * @return Double
     */
    public double getLeaveAmount(){
        String value = getAttributeValue(leaveAmountBy, "投资页面_可用余额", "data-amount");
        return Double.parseDouble(value);
    }
}
