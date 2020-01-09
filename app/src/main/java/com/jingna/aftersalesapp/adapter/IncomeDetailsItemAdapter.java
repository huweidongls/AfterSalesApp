package com.jingna.aftersalesapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jingna.aftersalesapp.R;
import com.jingna.aftersalesapp.bean.IncomeDetailsBean;
import com.jingna.aftersalesapp.net.NetUrl;

import java.util.List;

/**
 * Created by Administrator on 2019/11/22.
 */

public class IncomeDetailsItemAdapter extends RecyclerView.Adapter<IncomeDetailsItemAdapter.ViewHolder> {

    private Context context;
    private List<IncomeDetailsBean.DataBean.ListBean> data;

    public IncomeDetailsItemAdapter(List<IncomeDetailsBean.DataBean.ListBean> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_income_details_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(context).load(NetUrl.BASE_URL+data.get(position).getHeadPhoto()).into(holder.iv);
        holder.tvTime.setText(data.get(position).getTime());
        holder.tvTitle.setText(data.get(position).getTitle());
        String type = data.get(position).getType();
        if(type.equals("0")){
            holder.tvPrice.setText("+ "+data.get(position).getPrice());
        }else if(type.equals("1")){
            holder.tvPrice.setText("- "+data.get(position).getPrice());
        }
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvTitle;
        private TextView tvPrice;
        private TextView tvTime;
        private ImageView iv;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvPrice = itemView.findViewById(R.id.tv_price);
            tvTime = itemView.findViewById(R.id.tv_time);
            iv = itemView.findViewById(R.id.iv);
        }
    }

}
