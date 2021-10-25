package com.swufestu.zzreader;

import java.util.List;

public class NovalContentBean {

    private String title;
    private String novel_name;
    private String author;
    private String time;
    private String wdnumber;
    private List<String> nv_content;
    private String pre_link;
    private String next_link;
    private String cata_link;
    private String wholecon;
    public NovalContentBean(){

    }

    public NovalContentBean(String author, String next_link, String novel_name, List<String> nv_content, String pre_link, String title, String time, String wdnumber0) {
        this.author = author;
        this.next_link = next_link;
        this.novel_name = novel_name;
        this.nv_content = nv_content;
        this.pre_link = pre_link;
        this.title = title;
        this.time = time;
        this.wdnumber = wdnumber;
        this.cata_link=cata_link;
        this.wholecon=wholecon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNovel_name() {
        return novel_name;
    }

    public void setNovel_name(String novel_name) {
        this.novel_name = novel_name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getWdnumber() {
        return wdnumber;
    }

    public void setWdnumber(String wdnumber) {
        this.wdnumber = wdnumber;
    }

    public List<String> getNv_content() {
        return nv_content;
    }

    public void setNv_content(List<String> nv_content) {
        this.nv_content = nv_content;
    }

    public String getPre_link() {
        return pre_link;
    }

    public void setPre_link(String pre_link) {
        this.pre_link = pre_link;
    }

    public String getNext_link() {
        return next_link;
    }

    public void setNext_link(String next_link) {
        this.next_link = next_link;
    }

    public String getCata_link() {
        return cata_link;
    }

    public void setCata_link(String cata_link) {
        this.cata_link = cata_link;
    }

    public String getWholecon() {
        return wholecon;
    }

    public void setWholecon(String wholecon) {
        this.wholecon = wholecon;
    }

    @Override
    public String toString() {
        return "NovalContentBean{" +
                "author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", novel_name='" + novel_name + '\'' +
                ", time='" + time + '\'' +
                ", wdnumber='" + wdnumber + '\'' +
                ", nv_content=" + nv_content +
                ", pre_link='" + pre_link + '\'' +
                ", next_link='" + next_link + '\'' +
                '}';
    }

}
