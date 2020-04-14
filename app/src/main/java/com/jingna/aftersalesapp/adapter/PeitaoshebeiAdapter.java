package com.jingna.aftersalesapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jingna.aftersalesapp.R;
import com.jingna.aftersalesapp.bean.PeitaoshebeiBean;
import com.jingna.aftersalesapp.util.StringUtils;

import java.util.List;

/**
 * Created by Administrator on 2019/8/7.
 */

public class PeitaoshebeiAdapter extends RecyclerView.Adapter<PeitaoshebeiAdapter.ViewHolder> {

    private Context context;
    private List<PeitaoshebeiBean.DataBean> data;

    public PeitaoshebeiAdapter(List<PeitaoshebeiBean.DataBean> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_peitaoshebei, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        if(data.get(position).getIsSelect() == 0){
            Glide.with(context).load(R.mipmap.dh_null).into(holder.iv);
        }else {
            Glide.with(context).load(R.mipmap.dh).into(holder.iv);
        }
        holder.tvName.setText(data.get(position).getName());
        holder.tv.setText(StringUtils.roundByScale(data.get(position).getDayMoney(), 2)+"元/"+data.get(position).getCompany());
        holder.rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(data.get(position).getIsSelect() == 0){
                    data.get(position).setIsSelect(1);
                    notifyDataSetChanged();
                }else {
                    data.get(position).setIsSelect(0);
                    notifyDataSetChanged();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvName;
        private TextView tv;
        private ImageView iv;
        private RelativeLayout rl;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tv = itemView.findViewById(R.id.tv);
            iv = itemView.findViewById(R.id.iv);
            rl = itemView.findViewById(R.id.rl);
        }
    }

}
