package com.supermy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.Mongo;

/**
 * Spring MongoDB configuration file
 * 
 */
@Configuration
public class SpringMongoConfig extends AbstractMongoConfiguration {

	@Override
	public @Bean Mongo mongo() throws Exception {

		return new Mongo("localhost");
	}

	@Override
	public @Bean MongoTemplate mongoTemplate() throws Exception {

		return new MongoTemplate(mongo(),"yourdb");
	}

	@Override
	public String getDatabaseName() {
		// TODO Auto-generated method stub
		return null;
	}
		
}
