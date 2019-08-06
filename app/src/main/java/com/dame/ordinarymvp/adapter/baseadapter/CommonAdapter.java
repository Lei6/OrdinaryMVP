package com.dame.ordinarymvp.adapter.baseadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * created by 姚明亮
 * Time：2019/8/5 11:08
 */
public abstract class CommonAdapter<T> extends RecyclerView.Adapter<ViewHolder> {

    public Context mContext;
    public List<T> mData;
    private int mLayoutId;

    private LayoutInflater mInflater = null;
    private MultipleType<T> mTypeSupport = null;
//    private OnItemClickListener mItemClickListener = null;

    public CommonAdapter(Context mContext, List<T> mData, int mLayoutId) {
        this.mContext = mContext;
        this.mData = mData;
        this.mLayoutId = mLayoutId;
        mInflater = LayoutInflater.from(mContext);
    }

    public CommonAdapter(Context mContext, List<T> mData, int mLayoutId, LayoutInflater mInflater, MultipleType<T> mTypeSupport) {
        this.mContext = mContext;
        this.mData = mData;
        this.mLayoutId = mLayoutId;
        this.mInflater = mInflater;
        this.mTypeSupport = mTypeSupport;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getItemViewType(int position) {
        if (mTypeSupport != null) {
            return mTypeSupport.getLayoutId(mData.get(position), position);
        }
        return super.getItemViewType(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (mTypeSupport != null) {
            //需要多布局
            mLayoutId = viewType;
        }
        //创建view
        View view = mInflater.inflate(mLayoutId, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        bindData(holder, mData.get(position), position);
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mItemClickListener.onItemClick(mData.get(position), position);
//            }
//        });
    }

    public abstract void bindData(ViewHolder holder, T t, int position);

    @Override
    public int getItemCount() {
        if (mData == null) {
            return 0;
        }
        return mData.size();
    }

//    void setOnItemClickListeners(OnItemClickListener itemClickListener) {
//        this.mItemClickListener = itemClickListener;
//    }

}
