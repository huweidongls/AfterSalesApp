package com.jingna.aftersalesapp.page;

import android.graphics.Color;
import android.os.Bundle;

import com.jingna.aftersalesapp.R;
import com.jingna.aftersalesapp.base.BaseActivity;
import com.jingna.aftersalesapp.widget.LinePathView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QianmingActivity extends BaseActivity {

    @BindView(R.id.view)
    LinePathView linePathView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qianming);

        ButterKnife.bind(QianmingActivity.this);
        initData();

    }

    private void initData() {

        //修改背景、笔宽、颜色
        linePathView.setBackColor(Color.WHITE);
        linePathView.setPaintWidth(20);
        linePathView.setPenColor(Color.BLACK);

    }

}
