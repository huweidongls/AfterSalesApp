package com.jingna.aftersalesapp.page;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.jingna.aftersalesapp.R;
import com.jingna.aftersalesapp.adapter.BackCardListAdapter;
import com.jingna.aftersalesapp.base.BaseActivity;
import com.jingna.aftersalesapp.bean.BankCardListBean;
import com.jingna.aftersalesapp.net.NetUrl;
import com.jingna.aftersalesapp.util.SpUtils;
import com.jingna.aftersalesapp.util.StatusBarUtils;
import com.jingna.aftersalesapp.util.StringUtils;
import com.jingna.aftersalesapp.util.ToastUtil;
import com.jingna.aftersalesapp.util.ViseUtil;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CommissionActivity extends BaseActivity {

    private Context context = CommissionActivity.this;

    private List<String> mList;
    @BindView(R.id.money)
    EditText money;
    @BindView(R.id.btn_mone)
    Button btn_mone;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tv_bank_name)
    TextView tvBankName;
    @BindView(R.id.tv_select)
    TextView tvSelect;

    private RecyclerView recyclerView;

    private PopupWindow popupWindow;
    private BackCardListAdapter adapter;

    private String bankId = "";
    private double allMoney;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commission);

        StatusBarUtils.setStatusBar(CommissionActivity.this, getResources().getColor(R.color.statusbar_color));
        ButterKnife.bind(CommissionActivity.this);
        initData();

    }

    private void initData() {

        ViseHttp.GET(NetUrl.EngineerUsergetByUserMoney)
                .addParam("memberId", SpUtils.getUserId(context))
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if (jsonObject.optString("status").equals("200")) {
                                allMoney = jsonObject.optDouble("data");
                                tvMoney.setText("佣金余额¥" + StringUtils.roundByScale(allMoney, 2) + "，");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {

                    }
                });

        money.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String message = s.toString();
                if (TextUtils.isEmpty(message)) {
                    btn_mone.setBackgroundColor(Color.parseColor("#9CE6BF"));
                    ToastUtil.showShort(CommissionActivity.this, "请填写提现金额");
                } else {
                    btn_mone.setBackgroundColor(Color.parseColor("#02C160"));
                }

            }
        });
    }

    private void init_back() {
        mList = new ArrayList<>();
        mList.add("");
        mList.add("");
        mList.add("");
        adapter = new BackCardListAdapter(mList);
        LinearLayoutManager manager = new LinearLayoutManager(CommissionActivity.this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }

    @OnClick({R.id.rl_back, R.id.btn_mone, R.id.all, R.id.ll_bank})
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.btn_mone:
                String msg = money.getText().toString();
                if (TextUtils.isEmpty(msg)) {
                    ToastUtil.showShort(CommissionActivity.this, "请填写提现金额");
                } else if(TextUtils.isEmpty(bankId)){
                    ToastUtil.showShort(CommissionActivity.this, "请选择提现银行卡");
                }else {
                    double m = Double.valueOf(msg);
                    if(m == 0.00){
                        ToastUtil.showShort(context, "提现金额不能为0");
                    }else if(m>allMoney){
                        ToastUtil.showShort(context, "提现金额不能大于佣金余额");
                    }else {
                        Map<String, String> map = new LinkedHashMap<>();
                        map.put("engineerId", SpUtils.getUserId(context));
                        map.put("auditMoney", msg);
                        map.put("bankCardId", bankId);
                        ViseUtil.Post(context, NetUrl.AppEngineerCommissionAudittoUpdate, map, new ViseUtil.ViseListener() {
                            @Override
                            public void onReturn(String s) {
                                ToastUtil.showShort(context, "提现成功");
                                finish();
                            }
                        });
                    }
                }
                break;
            case R.id.all:
                money.setText(allMoney+"");
                money.setSelection(money.getText().toString().length());
                break;
            case R.id.ll_bank:
                intent.setClass(context, MyBankCardActivity.class);
                intent.putExtra("type", "tixian");
                startActivityForResult(intent, 1003);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1003 && data != null) {
            BankCardListBean.DataBean bean = (BankCardListBean.DataBean) data.getSerializableExtra("bean");
            bankId = bean.getId() + "";
            tvBankName.setText(bean.getCardType() + "(" + bean.getBankCardNum().substring(bean.getBankCardNum().length() - 4, bean.getBankCardNum().length()) + ")");
            tvSelect.setVisibility(View.GONE);
        }
    }

}
