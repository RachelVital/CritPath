package org.prisma.critpath.serviceTest;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.prisma.zenhubetl.GithubAPI;
import org.prisma.zenhubetl.dto.GithubEndpoints;
import org.prisma.zenhubetl.dto.GithubIssue;
import org.prisma.zenhubetl.dto.GithubMilestone;

public class TaskServiceETLTest {
	
			String owner = "RachelVital";
			
			String repository = "CritPathWeb";

			String zenhubRepoId = "132211103";

			GithubAPI api = new GithubAPI();
			
			@Before
			public void prepareAPI() {
				String githubAccessToken = System.getProperty("githubAccessToken");
				if(!githubAccessToken.isEmpty()) {
					this.api = new GithubAPI(githubAccessToken);
				}
			}
			
			@Test
			public void getEndpoints() {
			
				try {
					GithubEndpoints endpoints =  api.getEndpoints();
					assertNotNull(endpoints);
				}catch(Exception e) {
					e.printStackTrace();
				}catch (Throwable e) {
					e.printStackTrace();
				}
				
			}
			
			@Test
			public void getMilestones() {			
				List<GithubMilestone> milestones = api.getMilestones(owner, repository);
				assertNotNull(milestones);
			}
			
			@Test
			public void getIssues() {
				
				List<GithubIssue> issues = api.getIssues(owner, repository);
				assertNotNull(issues);
			}
			


}
