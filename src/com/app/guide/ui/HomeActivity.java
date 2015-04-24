package com.app.guide.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.app.guide.AppManager;
import com.app.guide.R;
import com.app.guide.adapter.FragmentTabAdapter;

public class HomeActivity extends BaseActivity {

	private RadioGroup mRadioGroup;
	private int pressedCount;
	private Timer timer;
	private List<Fragment> fragments;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		timer = new Timer();
		fragments = new ArrayList<Fragment>();
		Fragment one = new TestFragment();
		Bundle oneBundle = new Bundle();
		oneBundle.putString("title", "one");
		one.setArguments(oneBundle);
		fragments.add(one);
		Fragment two = new TestFragment();
		Bundle twoBundle = new Bundle();
		twoBundle.putString("title", "two");
		two.setArguments(twoBundle);
		fragments.add(two);
		Fragment three = new TestFragment();
		Bundle threeBundle = new Bundle();
		threeBundle.putString("title", "three");
		three.setArguments(threeBundle);
		fragments.add(three);
		Fragment four = new TestFragment();
		Bundle fourBundle = new Bundle();
		fourBundle.putString("title", "four");
		four.setArguments(fourBundle);
		fragments.add(four);
		mRadioGroup = (RadioGroup) findViewById(R.id.home_tab_group);
		new FragmentTabAdapter(this, fragments, R.id.home_realtabcontent,
				mRadioGroup);
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		if (pressedCount == 1) {
			AppManager.getAppManager().appExit(HomeActivity.this);
		} else {
			pressedCount += 1;
			Toast.makeText(HomeActivity.this, "再次点击退出程序", Toast.LENGTH_SHORT)
					.show();
			timer.schedule(new TimerTask() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					pressedCount = 0;
				}
			}, 2000);
		}
	}

}
