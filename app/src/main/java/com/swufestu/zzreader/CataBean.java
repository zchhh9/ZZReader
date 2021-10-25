package com.swufestu.zzreader;

public class CataBean {

    private String catainfo;
    private String catachoose;

    public CataBean(String catainfo, String catachoose) {
        this.catainfo = catainfo;
        this.catachoose = catachoose;
    }

    public String getCatainfo() {
        return catainfo;
    }

    public void setCatainfo(String catainfo) {
        this.catainfo = catainfo;
    }

    public String getCatachoose() {
        return catachoose;
    }

    public void setCatachoose(String catachoose) {
        this.catachoose = catachoose;
    }

    public String toString() {
        return "NovelBean{" +
                "catainfo'" + catainfo + '\'' +
                ", catachoose'" + catachoose + '\'' +
                '}';
    }




}
