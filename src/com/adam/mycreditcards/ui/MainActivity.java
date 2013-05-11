package com.adam.mycreditcards.ui;

import java.util.ArrayList;
import java.util.List;

import android.R.integer;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import com.adam.database.DatabaseService;
import com.adam.mycreditcards.R;
import com.adam.mycreditcards.entity.Cardsbean;

public class MainActivity extends Activity {
	private static int POSTION = 0;
	private Cursor myCursor;
	private ListView lv;
	private int _id = 0;
	private SQLiteDatabase db;
	private ListAdapter listAdapter;
	private List<Cardsbean> cardsList = new ArrayList<Cardsbean>();
	DatabaseService dbService = new DatabaseService(this);
	public int count;
	private ImageButton madd;
	protected final static int MENU_ADD = Menu.FIRST;
	protected static final int MENU_About = Menu.FIRST + 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		madd = (ImageButton)findViewById(R.id.main_head_add);
		
		madd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, AddActivity.class);
				startActivity(intent);
				
			}
		});
		init();
		count = listAdapter.getCount();
	}

	public void init() {
		try {
			dbService.open();
			/* 查询表，得到cursor对象 */
			myCursor = dbService.getAll();
			myCursor.moveToFirst();
			while (!myCursor.isAfterLast() && (myCursor.getString(1) != null)) {
				Cardsbean cards = new Cardsbean();
				cards.setId(myCursor.getString(0));
				cards.setCno(myCursor.getString(1));
				cards.setCname(myCursor.getString(2));
				cards.setCdate(myCursor.getString(3));
				cards.setCback(myCursor.getString(4));
				cards.setCget(myCursor.getString(5));
				cardsList.add(cards);
				myCursor.moveToNext();
			}
		}
		catch (IllegalArgumentException e) {
			// 当用SimpleCursorAdapter装载数据时，表ID列必须是_id，否则报错column '_id' does not
			// exist
			e.printStackTrace();
		}
		lv = (ListView) findViewById(R.id.listView1);
		listAdapter = new ListAdapter();
		lv.setAdapter(listAdapter);
		lv.setOnItemClickListener(new ListView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
				Bundle bundle = new Bundle();
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, CardsActivity.class);
				bundle.putInt("count", count);
				bundle.putString("cid", cardsList.get(position).getId());
				bundle.putString("cno", cardsList.get(position).getCno());
				bundle.putString("cname", cardsList.get(position).getCname());
				bundle.putString("cdate", cardsList.get(position).getCdate());
				bundle.putString("cback", cardsList.get(position).getCback());
				bundle.putString("cget", cardsList.get(position).getCget());
				intent.putExtras(bundle);		
				startActivity(intent);
			}

		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);

		super.onCreateOptionsMenu(menu);
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
			intent.setClass(MainActivity.this, AddActivity.class);
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
			tv.setText("" + cardsList.get(position).getCname() + " - "
					+ cardsList.get(position).getCno());
			TextView edit = (TextView) view.findViewById(R.id.tvEdit);
			edit.setText(R.string.edit);
			return view;
		}
	};

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
