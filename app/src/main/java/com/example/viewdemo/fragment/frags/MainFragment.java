package com.example.viewdemo.fragment.frags;

import android.annotation.SuppressLint;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.viewdemo.R;
import com.example.viewdemo.annotation.FragmentName;
import com.example.viewdemo.annotation.Layout;
import com.example.viewdemo.fragment.adpter.DataShowAdapter;
import com.example.viewdemo.fragment.architecture.OriginalFragment;
import com.example.viewdemo.fragment.data.ShowData;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *   @author wangjishun
 *   time: 2021/10/13
 *   desc: 主界面的fragment
 * </pre>
 **/
@SuppressLint("NonConstantResourceId")
@Layout(layout = R.layout.fragment_main_layout)
@FragmentName(name = "mainFragment")
public class MainFragment extends OriginalFragment {

    @Override
    protected void initView(View view) {
        initRecyclerView(view);
    }



    /**
     * 初始化recyclerView
     * @param view 当前fragment的布局
     */
    private void initRecyclerView(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview_show);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        DataShowAdapter adapter = new DataShowAdapter();
        adapter.setData(getTestData());
        dealAdapter(adapter);
        recyclerView.setAdapter(adapter);
    }

    private void dealAdapter(DataShowAdapter adapter) {
        adapter.setListener(position -> {
//            ActivityRecorder.SingleTon.getInstance()
//            .startFragment(R.id.fragment_container, new MainNextFragment(), true, new RuntimeData());
        });
    }

    private List<ShowData> getTestData() {
        List<ShowData> data = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            data.add(new ShowData());
        }
        return data;
    }

    @Override
    public void setTitleBar(ViewGroup vg) {

    }

    @Override
    public boolean isTitleBarGone() {
        return false;
    }

    @Override
    public boolean isBottomNaviGone() {
        return false;
    }
}
