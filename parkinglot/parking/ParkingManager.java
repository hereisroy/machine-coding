package parkinglot.parking;

import java.util.HashMap;
import java.util.LinkedHashMap;

import parkinglot.parking.floor.Floor;
import parkinglot.parking.floor.RangedSlotFloor;
import parkinglot.vehicle.VehicleType;

public class ParkingManager {
	
	private HashMap<String, Parking> parkings = new HashMap<>();
	private final String defaultParkingId = "PR1234";
	private final LinkedHashMap<VehicleType, int[]> defaultRanges = new LinkedHashMap<>() {
		private static final long serialVersionUID = 1L;
	{
		put(VehicleType.TRUCK, new int[] {1, 1});
		put(VehicleType.BIKE, new int[] {2, 3});
		put(VehicleType.CAR, new int[] {4, Integer.MAX_VALUE});
	}};
	
	private ParkingManager() {}
	
	private class ParkingManagerHolder{
		private static final ParkingManager instance = new ParkingManager();
	}
	
	public static ParkingManager getInstance() {
		return ParkingManagerHolder.instance;
	}
	
	public boolean createParkingWithRangedFloors(String pid, int numFloors, int numSlots) {
		if(parkings.containsKey(pid)) return false;
		Parking parking = new Parking(pid);
		Floor newFloor;
		for(int i=1;i<=numFloors;i++) {
			newFloor = new RangedSlotFloor(numSlots, defaultRanges);
			parking.addFloor(newFloor);
		}
		
		parkings.put(pid, parking);
		return true;
	}
	
	public Parking getParking(String pid) {
		if(!parkings.containsKey(pid)) return null;
		return parkings.get(pid);
	}
	
	public String getDefaultParkingId() {
		return defaultParkingId;
	}

}
