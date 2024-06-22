package parkinglot.vehicle;

public class Vehicle {
	
	private VehicleType type;
	private String regId;
	private String color;
	
	public Vehicle(VehicleType type, String regId, String color) {
		this.regId = regId;
		this.type = type;
		this.color = color;
	}
	
	public String getRegId() {
		return regId;
	}
	public void setRegId(String regId) {
		this.regId = regId;
	}
	public VehicleType getType() {
		return type;
	}
	public void setType(VehicleType type) {
		this.type = type;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
}
