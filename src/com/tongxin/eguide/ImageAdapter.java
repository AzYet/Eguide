package com.tongxin.eguide;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

/****
 * 
 * @author WQL
 * @version 1.0
 * 功能：主界面视图的图片Adapter类,九宫格图标
 */
public class ImageAdapter extends BaseAdapter
{
	private Context mcontext;
	
	private Integer[] mImageIds=
		{
			R.drawable.icon2,
			R.drawable.icon1,
			R.drawable.icon7,
			R.drawable.icon8,
			R.drawable.icon6,
			R.drawable.icon3,
			R.drawable.icon4,
			R.drawable.icon5,
			R.drawable.icon9,
		};
	
	public ImageAdapter(Context c)
	{
		mcontext=c;
	}
	
	public int getCount()
	{
		return mImageIds.length;
	}

	public Object getItem(int position)
	{
		return position;
	}

	public long getItemId(int position)
	{
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent)
	{
		ImageView imageview=new ImageView(mcontext);
		imageview.setImageResource(mImageIds[position]);
		imageview.setScaleType(ImageView.ScaleType.FIT_CENTER);
		return imageview;
	}

}
