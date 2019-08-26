package com.jingna.aftersalesapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jingna.aftersalesapp.R;
import com.jingna.aftersalesapp.bean.IndexDetailsBean;

import java.util.List;

/**
 * Created by Administrator on 2019/8/26.
 */

public class IndexDetailsAdapter extends RecyclerView.Adapter<IndexDetailsAdapter.ViewHolder> {

    private Context context;
    private List<IndexDetailsBean.DataBean.AppAfterSaleEquipmentsBean> data;

    public IndexDetailsAdapter(List<IndexDetailsBean.DataBean.AppAfterSaleEquipmentsBean> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_index_details, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv1.setText(data.get(position).getEquipmentName());
        holder.tv2.setText(data.get(position).getEquipmentModel());
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tv1;
        private TextView tv2;

        public ViewHolder(View itemView) {
            super(itemView);
            tv1 = itemView.findViewById(R.id.tv1);
            tv2 = itemView.findViewById(R.id.tv2);
        }
    }

}
