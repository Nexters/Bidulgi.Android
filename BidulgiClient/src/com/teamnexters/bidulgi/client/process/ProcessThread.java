package com.teamnexters.bidulgi.client.process;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import android.util.Log;

import com.teamnexters.kkaba.common.response.BidulgiResponseCode;
import com.teamnexters.kkaba.common.response.BidulgiResponsePacket;

public class ProcessThread extends Thread{
	private Object waitingObject;
	public ProcessThread() {
		setDaemon(true);
		waitingObject = new Object();
		processList = new ConcurrentLinkedQueue<BidulgiResponsePacket>();
		start();
	}
	private Queue<BidulgiResponsePacket> processList;

	@Override
	public void run() {
		while (true) {
			while (!processList.isEmpty()) {
				BidulgiResponsePacket process = processList.poll();
				switch (process.getResponseCode()) {
				case BidulgiResponseCode.RESPONSE_TEST:
					Log.i("response", "test");
					break;

				default:
					break;
				}
			}
			synchronized (waitingObject) {
				try {
					waitingObject.wait();
				} catch (InterruptedException e) {}
			}
		}
	}
	
	public void addProcess(BidulgiResponsePacket response) {
		processList.add(response);
		synchronized (waitingObject) {
			waitingObject.notify();
		}
	}

}
