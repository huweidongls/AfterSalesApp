package com.jingna.aftersalesapp.page;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.jingna.aftersalesapp.R;
import com.jingna.aftersalesapp.base.BaseActivity;
import com.jingna.aftersalesapp.util.StatusBarUtils;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class StartWeixiuActivity extends BaseActivity {

    private Context context = StartWeixiuActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_weixiu);

        StatusBarUtils.setStatusBar(StartWeixiuActivity.this, getResources().getColor(R.color.white_ffffff));
        ButterKnife.bind(StartWeixiuActivity.this);

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
