package ca.ubc.cs304.model;


import ca.ubc.cs304.modelInterface.Entity;
import ca.ubc.cs304.modelInterface.TableHelper;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Customer extends Entity {

    private CustomerHelper helper = null;

    private final String customerID;
	private final String customerName;
	private final int customerAge;
	private final String creditCardNumber;
	private final String phoneNumber;

	public Customer(String customerID, String customerName, int customerAge, String creditCardNumber, String phoneNumber) {
		this.customerID = customerID;
		this.customerName = customerName;
		this.customerAge = customerAge;
		this.creditCardNumber = creditCardNumber;
		this.phoneNumber = phoneNumber;
        this.helper = new CustomerHelper();

    }

	public String getCustomerID() {
		return customerID;
	}

	public String getCustomerName() {
		return customerName;
	}

	public int getCustomerAge() {
		return customerAge;
	}

	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

    @Override
    public int getAttributeCount() {
	    return 5;
    }

    @Override
    public TableHelper getTableHelper() {
        return this.helper;
    }

    @Override
    public void setTupleParametersToStatement(PreparedStatement ps) throws SQLException {
        ps.setString(1, this.getCustomerID());
        ps.setString(2, this.getCustomerName());
        ps.setInt(3, this.getCustomerAge());
        ps.setString(4, this.getCreditCardNumber());
        ps.setString(5, this.getPhoneNumber());
    }

}
