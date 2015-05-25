package com.yizi.iwuse.general.view;

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
import com.yizi.iwuse.general.view.WuseThemeFragment.Item;

/**		物件页面
 * @author hehaodong
 *
 */
public class ProductListFragment extends Fragment {

	private GridView gv_product;
	private int mScreenWidth = 0;
	private int gridWidth = 0;
	
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
		WindowManager wm = (WindowManager) getActivity()
                .getSystemService(Context.WINDOW_SERVICE);

        mScreenWidth = wm.getDefaultDisplay().getWidth();
        gridWidth = mScreenWidth/2-20;
        
		FirstItemMaxAdapter adapter = new FirstItemMaxAdapter();
		gv_product.setAdapter(adapter);
	}
	
	private class FirstItemMaxAdapter extends BaseAdapter {
        private ArrayList<Item> mDataSources;

        public FirstItemMaxAdapter() {
            mDataSources = new ArrayList<Item>();
            Item item0 = new Item("张一", R.drawable.image1, null);
            Item item1 = new Item("李二", R.drawable.image2, null);
            Item item2 = new Item("张三", R.drawable.image3, null);
            Item item3 = new Item("李四", R.drawable.image4, null);
            Item item4 = new Item("张五", R.drawable.image5, null);
            Item item5 = new Item("李六", R.drawable.image6, null);
            Item item6 = new Item("张七", R.drawable.image7, null);
            Item item7 = new Item("李八", R.drawable.image1, null);
            Item item8 = new Item("张九", R.drawable.image2, null);
            Item item9 = new Item("李十", R.drawable.image3, null);
            Item item10 = new Item("张十一", R.drawable.image4, null);
            Item item11 = new Item("李十二", R.drawable.image5, null);
            Item item12 = new Item("张十三", R.drawable.image6, null);
            Item item13 = new Item("李十四", R.drawable.image7, null);
            Item item14 = new Item("张十五", R.drawable.image1, null);
            Item item15 = new Item("李十六", R.drawable.image2, null);
            Item item16 = new Item("张十七", R.drawable.image3, null);
            Item item17 = new Item("李十八", R.drawable.image4, null);
            Item item18 = new Item("张十九", R.drawable.image5, null);
            Item item19 = new Item("李二十", R.drawable.image6, null);
            Item item20 = new Item("张十一", R.drawable.image4, null);
            Item item21 = new Item("李十二", R.drawable.image5, null);
            Item item22 = new Item("张十三", R.drawable.image6, null);
            Item item23 = new Item("李十四", R.drawable.image7, null);
            Item item24 = new Item("张十五", R.drawable.image1, null);
            Item item25 = new Item("李十六", R.drawable.image2, null);
            Item item26 = new Item("张十七", R.drawable.image3, null);
            Item item27 = new Item("李十八", R.drawable.image4, null);
            Item item28 = new Item("张十九", R.drawable.image5, null);
            Item item29 = new Item("李二十", R.drawable.image6, null);
            Item item30 = new Item("张十一", R.drawable.image4, null);
            Item item31 = new Item("李十二", R.drawable.image5, null);
            Item item32 = new Item("张十三", R.drawable.image6, null);
            Item item33 = new Item("李十四", R.drawable.image7, null);
            Item item34 = new Item("张十五", R.drawable.image1, null);
            Item item35 = new Item("李十六", R.drawable.image2, null);
            Item item36 = new Item("张十七", R.drawable.image3, null);
            Item item37 = new Item("李十八", R.drawable.image4, null);
            Item item38 = new Item("张十九", R.drawable.image5, null);
            Item item39 = new Item("李二十", R.drawable.image6, null);
            Item item40 = new Item("张十一", R.drawable.image4, null);
            Item item41 = new Item("李十二", R.drawable.image5, null);
            Item item42 = new Item("张十三", R.drawable.image6, null);
            Item item43 = new Item("李十四", R.drawable.image7, null);
            Item item44 = new Item("张十五", R.drawable.image1, null);
            Item item45 = new Item("李十六", R.drawable.image2, null);
            Item item46 = new Item("张十七", R.drawable.image3, null);
            Item item47 = new Item("李十八", R.drawable.image4, null);
            Item item48 = new Item("张十九", R.drawable.image5, null);
            Item item49 = new Item("李二十", R.drawable.image6, null);
            mDataSources.add(item0);
            mDataSources.add(item1);
            mDataSources.add(item2);
            mDataSources.add(item3);
            mDataSources.add(item4);
            mDataSources.add(item5);
            mDataSources.add(item6);
            mDataSources.add(item7);
            mDataSources.add(item8);
            mDataSources.add(item9);
            mDataSources.add(item10);
            mDataSources.add(item11);
            mDataSources.add(item12);
            mDataSources.add(item13);
            mDataSources.add(item14);
            mDataSources.add(item15);
            mDataSources.add(item16);
            mDataSources.add(item17);
            mDataSources.add(item18);
            mDataSources.add(item19);
            mDataSources.add(item20);
            mDataSources.add(item21);
            mDataSources.add(item22);
            mDataSources.add(item23);
            mDataSources.add(item24);
            mDataSources.add(item25);
            mDataSources.add(item26);
            mDataSources.add(item27);
            mDataSources.add(item28);
            mDataSources.add(item29);
            mDataSources.add(item30);
            mDataSources.add(item31);
            mDataSources.add(item32);
            mDataSources.add(item33);
            mDataSources.add(item34);
            mDataSources.add(item35);
            mDataSources.add(item36);
            mDataSources.add(item37);
            mDataSources.add(item38);
            mDataSources.add(item39);
            mDataSources.add(item40);
            mDataSources.add(item41);
            mDataSources.add(item42);
            mDataSources.add(item43);
            mDataSources.add(item44);
            mDataSources.add(item45);
            mDataSources.add(item46);
            mDataSources.add(item47);
            mDataSources.add(item48);
            mDataSources.add(item49);
        }

        @Override
        public int getCount() {
            return mDataSources.size();
        }

        @Override
        public Object getItem(int i) {
            return mDataSources.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            view = LayoutInflater.from(getActivity()).inflate(R.layout.first_item_max_item, null);
            viewHolder = new ViewHolder();
            viewHolder.cover = (ImageView) view.findViewById(R.id.cover);
            
            view.setLayoutParams(new AbsListView.LayoutParams(gridWidth, gridWidth));
            
            viewHolder.cover.setScaleType(ImageView.ScaleType.CENTER_CROP);
            viewHolder.cover.setImageResource(mDataSources.get(position).imgId);
            return view;
        }

        class ViewHolder {
            TextView name;
            ImageView cover;
        }
    }

    class Item {
        String name;
        int imgId;
        String videoUrl;

        Item(String name, int imgId, String videoUrl) {
            this.name = name;
            this.imgId = imgId;
            this.videoUrl = videoUrl;
        }
    }
    
	
}
