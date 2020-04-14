package com.jingna.aftersalesapp.page;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jingna.aftersalesapp.R;
import com.jingna.aftersalesapp.base.BaseActivity;
import com.jingna.aftersalesapp.dialog.BankCodeDialog;
import com.jingna.aftersalesapp.net.NetUrl;
import com.jingna.aftersalesapp.util.SpUtils;
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

public class InsertBankCardActivity extends BaseActivity {

    private Context context = InsertBankCardActivity.this;

    @BindView(R.id.iv_agree)
    ImageView ivAgree;
    @BindView(R.id.btn_agree)
    Button btnAgree;
    @BindView(R.id.et_bank_card)
    EditText etBankCard;
    @BindView(R.id.et_phonenum)
    EditText etPhonenum;
    @BindView(R.id.et_bank_name)
    EditText etBankName;
    @BindView(R.id.et_name)
    EditText etName;

    private String userId = "";

    private boolean isAgree = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_bank_card);

        userId = SpUtils.getUserId(context);
        StatusBarUtils.setStatusBar(InsertBankCardActivity.this, getResources().getColor(R.color.statusbar_color));
        ButterKnife.bind(InsertBankCardActivity.this);

    }

    @OnClick({R.id.rl_back, R.id.iv_agree, R.id.btn_agree})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.iv_agree:
                if(!isAgree){
                    isAgree = true;
                    Glide.with(context).load(R.mipmap.apply_true).into(ivAgree);
                    btnAgree.setBackgroundResource(R.drawable.bg_ff0004_2dp);
                }else {
                    isAgree = false;
                    Glide.with(context).load(R.mipmap.apply_false).into(ivAgree);
                    btnAgree.setBackgroundResource(R.drawable.bg_cccccc_2dp);
                }
                break;
            case R.id.btn_agree:
                if(isAgree){
                    final String bankCard = etBankCard.getText().toString();
                    final String phoneNum = etPhonenum.getText().toString();
                    final String bankName = etBankName.getText().toString();
                    final String name = etName.getText().toString();
                    if(StringUtils.isEmpty(bankCard)||StringUtils.isEmpty(phoneNum)||StringUtils.isEmpty(bankName)||StringUtils.isEmpty(name)){
                        ToastUtil.showShort(context, "请完善信息后提交");
                    }else if(!StringUtils.isPhoneNumberValid(phoneNum)){
                        ToastUtil.showShort(context, "请输入正确格式的手机号码");
                    }else {
                        BankCodeDialog dialog = new BankCodeDialog(context, phoneNum, new BankCodeDialog.ClickListener() {
                            @Override
                            public void onSure() {
                                ViseHttp.POST(NetUrl.EngineerBankCardinsertBankCard)
                                        .addParam("userId", SpUtils.getUserId(context))
                                        .addParam("bankCardNum", bankCard)
                                        .addParam("cardType", bankName)
                                        .addParam("phone", phoneNum)
                                        .addParam("bankName", name)
                                        .request(new ACallback<String>() {
                                            @Override
                                            public void onSuccess(String data) {
                                                try {
                                                    JSONObject jsonObject = new JSONObject(data);
                                                    if(jsonObject.optString("status").equals("200")){
                                                        Intent intent = new Intent();
                                                        intent.setClass(context, InsertBankCardSuccessActivity.class);
                                                        startActivity(intent);
                                                        finish();
                                                    }else {
                                                        ToastUtil.showShort(context, jsonObject.optString("errorMsg"));
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
                        dialog.show();
                    }
                }
                break;
        }
    }

}
