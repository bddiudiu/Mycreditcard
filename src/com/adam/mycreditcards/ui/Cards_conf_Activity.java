package com.adam.mycreditcards.ui;

import java.util.ArrayList;
import java.util.List;
import com.adam.database.DatabaseService;
import com.adam.mycreditcards.R;
import com.adam.mycreditcards.R.layout;
import com.adam.mycreditcards.R.menu;
import com.adam.mycreditcards.entity.Cardsbean;

import android.R.integer;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Cards_conf_Activity extends Activity {
	private List<Cardsbean> cardsList = new ArrayList<Cardsbean>();
	private ListView lv;
	private SQLiteDatabase db;
	private Cursor myCursor;
	DatabaseService dbService = new DatabaseService(this);
	private ListAdapter listAdapter;
	String id;
	ImageButton ib_home;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cards_conf_);
		lv = (ListView) findViewById(R.id.listView_conf);
		init();
		
		
		ib_home = (ImageButton)findViewById(R.id.main_head_logo);
		ib_home.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(Cards_conf_Activity.this, MainActivity.class);
				startActivity(intent);
			}
		});
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
		
		listAdapter = new ListAdapter();
		lv.setAdapter(listAdapter);
		lv.setOnItemClickListener(new ListView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
				dbService.open();
				id = cardsList.get(position).getId().trim();
				Log.i("conf", id);
				dbService.delete(Integer.parseInt(id));
			}
		});
	}



	public class ListAdapter extends BaseAdapter {
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
		public void notifyDataSetChanged() {
			
			
			
		};

		@Override
		public View getView(final int position, View view, ViewGroup parent) {
			// TODO Auto-generated method stub
			view = getLayoutInflater().inflate(R.layout.listview, null);

			TextView tv = (TextView) view.findViewById(R.id.tvCardsName);
			tv.setText("" + cardsList.get(position).getCname() + " - "
					+ cardsList.get(position).getCno());
			TextView edit = (TextView) view.findViewById(R.id.tvEdit);
			edit.setText(R.string.delete);
			edit.setId(Integer.parseInt(cardsList.get(position).getId()));
			
			edit.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View view) {
					// TODO Auto-generated method stub
					try {
						dbService.delete(view.getId());
						cardsList.remove(position);
						lv.setAdapter(new ListAdapter());
					}
					catch (Exception e) {
						// TODO: handle exception
					}
				}
			});

			return view;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_cards_conf_, menu);
		return true;
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

}
