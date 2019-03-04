package org.cboard.modules.services.job;

import org.cboard.CboardApplication;
import org.cboard.modules.pojo.DashboardJob;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by yfyuan on 2017/2/20.
 */
public class MailJobExecutor implements Job {

    private Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobService jobService = CboardApplication.run.getBean(JobService.class);
        jobService.sendMail((DashboardJob) jobExecutionContext.getMergedJobDataMap().get("job"));
    }
}
