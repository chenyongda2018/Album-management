package com.example.cyd.albummanager.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import com.truizlop.sectionedrecyclerview.SectionedRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by cyd on 19-4-8.
 */

public class GridImageAdapter extends SectionedRecyclerViewAdapter {
    private static final String TAG = GridImageAdapter.class.getSimpleName();
    private Context mContext;
    //存放所有图片的路径的列表
    private List<StringBuffer> imgList = new ArrayList<>();
    private String[] IMAGE_LIST = Constants.IMAGES;//网络图片列表
    private DisplayImageOptions mOptions;//加载设置

    public GridImageAdapter(Context context, List<StringBuffer> imgList) {
        this.mContext = context;
        this.imgList = imgList;
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

        final GirdImageHolder imgHolder = (GirdImageHolder) holder;
        ImageLoader.getInstance()
                .displayImage(imgList.get(position).toString(), imgHolder.imgView, mOptions, new SimpleImageLoadingListener() {
                    @Override
                    public void onLoadingStarted(String imageUri, View view) {

                    }

                    @Override
                    public void onLoadingFailed(String imageUri, View view, FailReason failReason) {

                    }

                    @Override
                    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {

                    }
                }, new ImageLoadingProgressListener() {
                    @Override
                    public void onProgressUpdate(String imageUri, View view, int current, int total) {

                    }
                });
    }

    @Override
    protected int getSectionCount() {
        return 0;
    }

    @Override
    protected int getItemCountForSection(int section) {
        return 0;
    }

    @Override
    protected boolean hasFooterInSection(int section) {
        return false;
    }

    @Override
    protected RecyclerView.ViewHolder onCreateSectionHeaderViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    protected RecyclerView.ViewHolder onCreateSectionFooterViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    protected RecyclerView.ViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    protected void onBindSectionHeaderViewHolder(RecyclerView.ViewHolder holder, int section) {

    }

    @Override
    protected void onBindSectionFooterViewHolder(RecyclerView.ViewHolder holder, int section) {

    }

    @Override
    protected void onBindItemViewHolder(RecyclerView.ViewHolder holder, int section, int position) {

    }

    @Override
    public int getItemCount() {
        return imgList.size();
    }

    class GirdImageHolder extends RecyclerView.ViewHolder {
        public ImageView imgView;


        public GirdImageHolder(View itemView) {
            super(itemView);
            imgView = (ImageView) itemView.findViewById(R.id.img_item);

        }
    }
}
