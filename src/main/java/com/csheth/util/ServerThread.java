package com.csheth.util;

import java.util.List;
import java.util.concurrent.BlockingQueue;

import org.hibernate.Session;
import org.hibernate.Transaction;

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
				Session session = HibernateUtil.getSessionFactory()
						.openSession();
				Transaction tx = session.beginTransaction();

				String hqlUpdate = "update Player wp set wp.votes = wp.votes + 1 where wp.playerId = :id";
				
				int updatedEntities = session.createQuery( hqlUpdate )
				        .setInteger( "id", Integer.parseInt(msg))
				        .executeUpdate();
				tx.commit();
				
				tx = session.beginTransaction();
				List<Integer> top5 = session.createSQLQuery("select player_id from player order by votes DESC limit 5").list();
				tx.commit();
				System.out.println(top5);
				session.close();
				
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
			System.out.println("Server message" + msg);
		}
	}

}
