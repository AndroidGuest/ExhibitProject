package com.example.maptest;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.estimote.sdk.Beacon;
import com.example.maptest.MarkObject.MarkClickListener;
import com.library.beacon.BeaconUtils;
import com.library.beacon.BeaconUtils.OnRangingListener;

public class MainActivity extends Activity {

	private MyMap sceneMap;
	private BeaconUtils mBeaconUtils;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		sceneMap = (MyMap) findViewById(R.id.sceneMap);
		Bitmap b = BitmapFactory
				.decodeResource(getResources(), R.drawable.test);
		sceneMap.setBitmap(b);
		MarkObject markObject = new MarkObject();
		markObject.setMapX(0.34f);
		markObject.setMapY(0.5f);
		markObject.setmBitmap(BitmapFactory.decodeResource(getResources(),
				R.drawable.icon_marka));
		markObject.setMarkListener(new MarkClickListener() {

			@Override
			public void onMarkClick(int x, int y) {
				// TODO Auto-generated method stub
				MarkDialog dialog = new MarkDialog(MainActivity.this);
				dialog.prepare();
				dialog.show();
			}
		});

		sceneMap.addMark(markObject);
		((Button) findViewById(R.id.button_in))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						sceneMap.zoomIn();
					}
				});
		((Button) findViewById(R.id.button_out))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						sceneMap.zoomOut();
					}
				});

		mBeaconUtils = new BeaconUtils(this);
		mBeaconUtils.setMinDistance(2);
		if(mBeaconUtils.prepareBluetooth()){
			mBeaconUtils.setOnRangingListener(new MyOnRangeListener());
		}
		
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(mBeaconUtils.onActivityResult(requestCode, resultCode)){
			mBeaconUtils.setOnRangingListener(new MyOnRangeListener());
		}
	}

	private class MyOnRangeListener implements OnRangingListener {

		private boolean isIn = false;
		private MarkDialog mDialog;

		@Override
		public void onRangOut() {
			// TODO Auto-generated method stub
			if (isIn) {
				if (mDialog != null) {
					mDialog.stop();
					mDialog.dismiss();
				}
			}
			isIn = false;
		}

		@Override
		public void onRangIn(Beacon beacon) {
			// TODO Auto-generated method stub
			if (!isIn) {
				mDialog = new MarkDialog(MainActivity.this);
				mDialog.prepare();
				mDialog.show();
			}
			isIn = true;
		}

	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		sceneMap.onDestory();
		mBeaconUtils.disconnect();
	}
	
}
