package com.adam.mycreditcards.ui;

import java.util.ArrayList;
import java.util.List;
import com.adam.database.DatabaseService;
import com.adam.mycreditcards.R;
import com.adam.mycreditcards.entity.Cardsbean;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class AddActivity extends Activity {

	private EditText etCardNO;
	private EditText etCardName;
	private EditText etCardDate;
	private EditText etCardBack;
	private RadioGroup radioGroup;
	private Button btAdd;
	private Button btCancle;
	private int radioButtonId;
	private SQLiteDatabase db;
	private ListAdapter listAdapter;
	DatabaseService dbService = new DatabaseService(this);
	private List<Cardsbean> cardsList = new ArrayList<Cardsbean>();


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add);
		FindViews();
		btAdd.setOnClickListener(add);
		btCancle.setOnClickListener(cancle);
		radioGroup.setOnCheckedChangeListener(radio);
	}

	private void FindViews() {
		etCardNO = (EditText) findViewById(R.id.etCardNO);
		etCardName = (EditText) findViewById(R.id.etCardName);
		etCardDate = (EditText) findViewById(R.id.etCardDate);
		etCardBack = (EditText) findViewById(R.id.etCardBack);
		radioGroup = (RadioGroup) findViewById(R.id.radioCardGet);
		btAdd = (Button) findViewById(R.id.btAdd);
		btCancle = (Button) findViewById(R.id.btCancle);
	}

	private OnClickListener add = new OnClickListener() {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			String cno = etCardNO.getText().toString().trim();
			String cname = etCardName.getText().toString();
			String cdate = etCardDate.getText().toString();
			String cback = etCardBack.getText().toString();
			String cget = ""+radioButtonId;
			if (cno.equals("")||cname.equals("")||cback.equals("")||cdate.equals("")) {
				openOptionsDialog();
			}else {
				dbService.open();
				dbService.insertNew(cno, cname, cdate, cback, cget);
				Intent intent = new Intent();
				intent.setClass(AddActivity.this, MainActivity.class);
				startActivity(intent);
			}		
		}
	};
	
	private void openOptionsDialog() {
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
	

	private OnClickListener cancle = new OnClickListener() {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent();
			intent.setClass(AddActivity.this, MainActivity.class);
			startActivity(intent);
		}
	};

	private OnCheckedChangeListener radio = new OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(RadioGroup arg0, int checkedId) {
			// TODO Auto-generated method stub
			radioButtonId = arg0.getCheckedRadioButtonId();
		}

	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_add, menu);
		return true;
	}

	private class ListAdapter extends BaseAdapter {
		public ListAdapter() {
			super();
		}

		@Override
		public int getItemViewType(int postion) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public Object getItem(int postion) {
			// TODO Auto-generated method stub
			return postion;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return cardsList.size();
		}

		@Override
		public View getView(int position, View view, ViewGroup parent) {
			// TODO Auto-generated method stub
			view = getLayoutInflater().inflate(R.layout.listview, null);
			TextView tv = (TextView) view.findViewById(R.id.tvCardsName);
			tv.setText("" + cardsList.get(position).getCname());
			TextView edit = (TextView) view.findViewById(R.id.tvEdit);
			edit.setText(R.string.edit);
			return view;
		}

	};
}
