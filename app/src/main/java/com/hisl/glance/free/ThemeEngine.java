package com.hisl.glance.free;

import android.app.WallpaperManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.text.Layout;
import android.view.View;
import android.widget.RelativeLayout;

public class ThemeEngine {

    public void bg(Context context, View layout){
        layout.setBackground(WallpaperManager.getInstance(context).getDrawable());
    }

    public Drawable getResource(Context context, int source){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return context.getResources().getDrawable(source, context.getTheme());
        } else {
            return context.getResources().getDrawable(source);
        }
    }


}
