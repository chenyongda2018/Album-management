package com.example.cyd.albummanager.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.cyd.albummanager.R;
import com.example.cyd.albummanager.data.Constants;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;


/**
 * Created by cyd on 19-4-8.
 */

public class GridImageAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private String[] IMAGE_LIST = Constants.IMAGES;//网络图片列表
    private DisplayImageOptions mOptions;//加载设置
    public GridImageAdapter(Context context) {
        this.mContext = context;

        mOptions = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.ic_stub)//还没加载出来时显示的图片
                .showImageForEmptyUri(R.drawable.ic_empty1)//无效URL加载的图片
                .showImageOnFail(R.drawable.ic_error)//加载失败时显示的图片
                .cacheInMemory(true)//打开内存缓存
                .cacheOnDisk(true)//打开磁盘缓存
                .considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
    }

    @Override
    public GirdImageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.grid_item_layout, parent, false);
        return new GirdImageHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        final GirdImageHolder imgHolder = (GirdImageHolder)holder;
        ImageLoader.getInstance()
                .displayImage(IMAGE_LIST[position],imgHolder.imgView,mOptions,new SimpleImageLoadingListener() {
                    @Override
                    public void onLoadingStarted(String imageUri, View view) {
                        imgHolder.imgProgress.setProgress(0);
                        imgHolder.imgProgress.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                        imgHolder.imgProgress.setVisibility(View.GONE);
                    }

                    @Override
                    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                        imgHolder.imgProgress.setVisibility(View.GONE);
                    }
                }, new ImageLoadingProgressListener() {
                    @Override
                    public void onProgressUpdate(String imageUri, View view, int current, int total) {
                        imgHolder.imgProgress.setProgress(Math.round(100.0f * current / total));
                    }
                });
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class GirdImageHolder extends RecyclerView.ViewHolder {
        public ImageView imgView;
        public ProgressBar imgProgress;

        public GirdImageHolder(View itemView) {
            super(itemView);
            imgView = (ImageView) itemView.findViewById(R.id.img_item);
            imgProgress = (ProgressBar) itemView.findViewById(R.id.progress);
        }
    }
}
