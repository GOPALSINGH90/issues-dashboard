package com.issuesdashboard.github;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.springframework.web.client.RestTemplate;

import com.issuesdashboard.GitHubProperties;
import com.sun.xml.fastinfoset.sax.Properties;

@Component
public class GitHubClient {
	private static final String EVENT_ISSUE_URL = "https://api.github.com/repos/{orgName}/{repoName}/issues/events";
	private final RestTemplate restTemplate;

	public GitHubClient(RestTemplateBuilder builder, GitHubProperties gitHubProperties) {
		this.restTemplate = builder
				.additionalInterceptors(new GitHubAppTokenInterceptor(gitHubProperties.getToken()))
				.build();
	}

	public ResponseEntity<RepositoryEvents[]> fetchEvents(String orgName, String repoName) {
		return this.restTemplate.getForEntity(EVENT_ISSUE_URL, RepositoryEvents[].class, orgName, repoName);
	}

	public List<RepositoryEvents> fetchEventList(String orgName, String repoName){
		return Arrays.asList(fetchEvents(orgName, repoName).getBody());
	}
	
	private static class GitHubAppTokenInterceptor implements ClientHttpRequestInterceptor {
		private final String token;

		public GitHubAppTokenInterceptor(String token) {
			this.token = token;
		}

		@Override
		public ClientHttpResponse intercept(HttpRequest request, byte[] bytes, ClientHttpRequestExecution execution)
				throws IOException {
			if (org.springframework.util.StringUtils.hasText(this.token)) {
				byte[] basicAuthValue = this.token.getBytes(StandardCharsets.UTF_8);
				request.getHeaders().set(HttpHeaders.AUTHORIZATION,
						"basic" + Base64Utils.encodeToString(basicAuthValue));
			}
			return execution.execute(request, bytes);
		}
	}

}
