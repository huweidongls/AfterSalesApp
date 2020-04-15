package com.jingna.aftersalesapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jingna.aftersalesapp.R;
import com.jingna.aftersalesapp.bean.AppMemberCommissionAuditqueryListAppBean;
import com.jingna.aftersalesapp.util.StringUtils;

import java.util.List;

/**
 * Created by Administrator on 2020/4/15.
 */

public class CommissionDetailsAdapter extends RecyclerView.Adapter<CommissionDetailsAdapter.ViewHolder> {

    private Context context;
    private List<AppMemberCommissionAuditqueryListAppBean.DataBean> data;

    public CommissionDetailsAdapter(List<AppMemberCommissionAuditqueryListAppBean.DataBean> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_commission_details, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvMoney.setText("提现金额："+ StringUtils.roundByScale(data.get(position).getAuditMoney(), 2));
        holder.tvTime.setText("提现时间："+data.get(position).getCreateTime());
        int type = data.get(position).getIsPass();
        if(type == 0){
            holder.tvType.setText("提现状态：审核中");
        }else if(type == 1){
            holder.tvType.setText("提现状态：审核通过");
        }else if(type == 2){
            holder.tvType.setText("提现状态：审核拒绝");
        }
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvMoney;
        private TextView tvType;
        private TextView tvTime;

        public ViewHolder(View itemView) {
            super(itemView);
            tvMoney = itemView.findViewById(R.id.tv_money);
            tvType = itemView.findViewById(R.id.tv_type);
            tvTime = itemView.findViewById(R.id.tv_time);
        }
    }

}
