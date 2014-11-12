package com.csheth;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Pattern;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.csheth.model.Player;
import com.csheth.util.ClientThread;
import com.csheth.util.HibernateUtil;

public class App {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory()
				.openSession();
		Transaction tx = session.beginTransaction();
		
		/*Seed database with player id, player name and positions*/
		try {
			Scanner S= new Scanner(new File("/Users/chintan/Codejam/Glint/GlintTest/src/main/resources/seed_table.txt"));
			S.useDelimiter(Pattern.compile("\\n"));
			
			for ( int i=0; i<100; i++ ) {
				String elements[] = S.next().split(" ");
			    Player player = new Player(Integer.parseInt(elements[0]),elements[1],Integer.parseInt(elements[2]));
			    session.save(player);
			    if ( i % 20 == 0 ) { //20, same as the JDBC batch size
			        //flush a batch of inserts and release memory:
			        session.flush();
			        session.clear();
			    }
			}

			tx.commit();
			session.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			tx.rollback();
			session.close();
			e.printStackTrace();
		}		
		
		
		
	
		/* Generate 10 client threads */
        ExecutorService clientExecutor = Executors.newFixedThreadPool(12);
		
        /* Generate 2 server threads */
        ExecutorService serverExecutor = Executors.newFixedThreadPool(2);
        
		for (int i = 0; i < 10; i++) {
			Runnable worker = new ClientThread(serverExecutor);
			clientExecutor.execute(worker);
		}
				
		
		clientExecutor.shutdown();
		
		
		
		
		
		
		
		
		
		
		
	}

}