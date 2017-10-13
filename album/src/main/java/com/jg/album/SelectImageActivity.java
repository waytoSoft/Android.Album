package com.jg.album;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.jg.album.model.SelectImageContract;
import com.jg.album.model.SelectImagePresent;
import com.jg.album.view.PullToRefresRecyclerView;
import com.jg.album.view.PullToRefreshRecyclerViewListener;

import java.util.List;

/**
 * author: hezhiWu <hezhi.woo@gmail.com>
 * version: V1.0
 * created at 2017/10/13 11:29
 * <p>
 * Copyright (c) 2017 Shenzhen O&M Cloud Co., Ltd. All rights reserved.
 */
public class SelectImageActivity extends AppCompatActivity implements PullToRefreshRecyclerViewListener, SelectImageContract.SelectImageView {
    public final int DEFULT_PAGESIZE = 20;
    public int pageIndex = 0;

    private PullToRefresRecyclerView refresRecyclerView;

    private SelectImageAdapter adapter;

    private SelectImagePresent selectImagePresent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_image);
        refresRecyclerView = (PullToRefresRecyclerView) findViewById(R.id.RecycleView);

        selectImagePresent = new SelectImagePresent(this, this);
        initRecycleView();

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 390);
        } else {
            refresRecyclerView.startDownRefresh();
        }
    }

    private void initRecycleView() {
        adapter = new SelectImageAdapter(this);

        refresRecyclerView.setRecyclerViewAdapter(adapter);
        refresRecyclerView.setPullToRefreshRecyclerViewListener(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 390) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                refresRecyclerView.startDownRefresh();
            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onDownRefresh() {
        pageIndex = 0;
        selectImagePresent.queryAlbum();
    }

    @Override
    public void onPullRefresh() {
        pageIndex++;
    }

    @Override
    public int getPageIndex() {
        return pageIndex;
    }

    @Override
    public int getPageSize() {
        return DEFULT_PAGESIZE;
    }

    @Override
    public void onQueryAlbumSuccess(List<String> imgs) {
        adapter.clearList();
        adapter.addItems(imgs);
    }

    @Override
    public void onQueryAlbumMoreSuccess(List<String> imgs) {
        adapter.addItems(adapter.getItemCount() - 1, imgs);
    }

    @Override
    public void onQueryAlbumFailure(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onQueryEmpty() {
        refresRecyclerView.showEmptyTextView("暂无图片");
    }

    @Override
    public void onCloseDownRefresh() {
        refresRecyclerView.onCloseDownRefresh();
    }

    @Override
    public void onClosePullRefresh() {
        refresRecyclerView.onCloseLoadMore();
    }

    @Override
    public void onLoadMoreComplete() {
        refresRecyclerView.onLoadMoreComplete();
    }
}
