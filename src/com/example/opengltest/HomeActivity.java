package com.example.opengltest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.estimote.sdk.Beacon;
import com.example.maptest.R;
import com.example.maptest.wdiget.LyricView;
import com.library.beacon.BeaconUtils;
import com.library.beacon.BeaconUtils.OnRangingListener;
import com.library.beacon.constant.Constants;

public class HomeActivity extends Activity {

	private static final String TAG = HomeActivity.class.getSimpleName();

	private LyricView mLyricView;
	private Button button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String mp3Path = Environment.getExternalStorageDirectory()
				.getAbsolutePath() + "/LyricSync/1.mp3";
		String lyricPath = Environment.getExternalStorageDirectory()
				.getAbsolutePath() + "/LyricSync/1.lrc";
		mLyricView.prepare(mp3Path, lyricPath);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (mLyricView.isPlaying()) {
					mLyricView.pause();
					button.setText("Start");
				} else {
					mLyricView.start();
					button.setText("Pause");
				}
			}
		});
		BeaconUtils mBeaconUtils = new BeaconUtils(this);
		mBeaconUtils.prepareBluetooth();
		mBeaconUtils.setOnRangingListener(new MyOnRangeListener());
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == Constants.REQUEST_ENABLE_BT) {
			if (resultCode == Activity.RESULT_OK) {
				return;
			} else {
				Toast.makeText(HomeActivity.this, "Bluetooth not enabled",
						Toast.LENGTH_SHORT).show();
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}

	private class MyOnRangeListener implements OnRangingListener {

		private boolean isIn = false;

		@Override
		public void onRangOut() {
			// TODO Auto-generated method stub
			if (isIn) {
				mLyricView.pause();
			}
			isIn = false;
		}

		@Override
		public void onRangIn(Beacon beacon) {
			// TODO Auto-generated method stub
			if (!isIn) {
				mLyricView.start();
			}
			isIn = true;
		}

	}

}
