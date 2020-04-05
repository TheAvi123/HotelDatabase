package model.tables;

import model.tableHelpers.CustomerHelper;
import model.Table;
import model.TableHelper;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Customer extends Table {

    private CustomerHelper helper;

    private final String id;
	private final String name;
	private final int age;
	private final String paymentInformation;
	private final String phoneNumber;

	public Customer(String id, String name, int age, String paymentInformation, String phoneNumber) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.paymentInformation = paymentInformation;
		this.phoneNumber = phoneNumber;

        this.helper = new CustomerHelper();
    }

	/////////////////////////////////////////////////////////////////////////////////////////////////////////

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
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
        ps.setString(1, this.getId());
        ps.setString(2, this.getName());
        ps.setInt(3, this.getAge());
        ps.setString(4, this.getPaymentInformation());
        ps.setString(5, this.getPhoneNumber());
    }

}
