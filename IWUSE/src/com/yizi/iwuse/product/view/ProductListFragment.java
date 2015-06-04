package com.yizi.iwuse.product.view;

import java.util.ArrayList;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.yizi.iwuse.R;
import com.yizi.iwuse.general.service.GeneralService;
import com.yizi.iwuse.product.model.ProductItem;
import com.yizi.iwuse.product.service.ProductService;
import com.yizi.iwuse.product.service.events.ProductEvent;
import com.yizi.iwuse.product.service.events.ThemeEvent;

import de.greenrobot.event.EventBus;

/**		单品页面
 * @author hehaodong
 *
 */
public class ProductListFragment extends Fragment {

	private GridView gv_product;
	/***单品宽度***/
	private int gridWidth = 0;
	/***单品数据***/
	private ArrayList<ProductItem> productArray;
	private FirstItemMaxAdapter adapter;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View mView = inflater.inflate(R.layout.frg_productlist, container,false);
		return mView;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		gv_product = (GridView)view.findViewById(R.id.gv_product);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
		EventBus.getDefault().register(this);
		
		WindowManager wm = (WindowManager) getActivity()
                .getSystemService(Context.WINDOW_SERVICE);

		int mScreenWidth = wm.getDefaultDisplay().getWidth();
        gridWidth = mScreenWidth/2-20;
        
		adapter = new FirstItemMaxAdapter();
		gv_product.setAdapter(adapter);
		
		ProductService server = new ProductService();
		server.doProductNetWork();
	}
	
	private class FirstItemMaxAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return productArray==null?0:productArray.size();
        }

        @Override
        public Object getItem(int i) {
            return productArray.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            view = LayoutInflater.from(getActivity()).inflate(R.layout.single_img, null);
            viewHolder = new ViewHolder();
            viewHolder.cover = (ImageView) view.findViewById(R.id.cover);
            
            view.setLayoutParams(new AbsListView.LayoutParams(gridWidth, gridWidth));
            
            viewHolder.cover.setScaleType(ImageView.ScaleType.CENTER_CROP);
            viewHolder.cover.setImageResource(productArray.get(position).themeUrl);
            return view;
        }

        class ViewHolder {
            TextView name;
            ImageView cover;
        }
    }

	public void onEventMainThread(ProductEvent event) {
		productArray = event.getProductArray();
		adapter.notifyDataSetChanged();
	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		EventBus.getDefault().unregister(this);
		super.onDestroy();
	}
	
}
