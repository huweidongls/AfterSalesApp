package com.jingna.aftersalesapp.page;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.jingna.aftersalesapp.R;
import com.jingna.aftersalesapp.adapter.CommissionDetailsAdapter;
import com.jingna.aftersalesapp.base.BaseActivity;
import com.jingna.aftersalesapp.bean.AppMemberCommissionAuditqueryListAppBean;
import com.jingna.aftersalesapp.net.NetUrl;
import com.jingna.aftersalesapp.util.SpUtils;
import com.jingna.aftersalesapp.util.ViseUtil;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CommissionDetailsActivity extends BaseActivity {

    private Context context = CommissionDetailsActivity.this;

    @BindView(R.id.rv)
    RecyclerView recyclerView;

    private CommissionDetailsAdapter adapter;
    private List<AppMemberCommissionAuditqueryListAppBean.DataBean> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commission_details);

        ButterKnife.bind(CommissionDetailsActivity.this);
        initData();

    }

    private void initData() {

        Map<String, String> map = new LinkedHashMap<>();
        map.put("engineerId", SpUtils.getUserId(context));
        ViseUtil.Get(context, NetUrl.AppEngineerCommissionAuditqueryListApp, map, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Gson gson = new Gson();
                AppMemberCommissionAuditqueryListAppBean bean = gson.fromJson(s, AppMemberCommissionAuditqueryListAppBean.class);
                mList = bean.getData();
                adapter = new CommissionDetailsAdapter(mList);
                LinearLayoutManager manager = new LinearLayoutManager(context);
                manager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(manager);
                recyclerView.setAdapter(adapter);
            }
        });

    }

    @OnClick({R.id.rl_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
        }
    }

}
