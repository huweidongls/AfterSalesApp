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
    //新增个人详情
    public static final String EngineerUsertoUpdate = "EngineerUser/toUpdate";
    //查询该用户绑定的银行卡
    public static final String EngineerBankCardqueryList = "EngineerBankCard/queryList";
    //移除银行卡
    public static final String EngineerBankCardtoDelete = "EngineerBankCard/toDelete";
    //新增银行卡
    public static final String EngineerBankCardinsertBankCard = "EngineerBankCard/insertBankCard";
    //根据用户ID查询该用户的可提现维修费
    public static final String EngineerUsergetByUserMoney = "EngineerUser/getByUserMoney";
    //维修金提现申请接口
    public static final String AppEngineerCommissionAudittoUpdate = "AppEngineerCommissionAudit/toUpdate";
    //查询所有未接单的维修订单接口
    public static final String AfterSaleOrderfindAllUnmaintained = "AfterSaleOrder/findAllUnmaintained";
    //所有订单
    public static final String AfterSaleOrdergetByRepairIdOrder = "AfterSaleOrder/getByRepairIdOrder";
    //工程师修改订单状态接口
    public static final String AfterSaleOrdergetByOrderRepairId = "AfterSaleOrder/getByOrderRepairId";
    //根据订单ID查询具体数据
    public static final String AfterSaleOrdergetOneByOrderId = "AfterSaleOrder/getOneByOrderId";
}
