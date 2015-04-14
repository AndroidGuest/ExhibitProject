package com.example.maptest;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

public class MapCompass extends ImageView implements SensorEventListener {

	private float currentDegree;

	public MapCompass(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init(context);
	}

	public MapCompass(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init(context);
	}

	public MapCompass(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		init(context);
	}

	private void init(Context context) {
		SensorManager sm = (SensorManager) context
				.getSystemService(Context.SENSOR_SERVICE);
		// 注册传感器(Sensor.TYPE_ORIENTATION(方向传感器);SENSOR_DELAY_FASTEST(0毫秒延迟);
		// SENSOR_DELAY_GAME(20,000毫秒延迟)、SENSOR_DELAY_UI(60,000毫秒延迟))
		sm.registerListener(MapCompass.this,
				sm.getDefaultSensor(Sensor.TYPE_ORIENTATION),
				SensorManager.SENSOR_DELAY_FASTEST);
		this.setBackgroundResource(R.drawable.compass);
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		if (event.sensor.getType() == Sensor.TYPE_ORIENTATION) {
			float degree = event.values[0];
			/*
			 * RotateAnimation类：旋转变化动画类
			 * 
			 * 参数说明:
			 * 
			 * fromDegrees：旋转的开始角度。 toDegrees：旋转的结束角度。
			 * pivotXType：X轴的伸缩模式，可以取值为ABSOLUTE
			 * 、RELATIVE_TO_SELF、RELATIVE_TO_PARENT。 pivotXValue：X坐标的伸缩值。
			 * pivotYType
			 * ：Y轴的伸缩模式，可以取值为ABSOLUTE、RELATIVE_TO_SELF、RELATIVE_TO_PARENT。
			 * pivotYValue：Y坐标的伸缩值
			 */
			RotateAnimation ra = new RotateAnimation(currentDegree, -degree,
					Animation.RELATIVE_TO_SELF, 0.5f,
					Animation.RELATIVE_TO_SELF, 0.5f);
			// 旋转过程持续时间
			ra.setDuration(150);
			// 罗盘图片使用旋转动画
			this.startAnimation(ra);
			currentDegree = -degree;
		}
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub

	}

}
