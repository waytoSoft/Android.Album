package com.jg.album.model.soure;

import android.content.Context;

import java.util.List;

/**
 * author: hezhiWu <hezhi.woo@gmail.com>
 * version: V1.0
 * created at 2017/10/13 11:53
 * <p>
 * Copyright (c) 2017 Shenzhen O&M Cloud Co., Ltd. All rights reserved.
 */
public interface SelectImageDataSoure {

    interface QueryAlbumCallBack {
        Context getContext();

        int getPageIndex();

        int getPageSize();

        void onQueryAlbumSuccess(List<String> imgs);

        void onQueryAlbumFailure(String error);
    }

    void queryAlbum(QueryAlbumCallBack callBack);
}
