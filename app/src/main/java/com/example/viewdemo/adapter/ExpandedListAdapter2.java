package com.example.viewdemo.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.viewdemo.R;

import java.util.List;

/**
 * <pre>
 *   @author wangjishun
 *   time: 2021/08/02
 *   desc:
 * </pre>
 **/
public class ExpandedListAdapter2 extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ExpandBean2> mExpandedBeans;

    public void setExpandedBeans(List<ExpandBean2> expandedBeans) {
        this.mExpandedBeans = expandedBeans;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == 0) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
            return new NewAddHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_itemqqq, parent, false);
            return new OriginViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof OriginViewHolder) {
            ((OriginViewHolder) holder).setPosition(position);
        } else if (holder instanceof NewAddHolder) {
            ((NewAddHolder) holder).setPosition(position);
        }
    }

    @Override
    public int getItemCount() {
        return mExpandedBeans == null ? 0 : mExpandedBeans.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (mExpandedBeans.get(position).isNewAdd()) {
            return 0;
        } else {
            return 1;
        }
    }

    public class OriginViewHolder extends RecyclerView.ViewHolder {

        protected int mPosition;

        private View mItemView;

        private View.OnClickListener mListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mItemView.setOnClickListener(null);
                int next = mPosition + 1;
                if (next < mExpandedBeans.size()) {
                    if (mExpandedBeans.get(next).isNewAdd()) {
                        mExpandedBeans.remove(next);
                        notifyItemRemoved(next);
                        notifyItemRangeChanged(next, mExpandedBeans.size() - next - 1);
                    } else {
                        ExpandBean2 bean2 = new ExpandBean2();
                        bean2.setNewAdd(true);
                        mExpandedBeans.add(next, bean2);
                        notifyItemInserted(next);
                        notifyItemRangeChanged(next, mExpandedBeans.size() - next - 1);
                    }
                }
                mItemView.setOnClickListener(mListener);
            }
        };

        public OriginViewHolder(@NonNull View itemView) {
            super(itemView);
            mItemView = itemView;
            itemView.setOnClickListener(mListener);
        }

        public void setPosition(int position) {
            this.mPosition = position;
        }
    }

    public class NewAddHolder extends RecyclerView.ViewHolder {

        protected int mPosition;

        public NewAddHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void setPosition(int position) {
            this.mPosition = position;
        }
    }
}
