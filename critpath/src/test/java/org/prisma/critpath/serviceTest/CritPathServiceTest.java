package org.prisma.critpath.serviceTest;

import org.junit.Before;
import org.junit.Test;
import org.prisma.critpath.service.CritPathService;
import org.prisma.kip.domain.projectInstance.Project;
import org.prisma.zenhubetl.GithubAPI;
import org.prisma.zenhubetl.Zenhub2Project;

public class CritPathServiceTest {
	
//	String owner = "RachelVital";
//	String repository = "CritPathWeb";
//	String zenhubRepoId = "132211103";
	
//	String owner = "carloseduardov8";	
//	String repository = "Viajato";
//	String zenhubRepoId = "147525587";
	
	String owner = "gfrebello";	
	String repository = "qs-trip-planning-procedure";
	String zenhubRepoId = "147525741";
	
//	String owner ="utelemaco";
//	String repository = "prisma-sandbox-project";
//	String zenhubRepoId = "141082272";
	
	
	
	//String repository = "CritPath";

	//String zenhubRepoId = "131373207";
	
	Project projeto;
	
	GithubAPI api = new GithubAPI();
	
	@Before
	public void prepareAPI() {
		
		String githubAccessToken = System.getProperty("githubAccessToken");
		if(!githubAccessToken.isEmpty()) {
			this.api = new GithubAPI(githubAccessToken);
		}
	}
	
	@Test
	public void viewCriticalPath () {
		
		Zenhub2Project zenhub2Project = new Zenhub2Project(owner, repository, zenhubRepoId);
		
		Project project = zenhub2Project.loadFromZenhub();
		
		CritPathService.calculeCriticalPath(project);

	}
	

}
