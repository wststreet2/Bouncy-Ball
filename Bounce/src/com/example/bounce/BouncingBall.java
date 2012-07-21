package com.example.bounce;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RelativeLayout;

public class BouncingBall extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		RelativeLayout layout = (RelativeLayout) findViewById(R.id.layout);
		BallView bv = new BallView(this);

		layout.addView(bv);
	}

}
