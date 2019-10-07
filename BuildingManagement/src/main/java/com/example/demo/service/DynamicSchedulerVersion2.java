package com.example.demo.service;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ScheduledFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import com.example.demo.entity.ConfigItem;
import com.example.demo.repository.ConfigRepo;

/**
 * Alternative version for DynamicScheduler This one should support everything
 * the basic dynamic scheduler does, and on top of it, you can cancel and
 * re-activate the scheduler.
 */
@Service
public class DynamicSchedulerVersion2 implements SchedulingConfigurer {
	@Autowired
	ConfigRepo repo;
	ScheduledTaskRegistrar scheduledTaskRegistrar;

	// ScheduledFuture future1;
	// ScheduledFuture future2;
	ScheduledFuture future3;
	Map<ScheduledFuture, Boolean> futureMap = new HashMap<>();

	@Bean
	public TaskScheduler poolScheduler2() {
		ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
		scheduler.setThreadNamePrefix("ThreadPoolTaskScheduler");
		scheduler.setPoolSize(1);
		scheduler.initialize();
		return scheduler;
	}

	// We can have multiple tasks inside the same registrar as we can see below.
	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		if (scheduledTaskRegistrar == null) {
			scheduledTaskRegistrar = taskRegistrar;
		}
		if (taskRegistrar.getScheduler() == null) {
			taskRegistrar.setScheduler(poolScheduler2());
		}

		/*
		 * if (future1 == null || (future1.isCancelled() && futureMap.get(future1) ==
		 * true)) { future1 = taskRegistrar.getScheduler().schedule(() ->
		 * scheduleFixed(5), t -> { Calendar nextExecutionTime = new
		 * GregorianCalendar(); Date lastActualExecutionTime =
		 * t.lastActualExecutionTime();
		 * nextExecutionTime.setTime(lastActualExecutionTime != null ?
		 * lastActualExecutionTime : new Date()); nextExecutionTime.add(Calendar.SECOND,
		 * 5); return nextExecutionTime.getTime(); }); }
		 * 
		 * if (future2 == null || (future2.isCancelled() && futureMap.get(future2) ==
		 * true)) { future2 = taskRegistrar.getScheduler().schedule(() ->
		 * scheduleFixed(8), t -> { Calendar nextExecutionTime = new
		 * GregorianCalendar(); Date lastActualExecutionTime =
		 * t.lastActualExecutionTime();
		 * nextExecutionTime.setTime(lastActualExecutionTime != null ?
		 * lastActualExecutionTime : new Date()); nextExecutionTime.add(Calendar.SECOND,
		 * 8); return nextExecutionTime.getTime(); }); }
		 */

		// Or cron way, you can also get the expression from DB or somewhere else just
		// like we did in DynamicScheduler service.
		if (future3 == null || (future3.isCancelled() && futureMap.get(future3) == true)) {
			ConfigItem configItem = repo.findByConfigKey("a");
			CronTrigger croneTrigger = new CronTrigger(configItem.getConfigValue(), TimeZone.getDefault());
			future3 = taskRegistrar.getScheduler().schedule(() -> scheduleCron(configItem.getConfigValue()),
					croneTrigger);
		}
	}

//	public void scheduleFixed(int frequency) {
//		System.out.println(frequency);
//
//	}

	// Only reason this method gets the cron as parameter is for debug purposes.
	public void scheduleCron(String cron) {
		System.out.println(cron);
	}

	/**
	 * @param mayInterruptIfRunning {@code true} if the thread executing this task
	 *                              should be interrupted; otherwise, in-progress
	 *                              tasks are allowed to complete
	 */

	@SuppressWarnings("rawtypes")
	public void cancelFuture(boolean mayInterruptIfRunning, ScheduledFuture future) {

		future.cancel(mayInterruptIfRunning); // set to false if you want the running
		futureMap.put(future, false);
	}

	@SuppressWarnings("rawtypes")
	public void activateFuture(ScheduledFuture future) {

		futureMap.put(future, true);
		configureTasks(scheduledTaskRegistrar);
	}

	public void cancelAll() {
		// cancelFuture(true, future1);
		// cancelFuture(true, future2);
		cancelFuture(true, future3);
	}

	public void activateAll() {
		// activateFuture(future1);
		// activateFuture(future2);
		activateFuture(future3);
	}

}
