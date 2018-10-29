package com.javasampleapproach.angular4mongodb.repo;

import org.bson.types.ObjectId;
import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.javasampleapproach.angular4mongodb.model.Customer;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.List;

public interface CustomerMongoRepository extends MongoRepository<Customer, String> {

//
//    Resource findById(@Param("Id") String Id);
//
//    Customer findBy_id(String id);

//    Customer findBy_id(ObjectId id);

    List<Customer> findById(ObjectId id);


}
