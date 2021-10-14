package com.example.viewdemo.fragment.adpter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.viewdemo.R;
import com.example.viewdemo.fragment.data.ShowData;

import java.util.List;

/**
 * <pre>
 *   author: wangjishun
 *   time: 2021/10/13
 *   desc: 列表展示 adapter
 * </pre>
 **/
public class DataShowAdapter extends RecyclerView.Adapter<DataShowAdapter.VH> {

    private List<ShowData> mData;

    private ItemClickListener mListener;

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_item_layout, parent, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.setPosition(position);
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public void setData(List<ShowData> mDatas) {
        this.mData = mDatas;
        notifyDataSetChanged();
    }

    public void setListener(ItemClickListener listener) {
        this.mListener = listener;
    }

    public interface ItemClickListener {
        /**
         * 绑定位置
         * @param position holder所在的位置
         */
        void onItemClick(int position);
    }

    public class VH extends RecyclerView.ViewHolder {

        android.view.View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.onItemClick(position);
                }
            }
        };

        int position;

        public VH(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(listener);
        }

        public void setPosition(int position) {
            this.position = position;
        }
    }
}
