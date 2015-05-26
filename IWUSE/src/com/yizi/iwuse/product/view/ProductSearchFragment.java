package com.yizi.iwuse.product.view;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.yizi.iwuse.R;
import com.yizi.iwuse.general.MainHomeActivity;

/***
 * 筛选商品信息
 * 
 * @author zhangxiying
 *
 */
public class ProductSearchFragment extends Fragment {

	private ImageView tv_close;
	private TextView tv_kitchen;
	private TextView tv_bathroom;
	private TextView tv_bedroom;
	private TextView tv_hall;
	private TextView tv_outdoor;
	private TextView tv_accessories;
	
	private TextView tv_usa_city;
	private TextView tv_jap_elegant;
	private TextView tv_ornate_palace;
	private TextView tv_nordic_simply;
	private TextView tv_asia_holiday;
	private TextView tv_modern_luxury;
	
	private NoScrollGridView gv_select;
	private ArrayList<String> selectArray;
	private TextSelectAdapter selectAdapter;
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		tv_close.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				((MainHomeActivity) getActivity()).closeProductSearch();
			}
		});

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.frg_productsearch, null);
		return view;
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		tv_close = (ImageView)view.findViewById(R.id.tv_close);
		tv_kitchen = (TextView)view.findViewById(R.id.tv_kitchen);
		tv_kitchen.setOnClickListener(new TextViewClick());
		tv_bathroom = (TextView)view.findViewById(R.id.tv_bathroom);
		tv_bathroom.setOnClickListener(new TextViewClick());
		tv_bedroom = (TextView)view.findViewById(R.id.tv_bedroom);
		tv_bedroom.setOnClickListener(new TextViewClick());
		tv_hall = (TextView)view.findViewById(R.id.tv_hall);
		tv_hall.setOnClickListener(new TextViewClick());
		tv_outdoor = (TextView)view.findViewById(R.id.tv_outdoor);
		tv_outdoor.setOnClickListener(new TextViewClick());
		tv_accessories = (TextView)view.findViewById(R.id.tv_accessories);
		tv_accessories.setOnClickListener(new TextViewClick());
		
		tv_usa_city = (TextView)view.findViewById(R.id.tv_usa_city);
		tv_usa_city.setOnClickListener(new TextViewClick());
		tv_jap_elegant = (TextView)view.findViewById(R.id.tv_jap_elegant);
		tv_jap_elegant.setOnClickListener(new TextViewClick());
		tv_ornate_palace = (TextView)view.findViewById(R.id.tv_ornate_palace);
		tv_ornate_palace.setOnClickListener(new TextViewClick());
		tv_nordic_simply = (TextView)view.findViewById(R.id.tv_nordic_simply);
		tv_nordic_simply.setOnClickListener(new TextViewClick());
		tv_asia_holiday = (TextView)view.findViewById(R.id.tv_asia_holiday);
		tv_asia_holiday.setOnClickListener(new TextViewClick());
		tv_modern_luxury = (TextView)view.findViewById(R.id.tv_modern_luxury);
		tv_modern_luxury.setOnClickListener(new TextViewClick());
		
		gv_select = (NoScrollGridView)view.findViewById(R.id.gv_select);
		selectArray = new ArrayList<String>();
		selectAdapter = new TextSelectAdapter();
		gv_select.setAdapter(selectAdapter);
	}

	private class TextViewClick implements OnClickListener{

		@Override
		public void onClick(View view) {
			// TODO Auto-generated method stub
			TextView textView = (TextView)view;
			if(textView.isSelected()){
				textView.setSelected(false);
				selectArray.remove(textView.getText().toString());
				selectAdapter.notifyDataSetChanged();
			}else{
				textView.setSelected(true);
				selectArray.add(textView.getText().toString());
				selectAdapter.notifyDataSetChanged();
			}
		}
		
	}
	
	private class TextSelectAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return selectArray.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return selectArray.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			convertView = LayoutInflater.from(getActivity()).inflate(R.layout.single_text, null);
			TextView tv_select = (TextView)convertView.findViewById(R.id.tv_select);
			String text = selectArray.get(position);
			tv_select.setText(text);
			return convertView;
		}
		
	}
	
}
