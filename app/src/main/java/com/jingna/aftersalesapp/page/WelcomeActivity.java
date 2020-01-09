package com.jingna.aftersalesapp.page;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jingna.aftersalesapp.R;
import com.jingna.aftersalesapp.util.SpUtils;
import com.jingna.aftersalesapp.util.StatusBarUtils;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WelcomeActivity extends AppCompatActivity {

    private Context context = WelcomeActivity.this;

    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.tv)
    TextView tv;

    private int num = 3;
    private Timer timer;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 100:
                    if(num == 0){
                        toMain();
                    }else {
                        tv.setText("跳过 "+num);
                    }
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        StatusBarUtils.setStatusBarTransparent(WelcomeActivity.this);
        ButterKnife.bind(WelcomeActivity.this);
        initData();

    }

    private void initData() {

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(100);
                num = num - 1;
            }
        };
        timer = new Timer();
        timer.schedule(timerTask, 1000, 1000);

    }

    @OnClick({R.id.tv})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv:
                timer.cancel();
                toMain();
                break;
        }
    }

    private synchronized void toMain(){
        Intent intent = new Intent();
        if(SpUtils.getUserId(context).equals("0")){
            intent.setClass(context, LoginActivity.class);
        }else {
            intent.setClass(context, MainActivity.class);
        }
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }

}
