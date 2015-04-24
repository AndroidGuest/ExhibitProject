package com.app.guide.ui;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.app.guide.AppManager;
import com.app.guide.R;

public class BaseActivity extends FragmentActivity {

	@SuppressLint("InlinedApi")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 添加Activity到堆栈
		AppManager.getAppManager().addActivity(this);
		if (!isFullScreen()) {
		} else {
			setTheme(android.R.style.Theme_Holo_Light_NoActionBar);
		}
		ActionBar actionBar = getActionBar();
		if (actionBar != null) {
			actionBar.setTitle(getTitleStr());
			actionBar.setBackgroundDrawable(getResources().getDrawable(
					R.drawable.actionbar_bg));
		}
		
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// 结束Activity&从堆栈中移除
		AppManager.getAppManager().finishActivity(this);
	}

	protected boolean isFullScreen() {
		return false;
	}

	protected String getTitleStr() {
		return "GuideApp";
	}

}
