package com.jingna.aftersalesapp.page;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jingna.aftersalesapp.R;
import com.jingna.aftersalesapp.base.BaseActivity;
import com.jingna.aftersalesapp.bean.LoginBean;
import com.jingna.aftersalesapp.net.NetUrl;
import com.jingna.aftersalesapp.util.Logger;
import com.jingna.aftersalesapp.util.SpUtils;
import com.jingna.aftersalesapp.util.StatusBarUtils;
import com.jingna.aftersalesapp.util.ToastUtil;
import com.jingna.aftersalesapp.util.WeiboDialogUtils;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterSetPwdActivity extends BaseActivity {

    private Context context = RegisterSetPwdActivity.this;

    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.iv_is_show)
    ImageView ivIsShow;

    private boolean isShowPwd = false;
    private String phoneNumber = "";

    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_set_pwd);

        phoneNumber = getIntent().getStringExtra("number");
        StatusBarUtils.setStatusBar(RegisterSetPwdActivity.this, getResources().getColor(R.color.statusbar_color));
        ButterKnife.bind(RegisterSetPwdActivity.this);

    }

    @OnClick({R.id.rl_back, R.id.rl_is_show, R.id.btn_sure})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.rl_is_show:
                if(isShowPwd){
                    isShowPwd = false;
                    Glide.with(context).load(R.mipmap.miwen).into(ivIsShow);
                    etPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    etPwd.setSelection(etPwd.getText().length());
                }else {
                    isShowPwd = true;
                    Glide.with(context).load(R.mipmap.mingwen).into(ivIsShow);
                    etPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    etPwd.setSelection(etPwd.getText().length());
                }
                break;
            case R.id.btn_sure:
                sure();
                break;
        }
    }

    /**
     * 验证密码是否符合规则及访问注册接口
     */
    private void sure() {

        String pwd = etPwd.getText().toString();
        if(TextUtils.isEmpty(pwd)){
            ToastUtil.showShort(context, "密码不能为空");
        }else if(pwd.length()<6||pwd.length()>20){
            ToastUtil.showShort(context, "密码长度为6-20位，请重新设置密码");
        }else {
            dialog = WeiboDialogUtils.createLoadingDialog(context, "请等待...");
            ViseHttp.GET(NetUrl.MemUseraddMember)
                    .addParam("phone", phoneNumber)
                    .addParam("password", pwd)
                    .request(new ACallback<String>() {
                        @Override
                        public void onSuccess(String data) {
                            try {
                                Logger.e("123123", data);
                                JSONObject jsonObject = new JSONObject(data);
                                if(jsonObject.optString("status").equals("200")){
                                    ToastUtil.showShort(context, "注册成功");
                                    Gson gson = new Gson();
                                    LoginBean loginBean = gson.fromJson(data, LoginBean.class);
                                    SpUtils.setUserId(context, loginBean.getData().getUserId()+"");
                                    SpUtils.setToken(context, loginBean.getData().getToken());
                                    SpUtils.setPhoneNum(context, phoneNumber);
                                    Intent intent = new Intent();
                                    intent.setClass(context, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                                WeiboDialogUtils.closeDialog(dialog);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onFail(int errCode, String errMsg) {
                            Logger.e("123123", errMsg);
                            WeiboDialogUtils.closeDialog(dialog);
                        }
                    });
        }

    }

}
