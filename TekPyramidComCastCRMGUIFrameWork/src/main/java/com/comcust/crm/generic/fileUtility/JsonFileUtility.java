package com.comcust.crm.generic.fileUtility;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonFileUtility {

	public String togetDataFromJsonFile(String key) throws IOException, ParseException
	{
		FileReader reader=new FileReader("./ConfigAppData\\JsonCommonData.json");
		JSONParser jsonparser=new JSONParser();
		Object javaObj = jsonparser.parse(reader);
		JSONObject obj=(JSONObject)javaObj;
		String value = obj.get(key).toString();
		return value;
	}
}
