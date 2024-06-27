package org.kie.akrivis.scheduler;

import io.quarkus.scheduler.ScheduledExecution;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.time.Instant;
import java.util.logging.Logger;

@ApplicationScoped
public class FetchJobExecutor {

    private static final Logger LOG = Logger.getLogger(FetchJobScheduler.class.getName());

    @Inject
    FetchJobSchedulerRepository fetchJobSchedulerRepository;

    public void run(ScheduledExecution executionContext, FetchJob job)  {
        job.lastRun = Instant.now();
        LOG.info("Executing job: %d at %s".formatted(job.id, job.lastRun.toString()));
        fetchJobSchedulerRepository.persist(job);
    }
}
