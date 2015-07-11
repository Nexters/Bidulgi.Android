package com.teamnexters.bidulgi.common.response;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class ResponseJsonParser implements BidulgiResponseCode {

	private static ResponseJsonParser parser = new ResponseJsonParser();
	private Gson gson;
	private JsonParser jsonParser;
	private ResponseJsonParser() {
		jsonParser = new JsonParser();
		gson = new Gson();
	}
	public static ResponseJsonParser getInstance() {
		return parser;
	}

	
	public BidulgiResponsePacket parse(String responseString) {
		JsonObject json = (JsonObject) jsonParser.parse(responseString);
		return gson.fromJson(json,BidulgiResponsePacket.getResponseClass(json.get("responseCode").getAsInt()));
	}
}