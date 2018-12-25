package com.studysiba.common;

import org.json.simple.JSONArray;

public class MakeJSON {

	public static JSONArray change(String value) {
		JSONArray array = new JSONArray();
		array.add(value);
		return array;
	}
	
}
