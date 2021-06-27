package com.lemon.listener;

import com.lemon.base.BaseTest;
import io.qameta.allure.Attachment;
import org.testng.IHookCallBack;
import org.testng.IHookable;
import org.testng.ITestNGListener;
import org.testng.ITestResult;

/**
 * @Project: auto_test_framework
 * @Create: 2021-05-30 11:57
 * @Desc：
 **/
public class TestFailListener implements IHookable {
    @Override
    public void run(IHookCallBack callBack, ITestResult testResult){
        //要保证主体能够正常去运行
        //testResult接收测试执行的结果
        callBack.runTestMethod(testResult);
        //监听到用例执行失败的情况
        if (testResult.getThrowable() != null){
            //执行失败截图
            //生成字节数组格式的截图
            byte[] screenshot = BaseTest.getScreenshotAsByte();
            //如何嵌入到Allure报表中呢？？
            saveScreenshotToAllure(screenshot);
        }
    }

    //allure的一个注解,保存截图到allure报表中的方法
    @Attachment
    public byte[] saveScreenshotToAllure(byte[] data){
        return data;
    }
}
