package com.issuesdashboard.events;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class GitHubProject {

	@Id
	@GeneratedValue
	private Long id;
	private String orgName;

	@Column(unique = true)
	@NotBlank(message = "")
	private String repoName;

	public GitHubProject() {
	}

	public GitHubProject(String orgName, String repoName) {
		super();
		this.orgName = orgName;
		this.repoName = repoName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getRepoName() {
		return repoName;
	}

	public void setRepoName(String repoName) {
		this.repoName = repoName;
	}

	@Override
	public String toString() {
		return "GitHubProject [id=" + id + ", orgName=" + orgName + ", repoName=" + repoName + "]";
	}

	
}
