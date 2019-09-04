package com.example.hp.nongye_test;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.ViewFlipper;


public class MainActivity extends TabActivity{
    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_MAX_OFF_PATH = 250;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;
    private GestureDetector gestureDetector;
    View.OnTouchListener gestureListener;
    private Animation slideLeftIn;
    private Animation slideLeftOut;
    private Animation slideRightIn;
    private Animation slideRightOut;
    private ViewFlipper viewFlipper;
    int currentView = 0;
    private static int maxTabIndex = 2;

    private  int[] image_set={R.drawable.datain,R.drawable.dataout,R.drawable.config};//未选中是TabWidget的图标
    private  int[] image_choose={R.drawable.indata,R.drawable.outdata,R.drawable.set};//选中的图标

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.tab_activity);

        final TabHost tabHost=this.getTabHost();
        tabHost.getTabWidget().bringToFront();
        //LayoutInflater.from(this).inflate(R.layout.tab_activity, tabHost.getTabContentView(), true);

        //tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("环境监测").setContent(new Intent(this,EnvData.class)));
        //tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("远程控制").setContent(new Intent(this,RemoteControl.class)));
        //tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("参数设定").setContent(new Intent(this,Settings.class)));
        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("", getResources().getDrawable(image_set[0])).setContent(new Intent(this,huanjing.class)));
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("", getResources().getDrawable(image_set[1])).setContent(new Intent(this,kongzhi.class)));
        tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("", getResources().getDrawable(image_set[2])).setContent(new Intent(this,canshu_sheding.class)));

        tabHost.setCurrentTab(0);
        slideLeftIn = AnimationUtils.loadAnimation(this, R.anim.slide_left_in);
        slideLeftOut = AnimationUtils.loadAnimation(this, R.anim.slide_left_out);
        slideRightIn = AnimationUtils.loadAnimation(this, R.anim.slide_right_in);
        slideRightOut = AnimationUtils.loadAnimation(this, R.anim.slide_right_out);

        gestureDetector = new GestureDetector(new MyGestureDetector());

        gestureListener = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (gestureDetector.onTouchEvent(event)) {

                    return true;
                }
                return false;
            }
        };

//        TabWidget tabWidget=tabHost.getTabWidget();
//        for (int i=0; i<tabWidget.getChildCount(); i++){//循环每个tabView
//            View view = tabWidget.getChildAt(i);	//获取tabView项
//            view.setBackgroundResource(R.color.whitesmoke);
//        }

        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                if(tabId.equals("tab1")){
                    ImageView iv=(ImageView) tabHost.getTabWidget().getChildAt(0).findViewById(android.R.id.icon);
                    iv.setImageDrawable(getResources().getDrawable(R.drawable.indata));

                    iv = (ImageView)tabHost.getTabWidget().getChildAt(1).findViewById(android.R.id.icon);
                    iv.setImageDrawable(getResources().getDrawable(R.drawable.dataout));

                    iv = (ImageView)tabHost.getTabWidget().getChildAt(2).findViewById(android.R.id.icon);
                    iv.setImageDrawable(getResources().getDrawable(R.drawable.config));
                }
                else if(tabId.equals("tab2")){
                    ImageView iv=(ImageView) tabHost.getTabWidget().getChildAt(0).findViewById(android.R.id.icon);
                    iv.setImageDrawable(getResources().getDrawable(R.drawable.datain));

                    iv = (ImageView)tabHost.getTabWidget().getChildAt(1).findViewById(android.R.id.icon);
                    iv.setImageDrawable(getResources().getDrawable(R.drawable.outdata));

                    iv = (ImageView)tabHost.getTabWidget().getChildAt(2).findViewById(android.R.id.icon);
                    iv.setImageDrawable(getResources().getDrawable(R.drawable.config));
                }
                else if(tabId.equals("tab3")){
                    ImageView iv=(ImageView) tabHost.getTabWidget().getChildAt(0).findViewById(android.R.id.icon);
                    iv.setImageDrawable(getResources().getDrawable(R.drawable.datain));

                    iv = (ImageView)tabHost.getTabWidget().getChildAt(1).findViewById(android.R.id.icon);
                    iv.setImageDrawable(getResources().getDrawable(R.drawable.dataout));

                    iv = (ImageView)tabHost.getTabWidget().getChildAt(2).findViewById(android.R.id.icon);
                    iv.setImageDrawable(getResources().getDrawable(R.drawable.set));
                }

            }
        });
    }

    class MyGestureDetector extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            TabHost tabHost = getTabHost();

            try {

                if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH)

                    return false;

                if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {

                    Log.i("test ", "right");
                    if (currentView == maxTabIndex) {
                        currentView = 0;

                    } else {
                        currentView++;
                    }
                    tabHost.setCurrentTab(currentView);

                } else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {

                    Log.i("test ", "left");

                    if (currentView == 0) {
                        currentView = maxTabIndex;

                    } else {
                        currentView--;
                    }
                    tabHost.setCurrentTab(currentView);

                }
            } catch (Exception e) {

                // nothing
            }
            return false;
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {

        if(gestureDetector.onTouchEvent(event)){

            event.setAction(MotionEvent.ACTION_CANCEL);

        }
        return super.dispatchTouchEvent(event);
    }

}

