package com.jg.album;

import android.content.Context;
import android.graphics.Point;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.jg.album.view.RecyclerViewBaseAdapter;

/**
 * author: hezhiWu <hezhi.woo@gmail.com>
 * version: V1.0
 * created at 2017/10/13 11:32
 * <p>
 * Copyright (c) 2017 Shenzhen O&M Cloud Co., Ltd. All rights reserved.
 */
public class SelectImageAdapter extends RecyclerViewBaseAdapter<String> {

    private Point mPoint = new Point(0, 0);

    public SelectImageAdapter(Context context) {
        super(context);
    }

    @Override
    public RecyclerView.ViewHolder onBaseCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mInflater.inflate(R.layout.item_image, parent, false));
    }

    @Override
    public void onBaseBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.imageView.setOnMeasureListener(new PhotoAlbumImageView.OnMeasureListener() {
            @Override
            public void onMeasureSize(int width, int height) {
                mPoint.set(width, width);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, width);
//                params.width = width;
//                params.height = height;
                viewHolder.imageView.setLayoutParams(params);
            }
        });
        Glide.with(mContent)
                .load("file://" + (mList.get(position)))
                .placeholder(R.mipmap.default_img)
                .error(R.mipmap.default_img)
                .into(viewHolder.imageView);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        PhotoAlbumImageView imageView;

        public ViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.Item_ImageView);
        }
    }
}
