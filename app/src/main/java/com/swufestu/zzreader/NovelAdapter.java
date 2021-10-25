package com.swufestu.zzreader;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

//listView的列表
public class NovelAdapter extends BaseAdapter {
    private Context context;
    private  String TAG="NovelAdapter";
    private List<NovelBean> novels =new ArrayList<>();
    private LayoutInflater inflater;
    private DisplayImageOptions options;

    public NovelAdapter(Context context, List<NovelBean> novels, DisplayImageOptions options) {
        this.context=context;
        this.novels=novels;
        this.options=options;
        inflater=LayoutInflater.from(context);
    }

    /*public NovelAdapter(MainActivity context, int resource, @NonNull ArrayList<NovelBean> data) {
        super(context, resource,data);
    }*/

    @Override
    public int getCount() {
        return novels.size();
    }

    @Override
    public Object getItem(int position) {
        return novels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder ;
        if(convertView==null){
            Log.i(TAG, "getView: converVier 不为空");
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.novel_item,parent,false);
            viewHolder.imageView=(ImageView) convertView.findViewById(R.id.novel_image);
            viewHolder.name=(TextView) convertView.findViewById(R.id.name);
            viewHolder.type=(TextView) convertView.findViewById(R.id.type);
            viewHolder.author=(TextView) convertView.findViewById(R.id.author);
            viewHolder.path=(TextView) convertView.findViewById(R.id.path);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //通过库加载图片
        ImageLoader.getInstance().displayImage(novels.get(position).getNovel_image(),viewHolder.imageView,options);
        viewHolder.name.setText(novels.get(position).getNovel_name());
        viewHolder.author.setText(novels.get(position).getNovel_author());
        viewHolder.type.setText(novels.get(position).getNovel_type());
        viewHolder.path.setText("");
        Log.i(TAG, "getView: img"+novels.get(position).getNovel_image());
        Log.i(TAG, "getView: author"+novels.get(position).getNovel_author());
        Log.i(TAG, "getView: type"+novels.get(position).getNovel_type());
        Log.i(TAG, "getView: name"+novels.get(position).getNovel_name());
        Log.i(TAG, "getView: path"+novels.get(position).getNovel_path());
        return convertView;
    }
    class ViewHolder{
        ImageView imageView;
        TextView name;
        TextView author;
        TextView type;
        TextView path;
    }
}
