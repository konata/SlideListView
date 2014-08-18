package cn.beriru.view;

import android.view.MotionEvent;

public interface Slidable {
	public void onPassTouchEvent(MotionEvent ev);
	public void close();
}
