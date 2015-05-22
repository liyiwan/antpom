package com.yizi.iwuse.product.view;

import com.yizi.iwuse.R;
import com.yizi.iwuse.general.MainHomeActivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/***
 * 筛选商品信息
 * 
 * @author zhangxiying
 *
 */
public class ProductSearchFragment extends Fragment {

	private ImageView tv_close;
	
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
	}

	
}
