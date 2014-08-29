package com.v.screentouch.service;

import android.content.Context;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;

public class ViewService {

	static final int TOUCH_VIEW = 1;
	static final int SCREEN_VIEW = 2;
	static ViewService instance = null;
	
	private Context mContext = null;
	private View touchView= null;
	private View screenView = null;
	private WindowManager mWindowManager = null;
	
	private boolean touchViewAdded = false;
	private boolean screenViewAdded = false;
	
	static ViewService getInstance(Context paramContext){
		if(instance == null){
			instance = new ViewService(paramContext);
		}
		return instance;
	}
	
	private ViewService(Context paramContext){
		this.mContext = paramContext;
		this.mWindowManager = (WindowManager)mContext.getSystemService(Context.WINDOW_SERVICE);
		instance = this;
	}
	
	private void addView(View view, WindowManager.LayoutParams paramLayout){
		WindowManager mWindowManager = (WindowManager)mContext.getSystemService(Context.WINDOW_SERVICE);
		view.setAlpha(0.0F);
		paramLayout.width = -1;
		paramLayout.height = -1;
		paramLayout.format = -2;
		mWindowManager.addView(view, paramLayout);
	}
	
	public void addView(int viewType){
		WindowManager.LayoutParams mParamLayout = new WindowManager.LayoutParams();
		if(viewType == TOUCH_VIEW){
			mParamLayout.flags |= WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
					| WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE 
					| WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH;
			mParamLayout.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
			touchView = new View(mContext);
			if(touchViewAdded){
				this.mWindowManager.updateViewLayout(touchView, mParamLayout);
				return;
			}
			addView(touchView, mParamLayout);
			touchViewAdded = true;
		}else if(viewType == SCREEN_VIEW){
			mParamLayout.type |= WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
			mParamLayout.type = WindowManager.LayoutParams.TYPE_SYSTEM_ERROR;
			screenView = new View(mContext);
			if(screenViewAdded){
				this.mWindowManager.updateViewLayout(screenView, mParamLayout);
				return;
			}
			addView(screenView, mParamLayout);
			screenViewAdded = true;
		}else{
			return;
		}
	}
	
	public void removeView(int viewType){
		if(viewType == TOUCH_VIEW){
			this.mWindowManager.removeView(touchView);
			touchViewAdded = false;
		}else if(viewType == SCREEN_VIEW){
			this.mWindowManager.removeView(screenView);
			screenViewAdded = false;
		}else{
			return;
		}
	}
	
	public View getView(int viewType){
		if(viewType == TOUCH_VIEW){
			return this.touchView;
		}else if(viewType == SCREEN_VIEW){
			return this.screenView;
		}else{
			return null;
		}
	}
}
