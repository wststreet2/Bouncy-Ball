package com.example.bounce;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.media.AudioManager;
import android.media.SoundPool;
import android.view.MotionEvent;
import android.view.View;

public class BallObject {

	private double height;
	private double width;
	private double posx;
	private double posy;
	private double accx;
	private double accy;
	private double friction = 0.025;
	private BlocksObject block = null;

	private SoundPool sounds;
	private int bounceSound;
	private int touchSound;

	private double bounce;
	private double defbounce;

	private int radius;
	private Paint p;

	public BallObject(double posx, double posy, double accx, double accy,
			double bounce, int radius, Paint p, View v) {
		this.posx = posx;
		this.posy = posy;
		this.accx = accx;
		this.accy = accy;
		this.bounce = bounce;
		this.defbounce = bounce;
		this.radius = radius;
		this.p = p;

		sounds = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
		bounceSound = sounds.load(v.getContext(), R.raw.bounce, 1);
		touchSound = sounds.load(v.getContext(), R.raw.piu, 1);
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

		if (block.isBlockDeleted() == false) {
			if (block.getRect().contains((int) posx, (int) posy + radius)
					|| block.getRect()
							.contains((int) posx, (int) posy - radius)) {
				playsound();
				block.delete();
				accy = accy * -1;
			}
			if (block.getRect().contains((int) posx + radius, (int) posy)
					|| block.getRect()
							.contains((int) posx - radius, (int) posy)) {
				playsound();
				block.delete();
				accx = accx * -1;
			}
		}

		if (accx > 0)
			accx -= friction;
		if (accx < 0)
			accx += friction;

		if (posy >= (height - radius)) {
			playsound();
			accy = (Math.abs(accy) * bounce);
			if (bounce > 0)
				bounce -= 0.01;
		}

		if (posy <= (0 + radius)) {
			playsound();
			accy = 0 - Math.abs(accy) * bounce;
		}

		if (posx >= (width - radius)) {
			playsound();
			accx = 0 - Math.abs(accx) * bounce;
		}

		if (posx <= (0 + radius)) {
			playsound();
			accx = Math.abs(accx) * bounce;
		}
	}

	public void playsound() {
		sounds.play(bounceSound, (float) Math.abs(bounce*bounce) * 0.1f,
				(float) Math.abs(bounce*bounce) * 0.1f, 0, 0, 1.0f);
	}

	public void draw(Canvas c) {

		height = c.getClipBounds().height();
		width = c.getClipBounds().width();
		c.drawCircle((float) posx, (float) posy, (float) radius, p);
	}

	public void control(MotionEvent e) {
		sounds.play(touchSound, 1.0f, 1.0f, 0, 0, 1.5f);
		accx = (e.getX() - posx) / 25;
		accy = (e.getY() - posy) / -25;
		bounce = defbounce;
	}
}
