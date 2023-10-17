package com.example.stressmeter.ui.home;

import android.content.Context;

import android.view.View;
import android.view.ViewGroup;

import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.stressmeter.R;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    // Constructor
    public ImageAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return images[0].length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;

        if (convertView == null) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(150, 150));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }
        else {
            imageView = (ImageView) convertView;
        }
        imageView.setImageResource(images[0][position]);
        return imageView;
    }

    // Keep all Images in array
    public int[][] images = {{
        R.drawable.psm_alarm_clock, R.drawable.psm_alarm_clock2, R.drawable.psm_bar, R.drawable.psm_anxious,
        R.drawable.psm_baby_sleeping, R.drawable.psm_bar, R.drawable.psm_barbed_wire2, R.drawable.psm_beach3,
        R.drawable.psm_bird3, R.drawable.psm_blue_drop, R.drawable.psm_cat, R.drawable.psm_clutter,
        R.drawable.psm_clutter3, R.drawable.psm_dog_sleeping, R.drawable.psm_exam4, R.drawable.psm_gambling4,
    }, {
        R.drawable.psm_headache, R.drawable.psm_headache2, R.drawable.psm_hiking3, R.drawable.psm_kettle,
        R.drawable.psm_lake3, R.drawable.psm_lawn_chairs3, R.drawable.psm_lonely, R.drawable.psm_lonely2,
        R.drawable.psm_mountains11, R.drawable.psm_neutral_child, R.drawable.psm_neutral_person2, R.drawable.psm_peaceful_person,
        R.drawable.psm_puppy, R.drawable.psm_puppy3, R.drawable.psm_reading_in_bed2, R.drawable.psm_running3,
    }, {
        R.drawable.psm_running4, R.drawable.psm_sticky_notes2, R.drawable.psm_stressed_cat, R.drawable.psm_stressed_person,
        R.drawable.psm_stressed_person3, R.drawable.psm_stressed_person4, R.drawable.psm_stressed_person6, R.drawable.psm_stressed_person7,
        R.drawable.psm_stressed_person8, R.drawable.psm_stressed_person12, R.drawable.psm_talking_on_phone2, R.drawable.psm_to_do_list,
        R.drawable.psm_to_do_list3, R.drawable.psm_wine3, R.drawable.psm_work4, R.drawable.psm_yoga4
    }};
}
