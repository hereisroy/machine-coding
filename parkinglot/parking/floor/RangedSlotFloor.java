package parkinglot.parking.floor;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import parkinglot.parking.strategy.SlotSelectionStrategy;
import parkinglot.vehicle.Vehicle;
import parkinglot.vehicle.VehicleType;

public class RangedSlotFloor extends Floor {
	
	private List<Slot> slots;
	private final LinkedHashMap<VehicleType, int[]> rangeMap;
	// HashMap<VehicleType, Integer> countMap is inherited from Floor

	public RangedSlotFloor(int capacity, LinkedHashMap<VehicleType, int[]> rangeMap) {
		slots = new ArrayList<>();
		slots.add(null);
		this.rangeMap = rangeMap;
		init(capacity);
	}
	
	private void init(int initCapacity) {
		VehicleType type;
		int range[], count;
		for(Entry<VehicleType, int[]> entry : rangeMap.entrySet()) {
			type = entry.getKey();
			range = entry.getValue();
			if(initCapacity<range[0]) {
				countMap.put(type, 0);
				continue;
			}
			count = Math.min(range[1], initCapacity)-range[0]+1;
			countMap.put(type, count);
			for(int i=0;i<count;i++)
				slots.add(new Slot(type));
		}
	}
	
	@Override
	public int park(Vehicle v, SlotSelectionStrategy strategy) {
		int slot = 0;
		if(strategy==SlotSelectionStrategy.FIRST_AVAILABLE)
			slot = parkAtFirstAvailableSlot(v);
		else
			System.out.println("Unsupported slot selection strategy!");
		return slot;
	}
	
	private int parkAtFirstAvailableSlot(Vehicle v) {
	int[] range = rangeMap.get(v.getType());
		int start = range[0];
		int end = Math.min(range[1], slots.size()-1);
		int count;
		for(int i=start; i<=end; i++)
			if(slots.get(i).isFree()) {
				slots.get(i).setV(v);
				count = countMap.get(v.getType());
				countMap.put(v.getType(), count-1);
				return i;
			}
		return 0;
	}
	
	@Override
	public Vehicle unpark(int slotId) {
		if(slots.get(slotId).isFree()) return null;
		Vehicle v = slots.get(slotId).getV();
		int freeCount = countMap.get(v.getType());
		slots.get(slotId).setV(null);
		countMap.put(v.getType(), freeCount+1);
		return v;
	}

	@Override
	public List<Integer> getFreeSlots(VehicleType type) {
		List<Integer> res = new LinkedList<>();
		if(!rangeMap.containsKey(type)) return res;
		int[] range = rangeMap.get(type);
		int start = range[0];
		int end = Math.min(range[1], slots.size()-1);
		for(int i=start; i<=end; i++)
			if(slots.get(i).isFree()) res.add(i);
		return res;
	}

	@Override
	public List<Integer> getOccupiedSlots(VehicleType type) {
		List<Integer> res = new LinkedList<>();
		if(!rangeMap.containsKey(type)) return res;
		int[] range = rangeMap.get(type);
		int start = range[0];
		int end = Math.min(range[1], slots.size()-1);
		for(int i=start; i<=end; i++)
			if(!slots.get(i).isFree()) res.add(i);
		return res;
	}
	
	@Override
	public int getSize() {
		return slots.size()-1;
	}
}
