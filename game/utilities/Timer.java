package game.utilities;

public class Timer {
	private long startTick;
	private long pauseTick;
	private long ticksPaused;
	private long duration;
	
	private boolean paused;
	
	// *** Constructors *** //
	public Timer() {
		this(0f);
	}
	public Timer(double seconds) {
		duration = (long) (seconds * 1000 / Time.MILLIS_PER_TICK);
		paused = false;
	}
	public Timer(long milliseconds) {
		duration = milliseconds / Time.MILLIS_PER_TICK;
		paused = false;
	}
	
	// *** Accessors *** //
	public long ticks() {
		if(paused)
			return pauseTick - startTick - ticksPaused;
		
		return Time.TICKS() - startTick;
	}
	public long milliseconds() {
		return ticks() * Time.MILLIS_PER_TICK;
	}
	public long duration() {
		return duration;
	}
	public long duration_ms() {
		return duration * Time.MILLIS_PER_TICK;
	}
	
	// *** Operations *** //
	public void start() {
		startTick = Time.TICKS();
		ticksPaused = 0;
		paused = false;
	}
	public void restart() {
		start();
	}
	public void pause() {
		if(!paused) {
			pauseTick = Time.TICKS();
			paused = true;
		}
	}
	public void resume() {
		if(paused) {
			ticksPaused += (Time.TICKS() - pauseTick);
			paused = false;
		}
	}
	public boolean complete() {
		return ticks() > duration;
	}
}
