package com.jingna.aftersalesapp.page;

import android.content.Context;
import android.os.Bundle;

import com.jingna.aftersalesapp.R;
import com.jingna.aftersalesapp.base.BaseActivity;
import com.jingna.aftersalesapp.util.StatusBarUtils;

import butterknife.ButterKnife;

public class CommitActivity extends BaseActivity {

    private Context context = CommitActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commit);

        StatusBarUtils.setStatusBar(CommitActivity.this, getResources().getColor(R.color.statusbar_color));
        ButterKnife.bind(CommitActivity.this);

    }



}
