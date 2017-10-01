package com.wizart.hunter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.PopupWindow;
import com.jxs.cofmod.ModPE;
import com.jxs.cofmod.Player;
import com.jxs.cofmod.control.CoffeeMod;
import com.jxs.cofmod.custome.AEntity;
import com.jxs.cofmod.utils.UI;

public class MyMod extends CoffeeMod implements Const {
	public static final int SKILL_SIZE=150;
	public static Career car=null;
	private static PopupWindow window=null;
	public static AEntity target=null;
	public static int magic;
	public static int gjjc=-1;

	@Override
	public void onCreate() {
		super.onCreate();
	}

	@Override
	public String getAuthor() {
		return "Xs.JIONG";
	}

	@Override
	public String getModName() {
		return "巫师猎人";
	}

	@Override
	public void screenChangeHook(String name) {
		super.screenChangeHook(name);

		if (name.equals("hud_screen")) {
			int s=new SPEditor(SP_FILE).getInt(SP_CHOOSE_CAREER, -1);
			if (s == -1) {
				String[] chooses=new String[Career.ALL.length];
				for (int i=0;i < chooses.length;i++) chooses[i] = Career.ALL[i].getName();
				ui.choose("请选择角色", chooses, false, new UI.AlertListener() {
						@Override
						public void onDone(int pos) {
							new SPEditor(SP_FILE).put(SP_CHOOSE_CAREER, pos).save();
							car = Career.ALL[pos];
							showView();
						}
						@Override
						public void onCancel() {}
					});
			} else {
				car = Career.ALL[s];
				showView();
			}
		} else {
			if (window != null) {
				window.dismiss();
				//window = null;
				target = null;
				System.gc();
			}
		}
	}

	private void showView() {
		ui.onUi(new Runnable() {
				@Override
				public void run() {
					if (car == null) return;
					window = car.show();
				}
			});
	}

	public static Bitmap getBitmap(String name) {
		return BitmapFactory.decodeStream(ModPE.openInputStreamFromTexturePack("data/res/drawable/" + name + ".png"));
	}

	@Override
	public void attackHook(long attacker, long victim) {
		super.attackHook(attacker, victim);
		if (attacker == getPlayerEnt()) {
			target = new AEntity(victim);
			AEntity vic=new AEntity(victim);
			if (gjjc != -1) vic.setHealth(vic.getHealth() - gjjc);
		}
	}

	public static AEntity getTarget() {
		try {
			if (target == null) return new AEntity(Player.getPointedEntity()); else return target;
		} catch (Throwable t) {
			print(t);
		}
		return null;
	}

	public static boolean cost(int j) {
		if (magic >= j) {
			magic -= j;
			return true;
		} else {
			clientMessage("魔力值不足！");
			return false;
		}
	}
}
