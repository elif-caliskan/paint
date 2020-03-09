package com.elif.paint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class PaintView extends View {

    enum Category{RECTANGLE, SQUARE, CIRCLE, LINE, DELETE, MOVE}
    Category selectedCategory = Category.LINE;
    Path currentPath = new Path();
    ArrayList<Path> paths = new ArrayList<>();
    ArrayList<Integer> pathColors = new ArrayList<>();
    int color;
    Paint paint = new Paint();

    public PaintView(Context context) {
        this(context, null);
    }

    public PaintView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PaintView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(20f);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAlpha(255);
    }

    public void setCategory(Category cat ){
        selectedCategory = cat;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for(int i = 0;i< paths.size(); i++){
            paint.setColor(pathColors.get(i));
            canvas.drawPath(paths.get(i), paint);
        }
        paint.setColor(color);
        canvas.drawPath(currentPath, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(selectedCategory == Category.LINE){
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN :
                    currentPath = new Path();
                    currentPath.moveTo(event.getX(), event.getY());
                    invalidate();
                    break;
                case MotionEvent.ACTION_MOVE:
                    currentPath.lineTo(event.getX(), event.getY());
                    invalidate();
                    break;
                case MotionEvent.ACTION_UP:
                    paths.add(currentPath);
                    pathColors.add(color);
                    currentPath = null;
                    invalidate();
                    break;
            }
        }

        return true;
    }

    void setColor(int color){
        this.color = color;
        paint.setColor(color);
        invalidate();
    }
}
