package com.example.wuht.win10loadingview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.btn_start)
    Button mBtnStart;
    @InjectView(R.id.win10_view)
    Win10LoadingView mWin10View;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
    }

    @OnClick({R.id.btn_start, R.id.win10_view})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_start:
                mWin10View.startLoading();
                break;
            case R.id.win10_view:
                break;
        }
    }
}
