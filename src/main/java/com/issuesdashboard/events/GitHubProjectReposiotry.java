package com.issuesdashboard.events;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface GitHubProjectReposiotry extends PagingAndSortingRepository<GitHubProject, Long> {
	GitHubProject findByRepoName(String repoName);
}
