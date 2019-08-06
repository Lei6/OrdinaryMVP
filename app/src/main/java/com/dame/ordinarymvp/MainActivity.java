package com.dame.ordinarymvp;

import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dame.ordinarymvp.adapter.MainAdapter;
import com.dame.ordinarymvp.adapter.baseadapter.OnItemClickListener;
import com.dame.ordinarymvp.base.BaseMvpActivity;
import com.dame.ordinarymvp.bean.JokesBean;
import com.dame.ordinarymvp.contract.MainContract;
import com.dame.ordinarymvp.mvp.presenter.MainPresenter;
import com.dame.ordinarymvp.utils.ToastUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

public class MainActivity extends BaseMvpActivity<MainPresenter> implements MainContract.MineView {

    @BindView(R.id.recycle)
    RecyclerView recycle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void getIntent(Intent intent) {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        Map<String, String> map = new HashMap<>();
        map.put("page", "1");
        mPresenter.requestData(map);
    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter();
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void showJokes(JokesBean jokesBean) {
        if (jokesBean == null) {
            return;
        }
        List<JokesBean.ListBean> list = jokesBean.getList();
        MainAdapter adapter = new MainAdapter(MainActivity.this, list, R.layout.item_mine);
        recycle.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recycle.setAdapter(adapter);
//        adapter.setOnItemClickListener(new OnItemClickListener() {
//            @Override
//            public void onItemClick(Object object, int postion) {
//                ToastUtils.show(MainActivity.this,"hh");
//            }
//        });
    }
}
