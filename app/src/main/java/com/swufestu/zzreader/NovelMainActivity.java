package com.swufestu.zzreader;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

public class NovelMainActivity extends AppCompatActivity {
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private CardView godnv;
    private CardView twonv;
    private CardView warnv;
    private CardView oldnv;
    private CardView newnv;
    private CardView dream;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novel_main);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        ArrayList<Fragment> datas=new ArrayList<>();
        datas.add(new ManBooksFragment());
        datas.add(new WomanBooksFragment());
        mSectionsPagerAdapter.setDatas(datas);
        mViewPager=(ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        oldnv=(CardView) findViewById(R.id.oldnv);
        newnv=(CardView) findViewById(R.id.newnv);
        dream=(CardView) findViewById(R.id.dreamnv);
        godnv=(CardView) findViewById(R.id.god);
        twonv=(CardView) findViewById(R.id.two);
        warnv=(CardView) findViewById(R.id.war);

        oldnv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(){
                    @Override
                    public void run() {
                        super.run();;
                        String url="http://huayu.zongheng.com/category/32.html";
                        Intent intent=new Intent(NovelMainActivity.this,MainActivity.class);
                        intent.putExtra("firstpath",url);
                        startActivity(intent);
                    }
                }.start();
            }
        });
       /* newnv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(){
                    @Override
                    public void run() {
                        super.run();;
                        String url="http://huayu.zongheng.com/category/31.html";
                        Intent intent=new Intent(NovelMainActivity.this,MainActivity.class);
                        intent.putExtra("firstpath",url);
                        startActivity(intent);
                    }
                }.start();
            }
        });
        dream.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(){
                    @Override
                    public void run() {
                        super.run();;
                        String url="http://huayu.zongheng.com/category/33.html";
                        Intent intent=new Intent(NovelMainActivity.this,MainActivity.class);
                        intent.putExtra("firstpath",url);
                        startActivity(intent);
                    }
                }.start();
            }
        });
        godnv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(){
                    @Override
                    public void run() {
                        super.run();;
                        String url="http://www.zongheng.com/category/3.html";
                        Intent intent=new Intent(NovelMainActivity.this,MainActivity.class);
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
                        Intent intent=new Intent(NovelMainActivity.this,MainActivity.class);
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
                        Intent intent=new Intent(NovelMainActivity.this,MainActivity.class);
                        intent.putExtra("firstpath",url);
                        startActivity(intent);
                    }
                }.start();
            }
        });*/
    }
    //创建自己的Fragment类继承自Fragment
    public static class WomanBooksFragment extends Fragment{
        public WomanBooksFragment(){

        }
        public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
            View rootView=inflater.inflate(R.layout.activity_novel_topic,container,false);
            return rootView;
        }
    }
    public static class ManBooksFragment extends Fragment {
        public ManBooksFragment() {

        }

        public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.activity_man_topic_main, container, false);
            return rootView;
        }
    }
    //创建ViewPager的适配器
    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        private ArrayList<Fragment> datas;

        public SectionsPagerAdapter(FragmentManager fm){
            super(fm);
        }
        public void setDatas(ArrayList<Fragment> datas){
            this.datas=datas;
        }
        public Fragment getItem(int position){
            return datas==null?null:datas.get(position);
        }
        public int getCount(){
            return datas==null?0:datas.size();
        }
    }
}