package com.caipiao.caipiao.mvp.home.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.caipiao.caipiao.R;
import com.caipiao.caipiao.app.Url;
import com.caipiao.caipiao.mvp.bean.LotteryListBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class HomeHallAdapter extends BaseQuickAdapter<LotteryListBean.ContentBean, BaseViewHolder> {

    Context context;


    public HomeHallAdapter(Context context, @Nullable List<LotteryListBean.ContentBean> data) {
        super(R.layout.home_all_adapter_item, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, LotteryListBean.ContentBean item) {
        if(item.isLottery()){
            ImageView imageView = helper.getView(R.id.icon);
            Glide.with(context).load(Url.lottery_png + item.getCode() + ".png").into(imageView);
        }else {
        }
        helper.setText(R.id.name, item.getName());
    }

}
