package com.tongxin.eguide;

import java.util.List;

import android.content.Intent;
import android.content.res.Resources;
import android.location.Location;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.GeoPoint;
import com.baidu.mapapi.LocationListener;
import com.baidu.mapapi.MKLocationManager;
import com.baidu.mapapi.MapActivity;
import com.baidu.mapapi.MapController;
import com.baidu.mapapi.MapView;
import com.baidu.mapapi.MyLocationOverlay;
import com.baidu.mapapi.Overlay;

public class LocateActivity extends MapActivity
{
	private BMapManager mBMapMan = null;
	private MapView mMapView=null;
    public  static Resources res=null;
    private DisplayMetrics dm;
    private MKLocationManager mLocationManager=null;
    private MapController mMapController=null;
    private List<Overlay> overlays=null;
    private MyLocationOverlay pos_overlay=null;
    
    public double userlatitude =  26.283510208129883 * 1E6;// 纬度33.49087222349736
    public double userlongitude = 110.77745056152344 * 1E6;// 经度115.27130064453128
    
    private TextView tlatitude;
    private TextView tlongitude;
    private Button shot_btn;
    private int tempcount=0;
	
    protected void onCreate(Bundle savedInstanceState)
    {
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_locate);
	    mBMapMan = new BMapManager(getApplication());
        mBMapMan.init("B20BFEB19EBBCCDC3F7DECA795BD2A9C9F821948", null);
	    super.initMapActivity(mBMapMan); 	      
	    dm=new DisplayMetrics();
	    getWindowManager().getDefaultDisplay().getMetrics(dm);
	    mMapView = (MapView) findViewById(R.id.bmapView);
	    mMapView.setBuiltInZoomControls(true);	
	    GeoPoint geoPoint = new GeoPoint((int)(userlatitude),(int)(userlongitude));  
	    mMapController = mMapView.getController();
	    mLocationManager = mBMapMan.getLocationManager();
	    overlays=mMapView.getOverlays();
	    pos_overlay=new MyLocationOverlay(LocateActivity.this, mMapView);
	    
	    mMapController.setCenter(geoPoint);
	    mMapController.setZoom(16);
	    pos_overlay.enableMyLocation();
	    overlays.add(pos_overlay);
	    
	    tlatitude=(TextView)findViewById(R.id.tlatitude);
	    tlongitude=(TextView)findViewById(R.id.tlongtitude);
	    shot_btn=(Button)findViewById(R.id.btn_shot);
	    
	    LocationListener listener = new LocationListener()
	      {

			public void onLocationChanged(Location pos)
			{
				tempcount++;
				userlatitude=pos.getLatitude();
				userlongitude=pos.getLongitude();
				tlatitude.setText("纬度:"+String.valueOf(userlatitude)+"次数"+tempcount);
				tlongitude.setText("经度"+String.valueOf(userlongitude)+"次数"+tempcount);
			}	    	  
	      };
	      mLocationManager.requestLocationUpdates(listener);
	      
	      shot_btn.setOnClickListener(new OnClickListener()
			{

				public void onClick(View v)
				{
					Bundle bundle=new Bundle();
					bundle.putDouble("latitude", userlatitude);
					bundle.putDouble("longitude",userlongitude);
					
					Intent intent = new Intent();				
					intent.setClass(LocateActivity.this, ShotActivity.class);
					intent.putExtras(bundle);
					startActivity(intent);
				}			
			});

    }
    
    
    protected void onDestroy() 
	{
        if (mBMapMan != null) 
        {
            mBMapMan.destroy();
            mBMapMan = null;
       }
       super.onDestroy();
    }
	
	   protected void onPause() 
	   {
		   if (mBMapMan != null) 
		   {
			   mBMapMan.stop();
		   }
		   super.onPause();
	   }

	   protected void onResume() 
	   {
		   if (mBMapMan != null) 
		   {
			   mBMapMan.start();
		   }
		   super.onResume();
	   }
	   
	protected boolean isRouteDisplayed()
	{
		return true;
	}

}
