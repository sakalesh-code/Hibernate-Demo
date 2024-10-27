package com.learn.Hibernate_Demo;

import javax.persistence.*;

@Entity
@Table(name = "Alien_table")
public class Alien {
	@Id
	private int aid;
	private String name;
	private int strength;

	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	@Override
	public String toString() {
		return "Alien [aid=" + aid + ", name=" + name + ", strength=" + strength + "]";
	}

}
