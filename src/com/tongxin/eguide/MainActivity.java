package com.tongxin.eguide;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class MainActivity extends Activity
{

	private GridView grid;
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		grid=(GridView)findViewById(R.id.GridView1);
		grid.setAdapter(new ImageAdapter(this));
		grid.setBackgroundResource(R.drawable.bgd);
		grid.setOnItemClickListener(new OnItemClickListener()		
				{
					public void onItemClick(AdapterView<?> parent, View v,
							int position, long id)
					{
						if(position==0)
						{
							Intent intent=new Intent();
							intent.setClass(MainActivity.this, LocateActivity.class);
							startActivity(intent);
						}
//						Toast.makeText(MainActivity.this, "you have choose"+(position+1), Toast.LENGTH_SHORT).show();
						if(position==2)
						{
							Intent intent=new Intent();
							intent.setClass(MainActivity.this, ShotActivity.class);
							startActivity(intent);
						}
					}
			
				});
	}

	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	protected void dialog()
	{
		AlertDialog.Builder build=new Builder(MainActivity.this);
		build.setMessage("确定要退出吗?");
		build.setTitle("提示");
		build.setPositiveButton("确认", new android.content.DialogInterface.OnClickListener()
		{
			
			public void onClick(DialogInterface dialog, int which)
			{
				dialog.dismiss();
				MainActivity.this.finish();				
			}
		});
		build.setNegativeButton("取消", new android.content.DialogInterface.OnClickListener()
		{
			public void onClick(DialogInterface dialog, int which)
			{
				dialog.dismiss();
			}
		});
		build.create().show();		
	}
	
	public boolean onKeyDown(int keyCode,KeyEvent event)
	{
		if(keyCode==KeyEvent.KEYCODE_BACK&&event.getRepeatCount()==0)
		{
			dialog();
			return false;
		}
		return false;		
	}

}
