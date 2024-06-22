package parkinglot.cli.command;

import parkinglot.parking.Parking;
import parkinglot.parking.ParkingManager;
import parkinglot.vehicle.Vehicle;

public class UnparkCommand implements Command {
	
	String pid;
	int floorId;
	int slotId;

	@Override
	public void parse(String[] tokens) throws IllegalArgumentException {
		if(tokens.length!=2)
			throw new IllegalArgumentException("Invalid Ticket");
		String[] ticketParts = tokens[1].split("_");
		if(ticketParts.length!=3)
			throw new IllegalArgumentException("Invalid Ticket");
		pid = ticketParts[0];
		try {
			floorId = Integer.parseInt(ticketParts[1]);
			slotId = Integer.parseInt(ticketParts[2]);
		} catch(NumberFormatException nfex) {
			throw new IllegalArgumentException("Invalid Ticket"); 
		}
		
	}

	@Override
	public void execute() {
		ParkingManager manager = ParkingManager.getInstance();
		Parking parking = manager.getParking(pid);
		if(parking==null) {
			System.out.println("Invalid Ticket");
			return;
		}
		Vehicle v = parking.unparkVehicle(floorId, slotId);
		if(v==null) {
			System.out.println("Invalid Ticket");
			return;
		}
		
		System.out.printf("Unparked vehicle with Registration Number: %s and Color: %s\n", v.getRegId(), v.getColor());

	}

}
