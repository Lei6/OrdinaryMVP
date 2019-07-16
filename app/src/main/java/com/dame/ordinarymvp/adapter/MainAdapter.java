package com.dame.ordinarymvp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dame.ordinarymvp.R;
import com.dame.ordinarymvp.bean.JokesBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private List<JokesBean.ListBean> list;
    private Context mContext;

    public MainAdapter(List<JokesBean.ListBean> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_mine, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.creteJokeTime.setText(list.get(position).getUpdateTime());
        holder.jokeContent.setText(list.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        if (list==null) {
            return 0;
        }
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.joke_content)
        TextView jokeContent;
        @BindView(R.id.crete_joke_time)
        TextView creteJokeTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
