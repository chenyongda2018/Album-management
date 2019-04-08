package com.example.cyd.albummanager;

import android.app.Application;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;

/**
 * Created by cyd on 19-4-7.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //创建文件的缓存目录
        File cacheDir = StorageUtils.getOwnCacheDirectory(this,"imageloader/Cache");


        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(this)
                                                .diskCache(new UnlimitedDiskCache(cacheDir)).build();
        ImageLoader.getInstance().init(configuration);
    }
}
