package org.kie.akrivis;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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

}
