package com.teamnexters.bidulgi.common.request;

public interface BidulgiRequestUri {
	public String REQUEST_TEST = "/test";
	public String REQUEST_LOGIN_CHECK_TEST = "/service/login_check_test";
	public String REQUEST_LOGIN = "/login";
	public String REQUEST_LOGIN_SUCCESS = "/service/login-success";
	public String REQUEST_LOGOUT_SUCCESS = "/logout-success";
	public String REQUEST_LOGIN_FAIL = "/login-fail";
	public String REQUEST_REQUIRE_LOGIN = "/require-login";
	public String REQUEST_REGISTRATION = "/registration";
	public String REQUEST_LOGOUT = "/service/logout";
	public String REQUEST_SEARCH_SOLDIER = "/service/search-soldier";
	public String REQUEST_ADD_FRIEND_SOLDIER = "/service/add-friend-soldier";
	public String REQUEST_LIST_FRIEND_SOLDIER = "/service/list-friend-soldier";
	public String REQUEST_REMOVE_FRIEND_SOLDIER = "/service/remove-friend-soldier";
	public String REQUEST_START_NICE_AUTH = "/service/start-nice-auth";
	public String REQUEST_SEND_NICE_SMS = "/service/send-nice-sms";
	public String REQUEST_NOTIFY_SMS_NUMBER = "/service/notify-sms-number";
}