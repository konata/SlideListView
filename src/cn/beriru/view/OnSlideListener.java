package cn.beriru.view;

public interface OnSlideListener {
	
	public void onSlide(SlideState state);
	
	public static enum SlideState {
		BEGIN,ON,OFF
	};
}

 