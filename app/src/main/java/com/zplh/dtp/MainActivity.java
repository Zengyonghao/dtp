package com.zplh.dtp;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.PopupWindow;
import android.widget.ProgressBar;

import com.zplh.dtp.utils.SPUtils;

public class MainActivity extends AppCompatActivity {
    private WebView mWebview;
    private WebSettings mWebSettings;
    private ProgressDialog pd;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        final PopUp popUp = new PopUp(this, new PopUp.OnClickListener() {
            @Override
            public void onError() {
                finish();
            }

            @Override
            public void onSuccess(PopupWindow popupWindow) {
                popupWindow.dismiss();
                showWeb();
                SPUtils.getInstance().putBoolean(MainActivity.this, "init",true);
            }
        });
        boolean flag = SPUtils.getInstance().getBoolean(this, "init");
        if (flag){
            showWeb();
        }else {
            new  Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            popUp.showBottom(getWindow().getDecorView());
                        }
                    });

                }
            }).start();

        }


    }


    private void initView() {
        initPermission();
        mWebview = findViewById(R.id.web_view);


    }

    private void initPermission() {

    }

    @Override
    protected void onStart() {
        super.onStart();


    }

    private void showWeb() {
        progressBar = (ProgressBar) findViewById(R.id.pb);

        mWebSettings = mWebview.getSettings();
        //        mWebSettings.setTextZoom(210);
        mWebview.loadUrl("http://m.dtpcoin.net");
        // 设置与Js交互的权限
        mWebSettings.setJavaScriptEnabled(true);
        // 设置允许JS弹窗
        mWebSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        //设置不用系统浏览器打开,直接显示在当前Webview
        mWebview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                //                pd.setMessage("页面正在加载中...");
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }

    //点击返回上一页面而不是退出浏览器
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && mWebview.canGoBack()) {
            mWebview.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    //销毁Webview
    @Override
    protected void onDestroy() {
        if (mWebview != null) {
            mWebview.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            mWebview.clearHistory();
            ((ViewGroup) mWebview.getParent()).removeView(mWebview);
            mWebview.destroy();
            mWebview = null;
        }
        super.onDestroy();
    }
}
