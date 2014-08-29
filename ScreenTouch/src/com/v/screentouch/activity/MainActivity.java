package com.v.screentouch.activity;

import java.util.Vector;

import com.v.screentouch.R;
import com.v.screentouch.R.id;
import com.v.screentouch.R.layout;
import com.v.screentouch.service.MainService;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ComponentInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Log.d("MainActivity", "onCreate");
		
		int [] layoutIds = {
				R.id.intervalLayout,
				R.id.screenShotEnableLayout,
				R.id.autoStartLayout,
				R.id.chooseAppLayout,
				R.id.pointsLayout,
				R.id.aboutLayout,
				R.id.exitLayout
		};
		
		ClickListener onClick = new ClickListener();	
		
		for(int i = 0; i < layoutIds.length; i++){
			RelativeLayout layoutButton = (RelativeLayout)findViewById(R.id.intervalLayout);
			layoutButton.setOnClickListener(onClick);
		}

		//开启服务
		Intent intent = new Intent(this, MainService.class);
		startService(intent);
	}

	class ClickListener implements View.OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch(v.getId()){
			case R.id.intervalLayout:
				Toast.makeText(MainActivity.this, "interval", Toast.LENGTH_SHORT).show();
				break;
			case R.id.screenShotEnableLayout:
				break;
			case R.id.autoStartLayout:
				break;
			case R.id.chooseAppLayout:
				break;
			case R.id.pointsLayout:
				break;
			case R.id.aboutLayout:
				break;
			case R.id.exitLayout:
				break;
			default:
				Log.i("invalied id:", String.valueOf(v.getId()));
				break;
			}
		}
	}
	
	@Override
	public void onResume(){ //重新打开时恢复信息
		super.onResume();
		Log.d("MainActivity", "onResume");
	}
	
	@Override
	public void onPause(){
		super.onPause();
		Log.d("MainActivity", "onPause");
	}
	
	@Override
	public void onStop(){
		super.onStop();
		Log.d("MainActivity", "onStop");
	}
	
	@Override
	public void onDestroy(){
		super.onDestroy();
		Log.d("MainActivity", "onDestroy");
	}
}
