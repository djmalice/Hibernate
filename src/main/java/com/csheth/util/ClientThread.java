package com.csheth.util;

import java.util.concurrent.BlockingQueue;

public class ClientThread implements Runnable {
		private final BlockingQueue<String> sharedQueue;
 
		public ClientThread(BlockingQueue<String> sharedQueue) {
			this.sharedQueue = sharedQueue;
		}
 
		@Override
	public void run() {

		try {
			
			// Generate votes and add to queue
			for (int i = 0; i < 2; i++) {
				String msg = String.valueOf((int)Math.ceil(Math.random() * 100));
				System.out.println("Produced: " + msg);
				sharedQueue.put(msg);
			}
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}

	}
	}

