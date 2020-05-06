package com.issuesdashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EnableAutoConfiguration
@Component("com.issuesdashboard.github")
@EnableConfigurationProperties(GitHubProperties.class)
public class IssuesDashboardApplication {
	public static void main(String[] args) {
		SpringApplication.run(IssuesDashboardApplication.class, args);
	}
}
