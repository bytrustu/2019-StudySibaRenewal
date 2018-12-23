package com.studysiba.common;

import org.json.simple.JSONArray;

public class makeJSON {

	public static JSONArray change(String value) {
		JSONArray array = new JSONArray();
		array.add(value);
		return array;
	}
}
