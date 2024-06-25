package org.kie.akrivis.github;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.kie.akrivis.RawData;

import static org.hamcrest.MatcherAssert.assertThat;
import static uk.co.datumedge.hamcrest.json.SameJSONAs.sameJSONAs;

@QuarkusTest
public class GitHubIntegrationTest {

    @Inject
    EntityManager entityManager;

    @Test
    public void testInitData() {
        RawData rawData = entityManager.find(RawData.class, 2L);

        assertThat(rawData.data.toString(),
                   sameJSONAs("""
                                        [
                                         {"number":{"string":"1"}},
                                         {"number":{"string":"2"}},
                                         {"number":{"string":"3"}},
                                         ]
                                        """)
                           .allowingExtraUnexpectedFields()
                           .allowingAnyArrayOrdering());
    }

}
