package org.kie.akrivis.github;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.jcabi.github.Issue;
import com.jcabi.github.Repo;
import com.jcabi.github.Repos.RepoCreate;
import com.jcabi.github.mock.MkGithub;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.kie.akrivis.RawData;

import java.time.Instant;
import java.util.logging.Logger;

@Singleton
public class GithubIssuesInitializer {

    private static final Logger LOG = Logger.getLogger(GithubIssuesInitializer.class.getName());

    @Inject
    EntityManager entityManager;

    @Transactional
    public void initData(@Observes StartupEvent event) throws Exception {
        final Repo repo = new MkGithub().repos().create(
                new RepoCreate("testRepo", false)
        );

        ObjectMapper objectMapper = new ObjectMapper();
        ArrayNode arrayNode = objectMapper.createArrayNode();

        for (int i = 0; i < 3; i++) {
            final Issue issue = repo.issues().create("Entity", "");
            JsonNode node = objectMapper.valueToTree(issue.json());
            arrayNode.add(node);
        }

        LOG.info("Created json" + arrayNode);

        RawData rawData = new RawData();

        rawData.data = arrayNode.toString();
        rawData.id = 2L;
        rawData.createdAt = Instant.parse("2021-01-01T00:00:00Z");

        entityManager.persist(rawData);
    }
}
