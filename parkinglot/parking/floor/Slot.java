package parkinglot.parking.floor;

import parkinglot.vehicle.Vehicle;
import parkinglot.vehicle.VehicleType;

public class Slot {
	
	private VehicleType type;
	private Vehicle v;
	
	public Slot(VehicleType type) {
		this.type = type;
	}

	public boolean isFree() {
		if(this.v==null) return true;
		return false;
	}
	
	public VehicleType getType() {
		return type;
	}
	public void setType(VehicleType type) {
		this.type = type;
	}
	public Vehicle getV() {
		return v;
	}
	public void setV(Vehicle v) {
		this.v = v;
	}

}
