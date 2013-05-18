package com.adam.mycreditcards.ui;

import com.adam.mycreditcards.R;
import com.adam.mycreditcards.R.layout;
import com.adam.mycreditcards.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class BillsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bills);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_bills, menu);
		return true;
	}

}
