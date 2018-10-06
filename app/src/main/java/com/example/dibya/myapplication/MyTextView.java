package com.example.dibya.myapplication;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class MyTextView extends android.support.v7.widget.AppCompatTextView{
    public MyTextView(Context context, AttributeSet attr)
    {
        super(context,attr);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(),"fonts/GoogleSans-Regular.ttf"));
    }
}
