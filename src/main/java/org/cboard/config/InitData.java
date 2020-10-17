package org.cboard.config;

import org.cboard.modules.dao.JobDao;
import org.cboard.modules.pojo.DashboardJob;
import org.cboard.modules.services.job.JobService;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * @author WangKun
 * @create 2019-03-02
 * @desc Initialize project data
 **/
@Component
public class InitData implements ApplicationRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(InitData.class);

    @Autowired
    private PropertiesConfig propertiesConfig;

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    @Autowired
    private JobDao jobDao;

    @Autowired
    private JobService jobService;

    /**
     * db Scheduler job init
     */
    public void configScheduler() {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        try {
            GroupMatcher<TriggerKey> matcher = GroupMatcher.anyTriggerGroup();
            Set<TriggerKey> triggerKeySet = scheduler.getTriggerKeys(matcher);
            if(!triggerKeySet.isEmpty()) {
                triggerKeySet.forEach(triggerKey -> {
                    try {
                        scheduler.unscheduleJob(triggerKey);
                    } catch (SchedulerException e) {
                        LOGGER.error("" , e);
                    }
                });
            }
            scheduler.clear();
        } catch (SchedulerException e) {
            LOGGER.error("", e);
        }
        List<DashboardJob> jobList = jobDao.getJobList(propertiesConfig.getAdminId());
        for (DashboardJob job : jobList) {
            jobService.scheduleJob(job);
        }
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        /**db job init*/
        configScheduler();
    }
}
