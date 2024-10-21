package com.xweb.starter.modules.schedules.service.impl;

import com.xweb.starter.modules.schedules.req.CronJobReq;
import com.xweb.starter.modules.schedules.req.DailyTimeJobReq;
import com.xweb.starter.modules.schedules.req.JobReq;
import com.xweb.starter.modules.schedules.req.SimpleJobReq;
import com.xweb.starter.modules.schedules.resp.JobResp;
import com.xweb.starter.modules.schedules.service.SchedulerService;
import com.xweb.starter.utils.LogUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.quartz.impl.triggers.DailyTimeIntervalTriggerImpl;
import org.quartz.impl.triggers.SimpleTriggerImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class QuartzSchedulerServiceImpl implements SchedulerService {

  private final Scheduler scheduler;

  @Override
  public JobResp list() {
    var result = new JobResp();
    try {
      for (var groupName : scheduler.getJobGroupNames()) {
        for (var jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {
          var triggers = scheduler.getTriggersOfJob(jobKey);
          var trigger = triggers.getFirst();
          if (trigger instanceof CronTrigger cronTrigger) {
            var jobResp = new JobResp.CronJobResp();
            jobResp.setName(jobKey.getName());
            jobResp.setGroup(jobKey.getGroup());
            jobResp.setNextFireTime(cronTrigger.getNextFireTime());
            jobResp.setPreFireTime(cronTrigger.getPreviousFireTime());
            jobResp.setCronExpression(cronTrigger.getCronExpression());
            jobResp.setDescription(cronTrigger.getDescription());
            jobResp.setFinalFireTime(cronTrigger.getFinalFireTime());
            var triggerState = scheduler.getTriggerState(cronTrigger.getKey());
            jobResp.setState(triggerState.name());
            var cronJobList = result.getCronJobList();
            if(CollectionUtils.isEmpty(cronJobList)) {
              cronJobList = new ArrayList<>();
              result.setCronJobList(cronJobList);
            }
            cronJobList.add(jobResp);
          } else if (trigger instanceof DailyTimeIntervalTrigger dailyTimeIntervalTrigger) {
            var jobResp = new JobResp.DailyTimeJobResp();
            jobResp.setName(jobKey.getName());
            jobResp.setGroup(jobKey.getGroup());
            jobResp.setNextFireTime(dailyTimeIntervalTrigger.getNextFireTime());
            jobResp.setPreFireTime(dailyTimeIntervalTrigger.getPreviousFireTime());
            jobResp.setStartTimeOfDay(dailyTimeIntervalTrigger.getStartTimeOfDay());
            jobResp.setEndTimeOfDay(dailyTimeIntervalTrigger.getEndTimeOfDay());
            jobResp.setRepeatCount(dailyTimeIntervalTrigger.getRepeatCount());
            jobResp.setRepeatInterval(dailyTimeIntervalTrigger.getRepeatInterval());
            jobResp.setIntervalUnit(dailyTimeIntervalTrigger.getRepeatIntervalUnit());
            jobResp.setFinalFireTime(dailyTimeIntervalTrigger.getFinalFireTime());
            jobResp.setTimesTriggered(dailyTimeIntervalTrigger.getTimesTriggered());
            jobResp.setDescription(dailyTimeIntervalTrigger.getDescription());

            var triggerState = scheduler.getTriggerState(dailyTimeIntervalTrigger.getKey());
            jobResp.setState(triggerState.name());
            var dailyTimeJobList = result.getDailyTimeJobList();
            if(CollectionUtils.isEmpty(dailyTimeJobList)) {
              dailyTimeJobList = new ArrayList<>();
              result.setDailyTimeJobList(dailyTimeJobList);
            }
            dailyTimeJobList.add(jobResp);
          } else if (trigger instanceof SimpleTrigger simpleTrigger) {
            var jobResp = new JobResp.SimpleJobResp();
            jobResp.setName(jobKey.getName());
            jobResp.setGroup(jobKey.getGroup());
            jobResp.setNextFireTime(simpleTrigger.getNextFireTime());
            jobResp.setPreFireTime(simpleTrigger.getPreviousFireTime());
            var triggerState = scheduler.getTriggerState(simpleTrigger.getKey());
            jobResp.setState(triggerState.name());
            jobResp.setTimesTriggered(simpleTrigger.getTimesTriggered());
            jobResp.setRepeatInterval(simpleTrigger.getRepeatInterval());
            jobResp.setRepeatCount(simpleTrigger.getRepeatCount());
            jobResp.setDescription(simpleTrigger.getDescription());
            var simpleJobList = result.getSimpleJobList();
            if(CollectionUtils.isEmpty(simpleJobList)) {
              simpleJobList = new ArrayList<>();
              result.setSimpleJobList(simpleJobList);
            }
            simpleJobList.add(jobResp);
          }
        }
      }
    } catch (SchedulerException e) {
      LogUtil.error("查看定时任务失败:调度异常",e);
    }
    return result;
  }

  @Override
  public Date saveJob(JobReq req) {
      return switch (req) {
          case DailyTimeJobReq dailyTimeJobReq -> saveDailyTimeJob(dailyTimeJobReq);
          case SimpleJobReq simpleJobReq -> saveSimpleJob(simpleJobReq);
          case CronJobReq cronJobReq -> saveCronJob(cronJobReq);
          case null, default -> null;
      };
  }

  @Override
  public void run(JobReq jobReq) {
    var jobClassName = jobReq.getName();
    var jobGroupName = jobReq.getGroup();
    try {
      scheduler.triggerJob(JobKey.jobKey(jobClassName, jobGroupName));
    } catch(SchedulerException e){
      LogUtil.error("创建定时任务失败:调度异常",e);
    }
  }

  @Override
  public void pause(JobReq jobReq) {
    var jobClassName = jobReq.getName();
    var jobGroupName = jobReq.getGroup();
    try {
      scheduler.pauseJob(JobKey.jobKey(jobClassName, jobGroupName));
    } catch (SchedulerException e) {
      LogUtil.error("暂停定时任务失败:调度异常",e);
    }
  }

  @Override
  public void resume(JobReq jobReq) {
    var jobClassName = jobReq.getName();
    var jobGroupName = jobReq.getGroup();
    try {
      scheduler.resumeJob(JobKey.jobKey(jobClassName, jobGroupName));
    } catch (SchedulerException e) {
      LogUtil.error("重启定时任务失败:调度异常",e);
    }
  }

  @Override
  public Date rescheduleJob(JobReq req) {
    return switch (req) {
      case DailyTimeJobReq dailyTimeJobReq -> rescheduleDailyTimeJob(dailyTimeJobReq);
      case SimpleJobReq simpleJobReq -> rescheduleSimpleJob(simpleJobReq);
      case CronJobReq cronJobReq -> rescheduleCronJob(cronJobReq);
      case null, default -> null;
    };
  }

  @Override
  public boolean delete(JobReq jobReq) {
    var jobClassName = jobReq.getName();
    var jobGroupName = jobReq.getGroup();
    try {
      scheduler.pauseTrigger(TriggerKey.triggerKey(jobClassName, jobGroupName));
      scheduler.unscheduleJob(TriggerKey.triggerKey(jobClassName, jobGroupName));
      return scheduler.deleteJob(JobKey.jobKey(jobClassName, jobGroupName));
    } catch (SchedulerException e) {
      LogUtil.error("删除定时任务失败:调度异常",e);
      return false;
    }
  }

  /**
   * 用于基于Cron 表达式的调度任务。
   * Cron 表达式是一种用于表示复杂时间调度的字符串，
   * 能够精确控制任务的执行时间和频率。
   * 这种调度器在 Quartz 中非常灵活，可以精确到每分钟、每小时、每天、每周、每月，甚至特定的秒数。
   */
  private Date saveCronJob(CronJobReq req) {
    var jobClassName = req.getName();
    var jobGroupName = req.getGroup();
    var cronExpression = req.getCronExpression();
    var description = req.getDescription();
    try {
      //构建job信息
      var jobDetail = JobBuilder.newJob((Class<? extends Job>) Class.forName(jobClassName))
              .withIdentity(jobClassName, jobGroupName).withDescription(description).build();
      //表达式调度构建器(即任务执行的时间)
      var scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
      //按新的cronExpression表达式构建一个新的trigger
      var trigger = TriggerBuilder.newTrigger()
              .withIdentity(jobClassName, jobGroupName)
              .withDescription(description)
              .withSchedule(scheduleBuilder)
              .build();
      return scheduler.scheduleJob(jobDetail, trigger);
    } catch (SchedulerException e) {
      LogUtil.error("创建定时任务失败:调度异常",e);
    } catch (ClassNotFoundException e) {
      LogUtil.error("创建定时任务失败：任务类不存在",e);
    }
    return null;
  }

  /**
   * 基于简单的时间间隔，适合短周期、固定频率的任务
   */
  private Date saveSimpleJob(SimpleJobReq req) {
    var jobClassName = req.getName();
    var jobGroupName = req.getGroup();
    var description = req.getDescription();
    var fixedRate = req.getFixedRate();
    var repeatCount = req.getRepeatCount();
    try {
      //构建job信息
      var jobDetail = JobBuilder.newJob((Class<? extends Job>) Class.forName(jobClassName))
              .withIdentity(jobClassName, jobGroupName).withDescription(description).build();
      var schedule = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(fixedRate);
      if(repeatCount>0){
        schedule.withRepeatCount(repeatCount);
      } else{
        schedule.repeatForever();
      }
      var trigger = TriggerBuilder.newTrigger()
              .withIdentity(jobClassName,jobGroupName)
              .withSchedule(schedule)
              .withDescription(description).build();
      scheduler.scheduleJob(jobDetail, trigger);
    } catch (SchedulerException e) {
      LogUtil.error("创建定时任务失败:调度异常",e);
    } catch (ClassNotFoundException e) {
      LogUtil.error("创建定时任务失败：任务类不存在",e);
    }
    return null;
  }

  /**
   * 用于一天之内的特定时间段按固定间隔触发任务，可以精确控制作业在某个时间段内的执行频率，例如每天下午1点到5点每30分钟执行一次。它不会跨越到下一天，而是严格在一天之内的指定时间段内工作。
   * 使用场景：
   * 在工作时间内的定期提醒（如每天9:00到17:00每小时发送一次提醒）。
   * 在每天的特定时段进行轮询或监控（如每天晚上8:00到11:00每隔15分钟轮询一次任务状态）。
   * 在营业时间内的特定任务（如每天10:00到18:00每隔30分钟执行一次销售统计）。
   */
  private Date saveDailyTimeJob(DailyTimeJobReq req) {
    var jobClassName = req.getName();
    var jobGroupName = req.getGroup();
    var description = req.getDescription();
    var startTime = req.getStartTime();
    var endTime = req.getEndTime();
    var fixedRate = req.getFixedRate();
    var endingDailyAfterCount = req.getEndingDailyAfterCount();
    try {
      //构建job信息
      var jobDetail = JobBuilder.newJob((Class<? extends Job>) Class.forName(jobClassName))
              .withIdentity(jobClassName, jobGroupName).withDescription(description).build();
      var schedule = DailyTimeIntervalScheduleBuilder.dailyTimeIntervalSchedule().onEveryDay();
      schedule
              .startingDailyAt(startTime)
              .withIntervalInSeconds(fixedRate);

      if (Objects.nonNull(endTime)) {
        schedule.endingDailyAt(endTime);
      } else {
        schedule.endingDailyAfterCount(endingDailyAfterCount);
      }
      var trigger = TriggerBuilder.newTrigger()
              .withIdentity(jobClassName,jobGroupName)
              .withSchedule(schedule)
              .withDescription(description).build();
      scheduler.scheduleJob(jobDetail, trigger);
    } catch (SchedulerException e) {
      LogUtil.error("创建定时任务失败:调度异常",e);
    } catch (ClassNotFoundException e) {
      LogUtil.error("创建定时任务失败：任务类不存在",e);
    }
    return null;
  }

  private Date rescheduleCronJob(CronJobReq cronJobReq) {
    var jobClassName = cronJobReq.getName();
    var jobGroupName = cronJobReq.getGroup();
    var cronExpression = cronJobReq.getCronExpression();
    var description = cronJobReq.getDescription();
    try {
      var triggerKey = TriggerKey.triggerKey(jobClassName, jobGroupName);
      // 表达式调度构建器
      var scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
      var trigger = (CronTriggerImpl) scheduler.getTrigger(triggerKey);
      trigger.setStartTime(new Date());
      trigger.getTriggerBuilder().withIdentity(triggerKey).withDescription(description).withSchedule(scheduleBuilder).build();
      // 按新的trigger重新设置job执行
      return scheduler.rescheduleJob(triggerKey, trigger);
    } catch (SchedulerException e) {
      LogUtil.error("更新定时任务失败:调度异常",e);
    }
    return null;
  }

  private Date rescheduleSimpleJob(SimpleJobReq simpleJobReq) {
    var jobClassName = simpleJobReq.getName();
    var jobGroupName = simpleJobReq.getGroup();
    var description = simpleJobReq.getDescription();
    var repeatCount = simpleJobReq.getRepeatCount();
    var fixedRate = simpleJobReq.getFixedRate();
    try {
      var triggerKey = TriggerKey.triggerKey(jobClassName, jobGroupName);
      // 表达式调度构建器
      var trigger = (SimpleTriggerImpl)scheduler.getTrigger(triggerKey);
      trigger.setStartTime(new Date());
      var schedule = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(fixedRate);
      if(repeatCount>0){
        schedule.withRepeatCount(repeatCount);
      } else{
        schedule.repeatForever();
      }
      trigger.getTriggerBuilder().withIdentity(triggerKey).withDescription(description).withSchedule(schedule).build();
//       按新的trigger重新设置job执行
      return scheduler.rescheduleJob(triggerKey, trigger);
    } catch (SchedulerException e) {
      LogUtil.error("更新定时任务失败:调度异常",e);
    }
    return null;
  }

  private Date rescheduleDailyTimeJob(DailyTimeJobReq dailyTimeJobReq) {
    var jobClassName = dailyTimeJobReq.getName();
    var jobGroupName = dailyTimeJobReq.getGroup();
    var description = dailyTimeJobReq.getDescription();
    var endingDailyAfterCount = dailyTimeJobReq.getEndingDailyAfterCount();
    var fixedRate = dailyTimeJobReq.getFixedRate();
    var startTime = dailyTimeJobReq.getStartTime();
    var endTime = dailyTimeJobReq.getEndTime();
    try {
      var triggerKey = TriggerKey.triggerKey(jobClassName, jobGroupName);
      // 表达式调度构建器
      var trigger = (DailyTimeIntervalTriggerImpl) scheduler.getTrigger(triggerKey);
      trigger.setStartTime(new Date());
      var schedule = DailyTimeIntervalScheduleBuilder.dailyTimeIntervalSchedule().onEveryDay();

      schedule
              .startingDailyAt(startTime)
              .withIntervalInSeconds(fixedRate);

      if (Objects.nonNull(endTime)) {
        schedule.endingDailyAt(endTime);
      } else {
        schedule.endingDailyAfterCount(endingDailyAfterCount);
      }

      trigger.getTriggerBuilder().withSchedule(schedule).withDescription(description).build();
      // 按新的trigger重新设置job执行
      return scheduler.rescheduleJob(triggerKey, trigger);
    } catch (SchedulerException e) {
      LogUtil.error("更新定时任务失败:调度异常",e);
    }
    return null;
  }

}
