package com.jingna.aftersalesapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jingna.aftersalesapp.R;
import com.jingna.aftersalesapp.bean.OrderBean;
import com.jingna.aftersalesapp.dialog.DialogCustom;
import com.jingna.aftersalesapp.net.NetUrl;
import com.jingna.aftersalesapp.page.OrderDetailsActivity;
import com.jingna.aftersalesapp.page.StartWeixiuActivity;
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

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    private Context context;
    private List<OrderBean.DataBean> data;

    public OrderAdapter(List<OrderBean.DataBean> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_order, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final String status = data.get(position).getOrderStatus();
        if(status.equals("0")){
            holder.tvStatus.setText("维修状态：订单已取消");
            holder.tv.setVisibility(View.VISIBLE);
            holder.tv.setText("删除订单");
        }else if(status.equals("2")){
            holder.tvStatus.setText("维修状态：已接单");
            holder.tv.setVisibility(View.VISIBLE);
            holder.tv.setText("开始维修");
        }else if(status.equals("3")){
            holder.tvStatus.setText("维修状态：维修中");
            holder.tv.setVisibility(View.VISIBLE);
            holder.tv.setText("维修完成");
        }else if(status.equals("4")){
            holder.tvStatus.setText("维修状态：维修完成");
            holder.tv.setVisibility(View.VISIBLE);
            holder.tv.setText("提交订单");
        }else if(status.equals("5")){
            holder.tvStatus.setText("维修状态：已提交价格");
            holder.tv.setVisibility(View.GONE);
            holder.tv.setText("");
        }else if(status.equals("6")){
            holder.tvStatus.setText("维修状态：客户已支付");
            holder.tv.setVisibility(View.VISIBLE);
            holder.tv.setText("删除订单");
        }
        holder.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(status.equals("0")){
                    //删除订单
                    DialogCustom dialogCustom = new DialogCustom(context, "是否删除订单", new DialogCustom.OnYesListener() {
                        @Override
                        public void onYes() {
                            ViseHttp.GET(NetUrl.AfterSaleOrdertoDeleteRepairOrder)
                                    .addParam("orderId", data.get(position).getId())
                                    .request(new ACallback<String>() {
                                        @Override
                                        public void onSuccess(String d) {
                                            try {
                                                JSONObject jsonObject = new JSONObject(d);
                                                if(jsonObject.optString("status").equals("200")){
                                                    ToastUtil.showShort(context, "删除成功");
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
                    dialogCustom.show();
                }else if(status.equals("2")){
                    //开始维修
                    DialogCustom dialogCustom = new DialogCustom(context, "是否开始维修", new DialogCustom.OnYesListener() {
                        @Override
                        public void onYes() {
                            ViseHttp.GET(NetUrl.AfterSaleOrdergetByOrderRepairId)
                                    .addParam("repairId", SpUtils.getUserId(context))
                                    .addParam("orderId", data.get(position).getId())
                                    .addParam("orderStatus", "3")
                                    .request(new ACallback<String>() {
                                        @Override
                                        public void onSuccess(String d) {
                                            try {
                                                JSONObject jsonObject = new JSONObject(d);
                                                if(jsonObject.optString("status").equals("200")){
                                                    data.get(position).setOrderStatus("3");
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
                    dialogCustom.show();
                }else if(status.equals("3")){
                    //维修完成
                    DialogCustom dialogCustom = new DialogCustom(context, "是否确认维修完成", new DialogCustom.OnYesListener() {
                        @Override
                        public void onYes() {
                            ViseHttp.GET(NetUrl.AfterSaleOrdergetByOrderRepairId)
                                    .addParam("repairId", SpUtils.getUserId(context))
                                    .addParam("orderId", data.get(position).getId())
                                    .addParam("orderStatus", "4")
                                    .request(new ACallback<String>() {
                                        @Override
                                        public void onSuccess(String d) {
                                            try {
                                                JSONObject jsonObject = new JSONObject(d);
                                                if(jsonObject.optString("status").equals("200")){
                                                    data.get(position).setOrderStatus("4");
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
                    dialogCustom.show();
                }else if(status.equals("4")){
                    Intent intent = new Intent();
                    intent.setClass(context, StartWeixiuActivity.class);
                    intent.putExtra("id", data.get(position).getId());
                    intent.putExtra("name", data.get(position).getAddresUname());
                    intent.putExtra("phone", data.get(position).getAddresPhone());
                    intent.putExtra("address", data.get(position).getAddresName());
                    context.startActivity(intent);
                }else if(status.equals("6")){
                    //删除订单
                    DialogCustom dialogCustom = new DialogCustom(context, "是否删除订单", new DialogCustom.OnYesListener() {
                        @Override
                        public void onYes() {
                            ViseHttp.GET(NetUrl.AfterSaleOrdertoDeleteRepairOrder)
                                    .addParam("orderId", data.get(position).getId())
                                    .request(new ACallback<String>() {
                                        @Override
                                        public void onSuccess(String d) {
                                            try {
                                                JSONObject jsonObject = new JSONObject(d);
                                                if(jsonObject.optString("status").equals("200")){
                                                    ToastUtil.showShort(context, "删除成功");
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
                    dialogCustom.show();
                }
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(context, OrderDetailsActivity.class);
                intent.putExtra("id", data.get(position).getId());
                context.startActivity(intent);
            }
        });
        holder.tvId.setText(data.get(position).getId());
        holder.tvAddress.setText(data.get(position).getAddresName());
        holder.tvPhone.setText(data.get(position).getAddresPhone());
        holder.tvName.setText(data.get(position).getDeviceName());
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
        private TextView tvStatus;
        private TextView tv;

        public ViewHolder(View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tv_id);
            tvAddress = itemView.findViewById(R.id.tv_address);
            tvPhone = itemView.findViewById(R.id.tv_phone);
            tvName = itemView.findViewById(R.id.tv_name);
            tvStatus = itemView.findViewById(R.id.tv_status);
            tv = itemView.findViewById(R.id.tv);
        }
    }

}
