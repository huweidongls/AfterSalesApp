package com.jingna.aftersalesapp.page;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jingna.aftersalesapp.R;
import com.jingna.aftersalesapp.adapter.OrderShebeiAdapter;
import com.jingna.aftersalesapp.base.BaseActivity;
import com.jingna.aftersalesapp.bean.PeitaoshebeiBean;
import com.jingna.aftersalesapp.util.StatusBarUtils;
import com.jingna.aftersalesapp.util.StringUtils;
import com.jingna.aftersalesapp.util.ToastUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StartWeixiuActivity extends BaseActivity {

    private Context context = StartWeixiuActivity.this;

    @BindView(R.id.rv)
    RecyclerView recyclerView;
    @BindView(R.id.tv_zanwu)
    TextView tvZanwu;
    @BindView(R.id.ll_zanwu)
    LinearLayout llZanwu;
    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.et_price)
    EditText etPrice;
    @BindView(R.id.et_time)
    EditText etTime;
    @BindView(R.id.et_jiaotong)
    EditText etJiaotong;

    private OrderShebeiAdapter adapter;
    private List<PeitaoshebeiBean.DataBean> mList;

    private List<PeitaoshebeiBean.DataBean> beanList;
    private String type = "0";

    private String id = "";
    private String name = "";
    private String phone = "";
    private String address = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_weixiu);

        id = getIntent().getStringExtra("id");
        name = getIntent().getStringExtra("name");
        phone = getIntent().getStringExtra("phone");
        address = getIntent().getStringExtra("address");
        StatusBarUtils.setStatusBar(StartWeixiuActivity.this, getResources().getColor(R.color.white_ffffff));
        ButterKnife.bind(StartWeixiuActivity.this);
        initData();

    }

    private void initData() {

        beanList = new ArrayList<>();
        mList = new ArrayList<>();
        adapter = new OrderShebeiAdapter(mList);
        LinearLayoutManager manager = new LinearLayoutManager(context){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

    }

    @OnClick({R.id.rl_back, R.id.btn_insert, R.id.tv_reset, R.id.tv_sure})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.btn_insert:
                intent.setClass(context, PeitaoshebeiActivity.class);
                intent.putExtra("type", type);
                intent.putExtra("beanList", (Serializable) beanList);
                startActivityForResult(intent, 100);
                break;
            case R.id.tv_reset:
                mList.clear();
                adapter.notifyDataSetChanged();
                type = "0";
                tvZanwu.setVisibility(View.VISIBLE);
                llZanwu.setVisibility(View.GONE);
                etContent.setText(null);
                etPrice.setText(null);
                etTime.setText(null);
                etJiaotong.setText(null);
                break;
            case R.id.tv_sure:
                String content = etContent.getText().toString();
                String price = etPrice.getText().toString();
                String time = etTime.getText().toString();
                String jiaotong = etJiaotong.getText().toString();
                if(StringUtils.isEmpty(content)||StringUtils.isEmpty(price)||StringUtils.isEmpty(time)||StringUtils.isEmpty(jiaotong)){
                    ToastUtil.showShort(context, "请完善信息后提交");
                }else {
                    intent.setClass(context, CommitActivity.class);
                    intent.putExtra("id", id);
                    intent.putExtra("name", name);
                    intent.putExtra("phone", phone);
                    intent.putExtra("address", address);
                    intent.putExtra("list", (Serializable) mList);
                    intent.putExtra("content", content);
                    intent.putExtra("price", price);
                    intent.putExtra("time", time);
                    intent.putExtra("jiaotong", jiaotong);
                    startActivity(intent);
                    finish();
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 100&&resultCode == 100&&data != null){
            List<PeitaoshebeiBean.DataBean> list = (List<PeitaoshebeiBean.DataBean>) data.getSerializableExtra("bean");
            beanList.clear();
            beanList.addAll(list);
            type = "1";
            mList.clear();
            for (PeitaoshebeiBean.DataBean bean : list){
                if(bean.getIsSelect() == 1){
                    mList.add(bean);
                }
            }
            adapter.notifyDataSetChanged();
            tvZanwu.setVisibility(View.GONE);
            llZanwu.setVisibility(View.VISIBLE);
        }
    }

}
