package com.tongxin.eguide;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

@SuppressLint("SdCardPath")
public class ShotActivity extends Activity
{
	private Button btn;
	private ImageView imgview;
	private TextView tv_info;
	
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shot);
		
		tv_info=(TextView)findViewById(R.id.tv_info);
		btn=(Button)findViewById(R.id.btn_shot);
		btn.setOnClickListener(new OnClickListener()
				{

					public void onClick(View v)
					{
						Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
						startActivityForResult(intent,1);
					}			
				});
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode==Activity.RESULT_OK)
		{
			String sdStatus=Environment.getExternalStorageState();
			if(!sdStatus.equals(Environment.MEDIA_MOUNTED))
			{
				Log.v("TestFile","SD card is not avaiable");
				return;
			}
			Bundle bundle=data.getExtras();
			Bitmap bitmap=(Bitmap)bundle.get("data");
			FileOutputStream b=null;
			File file=new File("/sdcard/Eguide/");
			file.mkdirs();
			String filename="/sdcard/Eguide/first.jpg";
			try
			{
				b = new FileOutputStream(filename);
				bitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);
			}catch(FileNotFoundException e)
			{
				e.printStackTrace();
			}finally
			{
				try
				{
					b.flush();
					b.close();
				}catch (IOException e)
				{
					e.printStackTrace();
				}
			}			
			imgview=(ImageView)findViewById(R.id.imgView);
			imgview.setImageBitmap(bitmap);
			Intent intent=this.getIntent();
			Bundle bundle2=intent.getExtras();
			tv_info.setText("¾­¶È:"+String.valueOf(bundle2.getDouble("latitude"))+"\nÎ³¶È:"+String.valueOf(bundle2.getDouble("longitude")));
		}
	}

}
