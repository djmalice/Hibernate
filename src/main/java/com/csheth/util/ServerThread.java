package com.csheth.util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class ServerThread implements Callable<List<Integer>>{
	private String msg;
	
	public ServerThread(String msg) {
		this.msg = msg;
	}

	@Override
	public List<Integer> call() {
		long startTime = System.currentTimeMillis();
		long endTime;
		List<Integer> top5 = new ArrayList<Integer>();
	
		Session session = HibernateUtil.getSessionFactory()
				.openSession();
		Transaction tx = session.beginTransaction();
			try {
				String hqlUpdate = "update Player wp set wp.votes = wp.votes + 1 where wp.playerId = :id";
				
				int updatedEntities = session.createQuery( hqlUpdate )
				        .setInteger( "id", Integer.parseInt(msg))
				        .executeUpdate();
				
				top5 = session.createSQLQuery("select player_id from player order by votes DESC limit 5").list();
				tx.commit();
				session.close();
				endTime = System.currentTimeMillis();
				System.out.println("Time to process:" + (endTime-startTime) + "ms");
				return top5;
			
			} catch (Exception ex) {
				tx.rollback();
				session.close();
				ex.printStackTrace();
				
			}
			
			
		
		return top5;
		
	}

}
