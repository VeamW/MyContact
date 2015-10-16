package com.huaruiedu.contact;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class AddActivity extends Activity {
	private EditText firstName;
	private EditText lastName;
	private EditText phone;
	private EditText message;
	private ImageButton submit;
	private EditText address;

	private Intent i;
	private Bundle b;
	private List<String> names;
	private List<String> phones;
	private List<String> addresses;
	private String position;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add);
		i = getIntent();
		b = i.getExtras();

		initView();

		// 点击编辑过来时显示信息（判断，因为编辑时有带姓名电话等信息过来，而第一次添加的时候没有）
		if (i.getStringExtra("userName") != null && i.getStringExtra("iphone") != null
				&& i.getStringExtra("iphone") != null && i.getStringExtra("address") != null) {
			firstName.setText(i.getStringExtra("userName").toString().substring(0, 1));
			lastName.setText(
					i.getStringExtra("userName").toString().subSequence(1, i.getStringExtra("userName").length()));
			phone.setText(i.getStringExtra("iphone").toString());
			message.setText(i.getStringExtra("iphone").toString());
			address.setText(i.getStringExtra("address").toString());

			position = i.getStringExtra("p");
		}

		submit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (position != null) {
					names.remove(Integer.parseInt(position.toString()));
					phones.remove(Integer.parseInt(position.toString()));
					addresses.remove(Integer.parseInt(position.toString()));
				}

				if (firstName.getText().toString().equals("") || firstName.getText().toString() == null
						|| lastName.getText().toString().equals("") || lastName.getText().toString() == null
						||phone.getText().toString().equals("")||phone.getText().toString()==null) {
					Toast.makeText(getApplicationContext(), "信息填写错误", 0).show();

					return;
				}

				// 添加数据
				names.add(firstName.getText() + "" + lastName.getText());
				phones.add(phone.getText().toString());
				addresses.add(address.getText().toString());

				b.putStringArrayList("names", (ArrayList<String>) names);
				b.putStringArrayList("phones", (ArrayList<String>) phones);
				Intent intent = new Intent(AddActivity.this, MainActivity.class);
				intent.putExtras(b);
				startActivity(intent);
			}
		});
	}

	// 初始化
	private void initView() {
		firstName = (EditText) findViewById(R.id.firstName);
		lastName = (EditText) findViewById(R.id.lastName);
		phone = (EditText) findViewById(R.id.phone);
		submit = (ImageButton) findViewById(R.id.submit);
		message = (EditText) findViewById(R.id.msg);
		address = (EditText) findViewById(R.id.address);

		// 重新添加信息到集合
		names = b.getStringArrayList("names");
		phones = b.getStringArrayList("phones");
		addresses = b.getStringArrayList("addresses");

	}
}
