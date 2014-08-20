package cn.beriru.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
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
	protected static final float THRESHOLD = 20;
	
	public SlideListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	

	public SlideListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}
	
	private void init() {
	}
	
	private SlideItem mCurrentTag;
	private int mInitTagTop;
	
	public boolean onTouchEvent(MotionEvent e) {
		if(e.getAction() == MotionEvent.ACTION_DOWN){
			int x = (int) e.getX();
			int y = (int) e.getY();
			int position = pointToPosition(x, y) - getFirstVisiblePosition();
			if(position != INVALID_POSITION){
				SlideItem newItem = (SlideItem) getChildAt(position);
				if(mCurrentTag != null && newItem != mCurrentTag){
					mCurrentTag.close();
				}
				if(newItem != null){
					mCurrentTag = newItem;
					mInitTagTop = mCurrentTag.getTop();
				}
			}
		}
		if(mCurrentTag != null){
			int tagTop = mCurrentTag.getTop();
			Log.d("tagTop", " " + tagTop + " result : " + (Math.abs(tagTop - mInitTagTop) > THRESHOLD));
			if(Math.abs(tagTop - mInitTagTop) > THRESHOLD){
				mCurrentTag.close();
				mCurrentTag = null;
			}else{
				mCurrentTag.onPassTouchEvent(e);
			}
		}
		boolean superConsume =  super.onTouchEvent(e);
		return superConsume;
	}
}
