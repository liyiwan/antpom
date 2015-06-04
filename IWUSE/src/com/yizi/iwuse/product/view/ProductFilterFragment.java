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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.yizi.iwuse.R;
import com.yizi.iwuse.general.MainHomeActivity;

/***
 * 筛选商品信息
 * 
 * @author zhangxiying
 *
 */
public class ProductFilterFragment extends Fragment {

	private ListView lv_filter;
	private ArrayList<String> selectArray;
	private TextSelectAdapter selectAdapter;
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
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
		lv_filter = (ListView)view.findViewById(R.id.lv_filter);
		selectArray = new ArrayList<String>();
		selectArray.add("absdfsaf");
		selectAdapter = new TextSelectAdapter();
		lv_filter.setAdapter(selectAdapter);
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
			tv_select.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Toast.makeText(getActivity(), "sgsdga", Toast.LENGTH_LONG).show();
				}
			});
			return convertView;
		}
		
	}
	
}
