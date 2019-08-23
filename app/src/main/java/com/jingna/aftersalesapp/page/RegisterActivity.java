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
import com.jingna.aftersalesapp.dialog.RegisterDialog;
import com.jingna.aftersalesapp.dialog.SendYzmDialog;
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

public class RegisterActivity extends BaseActivity {

    private Context context = RegisterActivity.this;

    @BindView(R.id.et_number)
    EditText etNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        StatusBarUtils.setStatusBar(RegisterActivity.this, getResources().getColor(R.color.statusbar_color));
        ButterKnife.bind(RegisterActivity.this);
        initData();

    }

    private void initData() {

        RegisterDialog dialog = new RegisterDialog(context, new RegisterDialog.ClickListener() {
            @Override
            public void onSure() {

            }

            @Override
            public void onCancel() {
                RegisterActivity.this.finish();
            }
        });
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.show();

    }

    @OnClick({R.id.rl_back, R.id.btn_next})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.btn_next:
                next();
                break;
        }
    }

    private void next() {

        final String phoneNum = etNumber.getText().toString();
        if(TextUtils.isEmpty(phoneNum)){
            ToastUtil.showShort(context, "手机号不能为空");
        }else if(!StringUtils.isPhoneNumberValid(phoneNum)){
            ToastUtil.showShort(context, "请输入正确的手机号格式");
        }else {
            SendYzmDialog dialog = new SendYzmDialog(context, phoneNum, new SendYzmDialog.ClickListener() {
                @Override
                public void onSure() {
                    Log.e("123123", phoneNum);
                    ViseHttp.GET(NetUrl.MemUsersendMessage)
                            .addParam("phone", phoneNum)
                            .request(new ACallback<String>() {
                                @Override
                                public void onSuccess(String data) {
                                    try {
                                        Log.e("123123", data);
                                        JSONObject jsonObject = new JSONObject(data);
                                        if(jsonObject.optString("status").equals("200")){
                                            ToastUtil.showShort(context, "短信验证码发送成功");
                                            Intent intent = new Intent();
                                            intent.setClass(context, RegisterYzmActivity.class);
                                            intent.putExtra("number", phoneNum);
                                            startActivity(intent);
                                            finish();
                                        }else {
                                            ToastUtil.showShort(context, "短信验证码发送失败");
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }

                                @Override
                                public void onFail(int errCode, String errMsg) {
                                    Log.e("123123", errMsg);
                                }
                            });

                }
            });
            dialog.show();
        }

    }

}
