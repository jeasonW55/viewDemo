package com.example.viewdemo.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.core.content.ContextCompat;

import com.example.viewdemo.R;

/**
 * <pre>
 *   @author wangjishun
 *   time: 2021/07/28
 *   desc:
 * </pre>
 **/
public class SelectedShadowView extends View {

    public static final int MOVE_LEFT = -1;

    public static final int MOVE_NONE = 0;

    public static final int MOVE_RIGHT = 1;

    private final Paint mBtPaint = new Paint();

    private final Paint mShadePaint = new Paint();

    private final Drawable mCircle = ContextCompat.getDrawable(getContext(), R.drawable.oval_bt_bg);

    private int mRadius = 30;

    private int mLeftCenterX, mRightCenterX;

    private int mLeftBoundX, mRightBoundX;

    private float mFingerDownX;

    private String mShadeColor = "#20FF0000";

    private String mLineColor = "#FF0000";

    private int mDirection = MOVE_NONE;

    private boolean isInit = true;

    private final int[] mLocations = new int[2];

    private boolean mRightMoveSelected, mLeftMoveSelected;


    public SelectedShadowView(Context context) {
        super(context);
    }

    public SelectedShadowView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SelectedShadowView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (performClick()) {
            return false;
        }
        int rawX = (int) event.getRawX() - mLocations[0];
        int rawY = (int) event.getRawY() - mLocations[1];
        boolean isyEffective = rawY >= 0 && rawY <= getHeight();
        boolean isxEffective = rawX >= 0 && rawX <= getWidth();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                int firstLeftBoundX = (int) (mLeftCenterX - mRadius);
                int firstRightBoundX= (int) (mLeftCenterX + mRadius);
                int secondLeftBoundX = (int) (mRightCenterX - mRadius);
                int secondRightBoundX = (int) (mRightCenterX + mRadius);
                mRightMoveSelected = (rawX < secondRightBoundX && rawX > secondLeftBoundX);
                mLeftMoveSelected = (rawX > firstLeftBoundX && rawX < firstRightBoundX);
                mFingerDownX = event.getRawX();
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                dealMoveEvent(event);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                mDirection = MOVE_NONE;
                mRightMoveSelected = false;
                mLeftMoveSelected = false;
                invalidate();
                break;
            default:
                break;
        }
        return isxEffective && isyEffective;
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }

    private void dealMoveEvent(MotionEvent event) {
        if (event == null) {
            return;
        }
        float rawX = event.getRawX();
        float delta = rawX - mFingerDownX;
        if (delta > 0) {
            mDirection = MOVE_RIGHT;
        } else if (delta < 0) {
            mDirection = MOVE_LEFT;
        } else {
            mDirection = MOVE_NONE;
        }
        moveItem(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        return super.dispatchTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawSelf(canvas);
    }

    private void drawSelf(Canvas canvas) {
        setMinimumWidth(200);

        mBtPaint.setColor(Color.parseColor(mLineColor));
        mShadePaint.setColor(Color.parseColor(mShadeColor));
        if (isInit) {
            mLeftCenterX = mRadius;
            mLeftBoundX = mLeftCenterX;
            mRightCenterX = getWidth() - mRadius;
            mRightBoundX = mRightCenterX;
            getLocationInWindow(mLocations);
        }
        mBtPaint.setStrokeWidth(3);
        canvas.drawLine(mLeftCenterX, 0, mLeftCenterX, getHeight(), mBtPaint);
        canvas.drawLine(mRightCenterX, 0, mRightCenterX, getHeight(), mBtPaint);
        canvas.drawRect(mLeftCenterX, 0, mRightCenterX, getHeight(), mShadePaint);
        if (mLeftMoveSelected) {
            mShadePaint.setColor(Color.parseColor(mShadeColor));
            canvas.drawRect(mLeftCenterX - mRadius, 0, mLeftCenterX + mRadius, getHeight(), mShadePaint);
        }
        if (mRightMoveSelected) {
            mShadePaint.setColor(Color.parseColor(mShadeColor));
            canvas.drawRect(mRightCenterX - mRadius, 0, mRightCenterX + mRadius, getHeight(), mShadePaint);
        }
        int bottom = getHeight() / 2 + mRadius;
        int top = getHeight() / 2 - mRadius;
        mBtPaint.setStrokeWidth(2);

        if (mCircle == null) {
            return;
        }
        mCircle.setBounds(mLeftCenterX - mRadius, top, mLeftCenterX + mRadius, bottom);
        mCircle.draw(canvas);

        mCircle.setBounds(mRightCenterX - mRadius, top, mRightCenterX + mRadius, bottom);
        mCircle.draw(canvas);
    }

    private void moveItem(MotionEvent event) {
        if (event == null) {
            return;
        }
        float rawX = event.getRawX();
        float relativeX = rawX - mLocations[0];
        isInit = false;
        if (mLeftMoveSelected) {
            if (relativeX < mRadius || relativeX > getWidth() - mRadius - getMinimumWidth()) {
                return;
            }
            mLeftCenterX = (int)(rawX - mLocations[0]);
            boolean isMoveEntirety = moveEntirety();
            if (isMoveEntirety) {
                if (mDirection == MOVE_RIGHT && mRightCenterX >= mRightBoundX) {
                    mLeftCenterX = mRightCenterX - getMinimumWidth();
                    return;
                }
                mRightCenterX = mLeftCenterX + getMinimumWidth();
            }
            invalidate();
        } else if (mRightMoveSelected) {
            if (relativeX > getWidth() - mRadius || relativeX < mRadius + getMinimumWidth()) {
                return;
            }
            mRightCenterX = (int)(rawX - mLocations[0]);
            boolean isMoveEntirety = moveEntirety();
            if (isMoveEntirety) {
                if (mDirection == MOVE_LEFT && mLeftCenterX <= mLeftBoundX) {
                    mRightCenterX = mLeftCenterX + getMinimumWidth();
                    return;
                }
                mLeftCenterX = mRightCenterX - getMinimumWidth();
            }
            invalidate();
        }
    }

    private boolean moveEntirety() {
        int realWidth = mRightCenterX - mLeftCenterX;
        return realWidth <= getMinimumWidth();
    }

    private boolean isHorizontalMoveUnable() {
        int realWidth = mRightCenterX - mLeftCenterX;
        return realWidth == getMinimumWidth() && (mLeftCenterX <= mLeftBoundX || mRightCenterX >= mRightBoundX);
    }

    public void setRadius(int radius) {
        this.mRadius = radius;
        invalidate();
    }

    public void setShadeColor(String shadeColor) {
        this.mShadeColor = shadeColor;
        invalidate();
    }

    public void setLineColor(String lineColor) {
        this.mLineColor = lineColor;
        invalidate();
    }
}
