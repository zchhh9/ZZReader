package com.swufestu.zzreader;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class CataActivity extends AppCompatActivity {
    private static final String TAG="CataActivity";
    private ArrayList<CataBean> catas;
    private ListView listView ;
    private String cataurl;
    private String nname;
    TextView name;
    MyAdapter myAdapter;
   Handler mhandler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if(msg.what==91){
                myAdapter=new MyAdapter(CataActivity.this,
                        R.layout.cata_item,
                        catas);
                listView.setAdapter(myAdapter);
                //listView.setAdapter(new MyAdapter(CataActivity.this,catas));
                //Log.i(TAG, "handleMessage: catas"+catas);
                //获取目录列表成功，更新listView
                Log.i(TAG, "handleMessage: 更新list");
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cata);
        listView=(ListView) findViewById(R.id.catalist);
        cataurl=getIntent().getStringExtra("catapath");
        Log.i(TAG, "onCreate: catapath:"+cataurl);
        name=(TextView) findViewById(R.id.aboutname);
    }
    protected void onResume(){
        super.onResume();
        //从网络爬取数据
        http_getnovel();
        //list2的item点击事件
       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int position, long id) {
                Log.i(TAG, "onItemClick: 点击的位置为:"+position);
                new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        CataBean cataBean=catas.get(position);
                        String url=cataBean.getCatachoose();
                        Intent intent=new Intent(CataActivity.this,ConActivity.class);
                        intent.putExtra("path",url);
                        Log.i(TAG, "run:path"+url);
                        startActivity(intent);
                        finish();
                    }
                }.start();
            }
        });
    }
    private void http_getnovel(){
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    //获取连接
                    Connection connect= Jsoup.connect(cataurl).ignoreContentType(true);
                    //设置超时
                    connect.timeout(10000);
                    Document document=connect.get();
                    Log.i(TAG, "run: title="+document.title());
                    nname = document.select("div.book-meta").first().getElementsByTag("h1").first().text();
                    //name.setText(nname);
                    Elements detail=document.getElementsByTag("li");
                    detail.remove(0);
                    detail.remove(0);
                    detail.remove(0);
                    catas = new ArrayList<>();
                    for(Element dd:detail){
                        String catainfo=dd.getElementsByTag("a").text();
                        String catachoose=dd.getElementsByTag("a").attr("href");
                        CataBean cata=new CataBean(catainfo,catachoose);
                        catas.add(cata);
                        //Log.i(TAG, "run: 章节名称:"+catainfo);
                        //Log.i(TAG, "run: 连接:"+catachoose);
                        //Log.i(TAG, "run: cata2222"+cata);
                    }
                    mhandler.sendEmptyMessage(91);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                CataActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        name.setText(nname);
                    }
                });

            }
        }.start();
    }


}
