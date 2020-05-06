package com.issuesdashboard.events;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.ui.Model;
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

	@GetMapping("/events/{repoName}")
	@ResponseBody
	public RepositoryEvents[] fetchEvents(@PathVariable String repoName) {
		GitHubProject project = this.repository.findByRepoName(repoName);

		return this.githubclient.fetchEvents(project.getOrgName(), project.getRepoName()).getBody();
	}

	@GetMapping("/admin")
	public String admin(Model model) {
		model.addAttribute("porjects", this.repository.findAll());
		return "admin";
	}

	@GetMapping("/")
	public String dashboard(Model model) {
		Iterable<GitHubProject> projects = this.repository.findAll();
		List<DashBoardEntry> entries = StreamSupport.stream(projects.spliterator(), true)
				.map(p -> new DashBoardEntry(p, this.githubclient.fetchEventList(p.getOrgName(), p.getRepoName())))
				.collect(Collectors.toList());
		model.addAttribute("entries", entries);
		return "dashboard";
	}
}
