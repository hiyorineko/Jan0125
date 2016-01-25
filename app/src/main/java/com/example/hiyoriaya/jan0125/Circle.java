package com.example.hiyoriaya.jan0125;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.Random;

/**
 * Created by SNPC2022 on 2016/01/25.
 */
public class Circle extends Item{

    private float mx,my,mr;
    private int mred;
    private int mgreen;
    private int mblue;
    public float mspeed;

    Circle(){
        mx = 0;
        my = 0;
        mr = 0;
        mred = 0;
        mgreen = 0;
        mblue = 0;
        mspeed = 0;
    }

    Circle(float x,float y,float r){
        Random ran = new Random();
        mx = x;
        my = y;
        mr = r;
        mred = ran.nextInt(256);
        mgreen = ran.nextInt(256);
        mblue = ran.nextInt(256);
        mspeed = ran.nextFloat()*10;
    }
    @Override
    public void onDraw(Canvas c,Paint p){
        p.setColor(Color.rgb(mred,mgreen,mblue));
        my += mspeed;
        c.drawCircle(mx,my,mr,p);
        if(my>1400){
            my=0;
        }
    }

}
