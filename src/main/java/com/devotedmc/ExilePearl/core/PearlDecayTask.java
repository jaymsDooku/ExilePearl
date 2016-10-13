package com.devotedmc.ExilePearl.core;

import com.devotedmc.ExilePearl.ExilePearlApi;
import com.devotedmc.ExilePearl.config.PearlConfig;

/**
 * Interval task that deducts strength from existing prison pearls
 * @author Gordon
 */
final class PearlDecayTask extends ExilePearlTask {
	
	private int interval = 60;

	/**
	 * Creates a new FactoryWorker instance
	 */
	public PearlDecayTask(final ExilePearlApi pearlApi) {
		super(pearlApi);
	}
	
	@Override
	public void start() {
		
		super.start();
		if (enabled) {
			pearlApi.log("Pearl decay will run every %d minutes.", interval);
		}
	}


	@Override
	public String getTaskName() {
		return "Pearl Decay";
	}


	@Override
	public int getTickInterval() {
		return interval * TICKS_PER_MINUTE;
	}


	@Override
	public void run() {
		if (!enabled) {
			return;
		}
		
		pearlApi.decayPearls();
	}
	
	@Override
	public void loadConfig(PearlConfig config) {
		this.interval = pearlApi.getPearlConfig().getPearlHealthDecayIntervalMin();
		
		if (enabled) {
			restart();
		}
	}
}
