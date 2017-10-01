package com.wizart.hunter;

import android.widget.PopupWindow;
import com.jxs.cofmod.control.Modable;
import com.wizart.hunter.career.Warrior;

public interface Career {
	public static final Career[] ALL={
		new Warrior()
	};
	
	public String getName();
	public PopupWindow show();
}
