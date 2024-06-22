package parkinglot;

import java.util.Scanner;

import parkinglot.cli.CommandInvoker;
import parkinglot.cli.command.CreateParkingCommand;
import parkinglot.cli.command.DisplayCommand;
import parkinglot.cli.command.ParkCommand;
import parkinglot.cli.command.UnparkCommand;

public class Driver {
	
	private static final CommandInvoker invoker; 
	private static final Scanner inp;
	
	static {
		invoker = new CommandInvoker();
		invoker.register("create_parking_lot", new CreateParkingCommand());
		invoker.register("park_vehicle", new ParkCommand());
		invoker.register("unpark_vehicle", new UnparkCommand());
		invoker.register("display", new DisplayCommand());
		inp = new Scanner(System.in);
		
	}

	public static void main(String args[]) {
		String line;
		System.out.println("Welcome to ParkingManager.");
		while(true) {
			System.out.print("> ");
			line = inp.nextLine();
			if(line.equalsIgnoreCase("exit")) {
				System.out.println("Thank you for using ParkingLotManager. Bye Bye.");
				inp.close();
				break;
			}
			invoker.invoke(line);
		}
	}

}
