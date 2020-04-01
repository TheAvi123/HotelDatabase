package ca.ubc.cs304.model;

public class RoomClassification {

	private final char[] roomType;
	private final char[] tierLevel;

	public RoomClassification(char[] roomType, char[] tierLevel) {
		this.roomType = roomType;
		this.tierLevel = tierLevel;
	}

	public char[] getRoomType() {
		return roomType;
	}

	public char[] getTierLevel() {
		return tierLevel;
	}
}
