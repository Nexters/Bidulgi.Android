package com.teamnexters.bidulgi.common.util;


public class WaitingThread extends Thread {
	final private Object waitingLock;

	public WaitingThread() {
		waitingLock = new Object();
	}

	final protected void waitForWake() {
		if(Thread.currentThread()==this){
			synchronized (waitingLock) {
				try {
					waitingLock.wait();
				} catch (InterruptedException e) {
				}
			}
		}else{
			throw new IllegalWaitingThreadException();
		}
	}

	final protected void wake() {
		synchronized (waitingLock) {
			waitingLock.notify();
		}
	}
}