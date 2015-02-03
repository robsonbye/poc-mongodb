package com.netshoes.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

@Configuration
@EnableMongoRepositories
class ApplicationConfig extends AbstractMongoConfiguration {

	@Autowired
	private MongoClient mongoClient;

	@Override
	public Mongo mongo() throws Exception {
		return mongoClient;

	}
	
	//XML override this database data
	@Override
	protected String getDatabaseName() {
		return "springdata";
	}
}