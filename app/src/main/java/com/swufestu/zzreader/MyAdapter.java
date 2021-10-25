package com.swufestu.zzreader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

/*public class MyAdapter extends BaseAdapter {
    String TAG="MyAdapter";
    private Context context;
    private LayoutInflater inflater;
    private List<CataBean> catas =new ArrayList<>();
    public MyAdapter(Context context, List<CataBean> catas) {
        this.context=context;
        this.catas=catas;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return catas.size();
    }

    @Override
    public Object getItem(int position) {
        return catas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
      ViewHolder viewHolder ;
        if(convertView==null){
            Log.i(TAG, "getView: converVier 不为空");
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.novel_item,parent,false);
            viewHolder.catainfo=(TextView) convertView.findViewById(R.id.cataitem);
            viewHolder.catachoose=(TextView)convertView.findViewById(R.id.catachoose);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.catainfo.setText(catas.get(position).getCatainfo());
        viewHolder.catachoose.setText("start");
        return convertView;
    }
    class ViewHolder{
        TextView catainfo;
        TextView catachoose;
}

}*/
public class MyAdapter extends ArrayAdapter {
    //ArrayList<HashMap<String,String>>
    public MyAdapter(@NonNull Context context, int resource, @NonNull ArrayList<CataBean> data) {
        super(context, resource,data);
    }
    @NonNull
    @Override
    public View getView(int position, @NonNull View convertView, @NonNull ViewGroup parent){
        View itemView = convertView;
        if(itemView==null){
            itemView= LayoutInflater.from(getContext()).inflate(R.layout.cata_item,
                    parent,
                    false);
        }
        CataBean item=(CataBean) getItem(position);
        TextView title = (TextView) itemView.findViewById(R.id.cataitem);
        TextView detail= (TextView) itemView.findViewById(R.id.catachoose);

        title.setText(item.getCatainfo());
        detail.setText("");

        return itemView;
    }
}