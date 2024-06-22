package parkinglot.parking.floor;

import java.util.HashMap;
import java.util.List;

import parkinglot.parking.strategy.SlotSelectionStrategy;
import parkinglot.vehicle.Vehicle;
import parkinglot.vehicle.VehicleType;

public abstract class Floor {
	
	HashMap<VehicleType, Integer> countMap = new HashMap<>();

	public abstract int park(Vehicle v, SlotSelectionStrategy strategy);
	public abstract Vehicle unpark(int slotId);
	public abstract List<Integer> getFreeSlots(VehicleType type);
	public abstract List<Integer> getOccupiedSlots(VehicleType type);
	public abstract int getSize();	
	
	public int getFreeSlotCount(VehicleType type) {
		if(!countMap.containsKey(type)) return 0;
		return countMap.get(type);
	}
	
}
