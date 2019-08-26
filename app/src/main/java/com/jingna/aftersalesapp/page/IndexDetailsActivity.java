package com.jingna.aftersalesapp.page;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jingna.aftersalesapp.R;
import com.jingna.aftersalesapp.adapter.IndexDetailsAdapter;
import com.jingna.aftersalesapp.base.BaseActivity;
import com.jingna.aftersalesapp.bean.IndexDetailsBean;
import com.jingna.aftersalesapp.net.NetUrl;
import com.jingna.aftersalesapp.util.SpUtils;
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

public class IndexDetailsActivity extends BaseActivity {

    private Context context = IndexDetailsActivity.this;

    @BindView(R.id.rv)
    RecyclerView recyclerView;
    @BindView(R.id.tv_id)
    TextView tvId;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_address)
    TextView tvAddress;

    private IndexDetailsAdapter adapter;
    private List<IndexDetailsBean.DataBean.AppAfterSaleEquipmentsBean> mList;

    private String id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index_details);

        id = getIntent().getStringExtra("id");
        StatusBarUtils.setStatusBar(IndexDetailsActivity.this, getResources().getColor(R.color.statusbar_color));
        ButterKnife.bind(IndexDetailsActivity.this);
        initData();

    }

    private void initData() {

        ViseHttp.GET(NetUrl.AfterSaleOrdergetOneByOrderId)
                .addParam("orderId", id)
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.optString("status").equals("200")){
                                Gson gson = new Gson();
                                IndexDetailsBean bean = gson.fromJson(data, IndexDetailsBean.class);
                                mList = bean.getData().getAppAfterSaleEquipments();
                                adapter = new IndexDetailsAdapter(mList);
                                LinearLayoutManager manager = new LinearLayoutManager(context){
                                    @Override
                                    public boolean canScrollVertically() {
                                        return false;
                                    }
                                };
                                manager.setOrientation(LinearLayoutManager.VERTICAL);
                                recyclerView.setLayoutManager(manager);
                                recyclerView.setAdapter(adapter);
                                tvId.setText(bean.getData().getId());
                                tvTime.setText(bean.getData().getCreateTime());
                                tvName.setText(bean.getData().getAddresUname());
                                tvPhone.setText(bean.getData().getAddresPhone());
                                tvAddress.setText(bean.getData().getAddresName());
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

    @OnClick({R.id.rl_back, R.id.tv_jiedan})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.tv_jiedan:
                ViseHttp.GET(NetUrl.AfterSaleOrdergetByOrderRepairId)
                        .addParam("repairId", SpUtils.getUserId(context))
                        .addParam("orderId", id)
                        .addParam("orderStatus", "2")
                        .request(new ACallback<String>() {
                            @Override
                            public void onSuccess(String d) {
                                try {
                                    JSONObject jsonObject = new JSONObject(d);
                                    if(jsonObject.optString("status").equals("200")){
                                        ToastUtil.showShort(context, "接单成功");
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
