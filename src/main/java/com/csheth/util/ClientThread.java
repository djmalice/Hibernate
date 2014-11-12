package com.csheth.util;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class ClientThread implements Runnable {
		private final ExecutorService serverPool;
 
		public ClientThread(ExecutorService serverPool) {
			this.serverPool = serverPool;
		}
 
		@Override
	public void run() {

		try {
			
			// Generate votes and add to queue
			for (int i = 0; i < 1000; i++) {
				String msg = String.valueOf((int)Math.ceil(Math.random() * 100));
				System.out.println("Produced: " + msg);
				Future<List<Integer>> future1=serverPool.submit(new ServerThread(msg));
				System.out.println(future1.get());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
	}

