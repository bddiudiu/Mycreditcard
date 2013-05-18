package com.adam.mycreditcards.ui;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import com.adam.database.DatabaseService;
import com.adam.mycreditcards.R;
import com.adam.mycreditcards.R.string;
import com.adam.mycreditcards.entity.Cardsbean;
import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.content.DialogInterface;

public class AddbillsActivity extends Activity {

	private DatePicker dpicker;
	private EditText etbcost;
	private EditText etbname;
	private Spinner spcardslist;
	private ImageButton madd;
	private List<Cardsbean> cardsList = new ArrayList<Cardsbean>();
	private SQLiteDatabase db;
	private Cursor myCursor;
	DatabaseService dbService = new DatabaseService(this);
	private List<String> allCards;
	private ArrayAdapter<String> adapter;
	private String cardID;
	int id;
	// 声明时间变量
	private int mYear;
	private int mMonth;
	private int mDay;
	private int mHour;
	private int mMinute;
	private Calendar calendar;
	public String timstr;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_addbills);

		findviews();
		gettime();
		getCardsID();
		

		madd.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				insertbills();
			}
		});
	}

	public void insertbills() throws NumberFormatException, SQLException {
		String bno = null;
		String bname = etbname.getText().toString().trim();
		Integer bcid = id;
		Double bcost = Double.parseDouble(etbcost.getText().toString());
		String bdate = timstr;
		String bplace = null;
		String btype = null;
		Integer binstallment = null;
		if (bname.equals("") || bcost.equals("")) {
			openOptionsDialog();
		}
		else {
			dbService.open();
			dbService.insertBills(bno, bname, bcid, bcost, bdate, bplace,
					btype, binstallment);
			Intent intent = new Intent();
			intent.setClass(this, MainActivity.class);
			startActivity(intent);
		}
	}

	public void gettime() {
		/* 取得当前日期与时间 */
		calendar = Calendar.getInstance();
		mYear = calendar.get(Calendar.YEAR);
		mMonth = calendar.get(Calendar.MONTH);
		mDay = calendar.get(Calendar.DAY_OF_MONTH);
		mHour = calendar.get(Calendar.HOUR_OF_DAY);
		mMinute = calendar.get(Calendar.MINUTE);

		/* 当日期改变时，tTextView的日期也改变 */
		dpicker.init(mYear, mMonth, mMonth,
				new DatePicker.OnDateChangedListener() {
					@Override
					public void onDateChanged(DatePicker view, int year,
							int monthOfYear, int dayOfMonth) {
						// TODO Auto-generated method stub
						mYear = year;
						mMonth = monthOfYear;
						mDay = dayOfMonth;
						timstr = "mYear" + "mMonth+1" + "mDay";
					}
				});
	}

	private void openOptionsDialog() {
		// TODO Auto-generated method stub
		new AlertDialog.Builder(this)
				.setTitle(R.string.aboout_title)
				.setMessage(R.string.error_null)
				.setPositiveButton(R.string.about_button,
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int i) {
								// TODO Auto-generated method stub
							}
						})
				// 弹窗对话框 需要导入import android.widget.Toast;
				// Toast.makeText(this, "TIPS", Toast.LENGTH_SHORT)
				.show();
	}

	public void getCardsID() throws SQLException, NumberFormatException {
		dbService.open();
		/* 查询表，得到cursor对象 */
		myCursor = dbService.getAllCards();
		myCursor.moveToFirst();
		while (!myCursor.isAfterLast() && (myCursor.getString(1) != null)) {
			Cardsbean cards = new Cardsbean();
			cards.setId(Integer.parseInt(myCursor.getString(0)));
			cards.setCno(myCursor.getString(1));
			cards.setCname(myCursor.getString(2));
			cardsList.add(cards);
			myCursor.moveToNext();
		}
		int count = cardsList.size();
		allCards = new ArrayList<String>(); 
		Log.i("card", cardsList.get(0).getCname());
		Log.i("card", cardsList.get(0).getCno());
		for (int i = 0; i < count; i++) {
//			String str = cardsList.get(i).getCname() ;
//					+ "-"+ cardsList.get(i).getCno();
			Log.i("card", cardsList.get(i).getCname()+ "-"+ cardsList.get(i).getCno());
			allCards.add(cardsList.get(i).getCname()+ "-"+ cardsList.get(i).getCno());
			
		}
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, allCards);
		adapter.setDropDownViewResource(R.layout.myspinner_dropdown);
		spcardslist.setAdapter(adapter);
		spcardslist
				.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView arg0, View arg1,
							int arg2, long arg3) {
						/* 将所选mySpinner的值带入myTextView中 */
						cardID = arg0.getSelectedItem().toString();
						int begin = cardID.indexOf("-");
						int end = cardID.length();
						id = Integer.parseInt((String) cardID.subSequence(begin + 1, end));
					}

					@Override
					public void onNothingSelected(AdapterView arg0) {

					}
				});
	}

	public void findviews() {
		dpicker = (DatePicker) findViewById(R.id.dpBdate);
		etbcost = (EditText) findViewById(R.id.etBcost);
		etbname = (EditText) findViewById(R.id.etBillname);
		spcardslist = (Spinner) findViewById(R.id.spCardslist);
		madd = (ImageButton) findViewById(R.id.main_head_add);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_addbills, menu);
		return true;
	}

}
