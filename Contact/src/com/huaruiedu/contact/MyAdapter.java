package com.huaruiedu.contact;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
public class MyAdapter extends BaseAdapter {
	private View view;
	private Context context;
	private List<Map<String, String>> items;
	final class ListItemView extends ListView{
		public ListItemView(Context context) {
			super(context);
		}
		private ImageView img;
		private TextView name;
		private TextView phone;
		private TextView address;
	}

	
	public MyAdapter(Context context, List<Map<String, String>> items) {
		this.context = context;
		this.items = items;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return items.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		view=convertView;
		ListItemView l = null;
		if (view==null) {
			l = new ListItemView(context);
			LayoutInflater in=LayoutInflater.from(context);
			view = in.inflate(R.layout.info, null, false);
			
			ImageView img = (ImageView) view.findViewById(R.id.info_img);
			TextView name = (TextView) view.findViewById(R.id.info_name);
			TextView phone = (TextView) view.findViewById(R.id.info_phone);
			TextView address = (TextView) view.findViewById(R.id.info_address);
			
			l.img = img;
			l.name=  name;
			l.phone = phone;
			l.address = address;
			
			view.setTag(l);
			
			l.img.setImageResource(R.drawable.face1+position);
			l.name.setText(items.get(position).get("name"));
			l.phone.setText(items.get(position).get("phone"));
			l.address.setText(items.get(position).get("address"));
			
			
			//Ìí¼ÓÊÂ¼þ
		/*	view.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Toast.makeText(context, items.get(position).toString(), 0).show();
				}
			});*/
			
			
		}
		
		return view;
	}

}
