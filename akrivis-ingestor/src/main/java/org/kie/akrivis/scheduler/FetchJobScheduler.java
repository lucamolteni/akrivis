package org.kie.akrivis.scheduler;

import io.quarkus.scheduler.Scheduler;
import jakarta.inject.Inject;

import java.util.List;
import java.util.logging.Logger;

public class FetchJobScheduler {

    private static final Logger LOG = Logger.getLogger(FetchJobScheduler.class.getName());

    @Inject
    Scheduler scheduler;

    @Inject
    FetchJobSchedulerRepository fetchJobSchedulerRepository;

    @Inject
    FetchJobExecutor fetchJobExecutor;

    // This should be run at startup
    public void schedule() {

        List<FetchJob> activeJobs = fetchJobSchedulerRepository.findActiveJobs();

        if (activeJobs.isEmpty()) {
            LOG.info("There are active jobs, not scheduling new job");
            return;
        }

        for(FetchJob job : activeJobs) {
            scheduler.newJob(job.id + job.endpoint)
                     .setCron(job.cron)
                     .setTask(executionContext -> fetchJobExecutor.run(executionContext, job))
                     .schedule();
        }
    }
}
