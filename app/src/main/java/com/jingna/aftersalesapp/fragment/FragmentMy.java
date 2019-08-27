package com.jingna.aftersalesapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jingna.aftersalesapp.R;
import com.jingna.aftersalesapp.bean.GetOneBean;
import com.jingna.aftersalesapp.net.NetUrl;
import com.jingna.aftersalesapp.page.CommissionActivity;
import com.jingna.aftersalesapp.page.ForgotPwd1Activity;
import com.jingna.aftersalesapp.page.MyBankCardActivity;
import com.jingna.aftersalesapp.page.PersonInformationActivity;
import com.jingna.aftersalesapp.page.SMSLoginActivity;
import com.jingna.aftersalesapp.util.Logger;
import com.jingna.aftersalesapp.util.SpUtils;
import com.jingna.aftersalesapp.util.StatusBarUtils;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2019/8/23.
 */

public class FragmentMy extends Fragment {

    @BindView(R.id.iv_avatar)
    ImageView ivAvatar;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.ll_login)
    LinearLayout llLogin;
    @BindView(R.id.ll_name)
    LinearLayout llName;

    private String userId = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, null);

        userId = SpUtils.getUserId(getContext());
        ButterKnife.bind(this, view);
        initData();

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        StatusBarUtils.setStatusBar(getActivity(), getResources().getColor(R.color.statusbar_color));
        Logger.e("123123", userId);
        userId = SpUtils.getUserId(getContext());
        if (userId.equals("0")) {
            llName.setVisibility(View.GONE);
            llLogin.setVisibility(View.VISIBLE);
            Glide.with(getContext()).load(R.mipmap.weidenglu_avatar).into(ivAvatar);
        } else {
            llName.setVisibility(View.VISIBLE);
            llLogin.setVisibility(View.GONE);
            ViseHttp.GET(NetUrl.EngineerUsergetOne)
                    .addParam("id", userId)
                    .request(new ACallback<String>() {
                        @Override
                        public void onSuccess(String data) {
                            try {
                                Logger.e("123123", data);
                                JSONObject jsonObject = new JSONObject(data);
                                if(jsonObject.optString("status").equals("200")){
                                    Gson gson = new Gson();
                                    GetOneBean bean = gson.fromJson(data, GetOneBean.class);
                                    Glide.with(getContext()).load(NetUrl.BASE_URL+bean.getData().getHeadPhoto()).into(ivAvatar);
                                    tvName.setText(bean.getData().getMemName());
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
    }

    private void initData() {

    }

    @OnClick({R.id.ll_login, R.id.iv_avatar, R.id.tv_edit, R.id.rl1, R.id.rl2, R.id.rl3})
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.ll_login:
                intent.setClass(getContext(), SMSLoginActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_avatar:
                if (userId.equals("0")) {
                    intent.setClass(getContext(), SMSLoginActivity.class);
                    startActivity(intent);
                } else {
                    intent.setClass(getContext(), PersonInformationActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.tv_edit:
                if (userId.equals("0")) {
                    intent.setClass(getContext(), SMSLoginActivity.class);
                    startActivity(intent);
                } else {
                    intent.setClass(getContext(), PersonInformationActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.rl1:
                if (userId.equals("0")) {
                    intent.setClass(getContext(), SMSLoginActivity.class);
                    startActivity(intent);
                } else {
                    intent.setClass(getContext(), MyBankCardActivity.class);
                    intent.putExtra("type", "my");
                    startActivity(intent);
                }
                break;
            case R.id.rl2:
                if (userId.equals("0")) {
                    intent.setClass(getContext(), SMSLoginActivity.class);
                    startActivity(intent);
                } else {
                    intent.setClass(getContext(), ForgotPwd1Activity.class);
                    startActivity(intent);
                }
                break;
            case R.id.rl3:
                if (userId.equals("0")) {
                    intent.setClass(getContext(), SMSLoginActivity.class);
                    startActivity(intent);
                } else {
                    intent.setClass(getContext(), CommissionActivity.class);
                    startActivity(intent);
                }
                break;
        }
    }

}
