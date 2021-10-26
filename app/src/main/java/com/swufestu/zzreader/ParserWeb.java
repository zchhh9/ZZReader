package com.swufestu.zzreader;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParserWeb {

    private static final String TAG="ParseWeb";
    private static NovalContentBean novalContentBean;

    //解析书籍开始阅读地址
    public static String parser_web(String url) {
        String attr="";
        Log.i(TAG, "parser_web: "+url);
        try {
            Document document = Jsoup.connect(url).ignoreContentType(true).get();
            Elements book_btn=document.select("div.btn-group");
            attr=book_btn.first().getElementsByTag("a").attr("href");

        } catch (IOException e) {
            e.printStackTrace();
        }
        return attr;
    }
//解析文章内容，返回完整的文章对象
    public static NovalContentBean parser_nol(String url) {
        NovalContentBean novalcon=new NovalContentBean();
        List<String> con = new ArrayList<>();
        try {
            Document novel=Jsoup.connect(url).get();
            Element read_con=novel.select("div.rw_3").first();

            String title=read_con.select("div.title_txtbox").first().text();
            novalcon.setTitle(title);
            Log.i(TAG, "parser_nol: Title"+title);
            String novel_name="小说："+read_con.select("div.reader_crumb").first().getElementsByTag("a").last().text();
            novalcon.setNovel_name(novel_name);
            Log.i(TAG, "parser_nol: Name"+novel_name);
            String author="作者："+read_con.select("div.bookinfo").first().getElementsByTag("a").get(0).text();
            novalcon.setAuthor(author);
            Log.i(TAG, "parser_nol: Author"+author);
            String time="更新时间："+read_con.select("div.bookinfo").first().getElementsByTag("span").last().text();
            String number="本章字数："+read_con.select("div.bookinfo").first().getElementsByTag("span").first().text();
            novalcon.setTime(time);
            novalcon.setWdnumber(number);
            Log.i(TAG, "parser_nol: time:"+time);
            Log.i(TAG, "parser_nol: number:"+number);
            Elements select1=read_con.select("div.content").first().getElementsByTag("p");
            for(Element ele:select1){
                String p=ele.text()+'\n';
                Log.i(TAG, "parser_nol: p:"+p);
                con.add(p);
            }
            /*for(Element ele:select1){
                String p=ele.getElementsByTag("p").text();
                p=p+"\n";
                Log.i(TAG, "parser_nol: p:"+p);
                con.add(p);
                Log.i(TAG, "parser_nol: con:"+con.size());
            }*/
            novalcon.setNv_content(con);
            String wholecon=StringUtils.join(con.toArray(), "  ");
            Log.i(TAG, "parser_nol: wholecon"+wholecon);
            novalcon.setWholecon(wholecon);
            Log.i(TAG, "parser_nol: con:"+con);
            //Log.i(TAG, "parser_nol: content"+con.toString()+"\n");

            String pre="";
            String next="";
            Elements select2=read_con.select("div.chap_btnbox");
            Elements elements=select2.first().getElementsByTag("a");
            String cata=elements.first().attr("href");
            Log.i(TAG, "parser_nol: Cata:"+cata);

            elements.remove(0);
            Element select3=elements.first();
            if(!(select3.attr("href").equals("javascript:void(0)"))){
                pre=select3.attr("href");
            }
            Element select4=elements.select("a.nextchapter").first();
            if(!(select4.attr("href").equals("javascript:void(0)"))){
                next=select4.attr("href");
            }
            Log.i(TAG, "parser_nol: pre："+pre);
            Log.i(TAG, "parser_nol: next："+next);
            novalcon.setPre_link(pre);
            novalcon.setNext_link(next);
            novalcon.setCata_link(cata);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return novalcon;
    }
}
