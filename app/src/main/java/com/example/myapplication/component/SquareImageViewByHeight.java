package com.example.myapplication.component;

import android.content.Context;
import android.util.AttributeSet;
import androidx.annotation.Nullable;

public class SquareImageViewByHeight extends androidx.appcompat.widget.AppCompatImageView {

    public SquareImageViewByHeight(Context context) {
        super(context);
    }

    public SquareImageViewByHeight(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareImageViewByHeight(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasuredHeight(), getMeasuredHeight());
    }
}
