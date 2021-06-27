package com.lemon.data;

/**
 * @Project: auto_test_framework
 * @Create: 2021-05-22 17:19
 * @Desc：常量类
 **/
public class Constants {
    //项目BaseUrl (便于切换环境)
    public static final String BASE_URL = "http://8.129.91.152:8765";

    //登录页面地址
    //static 表示变量是一个静态变量，可以通过 Constant.LOGIN_URL 使用，不需要再去实例化
    //final 表示无法修改，因为常量类不可被修改
    //LOGIN_URL 常量规范写法，单词通过 _ 隔开
    public static final String LOGIN_URL = BASE_URL + "/Index/login.html";

    //前台登录的正确手机号码
    public static final String CORRECT_PHONE = "13323234545";
    //前台登录的正确密码
    public static final String CORRECT_PASSWORD = "lemon123456";
    //配置测试浏览器
    public static final String TEST_BROWSER = "chrome";
}
