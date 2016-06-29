package arun.com.chromer.webheads.ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Outline;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewOutlineProvider;

import arun.com.chromer.R;
import arun.com.chromer.util.Util;

/**
 * Circle view that draws bitmap shadow layers on pre L and system drop shadow on post L systems.
 */
public class ElevatedCircleView extends CircleView {

    public ElevatedCircleView(Context context) {
        this(context, null, 0);
    }

    public ElevatedCircleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ElevatedCircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (!Util.isLollipopAbove()) {
            float shadowR = context.getResources().getDimension(R.dimen.web_head_shadow_radius);
            float shadowDx = context.getResources().getDimension(R.dimen.web_head_shadow_dx);
            float shadowDy = context.getResources().getDimension(R.dimen.web_head_shadow_dy);
            mBgPaint.setShadowLayer(shadowR, shadowDx, shadowDy, 0x55000000);
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (Util.isLollipopAbove()) {
            setOutlineProvider(new ViewOutlineProvider() {
                @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void getOutline(View view, Outline outline) {
                    int shapeSize = getMeasuredWidth();
                    outline.setRoundRect(0, 0, shapeSize, shapeSize, shapeSize / 2);
                }
            });
            setClipToOutline(true);
        }
    }
}