package com.example.drowartaiv3;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;

public class ImageAdapterAnime extends BaseAdapter {

    private ArrayList<DataClass> dataList;
    private Context context;
    LayoutInflater layoutInflater;

    public ImageAdapterAnime(ArrayList<DataClass> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return dataList.size();
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
        if(layoutInflater == null){
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView == null){
            convertView = layoutInflater.inflate(R.layout.grid_item, null);
        }
        ImageView gridImage = convertView.findViewById(R.id.dridImage);



        gridImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Проверка, является ли позиция первой картинкой
                if (position == 0) {
                    Intent intent = new Intent(context, WebViewActivity.class);
                    // Передача URL в WebViewActivity, если у вас есть необходимые данные
                    intent.putExtra("url", "file:///android_asset/mobile.html");
                    context.startActivity(intent);
                }
            }
        });




        Glide.with(context).load(dataList.get(position).getImageUrl()).into(gridImage);

        return convertView;
    }

}
