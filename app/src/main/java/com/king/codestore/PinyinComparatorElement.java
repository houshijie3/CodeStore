package com.king.codestore;


import java.util.Comparator;

public class PinyinComparatorElement implements Comparator<ElementBean> {

    public int compare(ElementBean o1, ElementBean o2) {
        if (o1.getSortLetters().equals("@") || o2.getSortLetters().equals("#")) {
            return -1;
        } else if (o1.getSortLetters().equals("#")
                || o2.getSortLetters().equals("@")) {
            return 1;
        }else{
        	final int a = o1.getSortLetters().compareTo(o2.getSortLetters());
        	if(a == 0){
        		if((o1.getName().charAt(0) <= 'Z' && o1.getName().charAt(0) >= 'A')){
        			//如果第一个为 字母
        			return -1;
        		}else if((o2.getName().charAt(0) <= 'Z' && o2.getName().charAt(0) >= 'A')){
        			return 1;
        		}
        		return 0;
        	}else{
        		return a;
        	}
        }
    }

}
