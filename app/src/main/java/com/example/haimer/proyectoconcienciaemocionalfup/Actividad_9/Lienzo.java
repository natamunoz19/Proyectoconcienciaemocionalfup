package com.example.haimer.proyectoconcienciaemocionalfup.Actividad_9;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import android.graphics.Path;

public class Lienzo extends View {

    public Lienzo(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    float x=50;
    float y=50;

    String accion ="";
    Path path = new Path();





    public  void onDraw(Canvas canvas){

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        paint.setColor(Color.BLUE);

        int ancho = canvas.getWidth();

        if(accion=="down"){
            path.moveTo(x,y);

        }

        if(accion=="move"){
            path.lineTo(x,y);
        }

        // canvas.drawRect(10,70,ancho-10,20,paint);
        canvas.drawPath(path,paint);

    }


    public  boolean onTouchEvent(MotionEvent e){
        x=e.getX();
        y=e.getY();

        if(e.getAction()== MotionEvent.ACTION_DOWN){
            accion="down";
        }
        if (e.getAction()== MotionEvent.ACTION_MOVE) {
            accion="move";
        }
        invalidate();
        return true;

    }

}
