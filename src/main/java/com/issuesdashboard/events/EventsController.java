package com.issuesdashboard.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.issuesdashboard.github.GitHubClient;
import com.issuesdashboard.github.RepositoryEvents;

@RestController
public class EventsController {
	
	private final GitHubProjectReposiotry repository;
	
	private final GitHubClient githubclient;
	
	
	
	public EventsController(GitHubProjectReposiotry repository, GitHubClient githubclient) {
		super();
		this.repository = repository;
		this.githubclient = githubclient;
	}



	@GetMapping("events/{repoName}")
	@ResponseBody
	public RepositoryEvents[] fetchEvents(@PathVariable String repoName) {
		GitHubProject project = this.repository.findByRepoName(repoName);
		
		return this.githubclient.fetchEvents(project.getOrgName(), project.getRepoName()).getBody();
	}
}
