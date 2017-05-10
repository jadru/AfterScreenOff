package com.hisl.glance.free.activity.theme;

import android.app.WallpaperManager;
import android.content.Context;
import android.os.PowerManager;
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

import com.hisl.glance.free.R;
import com.hisl.glance.free.ThemeEngine;

public class Theme1 extends AppCompatActivity {

    TextClock tc;
    WebView wb;
    RelativeLayout lo;
    private ThemeEngine te;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.theme1);

        tc = (TextClock)findViewById(R.id.tc);
        wb = (WebView)findViewById(R.id.wb);
        lo = (RelativeLayout)findViewById(R.id.lo);

        wb.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        wb.getSettings().setJavaScriptEnabled(true);
        wb.setVerticalScrollBarEnabled(false);
        wb.setHorizontalScrollBarEnabled(false);

        tc.setVisibility(View.VISIBLE);
        wb.setVisibility(View.GONE);

        lo.setBackground(WallpaperManager.getInstance(this).getDrawable());
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
