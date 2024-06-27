package org.kie.akrivis.scheduler;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class FetchJobSchedulerRepository implements PanacheRepository<FetchJob> {

    public List<FetchJob> findActiveJobs() {
        return list("status", FetchJobStatus.SCHEDULED);
    }
}
