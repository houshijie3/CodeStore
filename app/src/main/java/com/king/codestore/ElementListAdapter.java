package com.king.codestore;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SectionIndexer;
import android.widget.TextView;

import java.util.List;

import com.R;

public class ElementListAdapter extends BaseAdapter implements SectionIndexer {
	private List<ElementBean> list = null;
	private boolean mShowLetter = true;
	public ElementListAdapter(Context mContext, List<ElementBean> list, boolean showLetter) {
		this.list = list;
		mShowLetter = showLetter;
	}

	public int getCount() {
		return this.list.size();
	}

	public Object getItem(int position) {
		return list.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(final int position, View view, ViewGroup arg2) {
		ViewHolder viewHolder ;
		final ElementBean mContent = list.get(position);
		if (view == null) {
			viewHolder = new ViewHolder();
			view = CodeStoreApplication.getInstance().getLayoutInflater().inflate(R.layout.item_element_listview, null);
			viewHolder.tvTitle = (TextView) view.findViewById(R.id.title);
			viewHolder.tvLetter = (TextView) view.findViewById(R.id.catalog);
			view.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) view.getTag();
		}
		
		if(mShowLetter){
		int section = getSectionForPosition(position);
			if(position == getPositionForSection(section)){
				viewHolder.tvLetter.setVisibility(View.VISIBLE);
				viewHolder.tvLetter.setText(mContent.getSortLetters());
			}else{
				viewHolder.tvLetter.setVisibility(View.GONE);
			}
		}else{
			viewHolder.tvLetter.setVisibility(View.GONE);
		}
		viewHolder.tvTitle.setText(this.list.get(position).getName());
		return view;
	}
	


	final static class ViewHolder {
		TextView tvLetter;
		TextView tvTitle;
	}


	public int getSectionForPosition(int position) {
		return list.get(position).getSortLetters().charAt(0);
	}

	public int getPositionForSection(int section) {
		for (int i = 0; i < getCount(); i++) {
			String sortStr = list.get(i).getSortLetters();
			char firstChar = sortStr.toUpperCase().charAt(0);
			if (firstChar == section) {
				return i;
			}
		}
		
		return -1;
	}
	
//	private String getAlpha(String str) {
//		String  sortStr = str.trim().substring(0, 1).toUpperCase();
//		if (sortStr.matches("[A-Z]")) {
//			return sortStr;
//		} else {
//			return "#";
//		}
//	}

	@Override
	public Object[] getSections() {
		return null;
	}

// --Commented out by Inspection START (15/4/7 上午11:31):
////	public Drawable getDrawableByString(Context context,String image){
////
////	    Drawable  result = null;
////	    int resID = context.getResources().getIdentifier(image, "drawable", "com.idache.dada.free");
////	    if(resID != 0){
////	        result = context.getResources().getDrawable(resID);
////	    }
////        return result;
////	}
//    public interface OnItemChoose{
//        void onItemChoose(int index);
//    }
// --Commented out by Inspection STOP (15/4/7 上午11:31)
}