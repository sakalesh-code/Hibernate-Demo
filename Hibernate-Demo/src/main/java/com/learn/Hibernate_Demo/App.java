package com.learn.Hibernate_Demo;

import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;
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
//		// To save
//		for (int i = 1; i <= 20; i++) {
//			Alien alien = new Alien();
//			alien.setAid(i);
//			alien.setName("name" + i);
//			alien.setStrength(rnd.nextInt(100));
//			session.persist(alien);
//		}
		// to get
		// List<Alien> list = session.createQuery("from Alien",Alien.class).list();
		Query q = session.createQuery("from Alien where strength < 20");
		// for List of records
		List<Alien> list = q.list();
		for (Alien l : list) {
			System.out.println(l);
		}
		Alien alien = new Alien();
		// for single record
		Query q1 = session.createQuery("from Alien where aid=14");
		alien = (Alien) q1.uniqueResult();
		System.out.println(alien);
		transaction.commit();
		session.close();
	}
}
