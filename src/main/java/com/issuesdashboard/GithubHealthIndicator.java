package com.issuesdashboard;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.issuesdashboard.github.GitHubClient;
import com.issuesdashboard.github.RepositoryEvents;

@Component
public class GithubHealthIndicator implements HealthIndicator {

	private final GitHubClient gitHubClient;

	public GithubHealthIndicator(GitHubClient gitHubClient) {
		this.gitHubClient = gitHubClient;
	}

	@Override
	public Health health() {
		try {
			ResponseEntity<RepositoryEvents[]> responseEntity = gitHubClient.fetchEvents("spring-projects",
					"spring-boot");
			if (responseEntity.getStatusCode().is2xxSuccessful()) {
				return Health.up().withDetail("X-RateLimit-Remaining",
						responseEntity.getHeaders().getFirst("X-RateLimit-Remaining")).build();
			} else {
				return Health.down().withDetail("status", responseEntity.getStatusCode()).build();
			}
		} catch (Exception exception) {
			return Health.down(exception).build();
		}
	}

}
