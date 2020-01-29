package com.mindtree.migrationaccelerator.dao;

import java.util.List;

import org.hibernate.Session;

public interface IBaseDao<T, I> {

	void insertEntity(Session session, T entity);
	
	void update(Session session, T entity);
	
	void save(Session session, T entity);

	void saveOrUpdate(Session session, T entity);
	
	void deleteEntity(Session session, T entity);

	T mergeEntity(Session session, T entity);

	T loadEntity(Session session, Class<T> clazz, I id);

	List<T> getEntity(Session session, Class<T> clazz);

	T getEntity(Session session, Class<T> clazz, String colName, String columnVal);

	T getEntity(Session session, Class<T> clazz, String firstColName, String firstColumnVal, String secColName,
			String secColVal);

	T getEntity(Session session, Class<T> clazz, I id);

	List<T> getByQuery(Session session, String query, Class<T> clazz);

	Session getSession();

	T getUserEntityByMasterSheetID(Session session, Class<T> clazz, String colName, Integer columnVal);

	List<T> getEntityList(Session session, Class<T> clazz, String colName, Integer columnVal);

	boolean ifExists(Session session, Class<T> clazz, String colName, String columnVal);

	boolean ifExists(Session session, Class<T> clazz, String colName1, String colVal1, String colName2, String colVal2);

	boolean ifExists(Session session, Class<T> clazz, String colName1, String colVal1, String colName2,
			Integer colVal2);

}