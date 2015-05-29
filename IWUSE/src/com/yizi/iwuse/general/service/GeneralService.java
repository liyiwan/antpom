package com.yizi.iwuse.general.service;

import java.util.ArrayList;
import java.util.List;

import com.yizi.iwuse.R;
import com.yizi.iwuse.common.base.ICoreService;
import com.yizi.iwuse.framework.model.CmdResultInfo;
import com.yizi.iwuse.framework.service.CmdSendAdapter;
import com.yizi.iwuse.framework.service.MsgInterface;
import com.yizi.iwuse.framework.service.MsgInterface.CmdInterface;
import com.yizi.iwuse.general.model.ProductItem;
import com.yizi.iwuse.general.model.ThemeItem;
import com.yizi.iwuse.general.service.events.ProductEvent;
import com.yizi.iwuse.general.service.events.ThemeEvent;

import de.greenrobot.event.EventBus;

/****
 * 公共/综合业务类
 * 
 * @author zhangxiying
 *
 */
public class GeneralService implements ICoreService {

	/***主题数据***/
	private ArrayList<ThemeItem> themeArray;
	/***单品数据***/
	private ArrayList<ProductItem> productArray;

	@Override
	public boolean initState() {
		return true;
	}

	public List getThemeImg() {
		CmdResultInfo retObj = CmdSendAdapter.sendCmd(
				CmdInterface.General_AdvertGuide, null);

		return null;
	}

	public void getNetData() {
		themeArray = new ArrayList<ThemeItem>();
		ThemeItem item0 = new ThemeItem("张一", R.drawable.image1, "分类类型", "视频");
		ThemeItem item1 = new ThemeItem("李二", R.drawable.image2, "分类类型", "图片");
		ThemeItem item2 = new ThemeItem("张三", R.drawable.image3, "分类类型", "图片");
		ThemeItem item3 = new ThemeItem("李四", R.drawable.image4, "分类类型", "图片");
		ThemeItem item4 = new ThemeItem("张五", R.drawable.image5, "分类类型", "图片");
		ThemeItem item5 = new ThemeItem("李六", R.drawable.image6, "分类类型", "图片");
		ThemeItem item7 = new ThemeItem("李八", R.drawable.image1, "分类类型", "图片");
		ThemeItem item8 = new ThemeItem("张九", R.drawable.image2, "分类类型", "图片");
		ThemeItem item9 = new ThemeItem("李十", R.drawable.image3, "分类类型", "图片");
		ThemeItem item10 = new ThemeItem("张十一", R.drawable.image4, "分类类型", "图片");
		ThemeItem item11 = new ThemeItem("李十二", R.drawable.image5, "分类类型", "图片");
		ThemeItem item12 = new ThemeItem("张十三", R.drawable.image6, "分类类型", "图片");
		themeArray.add(item0);
		themeArray.add(item1);
		themeArray.add(item2);
		themeArray.add(item3);
		themeArray.add(item4);
		themeArray.add(item5);
		themeArray.add(item7);
		themeArray.add(item8);
		themeArray.add(item9);
		themeArray.add(item10);
		themeArray.add(item11);
		themeArray.add(item12);
	}

	public void getProductNetData() {
		productArray = new ArrayList<ProductItem>();
		ProductItem item0 = new ProductItem("张一", R.drawable.image1, "分类类型", "图片");
		ProductItem item1 = new ProductItem("李二", R.drawable.image2, "分类类型", "图片");
		ProductItem item2 = new ProductItem("张三", R.drawable.image3, "分类类型", "图片");
		ProductItem item3 = new ProductItem("李四", R.drawable.image4, "分类类型", "图片");
		ProductItem item4 = new ProductItem("张五", R.drawable.image5, "分类类型", "图片");
		ProductItem item5 = new ProductItem("李六", R.drawable.image6, "分类类型", "图片");
		ProductItem item7 = new ProductItem("李八", R.drawable.image1, "分类类型", "图片");
		ProductItem item8 = new ProductItem("张九", R.drawable.image2, "分类类型", "图片");
		ProductItem item9 = new ProductItem("李十", R.drawable.image3, "分类类型", "图片");
		ProductItem item10 = new ProductItem("张十一", R.drawable.image4, "分类类型", "图片");
		ProductItem item11 = new ProductItem("李十二", R.drawable.image5, "分类类型", "图片");
		ProductItem item12 = new ProductItem("张十三", R.drawable.image6, "分类类型", "图片");
		productArray.add(item0);
		productArray.add(item1);
		productArray.add(item2);
		productArray.add(item3);
		productArray.add(item4);
		productArray.add(item5);
		productArray.add(item7);
		productArray.add(item8);
		productArray.add(item9);
		productArray.add(item10);
		productArray.add(item11);
		productArray.add(item12);
	}

	public void doNetWork() {
		getNetData();
		EventBus.getDefault().post(new ThemeEvent(themeArray));
	}

	public void doProductNetWork() {
		getProductNetData();
		EventBus.getDefault().post(new ProductEvent(productArray));
	}

}
