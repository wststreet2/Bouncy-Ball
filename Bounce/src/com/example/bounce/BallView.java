package com.example.bounce;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

@SuppressLint({ "DrawAllocation", "ParserError" })
public class BallView extends View {

	Paint mPaint;
	double bounciness = 0.8;
	double height = 50;
	double radius = 10;
	double bottom = 99999;
	double accel = (float) -0.5;

	public BallView(Context context) {
		super(context);
		mPaint = new Paint();
		mPaint.setARGB(0xFF, 0x00, 0x80, 0xFF);

	}

	private void updateball() {

		height -= accel;
		accel -= 0.5;

		if (height >= (bottom - radius)) {
			accel = (Math.abs(accel) * bounciness);
			if (bounciness >= 0.01)
				bounciness -= 0.01;
		}

		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.invalidate();
	}

	@Override
	public void onDraw(Canvas canvas) {
		bottom = canvas.getClipBounds().height();
		canvas.drawCircle(canvas.getWidth() / 2, (float) height,
				(float) radius, mPaint);

		this.updateball();
	}

}
