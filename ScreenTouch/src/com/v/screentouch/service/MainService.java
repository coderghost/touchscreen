package com.v.screentouch.service;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class MainService extends Service {

	private ViewService mViewService = null;
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate(){
		super.onCreate();
		startForeground(1, new Notification());
		mViewService = ViewService.getInstance(this);
		mViewService.addView(ViewService.TOUCH_VIEW);
		mViewService.getView(ViewService.TOUCH_VIEW).setOnTouchListener(new TouchListener());
	}
	
	//处理触摸事件
	public void doTouch(){
		Toast.makeText(this, "touched", Toast.LENGTH_SHORT).show();
		
	}
	
	
	class TouchListener implements View.OnTouchListener{

		@Override
		public boolean onTouch(View v, MotionEvent e) {
			// TODO Auto-generated method stub
			if(e.getAction() == MotionEvent.ACTION_OUTSIDE){
				MainService.this.doTouch();
			}
			return false;
		}
	}
	
	class ScreenListener implements View.OnTouchListener{

		@Override
		public boolean onTouch(View v, MotionEvent e) {
			// TODO Auto-generated method stub
			return false;
		}
		
	}
}

