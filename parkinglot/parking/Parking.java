package parkinglot.parking;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import parkinglot.parking.floor.Floor;
import parkinglot.parking.strategy.FloorSelectionStrategy;
import parkinglot.parking.strategy.SlotSelectionStrategy;
import parkinglot.vehicle.Vehicle;
import parkinglot.vehicle.VehicleType;

public class Parking {
	
	private String id;
	private List<Floor> floors;
	
	public Parking(String id) {
		this.id=id;
		floors = new ArrayList<>();
		floors.add(null);
	}
	
	synchronized void addFloor(Floor floor) {
		this.floors.add(floor);
	}
	
	public synchronized String parkVehicle(Vehicle v, FloorSelectionStrategy floorStrategy, SlotSelectionStrategy slotStrategy) {
		String ticket=null;
		if(floorStrategy==FloorSelectionStrategy.FIRST_AVAILABLE)
			ticket = parkVehicleAtFirstAvailableFloor(v, slotStrategy);
		return ticket;
	}
	
	private String parkVehicleAtFirstAvailableFloor(Vehicle v, SlotSelectionStrategy slotStrategy) {
		int slotId;
		for(int i=1;i<=getSize();i++)
			for(int j=1;j<=floors.get(i).getSize();j++) {
				slotId = floors.get(i).park(v, slotStrategy);
				if(slotId==0) continue;
				return String.format("%s_%d_%d", this.id, i, slotId);
			}
		return "";
	}
	
	public synchronized Vehicle unparkVehicle(int floorId, int slotId) {
		if(floorId>getSize() || slotId>floors.get(floorId).getSize())
			return null;
		return floors.get(floorId).unpark(slotId);
	}
	
	public synchronized List<int[]> getFreeCount(VehicleType type){
		List<int[]> res = new LinkedList<>();
		for(int i=1;i<=getSize();i++)
			res.add(new int[] {i, floors.get(i).getFreeSlotCount(type)});
		return res;
	}
	
	public synchronized LinkedHashMap<Integer, List<Integer>> getFreeSlots(VehicleType type){
		LinkedHashMap<Integer, List<Integer>> res = new LinkedHashMap<>();
		for(int i=1;i<=getSize();i++)
			res.put(i, floors.get(i).getFreeSlots(type));
		return res;
	}
	
	public synchronized LinkedHashMap<Integer, List<Integer>> getOccupiedSlots(VehicleType type){
		LinkedHashMap<Integer, List<Integer>> res = new LinkedHashMap<>();
		for(int i=1;i<=getSize();i++)
			res.put(i, floors.get(i).getOccupiedSlots(type));
		return res;
	}
	
	public synchronized int getSize() {
		return floors.size()-1;
	}

}
