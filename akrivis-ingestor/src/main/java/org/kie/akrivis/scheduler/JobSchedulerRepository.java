package org.kie.akrivis.scheduler;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class JobSchedulerRepository implements PanacheRepository<Job> {

    public List<Job> findActiveJobs() {
        return list("status", JobStatus.SCHEDULED);
    }
}
