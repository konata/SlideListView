package cn.beriru.slidelistview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import cn.beriru.view.SlideItem;
import cn.beriru.view.SlideListView;
public class LauncherActivity extends Activity {
	
	SlideListView mList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launcher);
		mList = (SlideListView) findViewById(R.id.slide_listview);
		mList.setAdapter(new BaseAdapter() {
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				SlideItem view = null;
				if(convertView != null){ // reset
					view = (SlideItem) convertView;
				}else{
					view = (SlideItem) View.inflate(getApplicationContext(), R.layout.slideitem, null);
				}
				return view;
			}
			
			@Override
			public long getItemId(int position) {
				return 0;
			}
			
			@Override
			public Object getItem(int position) {
				return null;
			}
			
			@Override
			public int getCount() {
				return 50;
			}
		});
	}

}
