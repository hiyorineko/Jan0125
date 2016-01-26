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
    private ArrayList<Circle> circles;
    private float mTouchedX;
    private float mTouchedY;
    private boolean touching;
    private final Context c;
    private float mBallRadius;
    private User mUser;
    private Ball mBall;

    public GameView(Context context) {
        super(context);
        c=context;
        setSurfaceTextureListener(this);
        setOnTouchListener(this);
    }

    public void readyitems(int width,int height){
        mUser = new User(c);
        mBallRadius = width < height ? width / 40 : height /40;
        mBall = new Ball(100,1000,mBallRadius);

        circles = new ArrayList<Circle>();
        Random r = new Random();
        for(int i=0;i<100;i++){
            circles.add(new Circle(i * r.nextInt(50), 0, 50));
        }
    }

    public void start(){
        mThread = new Thread(new Runnable() {
            @Override
            public void run() {
                Paint paint = new Paint();
                Random r = new Random();
                int i=0;
                while(mIsRunnable) {
                    long startTime = System.currentTimeMillis();
                    Canvas canvas = lockCanvas();
                    if (canvas == null) {
                        continue;
                    }
                    canvas.drawColor(Color.BLACK);
                    for(int j=0;j<circles.size();j++){
                        circles.get(j).onDraw(canvas, paint);
                        if(touching) {
                            mUser.onTouch(canvas, paint, mTouchedX,mTouchedY);
                        }else{
                            mUser.onDraw(canvas,paint);
                        }
                        mBall.move();
                        hit();
                        mBall.onDraw(canvas,paint);
                    }
                    unlockCanvasAndPost(canvas);
                    i++;
                    if(i>1000){
                        i=0;
                    }
                    long sleeptime = 16 - System.currentTimeMillis() + startTime;
                    if(sleeptime > 0){
                        try{
                            Thread.sleep(sleeptime);
                        }catch (InterruptedException e){}
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

    public void hit(){

        //壁ヒット時
        float ballTop = mBall.getY() - mBallRadius;
        float ballLeft = mBall.getX() - mBallRadius;
        float ballBottom = mBall.getY() + mBallRadius;
        float ballRight = mBall.getX() + mBallRadius;

        if(ballLeft < 0 && mBall.getSpeedX() < 0 || ballRight >= getWidth() && mBall.getSpeedX()>0){
            mBall.setSpeedX(-mBall.getSpeedX());
        }
        if(ballTop <0 || ballBottom > getHeight()){
            mBall.setSpeedY(-mBall.getSpeedY());
        }

        //泡ヒット時
       // for(int i=0;i<circles.size();i++){
        //    float circleTop = circles.get(i).getY() - circles.get(i).getRadius();
         //   float circleLeft = circles.get(i).getX() - circles.get(i).getRadius();
         //   float circleBottom = circles.get(i).getY() + circles.get(i).getRadius();
         //   float circleRight = circles.get(i).getX() + circles.get(i).getRadius();
         //   if(ballLeft < circleRight|| ballRight >= circleLeft){
         //       if(ballTop > circleBottom || ballBottom < circleTop) {
         //           mBall.setSpeedX(-mBall.getSpeedX());
         //           circles.get(i).collision();
         //           break;
          //      }
          // }
       // }

    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
        readyitems(width,height);
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
        readyitems(width,height);
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
