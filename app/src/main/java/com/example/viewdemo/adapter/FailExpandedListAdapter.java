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

import java.util.HashMap;

/**
 * <pre>
 *   @author wangjishun
 *   time: 2021/07/29
 *   desc:
 * </pre>
 **/
public class FailExpandedListAdapter extends RecyclerView.Adapter<FailExpandedListAdapter.ViewHolder> {

    private final int mItemCount;

    private final OnBindViewListener listener;

    private final boolean[] mVisibleArray;

    private final HashMap<Integer, ViewHolder> mHolderHashMap = new HashMap<>();

    public FailExpandedListAdapter(int itemCount, OnBindViewListener listener) {
        this.mItemCount = itemCount;
        this.listener = listener;
        this.mVisibleArray = new boolean[mItemCount];
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new ViewHolder(itemView);
    }

    /**
     * replace the contents of a view
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        mHolderHashMap.put(position, holder);
        if (listener != null) {
            listener.replaceContents(holder.getTopLayout(), holder.getBottomLayout());
        }
        holder.getTopLayout().setOnClickListener(view -> {
            dealItemVisible(position);
            dealHolder();
        });
    }

    @Override
    public int getItemCount() {
        return mItemCount;
    }

    private void dealHolder() {
        for(int i = 0; i < mHolderHashMap.size(); i++) {
            if (mVisibleArray[i]) {
                openBottom(mHolderHashMap.get(i), mHolderHashMap.get(i).getExpandView());
            } else {
                closeBottom(mHolderHashMap.get(i), mHolderHashMap.get(i).getExpandView());
            }
        }
    }

    private void dealItemVisible(int position) {
       for (int i = 0;i < mVisibleArray.length;i++) {
           if (i == position) {
               mVisibleArray[i] = !mVisibleArray[i];
               continue;
           }
           mVisibleArray[i] = false;
       }
    }

    public interface OnBindViewListener {
        /**
         * 替换view中的内容
         * @param topView 顶部视图
         * @param bottomView 底部视图
         */
        void replaceContents(ViewGroup topView, ViewGroup bottomView);
    }

    interface Expandable {
        /**
         * 获取要展开的View
         *
         * @return 获取要展开的View
         */
        View getExpandView();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements Expandable {

        private final RelativeLayout mTopLayout;

        private final RelativeLayout mBottomLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTopLayout = itemView.findViewById(R.id.item_top_layout);
            mBottomLayout = itemView.findViewById(R.id.item_bottom_layout);
        }

        public RelativeLayout getTopLayout() {
            return mTopLayout;
        }

        public RelativeLayout getBottomLayout() {
            return mBottomLayout;
        }

        @Override
        public View getExpandView() {
            return mBottomLayout;
        }

    }

    public static class ViewHolderAnimator {
        public static Animator ofItemViewHeight(RecyclerView.ViewHolder holder) {
            View parent = holder.itemView;
            int start = holder.itemView.getMeasuredHeight();
            parent.measure(View.MeasureSpec.makeMeasureSpec(parent.getMeasuredWidth(), View.MeasureSpec.AT_MOST),
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
            int end = parent.getMeasuredHeight();
            final Animator animator = LayoutAnimator.ofHeight(parent, start, end);
            animator.addListener(new ViewHolderAnimatorListener(holder));
            animator.addListener(new LayoutParamsAnimatorAdapter(parent, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            return animator;
        }

        public static class ViewHolderAnimatorListener extends AnimatorListenerAdapter {

            private final RecyclerView.ViewHolder mHolder;

            public ViewHolderAnimatorListener(RecyclerView.ViewHolder holder) {
                this.mHolder = holder;
            }

            @Override
            public void onAnimationStart(Animator animation) {
                mHolder.setIsRecyclable(false);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mHolder.setIsRecyclable(true);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                mHolder.setIsRecyclable(true);
            }
        }

        public static class LayoutParamsAnimatorAdapter extends AnimatorListenerAdapter {
            private final View mView;

            private final int mParamsWidth;

            private final int mParamsHeight;

            public LayoutParamsAnimatorAdapter(View view, int paramsWidth, int paramsHeight) {
                this.mView = view;
                this.mParamsHeight = paramsHeight;
                this.mParamsWidth = paramsWidth;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                final ViewGroup.LayoutParams params = mView.getLayoutParams();
                params.width = mParamsWidth;
                params.height = mParamsHeight;
                mView.setLayoutParams(params);
            }
        }
    }

    public static class LayoutAnimator {
        public static Animator ofHeight(View view, int start, int end) {
            final ValueAnimator animator = ValueAnimator.ofInt(start, end);
            animator.addUpdateListener(new LayoutHeightUpdateListener(view));
            return animator;
        }

        public static class LayoutHeightUpdateListener implements ValueAnimator.AnimatorUpdateListener {
            private final View mView;

            public LayoutHeightUpdateListener(View view) {
                this.mView = view;
            }

            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                final ViewGroup.LayoutParams lp = mView.getLayoutParams();
                lp.height = (int) valueAnimator.getAnimatedValue();
                mView.setLayoutParams(lp);
            }
        }
    }

    public static void openBottom(RecyclerView.ViewHolder holder, View expandView) {
        expandView.setVisibility(View.VISIBLE);
        final Animator animator = ViewHolderAnimator.ofItemViewHeight(holder);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
            }

            @Override
            public void onAnimationStart(Animator animation) {
            }
        });
        animator.start();
    }

    public static void closeBottom(RecyclerView.ViewHolder holder, View expandView) {

        expandView.setVisibility(View.GONE);
        final Animator animator = ViewHolderAnimator.ofItemViewHeight(holder);
        expandView.setVisibility(View.VISIBLE);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationCancel(Animator animation) {
                expandView.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                expandView.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationStart(Animator animation) {
            }
        });
        animator.start();
    }
}
