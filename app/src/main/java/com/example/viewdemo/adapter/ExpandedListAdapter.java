package com.example.viewdemo.adapter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.viewdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *   @author wangjishun
 *   time: 2021/07/30
 *   desc:
 * </pre>
 **/
public class ExpandedListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int DURATION = 200;
    public static final int HEADER = 0;
    public static final int FOOTER = 1;
    public static final int NORMAL = 2;
    public static final int TYPE_CANNOT = 0;
    public static final int TYPE_ALL = 1;
    public static final int TYPE_SINGLE = 2;
    private final List<ExpandedHolder> mHolderList = new ArrayList<>();
    private List<ExpandedBean> mData;
    private OnPosExpandListener mPosScrollListener;
    private OnBindHolderListener mBindHolderListener;
    private View mHeaderView;
    private View mFooterView;
    private int mExpandedHeight = 150;
    private int mExpandType = TYPE_CANNOT;

    private ExpandedListAdapter() {

    }

    private void setData(List<ExpandedBean> list) {
        if (this.mData == null) {
            this.mData = new ArrayList<>();
        }
        this.mData.clear();
        mData.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == HEADER) {
            return new ViewHolder(mHeaderView);
        } else if (viewType == FOOTER) {
            return new ViewHolder(mFooterView);
        } else {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
            ExpandedHolder expandedHolder = new ExpandedHolder(itemView);
            mHolderList.add(expandedHolder);
            return expandedHolder;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ExpandedHolder) {
            bindExpandedHolder((ExpandedHolder) holder, position);
        }
    }

    @Override
    public int getItemCount() {
        int itemCount;
        itemCount = mData == null ? 0 : mData.size();
        if (mFooterView != null) {
            ++itemCount;
        }
        if (mHeaderView != null) {
            ++itemCount;
        }
        return itemCount;
    }

    @Override
    public int getItemViewType(int position) {
        if (mHeaderView != null && position == 0) {
            return HEADER;
        }
        if (mFooterView != null && mData != null && position == getItemCount() - 1) {
            return FOOTER;
        }
        return NORMAL;
    }

    private void bindExpandedHolder(ExpandedHolder expandedHolder, int position) {
        if (expandedHolder == null || getItemViewType(position) == FOOTER || getItemViewType(position) == HEADER) {
            return;
        }
        int pos = getPosInDataSet(position);
        int size = mData.size();
        if (pos >= size) {
            return;
        }
        ExpandedBean expandedBean = mData.get(pos);
        expandedHolder.setPosInData(pos);
        expandedHolder.setPosInRecyclerView(position);
        ViewGroup expandedView = expandedHolder.getBottomLayout();
        ViewGroup visibleVg = expandedHolder.getTopLayout();
        if (mBindHolderListener != null) {
            mBindHolderListener.bindVisibleContent(position, visibleVg);
        }
        if (expandedBean.isVisible()) {
            if (expandedView.getVisibility() != View.VISIBLE) {
                expandedView.setVisibility(View.VISIBLE);
                expandedView.getLayoutParams().height = mExpandedHeight;
                expandedView.setLayoutParams(expandedView.getLayoutParams());
                if (mBindHolderListener != null) {
                    mBindHolderListener.bindExpandedContent(position, expandedView);
                }
            }
        } else {
            if (expandedView.getVisibility() != View.GONE) {
                expandedView.setVisibility(View.GONE);
                expandedView.getLayoutParams().height = 0;
                expandedView.setLayoutParams(expandedView.getLayoutParams());
            }
        }
    }

    private int getPosInDataSet(int position) {
        if (mHeaderView != null) {
            return position - 1;
        }
        return position;
    }

    private void verticalExpanded(@NonNull ViewGroup target, RecyclerView.ViewHolder holder, boolean isOpen, int height, int pos) {
        ValueAnimator animator = null;
        int visibility = target.getVisibility();
        boolean notAnimator = (visibility == View.GONE && !isOpen) || (visibility == View.INVISIBLE && isOpen);
        if (notAnimator) {
            return;
        }
        if (visibility == View.VISIBLE && !isOpen) {
            animator = ValueAnimator.ofInt(height, 0);
        } else if (visibility == View.GONE && isOpen) {
            target.setVisibility(View.VISIBLE);
            animator = ValueAnimator.ofInt(0, height);
        }
        if (animator == null) {
            return;
        }
        animator.setDuration(DURATION);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationCancel(Animator animation) {
                holder.setIsRecyclable(true);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                holder.setIsRecyclable(true);
                if (!isOpen) {
                    target.setVisibility(View.GONE);
                }
                if (mPosScrollListener != null) {
                    mPosScrollListener.onPosExpand(pos, isOpen);
                }
            }

            @Override
            public void onAnimationStart(Animator animation) {
                holder.setIsRecyclable(false);
                if (mBindHolderListener != null && target.getVisibility() == View.VISIBLE) {
                    target.removeAllViews();
                    mBindHolderListener.bindExpandedContent(pos, target);
                }
            }
        });
        animator.addUpdateListener(valueAnimator -> {
            final ViewGroup.LayoutParams lp = target.getLayoutParams();
            lp.height = (int) valueAnimator.getAnimatedValue();
            target.setLayoutParams(lp);
        });
        animator.start();
    }

    private void setPosExpandListener(OnPosExpandListener posScrollListener) {
        this.mPosScrollListener = posScrollListener;
    }

    private void setBindHolderListener(OnBindHolderListener bindHolderListener) {
        this.mBindHolderListener = bindHolderListener;
    }

    private void setExpandedHeight(int expandedHeight) {
        this.mExpandedHeight = expandedHeight;
    }

    private void setFooterView(View footerView) {
        this.mFooterView = footerView;
        notifyItemChanged(getItemCount() - 1);
    }

    private void setHeaderView(View headerView) {
        this.mHeaderView = headerView;
        notifyItemChanged(0);
    }

    public void setExpandType(int expandType) {
        mExpandType = expandType;
    }

    public interface OnPosExpandListener {
        /**
         * 折叠位置监听
         *
         * @param position 当前折叠/展开的位置
         * @param isOpen   是否是打开
         */
        void onPosExpand(int position, boolean isOpen);
    }

    public interface OnBindHolderListener {
        /**
         * 绑定一直可见的内容
         *
         * @param position 对应recyclerView中 Item 的位置
         * @param vg       对应recyclerView中 长显示Item 的位置
         */
        void bindVisibleContent(int position, ViewGroup vg);

        /**
         * 绑定可折叠的内容
         *
         * @param position   对应recyclerView中 Item 的位置
         * @param expandedVg 对应recyclerView中 折叠Item 的位置
         */
        void bindExpandedContent(int position, ViewGroup expandedVg);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public class ExpandedHolder extends RecyclerView.ViewHolder {

        private final RelativeLayout mTopLayout;
        private final RelativeLayout mBottomLayout;
        private int mPosInData;
        private int mPosInRecyclerView;

        public ExpandedHolder(@NonNull View itemView) {
            super(itemView);
            mTopLayout = itemView.findViewById(R.id.item_top_layout);
            mBottomLayout = itemView.findViewById(R.id.item_bottom_layout);
            View.OnClickListener mClickListener = view -> onItemClick();
            mTopLayout.setOnClickListener(mClickListener);
        }

        public void setPosInData(int posInData) {
            this.mPosInData = posInData;
        }

        public RelativeLayout getBottomLayout() {
            return mBottomLayout;
        }

        public void setPosInRecyclerView(int posInRecyclerView) {
            this.mPosInRecyclerView = posInRecyclerView;
        }

        public RelativeLayout getTopLayout() {
            return mTopLayout;
        }

        private void executeAnimation(boolean isOpen) {
            verticalExpanded(mBottomLayout, this, isOpen, mExpandedHeight, mPosInRecyclerView);
        }

        private void onItemClick() {
            switch (mExpandType) {
                case TYPE_CANNOT:
                    return;
                case TYPE_SINGLE:
                    deal(true);
                    break;
                case TYPE_ALL:
                    deal(false);
                    break;
                default:
                    break;
            }
        }

        private void deal(boolean isSingleExpandable) {
            if (isSingleExpandable) {
                int last = -1;
                for (int i = 0; i < mData.size(); i++) {
                    ExpandedBean bean = mData.get(i);
                    if (bean.isVisible()) {
                        last = i;
                    }
                    if (i == mPosInData) {
                        continue;
                    }
                    bean.setVisible(false);
                }
                for (ExpandedHolder expandedHolder : mHolderList) {
                    if (last == expandedHolder.mPosInData) {
                        verticalExpanded(expandedHolder.getBottomLayout(), expandedHolder, false, mExpandedHeight, expandedHolder.getLayoutPosition());
                    }
                }
            }
            ExpandedBean current = mData.get(mPosInData);
            current.setVisible(!current.isVisible());
            executeAnimation(current.isVisible());
        }
    }

    public static class Builder {

        private final ExpandedListAdapter adapter = new ExpandedListAdapter();

        public ExpandedListAdapter build() {
            return adapter;
        }

        public Builder setOnPosExpandListener(OnPosExpandListener expandListener) {
            adapter.setPosExpandListener(expandListener);
            return this;
        }

        public Builder setOnBindHolderListener(OnBindHolderListener bindHolderListener) {
            adapter.setBindHolderListener(bindHolderListener);
            return this;
        }

        public Builder setExpandType(int isExpandable) {
            adapter.setExpandType(isExpandable);
            return this;
        }

        public Builder setData(List<ExpandedBean> beans) {
            adapter.setData(beans);
            return this;
        }

        public Builder setHeaderView(View view) {
            adapter.setHeaderView(view);
            return this;
        }

        public Builder setFooterView(View view) {
            adapter.setFooterView(view);
            return this;
        }

    }

}
