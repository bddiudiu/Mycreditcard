package com.adam.mycreditcards.ui;

import com.adam.database.DatabaseService;
import android.net.Uri;
import android.os.Bundle;
import com.adam.mycreditcards.R;
import com.adam.mycreditcards.entity.Cardsbean;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class LauncherActivity extends Activity {
	private Button bt_add;
	protected final static int MENU_ADD = Menu.FIRST;
	protected static final int MENU_About = Menu.FIRST + 1;
	public DatabaseService dbService = new DatabaseService(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launcher);

		String tableNameString = Cardsbean.TABLE_NAME;
		Log.i("info", "判断是否有数据");
		// 启动时判断是否已经存在数据
		if (IsTableExist(tableNameString) == true) {
			Intent intent = new Intent();
			intent.setClass(LauncherActivity.this, MainActivity.class);
			startActivity(intent);

		}
		else {
			bt_add = (Button) findViewById(R.id.addbutton);
			bt_add.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent = new Intent();
					intent.setClass(LauncherActivity.this, AddActivity.class);
					startActivity(intent);
				}
			});
		}
	}

	public boolean IsTableExist(String tableName) {
		if (tableName == "") {
			return false;
		}
		SQLiteDatabase db = null;
		Cursor cursor = null;
		try {
			dbService.open();
			String sqlString = "select count(*) as c from sqlite_master where type ='table' and name ='"
					+ tableName.trim() + "'";
			cursor = db.rawQuery(sqlString, null);
			if (cursor.moveToNext()) {
				int count = cursor.getInt(0);
				if (count > 0) {
					return true;
				}
			}
			dbService.close();
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return true;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_launcher, menu);

		menu.add(Menu.NONE, MENU_ADD, 0, R.string.Add);
		menu.add(Menu.NONE, MENU_About, 0, R.string.About);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub

		super.onOptionsItemSelected(item);
		switch (item.getItemId()) {
		case MENU_ADD:
			operation("add");
			break;
		case MENU_About:
			openOptionsDialog();
			break;
		default:
			break;
		}
		return true;
	}

	private void operation(String cmd) {

		if (cmd == "add") {
			Intent intent = new Intent();
			intent.setClass(LauncherActivity.this, AddActivity.class);
			startActivity(intent);
		}
	}

	private void openOptionsDialog() {
		new AlertDialog.Builder(this)
				.setTitle(R.string.aboout_title)
				.setMessage(R.string.about_message)
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
	
	public void onStart() {
		super.onStart();
	 }
	 public void onResume() {
		 super.onResume();
	 }
	 public void onPause() {
		 super.onPause();
	 }
	 public void onStop() {
		 super.onStop();
	 }
	 public void onRestart() {
		 super.onRestart();
	 }
	 public void onDestroy() {
		 super.onDestroy();
	 }
	
}
