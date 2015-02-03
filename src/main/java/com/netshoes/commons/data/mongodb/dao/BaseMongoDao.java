package com.netshoes.commons.data.mongodb.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.mongodb.Mongo;

@Repository
public abstract class BaseMongoDao {

	@Autowired
	private Mongo mongo;
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	protected Mongo getMongo() {
		return mongo;
	}

	protected MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}
	
}
