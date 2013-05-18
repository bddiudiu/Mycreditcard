package com.adam.mycreditcards.ui;

import java.util.Calendar;
import com.adam.database.DatabaseService;
import com.adam.mycreditcards.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class CardsActivity extends Activity {

	public TextView tvCardNo;
	public TextView tvCardName;
	public TextView tvThisPay;
	public TextView tvLastPay;
	public TextView tvDays;
	private ImageButton madd;

	DatabaseService dbService = new DatabaseService(this);
	private Cursor myCursor;
	Calendar c = Calendar.getInstance();
	String name, no, get;
	int date = 0, back = 0, today, month, payday = 0;
	int thismonth = 30, nextmonth = 30;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cardsinfo);
		findViews();
		getCards();
//		getPayDay();
		fillViews();
		
		
		madd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(CardsActivity.this, AddbillsActivity.class);
				startActivity(intent);
				
			}
		});
		
		
	}

	private void getPayDay() throws NumberFormatException {
		today = c.get(Calendar.DAY_OF_MONTH);
		month = c.get(Calendar.MONTH);
		date = Integer.parseInt(myCursor.getString(3));
		back = Integer.parseInt(myCursor.getString(4));

		int[] m = { 1, 3, 5, 7, 8, 10, 12 };
		for (int i = 0; i < m.length; i++) {
			if (month == m[i]) {
				thismonth = 31;
			}
			else if (month + 1 == m[i]) {
				nextmonth = 31;
			}
		}
		if (today > date) {
			payday = thismonth + nextmonth - today + back;
		}
		else if (today > back && today < date) {
			payday = thismonth - today + back;
		}
		else {
			payday = back - today + thismonth;
		}
		Log.i("pay", payday + "");
	}

	private void fillViews() {
		try {
			tvCardName.setText(myCursor.getString(2));
			tvCardNo.setText(myCursor.getString(1));
			tvDays.setText(payday + "");
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void getCards() {
		dbService.open();
		Bundle bundle = this.getIntent().getExtras();
		Integer id = bundle.getInt("cid");
		myCursor = dbService.getOneCards(id);
		dbService.close();
	}

	private void findViews() {
		tvCardNo = (TextView) findViewById(R.id.tvCardNo);
		tvCardName = (TextView) findViewById(R.id.tvCardName);
		tvThisPay = (TextView) findViewById(R.id.tvThisPay);
		tvLastPay = (TextView) findViewById(R.id.tvLastPay);
		tvDays = (TextView) findViewById(R.id.tvDays);
		madd = (ImageButton)findViewById(R.id.main_head_add);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_cards, menu);
		return true;
	}

}
