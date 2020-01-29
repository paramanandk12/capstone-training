package com.mindtree.migrationaccelerator.service;

import org.hibernate.SessionFactory;

public interface IBaseService {
	SessionFactory getSessionFactory();
}