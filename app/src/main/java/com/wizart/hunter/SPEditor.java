package com.wizart.hunter;

import android.content.SharedPreferences;
import android.content.Context;
import com.jxs.cofmod.control.Modable;

public class SPEditor {
	private String file;
	private SharedPreferences.Editor edit;
	private SharedPreferences entity;

	public SPEditor(String file) {
		this.file = file;
		open();
	}

	public SPEditor() {}

	public SPEditor setFile(String file) {
		this.file = file;
		return this;
	}

	public String getFile() {
		return file;
	}

	public SPEditor open() {
		this.entity=Modable.ctx.getSharedPreferences(file, Context.MODE_PRIVATE);
		this.edit=entity.edit();
		return this;
	}

	public SPEditor put(String key, String data) {
		edit.putString(key, data);
		return this;
	}

	public SPEditor put(String key, int data) {
		edit.putInt(key, data);
		return this;
	}

	public SPEditor put(String key, boolean data) {
		edit.putBoolean(key, data);
		return this;
	}

	public SPEditor put(String key, float data) {
		edit.putFloat(key, data);
		return this;
	}

	public SPEditor put(String key, long data) {
		edit.putLong(key, data);
		return this;
	}

	public SPEditor save() {
		edit.commit();
		return this;
	}

	public SharedPreferences.Editor getEdit() {
		return edit;
	}

	public String getString(String key, String de) {
		return entity.getString(key, de);
	}

	public int getInt(String key, int de) {
		return entity.getInt(key, de);
	}

	public boolean getBoolean(String key, boolean de) {
		return entity.getBoolean(key, de);
	}

	public float getFloat(String key, float de) {
		return entity.getFloat(key,  de);
	}

	public long getLong(String key, long de) {
		return entity.getLong(key, de);
	}
}

