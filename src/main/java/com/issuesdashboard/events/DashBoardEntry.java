package com.issuesdashboard.events;

import java.util.List;

import com.issuesdashboard.github.RepositoryEvents;

public class DashBoardEntry {
	
	private final GitHubProject project;

	private final List<RepositoryEvents> events;

	public DashBoardEntry(GitHubProject project, List<RepositoryEvents> events) {
		this.project = project;
		this.events = events;
	}

	public GitHubProject getProject() {
		return project;
	}

	public List<RepositoryEvents> getEvents() {
		return events;
	}
	
	
	
}
