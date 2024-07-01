package org.kie.akrivis.scheduler;

import org.kie.akrivis.github.GitHubClientImpl;

@FunctionalInterface
public interface IngestorHttpClient {

    String fetchData(String url);

    static IngestorHttpClient findHttpClient(String type) {
        return switch (type) {
            case "GitHub" -> new GitHubClientImpl();
            default -> throw new UnsupportedOperationException("Unknown type: " + type);
        };
    }
}
