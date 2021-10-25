package com.swufestu.zzreader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;


//文章类容
public class ConActivity extends Activity {
    private NovalContentBean novalContentBean;
    private TextView tv_title;
    private TextView tv_name;
    private TextView tv_author;
    private TextView tv_time;
    private TextView tv_wd;
    private TextView tv_con;
    private Button bt_pre;
    private Button bt_next;
    private Button bt_cata;
    private String TAG="ConActivity";
    //更新文章内容
    private Handler mhandler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what==111){
                tv_title.setText(novalContentBean.getTitle());
                tv_name.setText(novalContentBean.getNovel_name());
                tv_author.setText(novalContentBean.getAuthor());
                tv_time.setText(novalContentBean.getTime());
                tv_wd.setText(novalContentBean.getWdnumber());
                tv_con.setText(novalContentBean.getWholecon());
                //如果上一章不存在
                if(novalContentBean.getPre_link()==""||novalContentBean.getPre_link()==null){
                    bt_pre.setVisibility(View.INVISIBLE);
                    bt_pre.setClickable(false);
                    Log.i(TAG, "handleMessage: 上一章不显示");
                }
                //如果下一章不存在
                if(novalContentBean.getNext_link()==""||novalContentBean.getNext_link()==null){
                    bt_next.setVisibility(View.INVISIBLE);
                    bt_next.setClickable(false);
                    Log.i(TAG, "handleMessage: 下一章不显示");
                }
                //上一章的点击事件
                bt_pre.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(ConActivity.this,ConActivity.class);
                        intent.putExtra("path",novalContentBean.getPre_link());
                        startActivity(intent);
                        finish();
                    }
                });
                //下一章点击事件
                bt_next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(ConActivity.this,ConActivity.class);
                        intent.putExtra("path",novalContentBean.getNext_link());
                        startActivity(intent);
                        finish();
                    }
                });
                //目录点击事件
                bt_cata.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(ConActivity.this,CataActivity.class);
                        intent.putExtra("catapath",novalContentBean.getCata_link());
                        startActivity(intent);
                        finish();
                    }
                });
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_con);
        TextView textView = (TextView)this.findViewById(R.id.tv_con);
        textView.setMovementMethod(ScrollingMovementMethod.getInstance());
        Log.i(TAG, "onCreate: 已经进入下一个页面");
        tv_title=(TextView) findViewById(R.id.tv_title);
        tv_name=(TextView) findViewById(R.id.tv_name);
        tv_author=(TextView) findViewById(R.id.tv_author);
        tv_time=(TextView) findViewById(R.id.tv_time);
        tv_wd=(TextView) findViewById(R.id.tv_wd);
        tv_con=(TextView) findViewById(R.id.tv_con);
        bt_pre=(Button) findViewById(R.id.bt_pre);
        bt_next=(Button) findViewById(R.id.bt_next);
        bt_cata=(Button) findViewById(R.id.catalogue);
        final String path=getIntent().getStringExtra("path");
        Log.i(TAG, "onCreate: 得到路径为："+path);
        new Thread(){
            @Override
            public void run() {
                super.run();
                novalContentBean=ParserWeb.parser_nol(path);
                if(novalContentBean!=null){
                    mhandler.sendEmptyMessage(111);
                }
            }
        }.start();
    }
}