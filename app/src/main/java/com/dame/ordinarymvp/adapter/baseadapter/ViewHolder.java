package com.dame.ordinarymvp.adapter.baseadapter;

import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

/**
 * created by 姚明亮
 * Time：2019/8/5 09:45
 */
public class ViewHolder extends RecyclerView.ViewHolder{
    //用于 缓存已找的界面
    private SparseArray<View> mView = null;

    public ViewHolder(View itemView) {
        super(itemView);
        mView = new SparseArray<>();
    }

    public View getView(int viewId){
        //对已有的view做缓存
        View view = mView.get(viewId);
        //使用缓存的方式减少findViewByID的次数
        if (view==null){
            view=itemView.findViewById(viewId);
            mView.put(viewId,view);
        }
        return view;
    }

    public View getViewGroup(int viewId){
        //对已有的view做缓存
        View view = mView.get(viewId);
        //使用缓存的方式减少findViewByID的次数
        if (view==null){
            view=itemView.findViewById(viewId);
            mView.put(viewId,view);
        }
        return view;
    }

    public ViewHolder setText(int viewId,CharSequence text){
        TextView view = (TextView) getView(viewId);
        view.setText(""+text);
        return this;
    }

    //点击事件
    public void setOnItemClickListener(View.OnClickListener listener){
        itemView.setOnClickListener(listener);
    }
}
