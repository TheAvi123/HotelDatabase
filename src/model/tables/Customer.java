package model.tables;

import model.tableHelpers.CustomerHelper;
import model.Table;
import model.TableHelper;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Customer extends Table {

    private CustomerHelper helper;

    private final String customerID;
	private final String customerName;
	private final int customerAge;
	private final String paymentInformation;
	private final String phoneNumber;

	public Customer(String customerID, String customerName, int customerAge, String paymentInformation, String phoneNumber) {
		this.customerID = customerID;
		this.customerName = customerName;
		this.customerAge = customerAge;
		this.paymentInformation = paymentInformation;
		this.phoneNumber = phoneNumber;

        this.helper = new CustomerHelper();
    }

	/////////////////////////////////////////////////////////////////////////////////////////////////////////

	public String getCustomerID() {
		return customerID;
	}

	public String getCustomerName() {
		return customerName;
	}

	public int getCustomerAge() {
		return customerAge;
	}

	public String getPaymentInformation() {
		return paymentInformation;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////

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
        ps.setString(4, this.getPaymentInformation());
        ps.setString(5, this.getPhoneNumber());
    }

}
