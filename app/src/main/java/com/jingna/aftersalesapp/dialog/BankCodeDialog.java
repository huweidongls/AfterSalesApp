package com.jingna.aftersalesapp.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jingna.aftersalesapp.R;
import com.jingna.aftersalesapp.app.MyApplication;
import com.jingna.aftersalesapp.net.NetUrl;
import com.jingna.aftersalesapp.util.ToastUtil;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2019/6/19.
 */

public class BankCodeDialog extends Dialog {

    private Context context;
    private RelativeLayout rlGetCode;
    private TextView tvGetCode;
    private TextView tvCancel;
    private TextView tvSure;
    private ClickListener listener;
    private String phone;
    private TextView tv;
    private EditText etCode;

    public BankCodeDialog(@NonNull Context context, String phone, ClickListener listener) {
        super(context, R.style.RoundCornerDialog);
        this.context = context;
        this.listener = listener;
        this.phone = phone;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.bankCodeTimeCount.setActivity(this);
        init();
    }

    public TextView getCode_btn(){
        return tvGetCode;
    }

    private void init() {

        View view = LayoutInflater.from(context).inflate(R.layout.dialog_bank_code, null);
        setContentView(view);

        rlGetCode = view.findViewById(R.id.rl_get_code);
        tvGetCode = view.findViewById(R.id.tv_get_code);
        tvCancel = view.findViewById(R.id.tv_cancel);
        tvSure = view.findViewById(R.id.tv_sure);
        tv = view.findViewById(R.id.tv);
        etCode = view.findViewById(R.id.et_code);

        tv.setText("输入尾号"+phone+"的短信验证码");

        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = etCode.getText().toString();
                ViseHttp.GET(NetUrl.MemUsermatchCode)
                        .addParam("phone", phone)
                        .addParam("code", code)
                        .request(new ACallback<String>() {
                            @Override
                            public void onSuccess(String data) {
                                try {
                                    JSONObject jsonObject = new JSONObject(data);
                                    if(jsonObject.optString("status").equals("200")){
                                        dismiss();
                                        listener.onSure();
                                    }else {
                                        ToastUtil.showShort(context, "验证码不正确");
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
        });

        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        rlGetCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyApplication.bankCodeTimeCount.start();
                ViseHttp.GET(NetUrl.MemUsersendMessage)
                        .addParam("phone", phone)
                        .request(new ACallback<String>() {
                            @Override
                            public void onSuccess(String data) {
                                try {
                                    JSONObject jsonObject = new JSONObject(data);
                                    if(jsonObject.optString("status").equals("200")){
                                        ToastUtil.showShort(context, "验证码发送成功");
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
        });

    }

    public interface ClickListener{
        void onSure();
    }

}
