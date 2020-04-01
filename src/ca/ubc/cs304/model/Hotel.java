package ca.ubc.cs304.model;

public class Hotel {

	private final char[] address;
	private final char[] name;
	private final int capacity;

	public Hotel(char[] address, char[] name, int capacity) {
		this.address = address;
		this.name = name;
		this.capacity = capacity;
	}

	public char[] getAddress() {
		return address;
	}

	public char[] getName() {
		return name;
	}

	public int getCapacity() {
		return capacity;
	}
}
