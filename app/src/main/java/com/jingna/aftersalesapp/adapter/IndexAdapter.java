package com.jingna.aftersalesapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jingna.aftersalesapp.R;
import com.jingna.aftersalesapp.bean.IndexBean;
import com.jingna.aftersalesapp.net.NetUrl;
import com.jingna.aftersalesapp.page.IndexDetailsActivity;
import com.jingna.aftersalesapp.util.SpUtils;
import com.jingna.aftersalesapp.util.ToastUtil;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Administrator on 2019/8/23.
 */

public class IndexAdapter extends RecyclerView.Adapter<IndexAdapter.ViewHolder> {

    private Context context;
    private List<IndexBean.DataBean> data;

    public IndexAdapter(List<IndexBean.DataBean> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_index, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tvId.setText(data.get(position).getId());
        holder.tvAddress.setText(data.get(position).getAddresName());
        holder.tvPhone.setText(data.get(position).getAddresPhone());
        holder.tvName.setText(data.get(position).getDeviceName());
        holder.tvJieshou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViseHttp.GET(NetUrl.AfterSaleOrdergetByOrderRepairId)
                        .addParam("repairId", SpUtils.getUserId(context))
                        .addParam("orderId", data.get(position).getId())
                        .addParam("orderStatus", "2")
                        .request(new ACallback<String>() {
                            @Override
                            public void onSuccess(String d) {
                                try {
                                    JSONObject jsonObject = new JSONObject(d);
                                    if(jsonObject.optString("status").equals("200")){
                                        ToastUtil.showShort(context, "接单成功");
                                        data.remove(position);
                                        notifyDataSetChanged();
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
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(context, IndexDetailsActivity.class);
                intent.putExtra("id", data.get(position).getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvId;
        private TextView tvAddress;
        private TextView tvPhone;
        private TextView tvName;
        private TextView tvJieshou;

        public ViewHolder(View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tv_id);
            tvAddress = itemView.findViewById(R.id.tv_address);
            tvPhone = itemView.findViewById(R.id.tv_phone);
            tvName = itemView.findViewById(R.id.tv_name);
            tvJieshou = itemView.findViewById(R.id.tv_jieshou);
        }
    }

}
