package com.example.hiyoriaya.jan0125;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by SNPC2022 on 2016/01/25.
 */
public class Beam extends Item{

    private int speed;

    public Beam(){

    }

    @Override
    public void onDraw(Canvas c,Paint p){

    }

    @Override
    public void onTouch(Canvas c,Paint p,float x,float y){
        p.setColor(Color.YELLOW);
        c.drawLine(x + 70, 1000, x + 70, 1000-y, p);

    }
}
