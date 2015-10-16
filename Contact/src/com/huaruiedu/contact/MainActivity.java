package com.huaruiedu.contact;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.provider.CallLog.Calls;
import android.provider.Contacts;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnCreateContextMenuListener;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private static final int PICK_CONTACT_SUBACTIVITY = 0;
	private ListView listView;
	private ImageView add;
	private ImageView message;
	private ImageView contacts;
	private ImageView bh;
	private ImageView jl;
	private List<Map<String, String>> list;
	private MyAdapter adapter;

	/*
	 * private String[] names=new
	 * String[]{"����ǿ","��ΰ","������","���Ӿ�","ϰ��ƽ","������","���"}; private String[]
	 * phones=new
	 * String[]{"13388997264","15007443218","18792961783","15974430394",
	 * "15576736445","15674400923","18776354432"};
	 */
	List<String> li = new ArrayList<String>();
	List<String> phones = new ArrayList<String>();
	List<String> addresses = new ArrayList<String>();

	private Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		intent = new Intent(getApplicationContext(), AddActivity.class);
		Intent i = getIntent();
		Bundle bs = null;
		if (i != null) {
			bs = i.getExtras();
		}

		// �ж�ˢ�����ݡ���һ�ν���ֱ��������޵����ݵ������У��޸Ļ��������֮����Ķ������е�����
		if (bs != null) {
			li = bs.getStringArrayList("names");
			phones = bs.getStringArrayList("phones");
			addresses = bs.getStringArrayList("addresses");
		} else {
			li.add("����ǿ");
			li.add("��ΰ");
			li.add("������");
			li.add("���Ӿ�");
			li.add("ϰ��ƽ");
			li.add("������");
			li.add("���");
			li.add("���ǿ");
			addresses.add("���ϳ�ɳ");
			addresses.add("��������");
			addresses.add("���ϳ���");
			addresses.add("��������");
			addresses.add("�Ϻ��ֶ�");
			addresses.add("���ϴ���");
			addresses.add("�����żҽ�");
			addresses.add("���Ϻ���");
			phones.add("13388997264");
			phones.add("15007443218");
			phones.add("18792961783");
			phones.add("13388997264");
			phones.add("15974430394");
			phones.add("15576736445");
			phones.add("15674400923");
			phones.add("18776354432");
		}
		// ��ʼ��
		initView();
	}

	private void initView() {
		add = (ImageView) findViewById(R.id.add);
		message = (ImageView) findViewById(R.id.message);
		contacts = (ImageView) findViewById(R.id.concat);
		bh = (ImageView) findViewById(R.id.bh);
		jl = (ImageView) findViewById(R.id.jl);

		listView = (ListView) findViewById(R.id.list);
		list = new ArrayList<Map<String, String>>();

		Map<String, String> map;
		for (int i = 0; i < li.size(); i++) {
			map = new HashMap<String, String>();
			map.put("img", R.drawable.face1 + i + "");
			map.put("name", li.get(i));
			map.put("phone", phones.get(i));
			map.put("address", addresses.get(i));
			list.add(map);
		}
		/*
		 * SimpleAdapter adapter = new SimpleAdapter(this, list,R.layout.info,
		 * new String[]{"img","name","phone"}, new
		 * int[]{R.id.info_img,R.id.info_name,R.id.info_phone});
		 */

		// �Զ���������
		adapter = new MyAdapter(this, list);
		listView.setAdapter(adapter);
		initMethod(this);

		// ��������
		listView.setOnCreateContextMenuListener(new OnCreateContextMenuListener() {
			@Override
			public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
				menu.setHeaderTitle("��ѡ�����");
				menu.add(0, 0, 0, "ɾ��");
				menu.add(0, 1, 0, "�༭");
			}
		});
	}

	// ѡ��֮���ȡѡ���е�ֵ
	public boolean onContextItemSelected(MenuItem item) {
		ContextMenuInfo info = item.getMenuInfo();
		AdapterView.AdapterContextMenuInfo contextMenuInfo = (AdapterContextMenuInfo) info;
		// ��ȡѡ����λ��
		int position = contextMenuInfo.position;
		if (item.getItemId() == 0) {
			li.remove(position);
			initView();
			return false;
		} else {
			Toast.makeText(getApplicationContext(), "��ѡ����Ǳ༭", 0).show();
		}

		/*
		 * Intent intent = new Intent(); intent.setAction(Intent.ACTION_PICK);
		 * intent.setData(Contacts.Phones.CONTENT_URI);
		 * startActivityForResult(intent, PICK_CONTACT_SUBACTIVITY);
		 */

		// ���༭
		intent.putExtra("userName", li.get(position));
		intent.putExtra("iphone", phones.get(position));
		intent.putExtra("address", addresses.get(position));
		intent.putExtra("p", position + "");
		Bundle b = new Bundle();
		b.putStringArrayList("names", (ArrayList<String>) li);
		b.putStringArrayList("phones", (ArrayList<String>) phones);
		b.putStringArrayList("addresses", (ArrayList<String>) addresses);
		intent.putExtras(b);
		startActivity(intent);
		// ����ˢ��
		initView();
		return false;
	}

	private void initMethod(final Context context) {
		// Intent
		// ����µ���ϵ��
		// ��Ϊû�������ݿ⣬��������ӵ�ʱ����Ҫ��֮ǰ�Ѿ����ڵ����ݼ��ϣ��ڴ˻����Ͻ������
		add.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// �����
				Bundle b = new Bundle();
				b.putStringArrayList("names", (ArrayList<String>) li);
				b.putStringArrayList("phones", (ArrayList<String>) phones);
				b.putStringArrayList("addresses", (ArrayList<String>) addresses);
				intent.putExtras(b);
				startActivity(intent);
			}
		});

		//��ϵ��
		contacts.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(context, "˧����ð���Լ��", 0).show();
			}
		});

		bh.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Uri telUri = Uri.parse("tel:10010");
				Intent intent = new Intent(Intent.ACTION_DIAL, telUri);
				startActivity(intent);
			}
		});

		message.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Uri smsToUri = Uri.parse("smsto:10010");
				Intent intent = new Intent(Intent.ACTION_SENDTO, smsToUri);
				startActivity(intent);
			}
		});

		jl.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				Intent intent = new Intent();

				intent.setAction(Intent.ACTION_CALL_BUTTON);

				startActivity(intent);

			}
		});

	}
}