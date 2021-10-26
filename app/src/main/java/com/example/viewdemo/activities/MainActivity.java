package com.example.viewdemo.activities;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;

import com.example.lib_base.framework.AbstractActivity;
import com.example.viewdemo.R;
import com.example.viewdemo.Url;
import com.example.viewdemo.adapter.DialogAdapter;
import com.example.lib_base.annotation.Layout;
import com.example.lib_base.annotation.Advertisement;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangjishun
 */
@SuppressLint("NonConstantResourceId")
@Layout(layout = R.layout.activity_main)
@Advertisement(adsLayout = R.layout.layout_show_advertisement)
public class MainActivity extends AbstractActivity {

    public static final String NAME = "type";

    public static final String SHADOW_TYPE = "shadow";

    public static final String LISTVIEW_TYPE = "singleShowItemListView";

    public static final String WEB_TYPE = "webView";

    public static final String VERSION_TYPE = "version";

    @Override
    public void initViews() {
        TextView shadowTv = findViewById(R.id.shadowView_tv);
        TextView singItemShowTv = findViewById(R.id.singleItemShowListView_tv);
        Intent intent = new Intent(this, ShowViewActivity.class);
        shadowTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra(NAME, SHADOW_TYPE);
                startActivity(intent);
            }
        });

        singItemShowTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra(NAME, LISTVIEW_TYPE);
                startActivity(intent);
            }
        });

        findViewById(R.id.gotoWeb).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra(NAME, WEB_TYPE);
                startActivity(intent);
            }
        });

        findViewById(R.id.notNameIntent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse(Url.BAIDU_URL);
                Intent intent1 = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent1);
            }
        });

        findViewById(R.id.versionName).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra(NAME, VERSION_TYPE);
                startActivity(intent);
            }
        });

        findViewById(R.id.bottom_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<String> strings = new ArrayList<>();
                strings.add("鸠摩智");
                strings.add("段誉");
                strings.add("乔峰");
                strings.add("虚竹");
                BottomSheetDialog dialog = new BottomSheetDialog(MainActivity.this, R.style.BottomSheetDialogStyle);
                dialog.setContentView(R.layout.layout_pop_up_dialog);
                dialog.getWindow().findViewById(R.id.design_bottom_sheet).setBackgroundColor(Color.TRANSPARENT);
                RecyclerView recyclerview = dialog.findViewById(R.id.recyclerview_dialog_content);
                recyclerview.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                recyclerview.setAdapter(new DialogAdapter(strings));

                dialog.show();
            }
        });

        findViewById(R.id.otherlayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, LayoutActivity.class));
            }
        });
    }

    @Override
    protected boolean needShowAds() {
        return false;
    }
}