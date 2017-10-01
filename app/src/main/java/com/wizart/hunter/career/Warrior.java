package com.wizart.hunter.career;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import com.jxs.cofmod.Entity;
import com.jxs.cofmod.control.Modable;
import com.jxs.cofmod.custome.AEntity;
import com.wizart.hunter.Career;
import com.wizart.hunter.MyMod;
import com.wizart.hunter.CircleImageView;
import com.jxs.cofmod.Player;

public class Warrior extends Modable implements Career {
	public static final int MAGIC=100;
	
	@Override
	public String getName() {
		return "战士";
	}

	@Override
	public PopupWindow show() {
		MyMod.magic=MAGIC;
		PopupWindow w=new PopupWindow(ctx);
		LinearLayout layout=new LinearLayout(ctx);
		//幻影刺杀
		CircleImageView i1=new CircleImageView(ctx);
		i1.setImageBitmap(MyMod.getBitmap("warrior_1"));
		layout.addView(i1);
		i1.setLayoutParams(new LinearLayout.LayoutParams(MyMod.SKILL_SIZE, MyMod.SKILL_SIZE));
		i1.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					if (MyMod.cost(10)) {
						clientMessage("幻影刺杀");
						double sin=-1 * Math.sin(Math.floor(toFloat(call("", "getYaw"))) % 360 * Math.PI / 180);
						double ssin=-1 * Math.sin(Math.floor(toFloat(call("", "getPitch"))) % 360 * Math.PI / 180);
						double cos=Math.cos(Math.floor(toFloat(call("", "getYaw"))) % 360 * Math.PI / 180);
						Object o=getPlayerEnt();
						Entity.setVelX(o, sin);
						Entity.setVelY(o, ssin);
						Entity.setVelZ(o, cos);
						new Thread(new Runnable() {
								@Override
								public void run() {
									MyMod.gjjc=2;
									try {
										Thread.sleep(2000);
									} catch (Exception e) {
										ui.print(e.toString());
									}
									MyMod.gjjc=-1;
								}
						}).start();
					}
				}
			});
		//
		w.setContentView(layout);
		w.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		w.setWidth(MyMod.SKILL_SIZE);
		w.setHeight(MyMod.SKILL_SIZE * layout.getChildCount());
		w.showAtLocation(ui.getDecorView(), Gravity.TOP | Gravity.RIGHT, 100, 100);
		return w;
	}
}
