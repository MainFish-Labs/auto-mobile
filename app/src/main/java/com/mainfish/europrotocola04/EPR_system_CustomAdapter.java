package com.mainfish.europrotocola04;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by designer on 04/08/16.
 */
public class EPR_system_CustomAdapter  extends ArrayAdapter {
	private Context context;
	private int textViewResourceId;
	private String[] objects;
	public static boolean flag = false;
	public EPR_system_CustomAdapter(Context context, int textViewResourceId,
	                                String[] objects) {
		super(context, textViewResourceId, objects);
		this.context = context;
		this.textViewResourceId = textViewResourceId;
		this.objects = objects;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null)
			convertView = View.inflate(context, textViewResourceId, null);
		if (flag != false) {
			TextView tv = (TextView) convertView;
			tv.setText(objects[position]);
		}
		return convertView;
	}
}
