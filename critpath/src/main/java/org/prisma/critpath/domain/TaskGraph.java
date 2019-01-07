package org.prisma.critpath.domain;

import java.util.List;

import org.prisma.kip.domain.projectInstance.Task;

public class TaskGraph extends Task{
	
	private boolean visited;
	
	public TaskGraph() {
		super();
		this.visited = false;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	
	

}
