package org.kie.akrivis.scheduler;

import io.quarkus.scheduler.ScheduledExecution;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.time.Instant;
import java.util.logging.Logger;

@ApplicationScoped
public class JobExecutor {

    private static final Logger LOG = Logger.getLogger(JobScheduler.class.getName());

    @Inject
    JobSchedulerRepository jobSchedulerRepository;

    public void run(ScheduledExecution executionContext, Job job)  {
        job.lastRun = Instant.now();
        LOG.info("Executing job: %d at %s".formatted(job.id, job.lastRun.toString()));
        jobSchedulerRepository.persist(job);
    }
}
