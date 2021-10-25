package com.swufestu.zzreader;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


//主界面，通过Jsoup爬虫来获取小说
public class MainActivity extends AppCompatActivity {

    private static final String TAG="MainActivity";
    private List<NovelBean> novels;
    private ListView listView;
    private String fitstpath;
    Handler mhandler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if(msg.what==22){
                //获取文章列表成功，更新listView
                Log.i(TAG, "handleMessage: eeeee"+novels);
                listView.setAdapter(new NovelAdapter(MainActivity.this,novels,options));
            }
        }
    };
    private Button get_;
    private DisplayImageOptions options;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fitstpath=getIntent().getStringExtra("firstpath");
        //初始化ImageLoader
        ImageLoaderConfiguration configuration=new ImageLoaderConfiguration.Builder(this)
                .diskCacheFileCount(100)//缓存的文件数量
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())////将保存的时候的URI名称用MD5加密
                .diskCacheSize(30*1024*1024)////设置磁盘缓存大小30M
                .tasksProcessingOrder(QueueProcessingType.LIFO)////设置图片下载和显示的工作队列排序
                .build();//开始构建
        Log.i(TAG, "onCreate: ImageLoader初始化完成");
        //初始化Options
        options= new DisplayImageOptions.Builder()
                .cacheInMemory(true)//设置下载的图片是否缓存在内存中
                .cacheOnDisk(true)//设置下载的图片是否缓存在SD卡中
                .imageScaleType(ImageScaleType.EXACTLY)//设置图片以如何的编码方式显示
                .displayer(new RoundedBitmapDisplayer(10))//设置圆角图片
                .build();
        ImageLoader.getInstance().init(configuration);
        listView=(ListView) findViewById(R.id.book_content);
        Log.i(TAG, "onCreate: Option初始化完成");
    }
    protected void onResume(){
        super.onResume();
        //从网络爬取数据
        http_getnovel();
        //listViewd的item点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int position, long id) {
                Log.i(TAG, "onItemClick: 点击的位置为:"+position);
                new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        NovelBean novelBean=novels.get(position);
                        String url=novelBean.getNovel_path();
                        String path=ParserWeb.parser_web(url);
                        Intent intent=new Intent(MainActivity.this,ConActivity.class);
                        intent.putExtra("path",path);
                        startActivity(intent);
                    }
                }.start();
            }
        });

    }
    //    <div class="index_tpic_info" >
//    <h3><a class="title" href="http://huayu.zongheng.com/book/1144179.html" title="帝君独宠小魔女" target="_blank" data-sa-d='{"page_module":"flowerGirlCategoryDetail","book_id":"1144179","recommend_type":"topBanner","pos":"2","category_id":"33","category_name":"幻想时空"}'>帝君独宠小魔女</a></h3>
//    <div class="author"><span>作者</span><a href="http://home.zongheng.com/show/userInfo/52921302.html" target="_blank">北归鸿</a></div>
//    <div class="cate"><span>类型</span><a href="http://www.zongheng.com/store/c33/c3101/b0/u0/p1/v9/s9/t0/u0/i0/ALL.html" target="_blank" data-sa-d='{"page_module":"flowerGirlCategoryDetail","book_id":"1144179","recommend_type":"topBanner","pos":"2","category_id":"33","category_name":"幻想时空"}'>奇幻玄幻</a></div>
//    <p class="info"><a title="一颗小魔石万丈渊底求千年，换来三世为人，却因他连死两世！" target="_blank" data-ack="100_15|100">一颗小魔石万丈渊底求千年，换来三世为人，却因他连死两世！</a></p>
//    </div>
    private void http_getnovel(){
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    //获取连接
                    //Connection connect= Jsoup.connect("http://huayu.zongheng.com/category/33.html").ignoreContentType(true);
                    Connection connect=Jsoup.connect(fitstpath).ignoreContentType(true);
                    //设置超时
                    connect.timeout(10000);
                    Document document=connect.get();
                    Log.i(TAG, "run: title="+document.title());
                    Elements detail=document.select("div.mind-book");
                    novels = new ArrayList<>();
                    for(Element dd:detail){
                        String novel_img=dd.getElementsByTag("img").first().attr("src");
                        String novel_name=dd.select("div.bookname").first().getElementsByTag("a").text();
                        String novel_author="作者："+dd.select("div.author").first().getElementsByTag("a").text();
                        String novel_type="简介："+dd.getElementsByTag("p").first().text();
                        if(novel_type.length()>38){
                            novel_type=novel_type.substring(0, 38)+"...";
                        }
                        String novel_path=dd.select("div.bookname").first().getElementsByTag("a").attr("href");
                        Log.i(TAG, "run: 小说名称:"+novel_name);
                        Log.i(TAG, "run: 小说类型:"+novel_type);
                        Log.i(TAG, "run: 小说作者:"+novel_author);
                        Log.i(TAG, "run: 小说路径:"+novel_path);
                        Log.i(TAG, "run: 小说图片:"+novel_img);
                        NovelBean novel=new NovelBean(novel_name,novel_type,novel_author,novel_path,novel_img);
                        novels.add(novel);
                    }
                    mhandler.sendEmptyMessage(22);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

}