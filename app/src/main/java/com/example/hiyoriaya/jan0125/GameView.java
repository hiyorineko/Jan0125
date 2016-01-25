package com.example.hiyoriaya.jan0125;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.SurfaceTexture;
import android.view.MotionEvent;
import android.view.TextureView;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by SNPC2022 on 2016/01/25.
 */
public class GameView extends TextureView implements TextureView.SurfaceTextureListener, View.OnTouchListener{

    volatile private boolean mIsRunnable;
    private Thread mThread;
    private ArrayList<Item> items;
    private float mTouchedX;
    private float mTouchedY;
    private boolean touching;
    private final Context c;

    public GameView(Context context) {
        super(context);
        c=context;
        setSurfaceTextureListener(this);
        readyitems();
        setOnTouchListener(this);
    }

    public void readyitems(){
        items = new ArrayList<Item>();
        Random r = new Random();
        items.add(new User(c));
        items.add(new Beam());
        for(int i=0;i<99;i++){
            items.add(new Circle(i*r.nextInt(50),0,50));
        }
    }

    public void start(){
        mThread = new Thread(new Runnable() {
            @Override
            public void run() {
                Paint paint = new Paint();
                Random r = new Random();
                paint.setStyle(Paint.Style.FILL);
                int i=0;
                while(mIsRunnable) {
                    Canvas canvas = lockCanvas();
                    if (canvas == null) {
                        continue;
                    }
                    canvas.drawColor(Color.BLACK);
                    for(int j=0;j<items.size();j++){
                        items.get(j).onDraw(canvas, paint);
                        if(touching) {
                            items.get(0).onTouch(canvas, paint, mTouchedX,mTouchedY);
                        }else{
                            items.get(0).onDraw(canvas,paint);
                        }
                    }
                    unlockCanvasAndPost(canvas);
                    i++;
                    if(i>1000){
                        i=0;
                    }
                }
            }
        });
        mIsRunnable = true;
        mThread.start();
    }

    public void stop(){
        mIsRunnable = false;
    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {

    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        return false;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_DOWN) {
            touching = true;
            mTouchedX = event.getX();
            mTouchedY = event.getY();
        }else if(event.getAction() == MotionEvent.ACTION_UP){
            touching = false;
        }else if(event.getAction() == MotionEvent.ACTION_MOVE){
            mTouchedX = event.getX();
            mTouchedY = event.getY();
        }
        return true;
    }
}
