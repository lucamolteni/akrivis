package org.kie.akrivis.scheduler;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.time.Instant;
import java.util.logging.Logger;

@ApplicationScoped
public class JobExecutor {

    private static final Logger LOG = Logger.getLogger(JobScheduler.class.getName());

    @Inject
    JobSchedulerRepository jobSchedulerRepository;

    @Transactional
    public void run(Job job, IngestorHttpClient client) {
        job.lastRun = Instant.now();
        LOG.info("Executing job: %d at %s".formatted(job.id, job.lastRun.toString()));

        RawData newRawData = new RawData();

        newRawData.data =  client.fetchData(job.endpoint);
        newRawData.job = job;
        newRawData.createdAt = job.lastRun;

        jobSchedulerRepository.getEntityManager().persist(newRawData);


    }
}
