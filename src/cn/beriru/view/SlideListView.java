package cn.beriru.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;


/**
 * @author Minami
 * 1.滑动事件需要关闭打开的item
 * 2.ListView自己探测滑动事件并分配给子view
 *
 */

public class SlideListView extends ListView {
	public static final String TAG = SlideListView.class.getCanonicalName();
	
	public SlideListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	public SlideListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	
	private Slidable mCurrentTag;
	
	public boolean onTouchEvent(MotionEvent e) {
		if(e.getAction() == MotionEvent.ACTION_DOWN){
			int x = (int) e.getX();
			int y = (int) e.getY();
			int position = pointToPosition(x, y) - getFirstVisiblePosition();
			if(position != INVALID_POSITION){
				mCurrentTag = (Slidable) getChildAt(position).getTag();
			}
		}
		if(mCurrentTag != null){
			mCurrentTag.onPassTouchEvent(e);
		}
		return super.onTouchEvent(e);
	}
	
	
}
