package com.hotbitmapgg.ohmybilibili.adapter.section;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.hotbitmapgg.ohmybilibili.R;
import com.hotbitmapgg.ohmybilibili.entity.region.RegionRecommendInfo;
import com.hotbitmapgg.ohmybilibili.module.video.VideoDetailsActivity;
import com.hotbitmapgg.ohmybilibili.rx.RxBus;
import com.hotbitmapgg.ohmybilibili.widget.sectioned.StatelessSection;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hcc on 2016/10/21 23:28
 * 100332338@qq.com
 * <p>
 * 分区推荐最新视频section
 */

public class RegionRecommendNewSection extends StatelessSection
{

    private Context mContext;

    private List<RegionRecommendInfo.DataBean.NewBean> news;

    public RegionRecommendNewSection(Context context, List<RegionRecommendInfo.DataBean.NewBean> news)
    {

        super(R.layout.layout_region_recommend_new_head, R.layout.layout_region_recommend_card_item);
        this.news = news;
        this.mContext = context;
    }

    @Override
    public int getContentItemsTotal()
    {

        return news.size();
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view)
    {

        return new ItemViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position)
    {

        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        RegionRecommendInfo.DataBean.NewBean newBean = news.get(position);

        Glide.with(mContext)
                .load(newBean.getCover())
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.bili_default_image_tv)
                .dontAnimate()
                .into(itemViewHolder.mImage);
        itemViewHolder.mTitle.setText(newBean.getTitle());
        itemViewHolder.mPlay.setText(String.valueOf(newBean.getPlay()));
        itemViewHolder.mReview.setText(String.valueOf(newBean.getDanmaku()));
        itemViewHolder.mCardView.setOnClickListener(v -> VideoDetailsActivity.launch((Activity) mContext,
                Integer.valueOf(newBean.getParam()), newBean.getCover()));
    }


    @Override
    public RecyclerView.ViewHolder getHeaderViewHolder(View view)
    {

        return new HeadViewHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder)
    {

        HeadViewHolder headViewHolder = (HeadViewHolder) holder;
        headViewHolder.mMore.setOnClickListener(v -> RxBus.getInstance().post(0));
    }

    static class HeadViewHolder extends RecyclerView.ViewHolder
    {

        @BindView(R.id.item_type_more)
        TextView mMore;

        public HeadViewHolder(View itemView)
        {

            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder
    {

        @BindView(R.id.card_view)
        CardView mCardView;

        @BindView(R.id.item_img)
        ImageView mImage;

        @BindView(R.id.item_title)
        TextView mTitle;

        @BindView(R.id.item_play)
        TextView mPlay;

        @BindView(R.id.item_review)
        TextView mReview;

        public ItemViewHolder(View itemView)
        {

            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
