package org.kie.akrivis.scheduler;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static uk.co.datumedge.hamcrest.json.SameJSONAs.sameJSONAs;

@QuarkusTest
class JobExecutorTest {

    @Inject
    JobExecutor jobExecutor;

    @Inject
    JobSchedulerRepository jobSchedulerRepository;

    @Inject
    EntityManager entityManager;

    @Test
    public void testExecuteHTTPCall() {
        Job byId = jobSchedulerRepository.findById(2L);
        String payload = """
                { "data": "rawData" }
                """;

        jobExecutor.run(byId, type -> payload);

        RawData job = entityManager.createQuery("select r from RawData r where job = :job",
                                                RawData.class)
                                .setParameter("job", byId)
                                .getSingleResult();

        assertThat(job.data, sameJSONAs(payload));
        assertNotNull(byId.lastRun);
    }
}