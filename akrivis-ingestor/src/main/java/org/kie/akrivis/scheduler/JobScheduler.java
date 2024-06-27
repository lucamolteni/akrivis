package org.kie.akrivis.scheduler;

import io.quarkus.scheduler.Scheduler;
import jakarta.inject.Inject;

import java.util.List;
import java.util.logging.Logger;

public class JobScheduler {

    private static final Logger LOG = Logger.getLogger(JobScheduler.class.getName());

    @Inject
    Scheduler scheduler;

    @Inject
    JobSchedulerRepository jobSchedulerRepository;

    @Inject
    JobExecutor fetchJobExecutor;

    // This should be run at startup
    public void schedule() {

        List<Job> activeJobs = jobSchedulerRepository.findActiveJobs();

        if (activeJobs.isEmpty()) {
            LOG.info("There are active jobs, not scheduling new job");
            return;
        }

        for(Job job : activeJobs) {
            scheduler.newJob(job.id + job.endpoint)
                     .setCron(job.cron)
                     .setTask(executionContext -> fetchJobExecutor.run(executionContext, job))
                     .schedule();
        }
    }
}
