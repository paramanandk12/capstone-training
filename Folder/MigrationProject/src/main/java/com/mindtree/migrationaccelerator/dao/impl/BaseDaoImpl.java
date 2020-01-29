package com.mindtree.migrationaccelerator.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mindtree.migrationaccelerator.dao.IBaseDao;
import com.mindtree.migrationaccelerator.service.IBaseService;

@Repository
public class BaseDaoImpl<T, I> implements IBaseDao<T, I> {
	final static Logger logger = Logger.getLogger(BaseDaoImpl.class);
	private static Session session = null;
	
	@Autowired
	private IBaseService baseService;
	
	@Override
	public Session getSession() {
		logger.info("Checking if Session is Avaialble is or not.. !!! ");
		if(session == null) {
			session = baseService.getSessionFactory().openSession();
			logger.info("New Session Model is created !!! ");
		}
		return session;
	}
	
	@Override
	public void insertEntity(Session session, T entity) {
		session.persist(entity);
	}
	
	@Override
	public void update(Session session, T entity) {
		session.update(entity);
	}
	
	@Override
	public void save(Session session, T entity) {
		session.save(entity);
	}
	
	@Override
	public void saveOrUpdate(Session session, T entity) {
		session.saveOrUpdate(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T mergeEntity(Session session, T entity) {
		return (T) session.merge(entity);
	}

	@Override
	public void deleteEntity(Session session, T entity) {
		session.delete(entity);
	}

	@Override
	public T loadEntity(Session session, Class<T> clazz, I id) {
		T object = (T) session.load(clazz, (Serializable) id);
		return object;
	}

	@Override
	public T getEntity(Session session, Class<T> clazz, I id) {
		T object = (T) session.get(clazz, (Serializable) id);
		return object;
	}

	@Override
	public List<T> getEntity(Session session, Class<T> clazz) {
		 CriteriaQuery<T> criteriaQuery =  session.getCriteriaBuilder().createQuery(clazz);
	     criteriaQuery.from(clazz);
		return session.createQuery(criteriaQuery).getResultList();
	}

	@Override
	public T getEntity(Session session, Class<T> clazz, String colName, String columnVal) {
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<T> criteria = (CriteriaQuery<T>) builder.createQuery(clazz);
		Root<T> root = (Root<T>) criteria.from(clazz);
		criteria.select(root).where(builder.equal(root.get(colName), columnVal));
		return session.createQuery(criteria).getSingleResult();
	}
	
	@Override
	public List<T> getEntityList(Session session, Class<T> clazz, String colName, Integer columnVal) {
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<T> criteria = (CriteriaQuery<T>) builder.createQuery(clazz);
		Root<T> root = (Root<T>) criteria.from(clazz);
		criteria.select(root).where(builder.equal(root.get(colName), columnVal));
		return session.createQuery(criteria).list();
	}

	@Override
	public T getEntity(Session session, Class<T> clazz, String firstColName, String firstColumnVal, String secColName,
			String secColVal) {
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<T> criteria = (CriteriaQuery<T>) builder.createQuery(clazz);
		Root<T> root = (Root<T>) criteria.from(clazz);
		Predicate valRestriction = builder.and(builder.equal(root.get(firstColName), firstColumnVal),
				builder.equal(root.get(secColName), secColVal));
		criteria.where(valRestriction);
		return session.createQuery(criteria).getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getByQuery(Session session, String query, Class<T> clazz) {
		List<T> result = (List<T>) session.createSQLQuery(query).addEntity(clazz).list();
		return result;
	}

	@Override
	public T getUserEntityByMasterSheetID(Session session, Class<T> clazz, String colName, Integer columnVal) {
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<T> criteria = (CriteriaQuery<T>) builder.createQuery(clazz);
		Root<T> root = (Root<T>) criteria.from(clazz);
		criteria.select(root).where(builder.equal(root.get(colName), columnVal));
		return session.createQuery(criteria).getSingleResult();
	}
	
	@Override
	public boolean ifExists(Session session, Class<T> clazz, String colName, String columnVal) {
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<T> criteria = (CriteriaQuery<T>) builder.createQuery(clazz);
		Root<T> root = (Root<T>) criteria.from(clazz);
		criteria.select(root).where(builder.equal(root.get(colName), columnVal));
		
		if(session.createQuery(criteria).list().size() > 0)
			return true;
		else
			return false;
		
	}
	
	@Override
	public boolean ifExists(Session session, Class<T> clazz, String colName1, String colVal1, String colName2, String colVal2) {
		
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<T> criteria = (CriteriaQuery<T>) builder.createQuery(clazz);
		Root<T> root = (Root<T>) criteria.from(clazz);
		
		Predicate valRestriction = builder.and(builder.equal(root.get(colName1), colVal1),
				builder.equal(root.get(colName2), colVal2));
		
		criteria.where(valRestriction);
		
		if(session.createQuery(criteria).list().size() > 0)
			return true;
		else
			return false;
		
	}
	
	@Override
	public boolean ifExists(Session session, Class<T> clazz, String colName1, String colVal1, String colName2, Integer colVal2) {
		
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<T> criteria = (CriteriaQuery<T>) builder.createQuery(clazz);
		Root<T> root = (Root<T>) criteria.from(clazz);
		
		Predicate valRestriction = builder.and(builder.equal(root.get(colName1), colVal1),
				builder.equal(root.get(colName2), colVal2));
		
		criteria.where(valRestriction);
		
		if(session.createQuery(criteria).list().size() > 0)
			return true;
		else
			return false;
		
	}
}