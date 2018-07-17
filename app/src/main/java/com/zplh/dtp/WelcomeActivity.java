package com.zplh.dtp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zplh.dtp.utils.SPUtils;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 开始使用
     */
    private Button mBtStart;
    private AlphaAnimation animation;
    private RelativeLayout mRl;
    private boolean flag = true;
    private Animation LogoAnimation;
    private TextView textView;
    private TextView tv;
    private AlphaAnimation alphaAnimation2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        intiData();
        initView();
        in();
    }

    private void initView() {
        mBtStart = (Button) findViewById(R.id.bt_start);
        textView = (TextView) findViewById(R.id.tv);
        tv = (TextView) findViewById(R.id.tv2);
        mBtStart.setOnClickListener(this);
        mRl = (RelativeLayout) findViewById(R.id.rl);
    }

    private void intiData() {
        boolean flag = SPUtils.getInstance().getBoolean(this, "flag");
        if (flag) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.bt_start:

                break;
        }
    }

    public void in() {
        animation = new AlphaAnimation(0.0f, 1.0f);
        animation.setDuration(1000);
        mRl.startAnimation(animation);

        AlphaAnimation alphaAnimation1 = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation1.setStartOffset(1000);
        alphaAnimation1.setDuration(1000);
        textView.startAnimation(alphaAnimation1);
        alphaAnimation1.setAnimationListener(new Animation.AnimationListener() {



            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        alphaAnimation2 = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation2.setStartOffset(2000);
        alphaAnimation2.setDuration(1000);
        tv.startAnimation(alphaAnimation2);
        alphaAnimation2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                SPUtils.getInstance().putBoolean(WelcomeActivity.this, "flag", flag);
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


    }
}
