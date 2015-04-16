package com.app.guide.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;

import com.app.guide.AppManager;

public class BaseActivity extends Activity {

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
