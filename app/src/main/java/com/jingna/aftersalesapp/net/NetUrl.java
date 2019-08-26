package com.jingna.aftersalesapp.net;

/**
 * Created by Administrator on 2019/8/23.
 */

public class NetUrl {
    public static final String BASE_URL = "http://192.168.2.103:80/";//231 http://192.168.2.231:80/
    //发送验证码
    public static final String MemUsersendMessage = "EngineerUser/sendMessage";
    //登录
    public static final String MemUserloginAPP = "EngineerUser/loginAPP";
    //匹配验证码是否正确
    public static final String MemUsermatchCode = "EngineerUser/matchCode";
    //添加用户
    public static final String MemUseraddMember = "EngineerUser/addMember";
    //通过手机验证码设置新密码
    public static final String EngineerUserretrievePassword = "EngineerUser/retrievePassword";
    //通过ID查询会员
    public static final String EngineerUsergetOne = "EngineerUser/getOne";
}
