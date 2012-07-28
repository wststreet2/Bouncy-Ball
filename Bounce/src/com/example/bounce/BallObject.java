package com.example.bounce;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;

public class BallObject {

	private double height;
	private double width;
	private double posx;
	private double posy;
	private double accx;
	private double accy;
	private double friction = 0.025;
	private BlocksObject block = null;

	private double bounce;
	private double defbounce;

	private int radius;
	private Paint p;

	public BallObject(double posx, double posy, double accx, double accy,
			double bounce, int radius, Paint p) {
		this.posx = posx;
		this.posy = posy;
		this.accx = accx;
		this.accy = accy;
		this.bounce = bounce;
		this.defbounce = bounce;
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

	public void setBlock(BlocksObject block) {
		this.block = block;
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

		posy -= accy;
		accy -= 0.5;
		posx += accx;
		
		if(block.isBlockDeleted()==false)
		{
			if(block.getRect().contains((int)posx,(int)posy+radius) || block.getRect().contains((int)posx,(int)posy-radius))
			{
				block.delete();
				accy = accy * -1;
			}
			if(block.getRect().contains((int)posx+radius,(int)posy) || block.getRect().contains((int)posx-radius,(int)posy))
			{
				block.delete();
				accx = accx * -1;
			}
		}

		if (accx > 0)
			accx -= friction;
		if (accx < 0)
			accx += friction;

		if (posy >= (height - radius)) {
			accy = (Math.abs(accy) * bounce);
			if (bounce > 0)
				bounce -= 0.01;
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

		height = c.getClipBounds().height();
		width = c.getClipBounds().width();
		c.drawCircle((float) posx, (float) posy, (float) radius, p);
	}

	public void control(MotionEvent e) {

		accx = (e.getX() - posx) / 25;
		accy = (e.getY() - posy) / -25;
		bounce = defbounce;
	}
}
