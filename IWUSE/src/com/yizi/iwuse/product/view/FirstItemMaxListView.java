/*
 * Copyright 2014 Lars wufenglong
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.yizi.iwuse.product.view;

import com.yizi.iwuse.R;
import com.yizi.iwuse.common.widget.ThemeVideoWidget;
import com.yizi.iwuse.common.widget.VideoThread;
import com.yizi.iwuse.product.model.ThemeItem;
import com.yizi.iwuse.product.view.WuseThemeFragment.ViewHolder;

import android.animation.IntEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.AbsListView;
import android.widget.ListView;

/**
 * 通过手势滑动控制Listview放大缩小
 * 
 */
public class FirstItemMaxListView extends ListView {
	private Context context;
	/***item标注高度***/
	private int ITEM_HEIGHT;
	/***item最大高度***/
	private int mITEM_MAX_HEIGHT = 0;
	private int mLastFirstVisiblePosition = 0;
	private int distanceOneItem;// 记录滚动距离，向上滚动时-ITEM_HEIGHT到0，向下滚动是0到ITEM_HEIGHT,当listview
								// FirstVisiblePosition 设置为0
	private int mLastDistanceOneItem = 1;
	
	private GestureDetector mGestureDetector;
	/***手指接触屏幕时，看到listview的第一个item的位置***/
	private int downVisiblePosition = 0;
	/***手指是否有接触屏幕***/
	public static boolean isFingerPress = false;
	/***手指接触屏幕时，listview的第一个item视图***/
	private View downView;

	public FirstItemMaxListView(Context context) {
		super(context);
		setLongClickable(true);
		setDividerHeight(0);
//		initOld(context);
		init(context);
	}

	public FirstItemMaxListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		setLongClickable(true);
		setDividerHeight(0);
//		initOld(context);
		init(context);
	}
	
	/**		旧的手势代码
	 * @param context
	 */
	private void initOld(Context context) {
		
		mGestureDetector = new GestureDetector(context,
					new GestureDetector.OnGestureListener() {
						@Override
						public boolean onDown(MotionEvent e) {
							return true;
						}

						@Override
						public void onShowPress(MotionEvent e) {

						}

						@Override
						public boolean onSingleTapUp(MotionEvent e) {
							System.out.println("onSingleTapUp");
							return false;
						}

						@SuppressLint("NewApi")
						@Override
						public boolean onScroll(MotionEvent e1, MotionEvent e2,
								float distanceX, float distanceY) {
//							smoothScrollBy(Math.round(distanceY), 0);

							//在distanceY这个距离，通过  canScrollVertically 判断是否能滚动
							if (canScrollVertically(Math.round(distanceY))) {
								distanceOneItem += Math.round(distanceY);
							} else {//不能滚动
								distanceOneItem = 0;
								if (distanceY > 0) {//向上拉动
									mLastDistanceOneItem = -1;
								} else {//向下拉动
									mLastDistanceOneItem = 1;
								}
							}

							//getFirstVisiblePosition()获取可见的第一个item的位置
							if (getFirstVisiblePosition() == mLastFirstVisiblePosition) {
								System.out.println("他们相等   distanceOneItem = " + distanceOneItem
									 + " mLastDistanceOneItem = " + mLastDistanceOneItem
									 + " mLastFirstVisiblePosition = " + mLastFirstVisiblePosition);
								//向下拉动
								if ((distanceY < 0 && (mLastDistanceOneItem >= 0 && distanceOneItem < 0))
										//向上拉动
										|| (distanceY > 0 && (mLastDistanceOneItem < 0 && distanceOneItem >= 0))) {// 从正变负或从负变正，但是firstposition没变
									//return false 后面的事件不处理
									System.out.println("返回false");
									return false;
								} else {
									mLastDistanceOneItem = distanceOneItem;
								}
								mLastFirstVisiblePosition = getFirstVisiblePosition();
							} else {
								System.out.println("他们不相等   distanceOneItem = " + distanceOneItem
									 + " mLastDistanceOneItem = " + mLastDistanceOneItem
									 + " mLastFirstVisiblePosition = " + mLastFirstVisiblePosition);
								mLastFirstVisiblePosition = getFirstVisiblePosition();
								distanceOneItem = 0;
								if (distanceY > 0) {
									mLastDistanceOneItem = 1;
								} else {
									mLastDistanceOneItem = -1;
								}
							}
							changeItemHeightOnScrollOld();
							//return false 后面的事件不处理
							return false;
						}

						@Override
						public void onLongPress(MotionEvent e) {

						}

						@Override
						public boolean onFling(MotionEvent e1, MotionEvent e2,
								float velocityX, float velocityY) {
							System.out.println("onFling");
							return true;
						}
					});
		
		this.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				return mGestureDetector.onTouchEvent(event);
			}
		});
	}

	/**		新的手势代码
	 * @param context
	 */
	@SuppressLint("NewApi")
	private void init(Context context) {
		
		mGestureDetector = new GestureDetector(context,
					new GestureDetector.OnGestureListener() {
						@Override
						public boolean onDown(MotionEvent e) {
							mLastFirstVisiblePosition = getFirstVisiblePosition();
							return true;
						}

						@Override
						public void onShowPress(MotionEvent e) {

						}

						@Override
						public boolean onSingleTapUp(MotionEvent e) {
							System.out.println("onSingleTapUp");
							return false;
						}

						@SuppressLint("NewApi")
						@Override
						public boolean onScroll(MotionEvent e1, MotionEvent e2,
								float distanceX, float distanceY) {
//							smoothScrollBy(Math.round(distanceY), 0);

							//在distanceY这个距离，通过  canScrollVertically 判断是否能滚动
							if (canScrollVertically(Math.round(distanceY))) {
								distanceOneItem += Math.round(distanceY);
							} else {//不能滚动
								distanceOneItem = 0;
								if (distanceY > 0) {//向上拉动
									mLastDistanceOneItem = -1;
								} else {//向下拉动
									mLastDistanceOneItem = 1;
								}
							}
							System.out.println("distanceOneItem = " + distanceOneItem
									 + " mLastDistanceOneItem = " + mLastDistanceOneItem
									 + " mLastFirstVisiblePosition = " + mLastFirstVisiblePosition);

							/*if(distanceY < 0){
								if(getFirstVisiblePosition() == mLastFirstVisiblePosition){
										return false;
								}
							}
							if(distanceY > 0){
								if(getFirstVisiblePosition() != mLastFirstVisiblePosition){
									distanceOneItem = 0;
									mLastFirstVisiblePosition = getFirstVisiblePosition();
								}
							}*/
							//getFirstVisiblePosition()获取可见的第一个item的位置
							if (getFirstVisiblePosition() == mLastFirstVisiblePosition) {
								System.out.println("他们相等");
								//向下拉动
								if ((distanceY < 0 && (mLastDistanceOneItem >= 0 && distanceOneItem < 0))
										//向上拉动
										|| (distanceY > 0 && (mLastDistanceOneItem < 0 && distanceOneItem >= 0))) {// 从正变负或从负变正，但是firstposition没变
									//return false 后面的事件不处理
									System.out.println("返回false");
									return false;
								} else {
									mLastDistanceOneItem = distanceOneItem;
								}
								mLastFirstVisiblePosition = getFirstVisiblePosition();
							} else {
								System.out.println("他们不相等");
								mLastFirstVisiblePosition = getFirstVisiblePosition();
								distanceOneItem = 0;
								if (distanceY > 0) {
									mLastDistanceOneItem = 1;
								} else {
									mLastDistanceOneItem = -1;
								}
							}
							changeItemHeightOnScroll();
							//return false 后面的事件不处理
							return false;
						}

						@Override
						public void onLongPress(MotionEvent e) {

						}

						@Override
						public boolean onFling(MotionEvent e1, MotionEvent e2,
								float velocityX, float velocityY) {
							System.out.println("onFling");
							return true;
						}
					});
		
		this.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					downVisiblePosition = getFirstVisiblePosition();
					downView = getChildAt(0);
					isFingerPress = true;
					break;
				case MotionEvent.ACTION_UP:
					System.out.println("手指离开屏幕");
					View item0 = getChildAt(0);
					View item1 = getChildAt(1);
					View item2 = getChildAt(2);
					View item3 = getChildAt(3);
					View whichVideo;
					int height = item0.getHeight();
					/*if (distanceOneItem > 0) {
						mLastDistanceOneItem = -1;
					} else {
						mLastDistanceOneItem = 1;
					}*/
					mLastDistanceOneItem = 1;
					distanceOneItem = 0;
					int firstVisiblePosition;
					if(height > mITEM_MAX_HEIGHT/3*2){
						whichVideo = item0;
						firstVisiblePosition = getFirstVisiblePosition() ;
//						if(firstVisiblePosition != downVisiblePosition){
//							distanceOneItem = (firstVisiblePosition - downVisiblePosition)*ITEM_HEIGHT;
//						}
						setSelection(firstVisiblePosition);
						
						if(item0 != null){
//							LargeView largeView = new LargeView(item0);
//							ObjectAnimator.ofInt(largeView,
//									"height", mITEM_MAX_HEIGHT).setDuration(500).start();
							item0.setLayoutParams(new AbsListView.LayoutParams(
								AbsListView.LayoutParams.MATCH_PARENT, mITEM_MAX_HEIGHT));
							ViewHolder viewHolder = (ViewHolder)item0.getTag();
							if(viewHolder != null){
								viewHolder.tv_grey.getBackground().setAlpha(0);
							}
						}
						if(item1 != null){
							item1.setLayoutParams(new AbsListView.LayoutParams(
								AbsListView.LayoutParams.MATCH_PARENT, ITEM_HEIGHT));
							ViewHolder viewHolder = (ViewHolder)item1.getTag();
							if(viewHolder != null){
								viewHolder.tv_grey.getBackground().setAlpha(255);
							}
						}
						if(item2 != null){
							item2.setLayoutParams(new AbsListView.LayoutParams(
								AbsListView.LayoutParams.MATCH_PARENT, ITEM_HEIGHT));
						}
					}else{
						whichVideo = item1;
						firstVisiblePosition = getFirstVisiblePosition() + 1;
//						if(firstVisiblePosition != downVisiblePosition){
//							distanceOneItem = (firstVisiblePosition - downVisiblePosition)*ITEM_HEIGHT;
//						}
						setSelection(firstVisiblePosition);
						
						if(item0 != null){
							item0.setLayoutParams(new AbsListView.LayoutParams(
								AbsListView.LayoutParams.MATCH_PARENT, ITEM_HEIGHT));
						}
						if(item1 != null){
							item1.setLayoutParams(new AbsListView.LayoutParams(
								AbsListView.LayoutParams.MATCH_PARENT, mITEM_MAX_HEIGHT));
							ViewHolder viewHolder = (ViewHolder)item1.getTag();
							if(viewHolder != null){
								viewHolder.tv_grey.getBackground().setAlpha(0);
							}
						}
						if(item2 != null){
							item2.setLayoutParams(new AbsListView.LayoutParams(
								AbsListView.LayoutParams.MATCH_PARENT, ITEM_HEIGHT));
							ViewHolder viewHolder = (ViewHolder)item2.getTag();
							if(viewHolder != null){
								viewHolder.tv_grey.getBackground().setAlpha(255);
							}
						}
						if(item3 != null){
							item3.setLayoutParams(new AbsListView.LayoutParams(
								AbsListView.LayoutParams.MATCH_PARENT, ITEM_HEIGHT));
						}
					}
					
					if(firstVisiblePosition != downVisiblePosition){
						ViewHolder videoHolder = (ViewHolder) downView.getTag();
						if(videoHolder != null){
							if(videoHolder.videoView != null){
								ThemeVideoWidget videoWidget = (ThemeVideoWidget)videoHolder.videoView;
								if(videoWidget.getPlayer() != null){
									videoWidget.getPlayer().stop();
									videoWidget.getPlayer().release();
									videoHolder.surface.setVisibility(View.GONE);
									videoHolder.cover.setVisibility(View.VISIBLE);
									videoWidget.setPlayer(null);
									videoHolder.videoView = null;
								}
							}
						}
					}
					mLastFirstVisiblePosition = firstVisiblePosition;
					ViewHolder viewHolder = (ViewHolder)whichVideo.getTag();
					if(viewHolder != null){
						ThemeItem themeItem = (ThemeItem)viewHolder.object;
//						viewHolder.tv_grey.getBackground().setAlpha(0);
						if("视频".equals(themeItem.property)){
							if(viewHolder.videoView == null){
								new VideoThread(viewHolder).start();
							}
						}
					}
					isFingerPress = false;
					break;
				}
				return mGestureDetector.onTouchEvent(event);
			}
		});
	}
	
	/**		旧的代码
	 * 		滚动时item高度变化代码
	 */
	private void changeItemHeightOnScrollOld() {
		View item0 = getChildAt(0);
		View item1 = getChildAt(1);

		int changeHeight1;
		int change;
		int changeHeight;
		if (distanceOneItem == 0)
			return;
		if (distanceOneItem > 0) {
			changeHeight1 = distanceOneItem * mITEM_MAX_HEIGHT / ITEM_HEIGHT;// 放大

			if (changeHeight1 > mITEM_MAX_HEIGHT) {
				changeHeight1 = mITEM_MAX_HEIGHT;
			}
			if (changeHeight1 <= ITEM_HEIGHT) {
				changeHeight1 = ITEM_HEIGHT;
			}
			change = changeHeight1 - item1.getHeight();
			changeHeight = item0.getHeight() - change;
			if (changeHeight > mITEM_MAX_HEIGHT) {
				changeHeight = mITEM_MAX_HEIGHT;
			}
			if (changeHeight <= ITEM_HEIGHT) {
				changeHeight = ITEM_HEIGHT;
			}
			System.out.println("放大过程changeHeight = " + changeHeight 
					+ " change = " + change + " changeHeight1= " + changeHeight1 
					+ " distanceOneItem = " + distanceOneItem);
			if (changeHeight == ITEM_HEIGHT) {
				ViewHolder videoHolder = (ViewHolder) item0.getTag();
				if(videoHolder != null){
					if(videoHolder.videoView != null){
						ThemeVideoWidget videoWidget = (ThemeVideoWidget)videoHolder.videoView;
						if(videoWidget.getPlayer() != null){
							videoHolder.videoView = null;
							videoWidget.getPlayer().stop();
							videoWidget.getPlayer().release();
							videoWidget.setPlayer(null);
						}
					}
				}
			}
			item0.setLayoutParams(new AbsListView.LayoutParams(
					AbsListView.LayoutParams.MATCH_PARENT, changeHeight));
			item1.setLayoutParams(new AbsListView.LayoutParams(
					AbsListView.LayoutParams.MATCH_PARENT, changeHeight1));
		} else {
			changeHeight1 = (ITEM_HEIGHT + distanceOneItem) * mITEM_MAX_HEIGHT
			/ ITEM_HEIGHT;// 缩小
			if (changeHeight1 > mITEM_MAX_HEIGHT) {
				changeHeight1 = mITEM_MAX_HEIGHT;
			}
			if (changeHeight1 <= ITEM_HEIGHT) {
				changeHeight1 = ITEM_HEIGHT;
			}
			change = item1.getHeight() - changeHeight1;
			changeHeight = item0.getHeight() + change;// 放大
			if (changeHeight > mITEM_MAX_HEIGHT) {
				changeHeight = mITEM_MAX_HEIGHT;
			}
			if (changeHeight <= ITEM_HEIGHT) {
				changeHeight = ITEM_HEIGHT;
			}
			System.out.println("缩小过程changeHeight = " + changeHeight + " change = " + change + " changeHeight1= " + changeHeight1);
			if (changeHeight == ITEM_HEIGHT) {
				ViewHolder videoHolder = (ViewHolder) item0.getTag();
				if(videoHolder != null){
					if(videoHolder.videoView != null){
						ThemeVideoWidget videoWidget = (ThemeVideoWidget)videoHolder.videoView;
						if(videoWidget.getPlayer() != null){
							videoHolder.videoView = null;
							videoWidget.getPlayer().stop();
							videoWidget.getPlayer().release();
							videoWidget.setPlayer(null);
						}
					}
				}
			}
			item0.setLayoutParams(new AbsListView.LayoutParams(
					AbsListView.LayoutParams.MATCH_PARENT, changeHeight));
			item1.setLayoutParams(new AbsListView.LayoutParams(
					AbsListView.LayoutParams.MATCH_PARENT, changeHeight1));
		}
	}

	/**		新的代码
	 * 		滚动时item高度变化代码
	 */
	/**
	 * 
	 */
	/**
	 * 
	 */
	/**
	 * 
	 */
	private void changeItemHeightOnScroll() {
		View item0 = getChildAt(0);
		View item1 = getChildAt(1);
		View item2 = getChildAt(2);
		View item3 = getChildAt(3);
		int changeHeight1;
		int change;
		int changeHeight;
		if (distanceOneItem == 0)
			return;
		if (distanceOneItem > 0) {// 向上第一个缩小
			changeHeight1 = distanceOneItem * mITEM_MAX_HEIGHT / ITEM_HEIGHT;

			if (changeHeight1 > mITEM_MAX_HEIGHT) {
				changeHeight1 = mITEM_MAX_HEIGHT;
			}
			if (changeHeight1 <= ITEM_HEIGHT) {
				changeHeight1 = ITEM_HEIGHT;
			}
			change = changeHeight1 - item1.getHeight();
			changeHeight = item0.getHeight() - change;
			if (changeHeight > mITEM_MAX_HEIGHT) {
				changeHeight = mITEM_MAX_HEIGHT;
			}
			if (changeHeight <= ITEM_HEIGHT) {
				changeHeight = ITEM_HEIGHT;
			}
			System.out.println("放大过程changeHeight = " + changeHeight 
					+ " change = " + change + " changeHeight1= " + changeHeight1 
					+ " distanceOneItem = " + distanceOneItem);
			if (changeHeight == ITEM_HEIGHT) {
				ViewHolder videoHolder = (ViewHolder) item0.getTag();
				if(videoHolder != null){
					if(videoHolder.videoView != null){
						ThemeVideoWidget videoWidget = (ThemeVideoWidget)videoHolder.videoView;
						if(videoWidget.getPlayer() != null){
							videoHolder.surface.setVisibility(View.GONE);
							videoHolder.cover.setVisibility(View.VISIBLE);
							videoHolder.videoView = null;
							videoWidget.getPlayer().stop();
							videoWidget.getPlayer().release();
							videoWidget.setPlayer(null);
						}
					}
				}
			}
			item0.setLayoutParams(new AbsListView.LayoutParams(
					AbsListView.LayoutParams.MATCH_PARENT, changeHeight));
			item1.setLayoutParams(new AbsListView.LayoutParams(
					AbsListView.LayoutParams.MATCH_PARENT, changeHeight1));
			ViewHolder videoHolder1 = (ViewHolder) item1.getTag();
			float fl_ratio = (float)(((float)changeHeight1 - (float)ITEM_HEIGHT)/((float)mITEM_MAX_HEIGHT - (float)ITEM_HEIGHT));
			int alpha = (int)Math.floor(255*(1-fl_ratio));
			videoHolder1.tv_grey.getBackground().setAlpha(alpha);
			if(item2 != null){
				ViewHolder videoHolder2 = (ViewHolder) item2.getTag();
				videoHolder2.tv_grey.getBackground().setAlpha(255);
			}
			if(item3 != null){
				ViewHolder videoHolder3 = (ViewHolder) item3.getTag();
				videoHolder3.tv_grey.getBackground().setAlpha(255);
			}
		}else{// 向下第一个放大
			changeHeight1 = (ITEM_HEIGHT + distanceOneItem) * mITEM_MAX_HEIGHT
					/ ITEM_HEIGHT;
			if (changeHeight1 > mITEM_MAX_HEIGHT) {
				changeHeight1 = mITEM_MAX_HEIGHT;
			}
			if (changeHeight1 <= ITEM_HEIGHT) {
				changeHeight1 = ITEM_HEIGHT;
			}
			change = item1.getHeight() - changeHeight1;
			changeHeight = item0.getHeight() + change;
			if (changeHeight > mITEM_MAX_HEIGHT) {
				changeHeight = mITEM_MAX_HEIGHT;
			}
			if (changeHeight <= ITEM_HEIGHT) {
				changeHeight = ITEM_HEIGHT;
			}
			System.out.println("缩小过程changeHeight = " + changeHeight + " change = " + change + " changeHeight1= " + changeHeight1);
			if (changeHeight == ITEM_HEIGHT) {
				ViewHolder videoHolder = (ViewHolder) item0.getTag();
				if(videoHolder != null){
					if(videoHolder.videoView != null){
						ThemeVideoWidget videoWidget = (ThemeVideoWidget)videoHolder.videoView;
						if(videoWidget.getPlayer() != null){
							videoHolder.surface.setVisibility(View.GONE);
							videoHolder.cover.setVisibility(View.VISIBLE);
							videoHolder.videoView = null;
							videoWidget.getPlayer().stop();
							videoWidget.getPlayer().release();
							videoWidget.setPlayer(null);
						}
					}
				}
			}
			item0.setLayoutParams(new AbsListView.LayoutParams(
					AbsListView.LayoutParams.MATCH_PARENT, changeHeight));
			item1.setLayoutParams(new AbsListView.LayoutParams(
					AbsListView.LayoutParams.MATCH_PARENT, changeHeight1));
			ViewHolder videoHolder0 = (ViewHolder) item0.getTag();
			videoHolder0.tv_grey.getBackground().setAlpha(0);
			ViewHolder videoHolder1 = (ViewHolder) item1.getTag();
			float fl_ratio = (float)(((float)changeHeight1 - (float)ITEM_HEIGHT)/((float)mITEM_MAX_HEIGHT - (float)ITEM_HEIGHT));
			int alpha = (int)Math.floor(255*(1-fl_ratio));
			videoHolder1.tv_grey.getBackground().setAlpha(alpha);
		}
	}

	public int getItemHeight() {
		return ITEM_HEIGHT;
	}

	public void setItemHeight(int itemHeight) {
		this.ITEM_HEIGHT = itemHeight;
	}

	public int getItemMaxHeight() {
		return mITEM_MAX_HEIGHT;
	}

	public void setItemMaxHeight(int itemMaxHeight) {
		this.mITEM_MAX_HEIGHT = itemMaxHeight;
	}

	/**
	 * Check if this view can be scrolled vertically in a certain direction.
	 *
	 * @param direction
	 *            Negative to check scrolling up, positive to check scrolling
	 *            down.
	 * @return true if this view can be scrolled in the specified direction,
	 *         false otherwise.
	 */
	public boolean canScrollVertically(int direction) {
		final int offset = computeVerticalScrollOffset();
		final int range = computeVerticalScrollRange()
				- computeVerticalScrollExtent();
		if (range == 0)
			return false;
		if (direction < 0) {
			return offset > 0;
		} else {
			return offset < range - 1;
		}
	}
	
	@SuppressLint("NewApi")
	private void startPropertyAnimation(final View target, final int startValue, final int endValue){
		final IntEvaluator intEvaluator=new IntEvaluator();
		//将动画值限定在(1,100)之间
		ValueAnimator valueAnimator=ValueAnimator.ofInt(1,100);
		//动画持续时间
		valueAnimator.setDuration(5000);
		//监听动画的执行
		valueAnimator.addUpdateListener(new AnimatorUpdateListener() {
		@Override
		public void onAnimationUpdate(ValueAnimator valueAnimator) {
		//得到当前瞬时的动画值,在(1,100)之间
		Integer currentAnimatedValue=(Integer) valueAnimator.getAnimatedValue();
		//计算得到当前系数fraction
		float fraction=currentAnimatedValue/100f;
		System.out.println("currentAnimatedValue="+currentAnimatedValue+",fraction="+fraction);
		//评估出当前的宽度其设置
		target.getLayoutParams().height=intEvaluator.evaluate(fraction, startValue, endValue);
		target.requestLayout();
		}
		});
		//开始动画
		valueAnimator.start();
	}
}