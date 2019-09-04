package com.example.hp.nongye_test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

public class welcome extends Activity {
    private ImageView welcomeImg = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_layout);
        welcomeImg =(ImageView) findViewById(R.id.welcome);
        AlphaAnimation anima = new AlphaAnimation(0.3f,5.0f);
        anima.setDuration(2000);// 设置动画显示时间
        welcomeImg.startAnimation(anima);
        anima.setAnimationListener(new AnimationImpl());

    }

    private class AnimationImpl implements Animation.AnimationListener {

        @Override
        public void onAnimationStart(Animation animation) {
            welcomeImg.setBackgroundResource(R.drawable.jiemian);
        }


        private void skip() {
            startActivity(new Intent(welcome.this, MainActivity.class));
            finish();
        }


        @Override
        public void onAnimationEnd(Animation arg0) {
            // TODO 自动生成的方法存根
            skip();
        }


        @Override
        public void onAnimationRepeat(Animation arg0) {
            // TODO 自动生成的方法存根

        }
    }
}
