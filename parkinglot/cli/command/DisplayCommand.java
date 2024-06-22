package parkinglot.cli.command;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import parkinglot.parking.Parking;
import parkinglot.parking.ParkingManager;
import parkinglot.vehicle.VehicleType;

public class DisplayCommand implements Command {
	
	private DisplayCommandType commandType;
	private VehicleType vehicleType;

	@Override
	public void parse(String[] tokens) throws IllegalArgumentException {
		try {
			commandType = DisplayCommandType.valueOf(tokens[1]);
		} catch(IllegalArgumentException illex) {
			throw new IllegalArgumentException("Invalid Display type");
		}
		try {
			vehicleType = VehicleType.valueOf(tokens[2]);
		} catch(IllegalArgumentException illex) {
			throw new IllegalArgumentException("Invalid Vehicle type");
		}
	}

	@Override
	public void execute() {
		ParkingManager manager = ParkingManager.getInstance();
		String defaultParkingId = manager.getDefaultParkingId();
		Parking parking = manager.getParking(defaultParkingId);
		if(parking==null) {
			System.out.printf("Parking %s not found\n", defaultParkingId);
			return;
		}
		if(this.commandType==DisplayCommandType.free_count) {
			List<int[]> freeCount = parking.getFreeCount(vehicleType);
			for(int[] count : freeCount)
				System.out.printf("No. of free slots for %s on Floor %d: %d\n", vehicleType.toString(), count[0], count[1]);
		
		} else if(this.commandType==DisplayCommandType.free_slots) {
			LinkedHashMap<Integer, List<Integer>> freeSlotMap = parking.getFreeSlots(vehicleType);
			int floorId;
			List<Integer> freeSlots;
			for(Entry<Integer, List<Integer>> entry : freeSlotMap.entrySet()) {
				floorId = entry.getKey();
				freeSlots = entry.getValue();
				System.out.printf("Free slots for %s on Floor %d: ", vehicleType.toString(), floorId);
				for(int i=0;i<freeSlots.size()-1;i++)
					System.out.print(freeSlots.get(i)+",");
				if(freeSlots.size()==0)
					System.out.println();
				else
					System.out.println(freeSlots.get(freeSlots.size()-1));
			}
		} else if(this.commandType==DisplayCommandType.occupied_slots) {
			LinkedHashMap<Integer, List<Integer>> occupiedSlotMap = parking.getOccupiedSlots(vehicleType);
			int floorId;
			List<Integer> occupiedSlots;
			for(Entry<Integer, List<Integer>> entry : occupiedSlotMap.entrySet()) {
				floorId = entry.getKey();
				occupiedSlots = entry.getValue();
				System.out.printf("Occupied slots for %s on Floor %d: ", vehicleType.toString(), floorId);
				for(int i=0;i<occupiedSlots.size()-1;i++)
					System.out.print(occupiedSlots.get(i)+",");
				if(occupiedSlots.size()==0)
					System.out.println();
				else 
					System.out.println(occupiedSlots.get(occupiedSlots.size()-1));
			}
		}
	}

}
