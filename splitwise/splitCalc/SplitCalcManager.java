package splitwise.splitCalc;

import java.util.HashMap;

public class SplitCalcManager {
	private HashMap<String, SplitCalc> calcMap = new HashMap<>();
	
	private SplitCalcManager() {}
	
	private static class SplitCalcManagerHolder{
		private static final SplitCalcManager instance = new SplitCalcManager();
	}
	
	public static SplitCalcManager getInstance() {
		return SplitCalcManagerHolder.instance;
	}
	
	public void registerCalc(String splitType, SplitCalc calc) {
		calcMap.put(splitType, calc);
	}
	
	public SplitCalc getCalc(String splitType) throws IllegalArgumentException {
		if(!calcMap.containsKey(splitType)) 
			throw new IllegalArgumentException("Unsupported Split Type");
		return calcMap.get(splitType);
	}
}
