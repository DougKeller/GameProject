package game.utilities;

public class Time {
	/// GAME_TIMER CLASS STATIC VALUES
	public static final int TICKS_PER_SECOND = 10;
	public static final long MILLIS_PER_TICK = 1000 / TICKS_PER_SECOND;
	
	private static boolean VALID = false;
	
	private static long START_TIME = 0;
	private static long TIME_PAUSED = 0;
	private static long TIME_SPENT_PAUSED = 0;
	private static long LOOP_BEGIN = 0;
	private static long LOOP_DURATION = 0;
	
	private static long TICKS = 0;
	
	private static boolean PAUSED = false;
	
	public static void START() {
		if(START_TIME == 0)
			START_TIME = System.currentTimeMillis();
		VALID = true;
	}	
	public static void PAUSE() {
		if(!PAUSED) {
			TIME_PAUSED = System.currentTimeMillis();
			PAUSED = true;
		}
	}
	public static void RESUME() {
		if(PAUSED) {
			TIME_SPENT_PAUSED += System.currentTimeMillis() - TIME_PAUSED;
			PAUSED = false;
		}
	}
	public static void START_LOOP() {
		LOOP_BEGIN = RUNTIME();
	}
	public static void END_LOOP() {
		LOOP_DURATION = RUNTIME() - LOOP_BEGIN;
	}
	public static void TICK() {
		TICKS++;
	}
	public static long TICKS() {
		return TICKS;
	}
	public static long RUNTIME() {
		if(!VALID)
			return 0;
		if(PAUSED)
			return TIME_PAUSED - START_TIME - TIME_SPENT_PAUSED;
		else
			return System.currentTimeMillis() - START_TIME - TIME_SPENT_PAUSED;
	}
	public static long LOOP_DURATION() {
		return LOOP_DURATION;
	}
	public static long TICKS_THIS_LOOP() {
		return LOOP_DURATION / MILLIS_PER_TICK;
	}
	public static boolean PAUSED() {
		return PAUSED;
	}
	
}
