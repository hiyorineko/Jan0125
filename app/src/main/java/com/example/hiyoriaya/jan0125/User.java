package com.example.hiyoriaya.jan0125;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by SNPC2022 on 2016/01/25.
 */
public class User extends Item{

    private Bitmap bmp;
    private float mx,my,mr;

    public User(Context context){
        Resources r = context.getResources();
        bmp = BitmapFactory.decodeResource(r, R.drawable.ziki);
        mx = 0;
        my = 1000;
        mr = 0;
    }

    @Override
    public void onDraw(Canvas c,Paint p){
        c.drawBitmap(bmp, mx, my, p);
    }

    @Override
    public void onTouch(Canvas c,Paint p,float x,float y){
        mx = x;
        c.drawBitmap(bmp,mx,my,p);
    }
}
