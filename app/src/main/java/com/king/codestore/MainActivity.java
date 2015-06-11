package com.king.codestore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.R;
import com.king.codestore.ui.textview.TextViewChangeColorAndSize;


public class MainActivity extends Activity {

    public static final String ask_return = "ask_return";

    private ListView listViewOne;
    private ListView listViewTwo;
    // private TextView dialog;
    private ElementListAdapter listOneAdapter;
    private ElementListAdapter listTwoAdapter;

    /**
     * 汉字转换成拼音的类
     */
    private List<ElementBean> listDataALL = new ArrayList<ElementBean>();
    private List<ElementBean> listOne;
    private List<ElementBean> listTwo;
    private SideBar mSideBar = null;
    private TextView dialog = null;

    private static CharacterParser characterParser;
    private static PinyinComparatorElement pinyinComparatorElement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (characterParser == null)
            characterParser = CharacterParser.getInstance();
        if (pinyinComparatorElement == null)
            pinyinComparatorElement = new PinyinComparatorElement();
        initView();
        init();
    }

    protected void initView() {
        // TODO Auto-generated method stub
        listViewOne = (ListView) findViewById(R.id.listview_level_one);
        listViewTwo = (ListView) findViewById(R.id.listview_level_two);
        mSideBar = (SideBar) findViewById(R.id.sidrbar);
        dialog = (TextView) findViewById(R.id.dialog);
        mSideBar.setTextView(dialog);

        //设置右侧触摸监听
        mSideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {
            @Override
            public void onTouchingLetterChanged(String s) {
                //该字母首次出现的位置
                int position = listOneAdapter.getPositionForSection(s.charAt(0));
                if (position != -1) {
                    listViewOne.setSelection(position);
                }

            }
        });
    }

    protected void init() {
        // TODO Auto-generated method stub
        listOne = fillDataFirst();
        listOneAdapter = new ElementListAdapter(this, listOne,true);
        listViewOne.setAdapter(listOneAdapter);
        listViewOne.setOnItemClickListener(onLevelOneClick);
        listViewOne.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                hideRightTwo();
                return false;
            }
        });

        listTwo = new ArrayList<ElementBean>();
        listTwoAdapter = new ElementListAdapter(this, listTwo,true);
        listViewTwo.setAdapter(listTwoAdapter);
        listViewTwo.setOnItemClickListener(onLevelTwoClick);



    }

    private void hideRightTwo(){
        listViewTwo.setVisibility(View.GONE);
        mSideBar.setVisibility(View.VISIBLE);
    }

    private List<ElementBean> fillDataFirst(){
        //第一部分
        List<ElementBean> AllList = new ArrayList<ElementBean>();

        List<ElementBean> list1 = new ArrayList<ElementBean>();
        ElementBean elementBeanC1 = new ElementBean(10001,"different color and size", TextViewChangeColorAndSize.class);
        list1.add(elementBeanC1);
        ElementBean elementBean1 = new ElementBean(1,"TextView",list1);
        AllList.add(elementBean1);
        return AllList;
    }

    private final AdapterView.OnItemClickListener onLevelOneClick = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                long arg3) {
            // TODO Auto-generated method stub
            ElementBean clickLeveOne = listOne.get(arg2);
            final int id = clickLeveOne.getId();
            final List<ElementBean> listAllDimTwo = clickLeveOne.getChildren();
            listTwo.clear();
            if (listAllDimTwo != null && listAllDimTwo.size() != 0)
                listTwo.addAll(listAllDimTwo);

            listTwoAdapter.notifyDataSetChanged();

            listViewTwo.setVisibility(View.VISIBLE);
            mSideBar.setVisibility(View.GONE);
            final Display display = getWindowManager().getDefaultDisplay();
            final int width = display.getWidth();
            final int height = display.getHeight();
            ObjectAnimator.ofFloat(listViewTwo, View.X, width,width/3).start();
        }
    };

    private final AdapterView.OnItemClickListener onLevelTwoClick = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                long arg3) {
            // TODO Auto-generated method stub
            ElementBean clickLevetwo = listTwo.get(arg2);
            Class intentClass = clickLevetwo.getIntentClass();
            if (intentClass != null){
                Intent intent = new Intent(MainActivity.this,intentClass);
                startActivity(intent);
            }
        }
    };

    public static void initElemenetBean(ElementBean allDim){
        String pinyin = characterParser.getSelling(allDim.getName());
        String sortString = null;
        try {
            sortString = pinyin.substring(0, 1).toUpperCase();
        } catch (Exception e) {
            // TODO: handle exception
        }

        // 正则表达式，判断首字母是否是英文字母
        if (sortString.matches("[A-Z]")) {
            allDim.setSortLetters(sortString.toUpperCase());
        } else {
            allDim.setSortLetters("#");
        }
    }

    public static void initElementBeanList(List<ElementBean> elementBeans){
        Collections.sort(elementBeans, pinyinComparatorElement);
    }
}
