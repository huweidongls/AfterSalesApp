package com.jingna.aftersalesapp.page;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jingna.aftersalesapp.R;
import com.jingna.aftersalesapp.adapter.CommitAdapter;
import com.jingna.aftersalesapp.base.BaseActivity;
import com.jingna.aftersalesapp.bean.PeitaoshebeiBean;
import com.jingna.aftersalesapp.net.NetUrl;
import com.jingna.aftersalesapp.util.StatusBarUtils;
import com.jingna.aftersalesapp.util.ToastUtil;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CommitActivity extends BaseActivity {

    private Context context = CommitActivity.this;

    @BindView(R.id.rv)
    RecyclerView recyclerView;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_jiaotong)
    TextView tvJiaotong;
    @BindView(R.id.tv_all_price)
    TextView tvAllPrice;
    @BindView(R.id.tv_gongshi)
    TextView tvGongshi;

    private CommitAdapter adapter;
    private List<PeitaoshebeiBean.DataBean> mList;

    private String id = "";
    private String name = "";
    private String phone = "";
    private String address = "";
    private String content = "";
    private String price = "";
    private String time = "";
    private String jiaotong = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commit);

        mList = (List<PeitaoshebeiBean.DataBean>) getIntent().getSerializableExtra("list");
        id = getIntent().getStringExtra("id");
        name = getIntent().getStringExtra("name");
        phone = getIntent().getStringExtra("phone");
        address = getIntent().getStringExtra("address");
        content = getIntent().getStringExtra("content");
        price = getIntent().getStringExtra("price");
        time = getIntent().getStringExtra("time");
        jiaotong = getIntent().getStringExtra("jiaotong");
        StatusBarUtils.setStatusBar(CommitActivity.this, getResources().getColor(R.color.statusbar_color));
        ButterKnife.bind(CommitActivity.this);
        initData();

    }

    private void initData() {

        tvName.setText(name);
        tvPhone.setText(phone);
        tvAddress.setText(address);
        tvPrice.setText("¥"+price);
        tvJiaotong.setText("¥"+jiaotong);
        adapter = new CommitAdapter(mList);
        LinearLayoutManager manager = new LinearLayoutManager(context){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        ViseHttp.GET(NetUrl.AfterSaleOrdergetByTimeOrMoney)
                .addParam("time", time)
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.optString("status").equals("200")){
                                double v = jsonObject.optDouble("data");
                                tvGongshi.setText("¥"+v);
                                double pri = Double.valueOf(price);
                                double jiao = Double.valueOf(jiaotong);
                                int all = 0;
                                for (int i = 0; i<mList.size(); i++){
                                    all = all+mList.get(i).getDayMoney()*mList.get(i).getNum();
                                }
                                tvAllPrice.setText("¥"+(v+pri+jiao+all));
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

    @OnClick({R.id.rl_back, R.id.tv_commit})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.tv_commit:
                Gson gson = new Gson();
                String json = gson.toJson(mList);
                ViseHttp.POST(NetUrl.AfterSaleOrdergetByOrderInsert)
                        .addParam("id", id)
                        .addParam("carMoney", jiaotong)
                        .addParam("repairText", content)
                        .addParam("equipment", json)
                        .addParam("times", time)
                        .addParam("repairMoney", price)
                        .request(new ACallback<String>() {
                            @Override
                            public void onSuccess(String data) {
                                try {
                                    JSONObject jsonObject = new JSONObject(data);
                                    if(jsonObject.optString("status").equals("200")){
                                        ToastUtil.showShort(context, "提交成功");
                                        finish();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onFail(int errCode, String errMsg) {

                            }
                        });
                break;
        }
    }

}
