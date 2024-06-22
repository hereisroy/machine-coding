package parkinglot.cli.command;

import parkinglot.parking.ParkingManager;

public class CreateParkingCommand implements Command {
	
	private String id;
	private int numFloors;
	private int numSlotsPerFloor;
	
	@Override
	public void parse(String[] tokens) throws IllegalArgumentException {
		if(tokens.length!=4) throw new IllegalArgumentException("Invalid number of arguments");
		this.id = tokens[1];
		try {
			this.numFloors = Integer.parseInt(tokens[2]);
			this.numSlotsPerFloor = Integer.parseInt(tokens[3]);
		} catch(NumberFormatException nfex) {
			throw new IllegalArgumentException("Invalid type of arguments");
		}
	}
	
	@Override
	public void execute() {
		String floor = numFloors>1? "floors" : "floor";
		String slot = numFloors>1? "slots" : "slot";
		ParkingManager manager = ParkingManager.getInstance();
		manager.createParkingWithRangedFloors(id, numFloors, numSlotsPerFloor);
		System.out.printf("Created parking lot with %d %s and %d %s per floor\n", numFloors, floor, numSlotsPerFloor, slot);
	}

}
