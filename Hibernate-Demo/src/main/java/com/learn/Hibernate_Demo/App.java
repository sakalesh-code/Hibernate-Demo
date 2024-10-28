package com.learn.Hibernate_Demo;

import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
 
import java.util.List;
import java.util.Map;
import java.util.Random;

public class App {
	public static void main(String[] args) {

		Random rnd = new Random();
		Configuration con = new Configuration().configure().addAnnotatedClass(Alien.class);
		StandardServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
		// Create SessionFactory
		SessionFactory sf = con.buildSessionFactory(reg);

		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		// To save
//		for (int i = 1; i <= 20; i++) {
//			Alien alien = new Alien();
//			alien.setAid(i);
//			alien.setName("name" + i);
//			alien.setStrength(rnd.nextInt(100));
//			session.persist(alien);
//		}

//		Alien alien = new Alien();
//		// Transient state
//		alien.setAid(21);
//		alien.setName("name21");
//		alien.setStrength(77);
//		// Persistent State
//		session.persist(alien);
//		alien.setStrength(66);
//		session.remove(alien);
//		transaction.commit();
//		// Detach State
//		session.detach(alien);
//		alien.setStrength(55);
//		session.close();
		// to get
		// List<Alien> list = session.createQuery("from Alien",Alien.class).list();
		Query q = session.createQuery("from Alien where strength < 20");
		// for List of records HQL
		List<Alien> list = q.list();
		for (Alien l : list) {
			System.out.println(l);
		}
//		Alien alien = new Alien();
//		// for single record
//		Query q1 = session.createQuery("from Alien where aid=14");
//		alien = (Alien) q1.uniqueResult();
//		System.out.println(alien);

		Query q2 = session.createQuery("select aid,name,strength from Alien where aid=14");
		Object[] alien = (Object[]) q2.uniqueResult();
//		for(Object o : alien) {
//			System.out.println(o);
//		}
		System.out.println(alien[0] + " : " + alien[0] + " : " + alien[1]);

		Query q3 = session.createQuery("select aid,name,strength from Alien");
		List<Object[]> alien2 = (List<Object[]>) q3.list();
		for (Object[] o : alien2) {
			System.out.println(o[0] + " : " + o[0] + " : " + o[1]);
		}

		Query q4 = session.createQuery("select aid,name,strength from Alien where strength>60");
		List<Object[]> alien3 = (List<Object[]>) q4.list();
		for (Object[] o : alien3) {
			System.out.println("  " + o[0] + " : " + o[0] + " : " + o[1]);
		}

		Query q5 = session.createQuery("select sum(strength) from Alien where strength>60");
		Integer strength = (Integer) q5.uniqueResult();
		System.out.println(" Total strength : " + strength);
//		int b = 60;
//		// Query q6 = session.createQuery("select sum(strength) from Alien where strength>"+b);
//		Query q6 = session.createQuery("select sum(strength) from Alien where strength> :b");
//		q6.setParameter("b", b);
//		Integer strength1 = (Integer) q6.uniqueResult();
//		System.out.println(" Total strength ::: " + strength1);

		// Native Queries
		NativeQuery sqlQuery = session.createNativeQuery("select * from Alien_table where strength>60");
		List<Object[]> ls = sqlQuery.list();
		System.out.println(ls);
		for (Object[] o : ls) {
			System.out.println(o[0] + " : " + o[0] + " : " + o[1]);
		}

		 NativeQuery<Object[]> sqlQuery1 = session.createNativeQuery("SELECT aid, name, strength FROM Alien_table WHERE strength > 60");

		    // Execute the query and retrieve the results
		    List<Object[]> ls1 = sqlQuery1.list();

		    // Print the results
		    for (Object[] row : ls1) {
		        Long aid = (Long) row[0]; // Adjust index based on your SELECT statement
		        String name = (String) row[1]; // Adjust as needed
		        Integer strength1 = (Integer) row[2]; // Adjust as needed

		        System.out.println(name + " : " + strength1);
		    }
		transaction.commit();
		session.close();
	}
}
