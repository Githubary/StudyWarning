package com.example.myapplication.mvp.view.warningReason;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.myapplication.R;
import com.example.myapplication.base.Bactivity.BaseActivity;
import com.tencent.smtt.sdk.WebView;

public class PPTActivity extends BaseActivity {

    private WebView webView;

    @Override
    protected int initLayout() {
        return R.layout.activity_ppt;
    }

    @Override
    protected void initView() {
//        webView=findViewById(R.id.web_view);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setState(false);
        setTitle(false);

        super.onCreate(savedInstanceState);
    }


}
