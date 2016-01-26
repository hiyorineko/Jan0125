package com.example.hiyoriaya.jan0125;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by SNPC2022 on 2016/01/26.
 */
public class Ball extends Item{
    private float mX;
    private float mY;
    private float mSpeedX;
    private float mSpeedY;
    private final float mRadius;

    public Ball(float x,float y,float radius){
        mX = x;
        mY = y;
        mSpeedX = radius/100;
        mSpeedY = -radius/100;
        mRadius = radius;
    }

    public void move(){
        mX += mSpeedX;
        mY += mSpeedY;
    }

    public float getSpeedX(){
        return mSpeedX;
    }

    public float getSpeedY(){
        return mSpeedY;
    }

    public float getX(){
        return mX;
    }

    public float getY(){
        return mY;
    }

    public void setSpeedX(float speedX){
        mSpeedX = speedX;
    }

    public void setSpeedY(float speedY){
        mSpeedY = speedY;
    }


    public void onDraw(Canvas c,Paint p){
        p.setColor(Color.WHITE);
        p.setStyle(Paint.Style.FILL);
        c.drawCircle(mX,mY,mRadius,p);
    }


}
