package com.javasampleapproach.angular4mongodb.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "customer")
public class Customer {
    @Id
    private ObjectId _id;

    private String name;
    private int age;

    public Customer() {
    }

    public Customer(ObjectId _id, String name, int age) {
        this._id = _id;
        this.name = name;
        this.age = age;
    }

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "_id=" + _id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}