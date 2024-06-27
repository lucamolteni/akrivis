package org.kie.akrivis.scheduler;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
class JobExecutorTest {

    @Inject
    JobExecutor jobExecutor;

    @Inject
    JobSchedulerRepository jobSchedulerRepository;

    @Test
    public void testRun() {
        Job byId = jobSchedulerRepository.findById(1L);
        jobExecutor.run(null, byId);

        assertNotNull(byId.lastRun);
    }

}