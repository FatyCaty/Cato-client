package yusuf.cato.util;

public class TimerUtil {
	public long lastMS = System.currentTimeMillis();
	
	public void reset() {
		lastMS = System.currentTimeMillis();
	}
	
	public boolean hasTimeElapsed(long time, boolean reset) {
		if(System.currentTimeMillis()-lastMS > time) {
			
			if(reset) {
				reset();
			}
			
			return true;
		}
		return false;
	}
	
	public boolean hasTimeElapsedNoReset(long time) {
		if(System.currentTimeMillis()-lastMS > time) {
			return true;
		}
		return false;
	}
}
