package com.example.viewdemo.activities;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.example.viewdemo.R;
import com.example.lib_base.annotation.Layout;
import com.example.viewdemo.fragment.FragmentLearnActivity;

/**
 * <pre>
 *   @author wangjishun
 *   time: 2021/10/09
 *   desc:
 * </pre>
 *
 */
@Layout(layout = R.layout.layout_otherview)
public class LayoutActivity extends AbstractActivity implements View.OnClickListener {

    private final int[] mViewIds = new int[] {
            R.id.tv_1, R.id.tv_2, R.id.tv_3, R.id.tv_4, R.id.tv_5, R.id.tv_6, R.id.tv_7
    };

    @Override
    public void initViews() {
        for (int id : mViewIds) {
            TextView tv = findViewById(id);
            tv.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_1:
                gotoFragmentActivity();
                break;
            case R.id.tv_2:
                goToTestH5Activity();
                break;
            default:
                break;
        }
    }

    private void goToTestH5Activity() {
        Intent intent = new Intent(this, H5FullScreenActivity.class);
        startActivity(intent);
    }

    private void gotoFragmentActivity() {
        Intent intent = new Intent(this, FragmentLearnActivity.class);
        startActivity(intent);
    }
}
