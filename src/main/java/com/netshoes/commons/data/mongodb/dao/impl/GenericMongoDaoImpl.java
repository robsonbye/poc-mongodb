package com.netshoes.commons.data.mongodb.dao.impl;

import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mongodb.WriteResult;
import com.netshoes.commons.data.mongodb.dao.BaseMongoDao;
import com.netshoes.commons.data.mongodb.dao.GenericMongoDao;

/**
 * Implements GenericMongoDao
 * 
 * @author denissys
 */
@Repository
public class GenericMongoDaoImpl extends BaseMongoDao implements GenericMongoDao {

	@Override
	public <T> T findOneByAttribute(final Class<T> collectionClass, final String attributeName, final String attributeValue) {
		final Query query = new Query(Criteria.where(attributeName).is(attributeValue));
		return (T) getMongoTemplate().findOne(query, collectionClass);
	}

	@Override
	public <T> void save(final T collectionObject) {
		getMongoTemplate().save(collectionObject);
	}

	@Override
	public <T> T findOneByAttributeInteger(final Class<T> collectionClass, final String attributeName, final int attributeValue) {
		final Query query = new Query(Criteria.where(attributeName).is(attributeValue));
		return (T) getMongoTemplate().findOne(query, collectionClass);
	}
	
	@Override
	public <T> WriteResult delete(final T collectionObject) {
		return getMongoTemplate().remove(collectionObject);
	}
	
	@Override
	public <T> List<T> findAll(final Class<T> collectionClass) {
		return getMongoTemplate().findAll(collectionClass);
	}

	@Override
	public <T> T findById(final Class<T> collectionClass, final Object attributeValue) {
		return (T) getMongoTemplate().findById(attributeValue, collectionClass);
	}
}