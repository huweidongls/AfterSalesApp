package com.jingna.aftersalesapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jingna.aftersalesapp.R;
import com.jingna.aftersalesapp.bean.BankCardListBean;
import com.jingna.aftersalesapp.page.InsertBankCardActivity;

import java.util.List;

/**
 * Created by Administrator on 2019/6/19.
 */

public class MyBankCardAdapter extends RecyclerView.Adapter<MyBankCardAdapter.ViewHolder> {

    private Context context;
    private List<BankCardListBean.DataBean> data;
    private ClickListener listener;

    public MyBankCardAdapter(List<BankCardListBean.DataBean> data, ClickListener listener) {
        this.data = data;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_my_bank_card, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        String phone = "";
        String card = "";
        if(getItemViewType(position) == 1){
            holder.rlBank.setVisibility(View.GONE);
            holder.tvAdd.setVisibility(View.VISIBLE);
        }else {
            holder.rlBank.setVisibility(View.VISIBLE);
            holder.tvAdd.setVisibility(View.GONE);
            holder.tvBankName.setText(data.get(position).getCardType());
            phone = data.get(position).getPhone();
            phone = phone.substring(phone.length()-4, phone.length());
            holder.tvPhoneNum.setText("手机尾号"+phone);
            card = data.get(position).getBankCardNum();
            card = card.substring(card.length()-4, card.length());
            holder.tvBankCard.setText(card);
        }
        final String finalCard = card;
        holder.rlBank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(position, data.get(position).getCardType(), finalCard);
            }
        });
        holder.tvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(context, InsertBankCardActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        if(position == data.size()){
            return 1;
        }else {
            return 0;
        }
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size()+1;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private RelativeLayout rlBank;
        private TextView tvAdd;
        private TextView tvBankName;
        private TextView tvPhoneNum;
        private TextView tvBankCard;

        public ViewHolder(View itemView) {
            super(itemView);
            rlBank = itemView.findViewById(R.id.rl_bank);
            tvAdd = itemView.findViewById(R.id.tv_add);
            tvBankName = itemView.findViewById(R.id.tv_bank_name);
            tvPhoneNum = itemView.findViewById(R.id.tv_phonenum);
            tvBankCard = itemView.findViewById(R.id.tv_bank_card);
        }
    }

    public interface ClickListener{
        void onItemClick(int pos, String bankName, String card);
    }

}
