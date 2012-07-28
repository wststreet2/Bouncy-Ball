package com.example.bounce;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class BlocksObject {

	private Rect r;
	private Paint fill = new Paint();
	private boolean isDeleted;

	public BlocksObject(int left, int top, int right, int bottom) {
		r = new Rect(left, top, right, bottom);
		fill.setARGB(255, 255, 0, 0);
		isDeleted = false;
	}

	public void delete() {
		isDeleted = true;
	}

	public void draw(Canvas c) {
		if (!isDeleted) {
			c.drawRect(r, fill);
		}
	}

	public boolean isBlockDeleted() {
		return isDeleted;
	}

	public Rect getRect() {
		return r;
	}
}
