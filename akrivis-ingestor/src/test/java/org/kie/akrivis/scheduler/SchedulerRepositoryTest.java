package org.kie.akrivis.scheduler;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

@QuarkusTest
class SchedulerRepositoryTest {

    @Inject
    FetchJobSchedulerRepository fetchJobSchedulerRepository;

    @Test
    public void testFindActiveJobs() {
        assertThat(fetchJobSchedulerRepository.findActiveJobs(), hasSize(1));
    }
}