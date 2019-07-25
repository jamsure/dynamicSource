package org.jydw.mqtt.service.impl;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Map;

public class QuartzManager {
	private static SchedulerFactory schedulerFactory = new StdSchedulerFactory();
	
	private static final String DEFAULT_JOBNAME = "defaultJob";
	private static final String DEFAULT_GROUPNAME = "defaultGroup";
	private static int jobIndex = 0;
	private static int groupIndex = 0;
	
	//存储须执行的任务的键值对
	private static Map<Object, Object> keyMap = Maps.newHashMap();
	
	public static Object getValue(Object key) {
		return QuartzManager.keyMap.get(key);
	}

	/**
	 * @Description: 添加一个定时任务
	 * 
	 * @param jobName 任务名
	 * @param jobGroupName 任务组名
	 * @param triggerName 触发器名
	 * @param triggerGroupName 触发器组名
	 * @param jobClass 任务
	 * @param cronExpression 时间设置，参考quartz说明文档
	 * @param buffer 巡检需要传递的 StringBuffer
	 */
	public static void saveJob(String jobName, String jobGroupName, String triggerName, 
			String triggerGroupName, Class<? extends Job> jobClass, String cronExpression, Object buffer) {
		try {
			jobName = StringUtils.isBlank(jobName) ? DEFAULT_JOBNAME + (jobIndex ++) : jobName;
			jobGroupName = StringUtils.isBlank(jobGroupName) ? DEFAULT_GROUPNAME + (groupIndex ++) : jobGroupName;
			triggerName = StringUtils.isBlank(triggerName) ? DEFAULT_JOBNAME + (jobIndex ++)  + "trigger": triggerName;
			triggerGroupName = StringUtils.isBlank(triggerGroupName) ? DEFAULT_GROUPNAME + (groupIndex ++) + "trigger": triggerGroupName;
			//计划任务buffer为空，不添加任务
			if (buffer == null) {
				return ;
			}
			//表达式为空，不添加
			if (StringUtils.isBlank(cronExpression)) {
				return ;
			}
			//检查是否存在任务，存在则执行修改
			if (check(jobName, jobGroupName)) {
				modifyJobTime(triggerName, triggerGroupName, cronExpression);
				return ;
			}
			Scheduler sched = schedulerFactory.getScheduler();
			//创建任务
			JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobName, jobGroupName).build();
			// 触发器
			CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(triggerName, triggerGroupName)
					.withSchedule(CronScheduleBuilder.cronScheduleNonvalidatedExpression(cronExpression)).build();
			sched.scheduleJob(jobDetail, trigger);
			QuartzManager.keyMap.put(jobDetail.getKey(), buffer);
		} catch (Exception e) {
			System.out.println(cronExpression);
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * @Description: 添加一个定时任务
	 * 
	 * @param jobKey
	 * @param triggerKey
	 * @param jobClass
	 * @param cronExpression
	 */
	public static void saveJob(JobKey jobKey, TriggerKey triggerKey, Class<? extends Job> jobClass,String cronExpression, String buffer) {
		try {
			//计划任务buffer为空，不添加任务
			if (StringUtils.isBlank(buffer)) {
				return ;
			}
			//检查是否存在任务，存在则执行修改
			if (check(jobKey)) {
				modifyJobTime(triggerKey, cronExpression);
				return ;
			}
			Scheduler sched = schedulerFactory.getScheduler();
			//创建任务
			JobDetail jobDetail = JobBuilder.newJob().withIdentity(jobKey).ofType(jobClass).build();
			// 触发器
			CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(triggerKey)
					.withSchedule(CronScheduleBuilder.cronScheduleNonvalidatedExpression(cronExpression)).build();
			sched.scheduleJob(jobDetail, trigger);
			QuartzManager.keyMap.put(jobDetail.getKey(), buffer);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 检查连接池中是否已经存在当前任务
	 * 
	 * @param jobName 任务名称
	 * @param jobGroupName 任务组名
	 * @return 存在 : true, 不存在 : false;
	 */
	private static Boolean check(String jobName, String jobGroupName){
		try {
			Scheduler sched = schedulerFactory.getScheduler();
			JobKey jobKey = new JobKey(jobName, jobGroupName);
			JobDetail jobDetail = sched.getJobDetail(jobKey);
			if (jobDetail != null) {
				return true;
			}
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	/**
	 * 检查连接池中是否已经存在当前任务
	 * 
	 * @param jobKey
	 * @return 存在 : true, 不存在 : false;
	 */
	private static Boolean check(JobKey jobKey){
		try {
			Scheduler sched = schedulerFactory.getScheduler();
			JobDetail jobDetail = sched.getJobDetail(jobKey);
			if (jobDetail != null) {
				return true;
			}
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	/**
	 * @Description: 修改一个任务的触发时间
	 * 
	 * @param triggerName
	 * @param triggerGroupName
	 * @param cronExpression
	 */
	public static void modifyJobTime(String triggerName,
			String triggerGroupName, String cronExpression) {
		try {
			Scheduler sched = schedulerFactory.getScheduler();
			TriggerKey triggerKey = new TriggerKey(triggerName, triggerGroupName);
			CronTrigger trigger = (CronTrigger) sched.getTrigger(triggerKey);
			if (trigger == null) {
				return;
			}
			String oldTime = trigger.getCronExpression();
			if (!oldTime.equalsIgnoreCase(cronExpression)) {
				// 修改触发器时间
				CronTrigger ct = TriggerBuilder.newTrigger().withIdentity(triggerKey).
						withSchedule(CronScheduleBuilder.cronScheduleNonvalidatedExpression(cronExpression)).build();
				// 重启触发器
				sched.rescheduleJob(triggerKey, ct);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @Description: 修改一个任务的触发时间
	 * 
	 * @param triggerKey
	 * @param cronExpression
	 */
	public static void modifyJobTime(TriggerKey triggerKey, String cronExpression) {
		try {
			Scheduler sched = schedulerFactory.getScheduler();
			CronTrigger trigger = (CronTrigger) sched.getTrigger(triggerKey);
			if (trigger == null) {
				return;
			}
			String oldTime = trigger.getCronExpression();
			if (!oldTime.equalsIgnoreCase(cronExpression)) {
				// 修改触发器时间
				CronTrigger ct = TriggerBuilder.newTrigger().withIdentity(triggerKey).
						withSchedule(CronScheduleBuilder.cronScheduleNonvalidatedExpression(cronExpression)).build();
				// 重启触发器
				sched.rescheduleJob(triggerKey, ct);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @Description: 移除一个任务
	 * 
	 * @param jobName
	 * @param jobGroupName
	 * @param triggerName
	 * @param triggerGroupName
	 */
	public static void removeJob(String jobName, String jobGroupName,
			String triggerName, String triggerGroupName) {
		try {
			//获取触发器关键字以，任务关键字
			TriggerKey triggerKey = new TriggerKey(triggerName, triggerGroupName);
			JobKey jobKey = new JobKey(jobName, jobGroupName);
			
			Scheduler sched = schedulerFactory.getScheduler();
			// 停止触发器
			sched.pauseTrigger(triggerKey);	
			// 移除触发器
			sched.unscheduleJob(triggerKey);				
			// 删除任务
			sched.deleteJob(jobKey);	
			QuartzManager.keyMap.remove(jobKey);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * @Description: 移除一个任务
	 * 
	 * @param jobKey
	 * @param triggerKey
	 */
	public static void removeJob(JobKey jobKey, TriggerKey triggerKey) {
		try {
			Scheduler sched = schedulerFactory.getScheduler();
			// 停止触发器
			sched.pauseTrigger(triggerKey);	
			// 移除触发器
			sched.unscheduleJob(triggerKey);				
			// 删除任务
			sched.deleteJob(jobKey);	
			QuartzManager.keyMap.remove(jobKey);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @Description:启动所有定时任务
	 */
	public static void startJobs() {
		try {
			Scheduler sched = schedulerFactory.getScheduler();
			sched.start();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @Description:关闭所有定时任务
	 */
	public static void shutdownJobs() {
		try {
			Scheduler sched = schedulerFactory.getScheduler();
			if (!sched.isShutdown()) {
				sched.shutdown();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * @Description:暂停一个任务
	 * 
	 * @param triggerName
	 * @param triggerGroupName
	 */
	public static void pauseJob(String triggerName, String triggerGroupName){
		try {
			//获取触发器关键字以，任务关键字
			TriggerKey triggerKey = new TriggerKey(triggerName, triggerGroupName);
			
			Scheduler sched = schedulerFactory.getScheduler();
			// 停止触发器
			sched.pauseTrigger(triggerKey);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void pauseJob(TriggerKey triggerKey){
		try {
			Scheduler sched = schedulerFactory.getScheduler();
			// 停止触发器
			sched.pauseTrigger(triggerKey);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
