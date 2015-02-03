package com.netshoes.web.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "customer")
public class Customer {

    @Id
    private ObjectId id;

    private int number;
    private String firstName;
    private String lastName;

    public Customer() {}

    public Customer(int number, String firstName, String lastName) {
    	this.number = number;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	@Override
    public String toString() {
        return String.format(
                "Customer[id=%s, number=%s, firstName='%s', lastName='%s']",
                id, number, firstName, lastName);
    }

}
