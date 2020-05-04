package com.issuesdashboard.github;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class GitHubClient {
	private static final String EVENT_ISSUE_URL = "https://api.github.com/repos/{owner}/{repo}/issues/events";
	private final RestTemplate restTemplate;
	
	

	public GitHubClient(RestTemplateBuilder builder) {
		this.restTemplate = builder.build();
	}
	
	public ResponseEntity<RepositoryEvents []> fetchEvents(String orgName, String repoName){
		return this.restTemplate.getForEntity(EVENT_ISSUE_URL, RepositoryEvents[].class,orgName,repoName);
	}
}
