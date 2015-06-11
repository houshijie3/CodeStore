package com.king.codestore;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.R;

/**
 * Created by houshijie on 15/6/11.
 */
public abstract class BaseActivity extends Activity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        initView();
        init();
        TextView tv_top_title = (TextView) findViewById(R.id.tv_top_title);
        if (tv_top_title != null)
            tv_top_title.setText(getTitleText());
    }


    protected abstract String getTitleText();
    protected abstract int getContentView();
    protected abstract void initView();
    protected abstract void init();

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
            default:
                break;
        }
    }
}
