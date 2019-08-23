package com.jingna.aftersalesapp.page;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.jingna.aftersalesapp.R;
import com.jingna.aftersalesapp.base.BaseActivity;
import com.jingna.aftersalesapp.net.NetUrl;
import com.jingna.aftersalesapp.util.StatusBarUtils;
import com.jingna.aftersalesapp.util.StringUtils;
import com.jingna.aftersalesapp.util.ToastUtil;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SMSLoginActivity extends BaseActivity {

    private Context context = SMSLoginActivity.this;

    @BindView(R.id.et_phonenum)
    EditText etPhoneNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smslogin);

        StatusBarUtils.setStatusBar(SMSLoginActivity.this, getResources().getColor(R.color.statusbar_color));
        ButterKnife.bind(SMSLoginActivity.this);

    }

    @OnClick({R.id.rl_back, R.id.btn_yzm, R.id.tv_pwd_login, R.id.tv_register})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.btn_yzm:
                next();
                break;
            case R.id.tv_pwd_login:
                intent.setClass(context, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.tv_register:
                intent.setClass(context, RegisterActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    /**
     * 获取验证码并跳转
     */
    private void next() {

        final String phoneNum = etPhoneNum.getText().toString();
        if(TextUtils.isEmpty(phoneNum)){
            ToastUtil.showShort(context, "手机号不能为空");
        }else if(!StringUtils.isPhoneNumberValid(phoneNum)){
            ToastUtil.showShort(context, "请输入正确格式的手机号码");
        }else {
            ViseHttp.GET(NetUrl.MemUsersendMessage)
                    .addParam("phone", phoneNum)
                    .request(new ACallback<String>() {
                        @Override
                        public void onSuccess(String data) {
                            Log.e("123123", data);
                            try {
                                JSONObject jsonObject = new JSONObject(data);
                                if(jsonObject.optString("status").equals("200")){
                                    ToastUtil.showShort(context, "验证码发送成功");
                                    Intent intent = new Intent();
                                    intent.setClass(context, SMSLoginYzmActivity.class);
                                    intent.putExtra("phone", phoneNum);
                                    startActivity(intent);
                                    finish();
                                }else {
                                    ToastUtil.showShort(context, "验证码发送失败");
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onFail(int errCode, String errMsg) {

                        }
                    });
        }

    }

}
