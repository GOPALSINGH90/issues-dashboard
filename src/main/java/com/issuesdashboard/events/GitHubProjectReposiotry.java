package com.issuesdashboard.events;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GitHubProjectReposiotry extends PagingAndSortingRepository<GitHubProject, Long> {
	GitHubProject findByRepoName(String repoName);
}
