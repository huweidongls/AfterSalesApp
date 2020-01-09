package com.jingna.aftersalesapp.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jingna.aftersalesapp.R;
import com.jingna.aftersalesapp.bean.IncomeDetailsBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/11/22.
 */

public class IncomeDetailsAdapter extends RecyclerView.Adapter<IncomeDetailsAdapter.ViewHolder> {

    private Context context;
    private List<IncomeDetailsBean.DataBean> data;

    public IncomeDetailsAdapter(List<IncomeDetailsBean.DataBean> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_income_details, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.tvYear.setText(data.get(position).getYear());
        holder.tvChu.setText("支出 ¥"+data.get(position).getChu());
        holder.tvRu.setText("收入 ¥"+data.get(position).getRu());

        List<IncomeDetailsBean.DataBean.ListBean> list = data.get(position).getList();
        IncomeDetailsItemAdapter itemAdapter = new IncomeDetailsItemAdapter(list);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        holder.rv.setLayoutManager(manager);
        holder.rv.setAdapter(itemAdapter);
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private RecyclerView rv;
        private TextView tvYear;
        private TextView tvChu;
        private TextView tvRu;

        public ViewHolder(View itemView) {
            super(itemView);
            rv = itemView.findViewById(R.id.rv);
            tvYear = itemView.findViewById(R.id.tv_year);
            tvChu = itemView.findViewById(R.id.tv_chu);
            tvRu = itemView.findViewById(R.id.tv_ru);
        }
    }

}
