package repository;

import java.util.List;

import jakarta.persistence.EntityManager;
import util.EntityManagerUtil;

public class EntityRepository<T> {
	private Class<T> entityClass;
	private String tableName;
	
	public EntityRepository(Class<T> entityClass, String tableName) {
		this.entityClass = entityClass;
		this.tableName = tableName;
	}
	
	public void create(T entity) {
		EntityManager em = EntityManagerUtil.getEntityManager();
		
		try {
			em.getTransaction().begin();
			em.persist(entity);
			em.getTransaction().commit();
		} catch(IllegalArgumentException e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
	}
	
	public T findById(Object id) {
		EntityManager em = EntityManagerUtil.getEntityManager();
		
		try {
			return em.find(entityClass, id);
		} catch(IllegalArgumentException e) {
			throw new IllegalArgumentException();
		} catch(NullPointerException e) {
			throw new NullPointerException();
		}finally {
			em.close();
		}
	}
	
	public List<T> findAll() {
		EntityManager em = EntityManagerUtil.getEntityManager();
		
		try {
			// returns a list with all the columns of the table in the database
			return em.createQuery("FROM " + tableName, entityClass).getResultList();
		} catch(IllegalArgumentException e) {
			throw new IllegalArgumentException();
		} finally {
			em.close();
		}
	}
	
	public void update(T entity) {
		EntityManager em = EntityManagerUtil.getEntityManager();
		
		try {
			em.getTransaction().begin();
			em.merge(entity);
			em.getTransaction().commit();
		} catch(IllegalArgumentException e) {
			throw new IllegalArgumentException();
		} finally {
			em.close();
		}
	}
	
	public void delete(Object id) {
		EntityManager em = EntityManagerUtil.getEntityManager();
		
		try {
			em.getTransaction().begin();
			T entity = em.find(entityClass, id);
			if (entity != null) {			
				em.remove(entity);
				em.getTransaction().commit();
				System.out.println("successfully deleted");
			} else throw new IllegalArgumentException("id doesnt exists: " + id);
	
		} catch(IllegalArgumentException e) {
			throw new IllegalArgumentException();
		} finally {
			em.close();
		}
	}
}
