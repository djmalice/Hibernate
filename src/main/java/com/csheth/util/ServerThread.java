package com.csheth.util;

import java.util.concurrent.BlockingQueue;

public class ServerThread implements Runnable {
	private String msg;
	private final BlockingQueue<String> sharedQueue;

	public ServerThread(BlockingQueue<String> sharedQueue) {
		this.msg = null;
		this.sharedQueue = sharedQueue;
	}

	@Override
	public void run() {
		while (!sharedQueue.isEmpty()) {
			try {

				msg = sharedQueue.take();
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
			System.out.println("Server message" + msg);
		}
	}

}
