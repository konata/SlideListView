package cn.beriru.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.AbsListView;
import android.widget.ListView;


/**
 * @author Minami
 * 1.滑动事件需要关闭打开的item
 * 2.ListView自己探测滑动事件并分配给子view
 */

public class SlideListView extends ListView {
	public static final String TAG = SlideListView.class.getCanonicalName();
	protected static final float THRESHOD = 5;
	
	public SlideListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	
	private void init() {
		setOnScrollListener(new OnScrollListener() {
			public float mScrollOffset = 0 ;
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				if(scrollState == SCROLL_STATE_IDLE){
					mScrollOffset = getScrollY();
				}
				// FIXME
				if(scrollState == SCROLL_STATE_TOUCH_SCROLL && mCurrentTag != null){
					if(Math.abs(mScrollOffset - getScrollY()) > THRESHOD){
						mCurrentTag.close();
					}
					mScrollOffset = getScrollY();
				}
			}
			
			@Override
			public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
				
			}
		});
		
	}

	public SlideListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}
	
	private Slidable mCurrentTag;
	
	public boolean onTouchEvent(MotionEvent e) {
		if(e.getAction() == MotionEvent.ACTION_DOWN){
			int x = (int) e.getX();
			int y = (int) e.getY();
			int position = pointToPosition(x, y) - getFirstVisiblePosition();
			if(position != INVALID_POSITION){
				Slidable newItem = (Slidable) getChildAt(position);
				if(mCurrentTag != null && newItem != mCurrentTag){
					mCurrentTag.close();
				}
				mCurrentTag = newItem;
			}
		}
		if(mCurrentTag != null){
			mCurrentTag.onPassTouchEvent(e);
		}
		boolean superConsume =  super.onTouchEvent(e);
		return superConsume;
	}
	
	
	
	
	
}
