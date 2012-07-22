package com.example.bounce;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;

public class BallObject {

	private double height;
	private double width;
	private double oldposx;
	private double oldposy;
	private double posx;
	private double posy;
	private double accx;
	private double accy;
	private double friction = 0.025;
	private double bounce;

	private Canvas backupCanvas;

	private int radius;
	private Paint p;

	public BallObject(double posx, double posy, double accx, double accy,
			double bounce, int radius, Paint p) {
		this.posx = posx;
		this.posy = posy;
		this.accx = accx;
		this.accy = accy;
		this.bounce = bounce;
		this.radius = radius;
		this.p = p;
	}

	public double getPosx() {
		return posx;
	}

	public double getPosy() {
		return posy;
	}

	public double getAccx() {
		return accx;
	}

	public double getAccy() {
		return accy;
	}

	public double getBounce() {
		return bounce;
	}

	public int getRadius() {
		return radius;
	}

	public Paint getP() {
		return p;
	}

	public void setPosx(double posx) {
		this.posx = posx;
	}

	public void setPosy(double posy) {
		this.posy = posy;
	}

	public void setAccx(double accx) {
		this.accx = accx;
	}

	public void setAccy(double accy) {
		this.accy = accy;
	}

	public void setBounce(double bounce) {
		this.bounce = bounce;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public void setP(Paint p) {
		this.p = p;
	}

	public void update() {

		oldposy = posy;
		oldposx = posx;

		posy -= accy;
		accy -= 0.5;
		posx += accx;

		if (accx > 0)
			accx -= friction;
		if (accx < 0)
			accx += friction;

		if (posy >= (height - radius)) {
			accy = (Math.abs(accy) * bounce);
			// if (bounce >= 0.01)
			// bounce -= 0.01;
		}

		if (posy <= (0 + radius)) {
			accy = 0 - Math.abs(accy) * bounce;
		}

		if (posx >= (width - radius)) {
			accx = 0 - Math.abs(accx) * bounce;
		}

		if (posx <= (0 + radius)) {
			accx = Math.abs(accx) * bounce;
		}
	}

	public void draw(Canvas c) {

		if (c != null)
			backupCanvas = c;
		else
			c = backupCanvas;

		if (c == null)
			return;

		height = c.getClipBounds().height();
		width = c.getClipBounds().width();
		c.drawCircle((float) posx, (float) posy, (float) radius, p);
	}

	public void control(MotionEvent e) {

		// if (posx > e.getX())
		accx = (e.getX() - posx) / 10;
		// else
		// accx = (e.getX() - posx) / 10;
		// if (posy < e.getY())
		accy = (e.getY() - posy) / -10;
		// else
		// accy = (e.getY() - posy) / 10;

	}
}
