package com.example.bounce;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

@SuppressLint({ "DrawAllocation", "ParserError" })
public class BallView extends View {

	Paint mPaint;
	double bottom = 99999;

	BallObject ball;

	public BallView(Context context) {
		super(context);
		mPaint = new Paint();
		mPaint.setARGB(0xFF, 0x00, 0x80, 0xFF);
		ball = new BallObject(100.0, 50.0, 4.0, 10.0, 0.8, 10, mPaint);
	}

	private void update() {

		ball.update();
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
		
		ball.draw(canvas);
		this.update();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		ball.control(event);

		return super.onTouchEvent(event);
	}
	
	
	
	

}
