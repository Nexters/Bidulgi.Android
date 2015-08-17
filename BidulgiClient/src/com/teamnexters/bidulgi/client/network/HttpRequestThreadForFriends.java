package com.teamnexters.bidulgi.client.network;

import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.teamnexters.bidulgi.client.BidoolgiFragmentActivity;
import com.teamnexters.bidulgi.client.process.ProcessThread;
import com.teamnexters.bidulgi.client.ui.UiHandlerForFriends;
import com.teamnexters.bidulgi.common.request.BidulgiRequestCode;
import com.teamnexters.bidulgi.common.request.BidulgiRequestPacket;
import com.teamnexters.bidulgi.common.request.RequestUriFactory;
import com.teamnexters.bidulgi.common.response.BidulgiResponsePacket;
import com.teamnexters.bidulgi.common.response.ResponseJsonParser;

import android.os.Message;
import android.util.Log;

public class HttpRequestThreadForFriends extends Thread implements BidulgiRequestCode {

	private ProcessThread processThread;
	private Object waitingObject;
	private static HttpRequestThreadForFriends instance = new HttpRequestThreadForFriends();
	private UiHandlerForFriends handler;
	private ClientManager clientManager;
	ConcurrentLinkedQueue<BidulgiRequestPacket> requestQueue;

	static {
		instance.start();
	}

	public void addRequest(BidulgiRequestPacket request) {
		requestQueue.add(request);
		synchronized (waitingObject) {
			waitingObject.notify();
		}
	}

	private HttpRequestThreadForFriends() {
		setDaemon(true);
		processThread = new ProcessThread();
		waitingObject = new Object();
		clientManager = new ClientManager();
		requestQueue = new ConcurrentLinkedQueue<BidulgiRequestPacket>();
	}

	public synchronized void setHandler(BidoolgiFragmentActivity activity) {
		handler = new UiHandlerForFriends(activity);
	}
	

	public static HttpRequestThreadForFriends getInstance() {
		return instance;
	}

	
	public void runOnUi(Runnable action){
		handler.runAction(action);
	}
	@Override
	public void run() {
		while (true) {
			while (!requestQueue.isEmpty()) {
				BidulgiRequestPacket request = requestQueue.poll();
				String URL = NetworkConfiguration.getHost()
						+ RequestUriFactory.requestUri(request.getRequestCode());
				HttpPost httpPost = new HttpPost(URL);
				HttpClient client = clientManager.getHttpClient();
				HttpResponse httpResponse;
				try {
					UrlEncodedFormEntity entity = new UrlEncodedFormEntity(
							request.toHttpParams(), HTTP.UTF_8);
					httpPost.setEntity(entity);
					httpResponse = client.execute(httpPost);
					String str = EntityUtils.toString(httpResponse.getEntity(),
							HTTP.UTF_8);
					BidulgiResponsePacket response = ResponseJsonParser.getInstance().parse(str);
					switch (response.getResponseType()) {
					case BidulgiResponsePacket.RESPONSE_TYPE_PROCESS:
						processThread.addProcess(response);
						break;
					case BidulgiResponsePacket.RESPONSE_TYPE_UI:
						synchronized (this) {
							if (handler != null) {
								Message msg = handler.obtainMessage();
								msg.obj = ResponseJsonParser.getInstance().parse(str);
								handler.sendMessage(msg);
							}
						}
						break;
					}

				} catch (Exception e) {
					Log.e("Response Error", "Response Error", e);
				}
			}
			synchronized (waitingObject) {
				try {
					waitingObject.wait();
				} catch (InterruptedException e) {}
			}
		}
	}
}
