package com.hisl.glance.free.activity.theme;

import android.app.WallpaperManager;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.AnalogClock;
import android.widget.RelativeLayout;
import android.widget.TextClock;
import android.widget.TextView;

import com.hisl.glance.free.R;
import com.hisl.glance.free.ThemeEngine;

public class Theme2 extends AppCompatActivity {

    TextView tv;
    WebView wb;
    private ThemeEngine te;
    RelativeLayout lo;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.theme2);

        tv = (TextView) findViewById(R.id.tv);
        wb = (WebView)findViewById(R.id.wb);
        lo = (RelativeLayout)findViewById(R.id.lo);
        pref = getSharedPreferences("pref", MODE_PRIVATE);

        wb.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        wb.getSettings().setJavaScriptEnabled(true);
        wb.setVerticalScrollBarEnabled(false);
        wb.setHorizontalScrollBarEnabled(false);

        tv.setVisibility(View.VISIBLE);
        wb.setVisibility(View.GONE);

        lo.setBackground(WallpaperManager.getInstance(this).getDrawable());

        tv.setTypeface(Typeface.createFromAsset(getAssets(), "Demo_ConeriaScript.ttf"));
        tv.setText(pref.getString("sig", "Hello, It's Me"));
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {

        switch (keyCode) {
            case KeyEvent.KEYCODE_HOME:
            case KeyEvent.KEYCODE_POWER:
                finish();
                break;
            default:
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int action = event.getAction();
        switch(action) {
            case MotionEvent.ACTION_DOWN :
            case MotionEvent.ACTION_UP :
            case MotionEvent.ACTION_MOVE :
                break;
        }
        return super.onTouchEvent(event);
    }
}
