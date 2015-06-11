package com.king.codestore;

import java.util.List;

/**
 * Created by houshijie on 15/6/11.
 */
public class ElementBean {
    private int id;
    private String name; // 显示的数据
    private String sortLetters; // 显示数据拼音的首字母
    private String image;

    private Class intentClass;
    private List<ElementBean> children;
    public String getName() {
        if (name != null && "#".equals(name)) {
            name = "其它";
        }
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSortLetters() {
        return sortLetters;
    }

    public void setSortLetters(String sortLetters) {
        this.sortLetters = sortLetters;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<ElementBean> getChildren() {
        return children;
    }

    public Class getIntentClass() {
        return intentClass;
    }

    public void setIntentClass(Class intentClass) {
        this.intentClass = intentClass;
    }

    public void setChildren(List<ElementBean> children) {
        this.children = children;
    }

    public ElementBean(int id,String name,List<ElementBean> children) {
        this.id = id;
        this.name = name;
        this.children = children;
        MainActivity.initElemenetBean(this);
        MainActivity.initElementBeanList(children);
    }
    public ElementBean(int id,String name,Class intentClass) {
        this.id = id;
        this.name = name;
        this.intentClass = intentClass;
        MainActivity.initElemenetBean(this);
    }
}
