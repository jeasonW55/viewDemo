package com.example.viewdemo.anim;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

/**
 * <pre>
 *   @author wangjishun
 *   time: 2021/07/30
 *   desc:
 * </pre>
 **/
public class SelfAnimator {

    public static final long DURATION = 200;

    public static void verticalExpanded(View target, RecyclerView.ViewHolder holder, boolean isOpen, int height) {
        ValueAnimator animator = null;
        int visibility = target.getVisibility();
        boolean notAnimator = (visibility == View.GONE && !isOpen) || (visibility == View.INVISIBLE && isOpen);
        if (notAnimator) {
            return;
        }
        if (visibility == View.VISIBLE && !isOpen) {
            animator = ValueAnimator.ofInt(height, 0);
        } else if (visibility == View.GONE && isOpen){
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
            }

            @Override
            public void onAnimationStart(Animator animation) {
                holder.setIsRecyclable(false);
            }
        });
        animator.addUpdateListener(valueAnimator -> {
            final ViewGroup.LayoutParams lp = target.getLayoutParams();
            lp.height = (int) valueAnimator.getAnimatedValue();
            target.setLayoutParams(lp);
        });
        animator.start();
    }

    public static void verticalExpanded(View target,boolean isOpen, int height) {
        ValueAnimator animator;
        if (target.getVisibility() == View.VISIBLE && isOpen) {
            return;
        }
        if (target.getVisibility() == View.GONE && !isOpen) {
            return;
        }
        if (!isOpen) {
            animator = ValueAnimator.ofInt(height, 0);
        } else{
            animator = ValueAnimator.ofInt(0, height);
        }
        if (animator == null) {
            return;
        }
        animator.setDuration(DURATION);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationCancel(Animator animation) {
                super.onAnimationCancel(animation);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if (!isOpen) {
                    target.setVisibility(View.GONE);
                }
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                if (isOpen) {
                    target.setVisibility(View.VISIBLE);
                }
            }
        });
        animator.addUpdateListener(valueAnimator -> {
            final ViewGroup.LayoutParams lp = target.getLayoutParams();
            lp.height = (int) valueAnimator.getAnimatedValue();
            target.requestLayout();
        });
        animator.start();
    }

}
