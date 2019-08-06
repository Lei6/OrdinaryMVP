package com.dame.ordinarymvp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dame.ordinarymvp.R;
import com.dame.ordinarymvp.adapter.baseadapter.CommonAdapter;
import com.dame.ordinarymvp.adapter.baseadapter.OnItemClickListener;
import com.dame.ordinarymvp.adapter.baseadapter.ViewHolder;
import com.dame.ordinarymvp.bean.JokesBean;
import com.dame.ordinarymvp.utils.ToastUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainAdapter extends CommonAdapter<JokesBean.ListBean> {


    public MainAdapter(Context mContext, List<JokesBean.ListBean> mData, int mLayoutId) {
        super(mContext, mData, mLayoutId);
    }

    @Override
    public void bindData(com.dame.ordinarymvp.adapter.baseadapter.ViewHolder holder, JokesBean.ListBean listBean, int position) {
        holder.setText(R.id.joke_content, listBean.getContent());
        holder.setText(R.id.crete_joke_time, listBean.getUpdateTime());
        holder.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.show(mContext, listBean.getContent());
            }
        });

    }
}
