package com.jingna.aftersalesapp.page;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

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

public class LoginActivity extends BaseActivity {

    private Context context = LoginActivity.this;

    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_pwd)
    EditText etPwd;

    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        StatusBarUtils.setStatusBar(LoginActivity.this, getResources().getColor(R.color.statusbar_color));
        ButterKnife.bind(LoginActivity.this);

    }

    @OnClick({R.id.rl_back, R.id.btn_login, R.id.tv_code_login, R.id.tv_register, R.id.tv_forgot_pwd})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.btn_login:
                login();
                break;
            case R.id.tv_code_login:
                intent.setClass(context, SMSLoginActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.tv_register:
                intent.setClass(context, RegisterActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.tv_forgot_pwd:
                intent.setClass(context, ForgotPwd1Activity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    private void login() {

        final String name = etName.getText().toString();
        String pwd = etPwd.getText().toString();
        if(TextUtils.isEmpty(name)||TextUtils.isEmpty(pwd)){
            ToastUtil.showShort(context, "手机号或密码不能为空");
        }else {
            dialog = WeiboDialogUtils.createLoadingDialog(context, "请等待...");
            ViseHttp.GET(NetUrl.MemUserloginAPP)
                    .addParam("phoneNum", name)
                    .addParam("password", pwd)
                    .request(new ACallback<String>() {
                        @Override
                        public void onSuccess(String data) {
                            Logger.e("123123", data);
                            try {
                                JSONObject jsonObject = new JSONObject(data);
                                if(jsonObject.optString("status").equals("200")){
                                    ToastUtil.showShort(context, "登录成功");
                                    Gson gson = new Gson();
                                    LoginBean loginBean = gson.fromJson(data, LoginBean.class);
                                    Logger.e("123123", loginBean.getData().getUserId()+"");
                                    SpUtils.setUserId(context, loginBean.getData().getUserId()+"");
                                    SpUtils.setToken(context, loginBean.getData().getToken());
                                    SpUtils.setPhoneNum(context, name);
                                    finish();
                                }else {
                                    ToastUtil.showShort(context, jsonObject.optString("errorMsg"));
                                }
                                WeiboDialogUtils.closeDialog(dialog);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onFail(int errCode, String errMsg) {
                            WeiboDialogUtils.closeDialog(dialog);
                        }
                    });
        }

    }

}
