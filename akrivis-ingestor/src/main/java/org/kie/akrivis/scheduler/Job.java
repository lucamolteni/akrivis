package org.kie.akrivis.scheduler;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;

@Entity
@Table(name = "job")
public class Job {

    @Id
    public long id;

    public String endpoint;

    public String type;

    public String cron;

    @Enumerated(EnumType.STRING)
    public JobStatus status;

    @Column(name = "last_run")
    public Instant lastRun;
}
