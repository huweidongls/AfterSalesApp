package com.jingna.aftersalesapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jingna.aftersalesapp.R;
import com.jingna.aftersalesapp.bean.PeitaoshebeiBean;
import com.jingna.aftersalesapp.util.StringUtils;

import java.util.List;

/**
 * Created by Administrator on 2019/8/28.
 */

public class CommitAdapter extends RecyclerView.Adapter<CommitAdapter.ViewHolder> {

    private Context context;
    private List<PeitaoshebeiBean.DataBean> data;

    public CommitAdapter(List<PeitaoshebeiBean.DataBean> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_commit, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv1.setText(data.get(position).getName());
        holder.tv2.setText(StringUtils.roundByScale(data.get(position).getDayMoney(), 2)+"元/"+data.get(position).getCompany());
        holder.tv3.setText("X "+data.get(position).getNum());
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tv1;
        private TextView tv2;
        private TextView tv3;

        public ViewHolder(View itemView) {
            super(itemView);
            tv1 = itemView.findViewById(R.id.tv1);
            tv2 = itemView.findViewById(R.id.tv2);
            tv3 = itemView.findViewById(R.id.tv3);
        }
    }

}
