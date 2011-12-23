package com.supermy.core;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.data.document.mongodb.MongoOperations;
import org.springframework.data.document.mongodb.query.Criteria;
import org.springframework.data.document.mongodb.query.Query;
import org.springframework.data.document.mongodb.query.Update;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.supermy.user.User;

public class App
{
	 //static final Logger logger = LoggerFactory.getLogger(App.class);  

    public static void main( String[] args )
    {
    	//For Annotation
    	//ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
    	
    	//For XML
    	ApplicationContext ctx = new GenericXmlApplicationContext("mongo-config.xml");
    	
    	MongoOperations mongoOperation = (MongoOperations)ctx.getBean("mongoTemplate");
    	
        User user = new User("1001", "yong", "mook kim", "yy@yy.com");
        
        //save
        mongoOperation.save("userprofile",user);
        
        //find
        User savedUser = mongoOperation.findOne("userprofile",
        		new Query(Criteria.where("id").is("1001")),
				User.class);
        
        System.out.println("savedUser : " + savedUser);
        
        //update
        mongoOperation.updateFirst("userprofile",
        		new Query(Criteria.where("username").is("yong")), 
        		Update.update("passwd", "new passwd"));
        
        //find
        User updatedUser = mongoOperation.findOne(
        		"userprofile",
        		new Query(Criteria.where("id").is("1001")),
				User.class);
        
        System.out.println("updatedUser : " + updatedUser);
        
        //delete
        mongoOperation.remove("userprofile",
        		new Query(Criteria.where("id").is("1001")),
        		User.class);
        
        //List
        List<User> listUser =  
        	mongoOperation.getCollection("userprofile", User.class);
        System.out.println("Deltele after Number of user = " + listUser.size());
        
        //分页查找
        PagingAndSortingRepository<User, Long> repository=null ;//todo
        Page<User> users = repository.findAll(new PageRequest(1, 20));
        
    }
    
}
