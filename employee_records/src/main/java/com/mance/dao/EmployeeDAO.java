package com.mance.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.mance.model.Employee;

@Stateless
public class EmployeeDAO {

	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("emp");
	private EntityManager em = emf.createEntityManager();

	public EntityManager getEm() {
		return this.em;
	}

	public List<Employee> list() {
		Query q = getEm().createQuery("FROM Employee", Employee.class);

		List<Employee> result = (ArrayList<Employee>) q.getResultList();
		return result;
	}

}
