package org.kie.akrivis.scheduler;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.hibernate.annotations.ColumnTransformer;

import java.time.Instant;

@Entity
@Table(name = "raw_data")
public class RawData {
    @Id
    public long id;

    @ColumnTransformer(write = "?::jsonb")
    public String data;
    @Column(name = "created_at")
    public Instant createdAt;

    @ManyToOne
    @JoinColumn(name = "fk_job")
    public Job job;

}
