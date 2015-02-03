package com.netshoes.commons.data.mongodb.dao;

import java.util.List;

import com.mongodb.WriteResult;


/**
 * CRUD for Mongo.
 *  
 * @author denissys
 */
public interface GenericMongoDao {

	/**
	 * Find one document by attribute and value;
	 * 
	 * @param collectionClass
	 * @param attributeName
	 * @param attributeValue
	 * @return a java object converted of a mongo document (json).
	 */
	<T> T findOneByAttribute(Class<T> collectionClass, String attributeName, String attributeValue);

	/**
	 * Save a document.
	 * 
	 * @param collectionObject
	 */
	<T> void save(T collectionObject);
	
	/**
	 * Find one document by attribute and value int;
	 *
	 * @param collectionClass
	 * @param attributeName
	 * @param attributeValue
	 * @return
	 */
	<T> T findOneByAttributeInteger(final Class<T> collectionClass, final String attributeName, final int attributeValue);

	/**
	 * Delete the document
	 *
	 * @param collectionObject
	 * @return
	 */
	<T> WriteResult delete(final T collectionObject);
	
	/**
	 * Find All document by Collection
	 *
	 * @param collectionClass
	 * @return
	 */
	<T> List<T> findAll(final Class<T> collectionClass);

	/**
	 * Find All document by IdMongoDB
	 *
	 * @param collectionClass
	 * @param attributeValue
	 * @return
	 */
	<T> T findById(final Class<T> collectionClass, final Object attributeValue);
}
