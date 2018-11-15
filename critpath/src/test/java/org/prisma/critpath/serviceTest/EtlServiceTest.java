package org.prisma.critpath.serviceTest;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.prisma.kip.domain.projectInstance.Project;
import org.prisma.zenhubetl.Zenhub2Project;

public class EtlServiceTest {
	
	
	String owner = "RachelVital";
	
	String repository = "CritPathWeb";

	String zenhubRepoId = "132211103";
	
	Project projeto;
	
	//String repository = "CritPath";

	//String zenhubRepoId = "131373207";
	
	

	Zenhub2Project zenhub2Project = new Zenhub2Project(owner, repository, zenhubRepoId);
	
	Project project = zenhub2Project.loadFromZenhub();
	

	
	
	@Test
	public void loadProjectFromZenhub() {
		
		
		Zenhub2Project zenhub2Project = new Zenhub2Project(owner, repository, zenhubRepoId);
		
		this.project = zenhub2Project.loadFromZenhub();
		
		assertNotNull(this.project);
		
	}
	
	public void calculeCriticalPath(Project project) {
		
		
		
		
	}
	
	

}
