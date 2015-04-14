package com.example.maptest;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.maptest.wdiget.LyricView;

public class MarkDialog extends Dialog {

	private LyricView mLyricView;
	private boolean already = false;
	private Button button;
	private String mp3Path;
	private String lyricPath;
	private String title;

	public MarkDialog(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.dialog_lrctext);
		mLyricView = (LyricView) findViewById(R.id.markdia_lryic_text);
		mLyricView.prepare(mp3Path, lyricPath);
		setTitle(title);
		button = (Button) findViewById(R.id.markdia_button_start);
		button.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (already) {
					if (mLyricView.isPlaying()) {
						mLyricView.pause();
						button.setText("Start");
					} else {
						mLyricView.start();
						button.setText("Pause");
					}
				}
			}
		});
	}

	public void prepare() {
		mp3Path = Environment.getExternalStorageDirectory().getAbsolutePath()
				+ "/LyricSync/1.mp3";
		lyricPath = Environment.getExternalStorageDirectory().getAbsolutePath()
				+ "/LyricSync/1.lrc";
		title = "明明就";
		already = true;
	}

	public void start() {
		if (already) {
			mLyricView.start();
			button.setText("Pause");
		}
	}

	public void pause() {
		if (already) {
			mLyricView.pause();
			button.setText("Start");
		}
	}
	
	public void stop(){
		if (mLyricView != null) {
			mLyricView.stop();
		}
	}

}
