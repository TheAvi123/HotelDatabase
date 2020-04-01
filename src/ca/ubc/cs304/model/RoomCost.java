package ca.ubc.cs304.model;

public class RoomCost {

	private final int roomFloor;
	private final int roomNumber;
	private final double cost;

	public RoomCost(int roomFloor, int roomNumber, double cost) {
		this.roomFloor = roomFloor;
		this.roomNumber = roomNumber;
		this.cost = cost;
	}

	public int getRoomFloor() {
		return roomFloor;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public double getCost() {
		return cost;
	}
}
