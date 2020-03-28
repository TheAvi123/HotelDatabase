package ca.ubc.cs304.model;

/**
 * The intent for this class is to update/store information about a single branch
 */
public class Service {
	private final char[] service_id;
	private final char[] min_tier_level;
	private final int cost;
	private final char[] hotel_address;

	public Service(char[] service_id, char[] min_tier_level, int cost, char[] hotel_address) {
		this.service_id = service_id;
		this.min_tier_level = min_tier_level;
		this.cost = cost;
		this.hotel_address = hotel_address;
	}

	public char[] getServiceID() {
		return service_id;
	}

	public char[] getMinTierLevel() {
		return min_tier_level;
	}

	public int getCost() {
		return cost;
	}

	public char[] getHotelAddress() {
		return hotel_address;
	}

}
