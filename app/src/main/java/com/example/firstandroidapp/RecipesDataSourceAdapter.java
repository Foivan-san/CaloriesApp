package com.example.firstandroidapp;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class RecipesDataSourceAdapter extends BaseAdapter {

    private Context myContext;
    private LayoutInflater myInflater;
    private RecipesDataSource myDataSource;

    public RecipesDataSourceAdapter(Context ctx, RecipesDataSource ds){
        myContext = ctx;
        myDataSource = ds;
        myInflater = (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return myDataSource.getDataSourceLength();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView thumbnail;
        TextView name;

        if (convertView == null)
            convertView = myInflater.inflate(R.layout.list_item_layout, parent,false);
        thumbnail = (ImageView)convertView.findViewById(R.list.thumb);
        thumbnail.setImageResource(myDataSource.getPhotoPool().get(position));
        name = (TextView)convertView.findViewById(R.list.text);
        name.setText(myDataSource.getDishesPool().get(position));

        return convertView;
    }
}
