package com.javasampleapproach.angular4mongodb.repo;

import com.javasampleapproach.angular4mongodb.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class SearchRepository {

    @Autowired
    MongoTemplate mongoTemplate;


    public Collection<Customer> searchCustomer(String text) {
        return mongoTemplate.find(Query.query(new Criteria()
                .orOperator(Criteria.where("id").regex(text, "i"),
                        Criteria.where("name").regex(text, "i"),
                        Criteria.where("age").regex(text, "i"))
        ), Customer.class);
    }
}
