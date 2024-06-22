package parkinglot.cli.command;

import parkinglot.parking.Parking;
import parkinglot.parking.ParkingManager;
import parkinglot.parking.strategy.FloorSelectionStrategy;
import parkinglot.parking.strategy.SlotSelectionStrategy;
import parkinglot.vehicle.Vehicle;
import parkinglot.vehicle.VehicleType;

public class ParkCommand implements Command {
	
	Vehicle vehicle;

	@Override
	public void parse(String[] tokens) throws IllegalArgumentException {
		if(tokens.length!=4) throw new IllegalArgumentException("Invalid number of arguments");
		try {
			VehicleType type = VehicleType.valueOf(tokens[1]);
			String regNo = tokens[2];
			String color = tokens[3];
			this.vehicle = new Vehicle(type, regNo, color);
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
			System.out.printf("Parking %s not found", defaultParkingId);
			return;
		}
		String ticket = parking.parkVehicle(vehicle, FloorSelectionStrategy.FIRST_AVAILABLE, SlotSelectionStrategy.FIRST_AVAILABLE);
		if(ticket.isEmpty())
			System.out.println("Parking Lot Full");
		else
			System.out.printf("Parked vehicle. Ticket ID: %s\n", ticket);
	}

}
