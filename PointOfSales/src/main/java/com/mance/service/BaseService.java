package com.mance.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class BaseService {

	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("pointOfSales");
	private EntityManager em = emf.createEntityManager();

	public EntityManager getEm() {
		return this.em;
	}

}
