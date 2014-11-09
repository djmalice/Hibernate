package com.csheth;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Pattern;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.csheth.model.Player;
import com.csheth.util.HibernateUtil;

public class App {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory()
				.openSession();
		
		
		/*Seed database with player id, player name and positions*/
		try {
			Scanner S= new Scanner(new File("/Users/chintan/Codejam/Glint/GlintTest/src/main/resources/seed_table.txt"));
			S.useDelimiter(Pattern.compile("\\n"));
			Transaction tx = session.beginTransaction();
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
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		
		/*Add votes to the database table*/
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		session.close();
		
	}

}