package com.netshoes.web.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Class which replace the ID with business id.
 */
@Document(collection = "customerReplace")
public class CustomerReplaceId {

	@Id
    private int number;
    private String firstName;
    private String lastName;

    public CustomerReplaceId() {}

    public CustomerReplaceId(int number, String firstName, String lastName) {
    	this.number = number;
        this.firstName = firstName;
        this.lastName = lastName;
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
                "Customer[number=%s, firstName='%s', lastName='%s']",
                number, firstName, lastName);
    }

}
