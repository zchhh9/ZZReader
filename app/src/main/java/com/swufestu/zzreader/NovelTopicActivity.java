package com.swufestu.zzreader;

import static android.view.View.OnClickListener;
import static android.view.View.OnTouchListener;

import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

public class NovelTopicActivity extends AppCompatActivity implements View.OnTouchListener, GestureDetector.OnGestureListener{
private CardView oldnv;
private CardView newnv;
private CardView dream;
private ConstraintLayout rll;
private GestureDetector gdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novel_topic);
        oldnv=(CardView) findViewById(R.id.oldnv);
        newnv=(CardView) findViewById(R.id.newnv);
        dream=(CardView) findViewById(R.id.dreamnv);

        rll=(ConstraintLayout)findViewById(R.id.drawer);
        rll.setOnTouchListener((OnTouchListener) this);
        rll.setLongClickable(true);
        gdd=new GestureDetector((GestureDetector.OnGestureListener)this);


        oldnv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(){
                    @Override
                    public void run() {
                        super.run();;
                        String url="http://huayu.zongheng.com/category/32.html";
                        Intent intent=new Intent(NovelTopicActivity.this,MainActivity.class);
                        intent.putExtra("firstpath",url);
                        startActivity(intent);
                    }
                }.start();
            }
        });
        newnv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(){
                    @Override
                    public void run() {
                        super.run();;
                        String url="http://huayu.zongheng.com/category/31.html";
                        Intent intent=new Intent(NovelTopicActivity.this,MainActivity.class);
                        intent.putExtra("firstpath",url);
                        startActivity(intent);
                    }
                }.start();
            }
        });
        dream.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(){
                    @Override
                    public void run() {
                        super.run();;
                        String url="http://huayu.zongheng.com/category/33.html";
                        Intent intent=new Intent(NovelTopicActivity.this,MainActivity.class);
                        intent.putExtra("firstpath",url);
                        startActivity(intent);
                    }
                }.start();
            }
        });
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        final int FLING_MIN_DISTANCE=100;
        final int FLING_MIN_VELOCITY=200;

        //左
        /*if(e1.getX() - e2.getX() > FLING_MIN_DISTANCE && Math.abs(velocityX) > FLING_MIN_VELOCITY){
            Intent intent = new Intent(NovelTopicActivity.this,ManTopicMainActivity.class);
            startActivity(intent);
        }*/
        //右
        if(e2.getX() - e1.getX() > FLING_MIN_DISTANCE && Math.abs(velocityX) > FLING_MIN_VELOCITY) {
            Intent intent = new Intent(NovelTopicActivity.this,ManTopicMainActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);
            finish();
        }

        return false;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return gdd.onTouchEvent(event);
    }

}



