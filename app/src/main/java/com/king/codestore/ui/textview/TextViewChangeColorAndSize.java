package com.king.codestore.ui.textview;

import android.annotation.SuppressLint;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import com.R;
import com.king.codestore.BaseActivity;
import com.king.codestore.UIUtils;

/**
 * Created by houshijie on 15/6/11.
 */
public class TextViewChangeColorAndSize extends BaseActivity{
    private TextView tv_test1,tv_test2,tv_test3;
    @Override
    protected String getTitleText() {
        return "修改字体颜色和大小";
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_textview_different_color_size;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void initView() {
        tv_test1 = (TextView) findViewById(R.id.tv_test1);
        tv_test2 = (TextView) findViewById(R.id.tv_test2);
        tv_test3 = (TextView) findViewById(R.id.tv_test3);
        setTextViewWithDifferentSize(tv_test1.getText().toString(),
                tv_test1,
                3,
                R.dimen.textView_size_big,
                R.dimen.textView_size_small);
        setTextViewWithDifferentColor(tv_test2.getText().toString(),
                tv_test2,
                3,
                R.color.red,
                R.color.blue);
        setTextViewWithDifferentSizeAndColor(tv_test3.getText().toString(),
                tv_test3,
                3,
                R.dimen.textView_size_big,
                R.dimen.textView_size_small,
                R.color.red,
                R.color.blue);

    }

    @Override
    protected void init() {

    }

    /**
     *
     * @param string        需要改变大小都字符串
     * @param textView
     * @param index         从第几位变化
     * @param firstSize     第一种大小的 资源值
     * @param secondSize    第二种大小的 资源值
     */
    public static void setTextViewWithDifferentSize(String string,TextView textView,int index,int firstSize,int secondSize){
        if (string == null) return ;
        SpannableStringBuilder builder = new SpannableStringBuilder(string);
        builder.setSpan(new AbsoluteSizeSpan(UIUtils.getDimens(firstSize)), 0, index, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.setSpan(new AbsoluteSizeSpan(UIUtils.getDimens(secondSize)), index, string.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(builder);
    }
    public static void setTextViewWithDifferentSizeAndColor(String string,TextView textView,int index,int firstSize,int secondSize,int firstColor,int secondColor){
        if (string == null) return ;
        SpannableStringBuilder builder = new SpannableStringBuilder(string);
        builder.setSpan(new AbsoluteSizeSpan(UIUtils.getDimens(firstSize)), 0, index, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.setSpan(new AbsoluteSizeSpan(UIUtils.getDimens(secondSize)), index, string.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        builder.setSpan(new ForegroundColorSpan(UIUtils.getColor(firstColor)), 0,index, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.setSpan(new ForegroundColorSpan(UIUtils.getColor(secondColor)), index, string.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        textView.setText(builder);
    }

    /**
     * 为 textview 设定颜色
     * @param tv
     * @param index
     * @param secondColor
     */
    public static void setTextViewWithDifferentColor(String text,TextView tv,int index, int firstColor,int secondColor) {
        // TODO Auto-generated method stub
        tv.setTextColor(UIUtils.getColor(secondColor));
        SpannableStringBuilder builder = new SpannableStringBuilder(text);
        //ForegroundColorSpan 为文字前景色，BackgroundColorSpan为文字背景色
        ForegroundColorSpan yellowSpan = new ForegroundColorSpan(UIUtils.getColor(firstColor));
        builder.setSpan(yellowSpan, 0,index, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        ForegroundColorSpan secondSpan = new ForegroundColorSpan(UIUtils.getColor(secondColor));
        builder.setSpan(secondSpan, index,text.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        tv.setText(builder);
    }
}
