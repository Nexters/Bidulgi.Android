package com.teamnexters.bidulgi.common.response;

public interface BidulgiResponseCode {
	public int RESPONSE_TEST = 0;
	public int RESPONSE_REQUIRE_LOGIN  = 1;
	public int RESPONSE_LOGIN_SUCCESS = 2;
	public int RESPONSE_LOGIN_FAIL = 3;
	public int RESPONSE_REGISTRATION_FAIL = 4;
	public int RESPONSE_REGISTRATION_SUCCESS = 5;
	public int RESPONSE_LOGOUT_SUCCESS = 6;
	public int RESPONSE_SOLDIER_INFO = 7;
	public int RESPONSE_SOLDIER_SEARCH_FAIL = 8;
	public int RESPONSE_ADD_FRIEND_SOLDIER_FAIL = 9;
	public int RESPONSE_ADD_FRIEND_SOLDIER_SUCCESS = 10;
	public int RESPONSE_REMOVE_FRIEND_SOLDIER_SUCCESS = 11;
	public int RESPONSE_REMOVE_FRIEND_SOLDIER_FAIL = 12;
	public int RESPONSE_LIST_FRIEND_SOLDIER = 13;
}