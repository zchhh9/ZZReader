package com.swufestu.zzreader;

import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

public class ManTopicMainActivity extends AppCompatActivity implements View.OnTouchListener, GestureDetector.OnGestureListener {
    private CardView godnv;
    private CardView twonv;
    private CardView warnv;
    private CardView gamenv;
    private CardView urbannv;
    private ConstraintLayout rl;
    private  GestureDetector gd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_topic_main);
        godnv=(CardView) findViewById(R.id.god);
        twonv=(CardView) findViewById(R.id.two);
        warnv=(CardView) findViewById(R.id.war);
        gamenv=(CardView)findViewById(R.id.game);
        urbannv=(CardView)findViewById(R.id.urban);

        rl=(ConstraintLayout)findViewById(R.id.drawer2);
        rl.setOnTouchListener(this);
        rl.setLongClickable(true);
        gd=new GestureDetector((GestureDetector.OnGestureListener) this);

        godnv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(){
                    @Override
                    public void run() {
                        super.run();;
                        String url="http://www.zongheng.com/category/3.html";
                        Intent intent=new Intent(ManTopicMainActivity.this,MainActivity.class);
                        intent.putExtra("firstpath",url);
                        startActivity(intent);
                    }
                }.start();
            }
        });
        twonv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(){
                    @Override
                    public void run() {
                        super.run();;
                        String url="http://www.zongheng.com/category/40.html";
                        Intent intent=new Intent(ManTopicMainActivity.this,MainActivity.class);
                        intent.putExtra("firstpath",url);
                        startActivity(intent);
                    }
                }.start();
            }
        });
        warnv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(){
                    @Override
                    public void run() {
                        super.run();;
                        String url="http://www.zongheng.com/category/6.html";
                        Intent intent=new Intent(ManTopicMainActivity.this,MainActivity.class);
                        intent.putExtra("firstpath",url);
                        startActivity(intent);
                    }
                }.start();
            }
        });
        gamenv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(){
                    @Override
                    public void run() {
                        super.run();;
                        String url="http://www.zongheng.com/category/15.html";
                        Intent intent=new Intent(ManTopicMainActivity.this,MainActivity.class);
                        intent.putExtra("firstpath",url);
                        startActivity(intent);
                    }
                }.start();
            }
        });
        urbannv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(){
                    @Override
                    public void run() {
                        super.run();;
                        String url="http://www.zongheng.com/category/9.html";
                        Intent intent=new Intent(ManTopicMainActivity.this,MainActivity.class);
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
        if(e1.getX() - e2.getX() > FLING_MIN_DISTANCE&& Math.abs(velocityX) > FLING_MIN_VELOCITY){
            Intent intent = new Intent(ManTopicMainActivity.this,NovelTopicActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_right);
            finish();
        }
        //右
        /*if(e1.getX() - e2.getX() < FLING_MIN_DISTANCE && Math.abs(velocityX) < FLING_MIN_VELOCITY){
            Intent intent = new Intent(ManTopicMainActivity.this,NovelTopicActivity.class);
            startActivity(intent);
        }*/

        return false;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return gd.onTouchEvent(event);
    }

}