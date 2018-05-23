package org.camunda.bpm.critpath.domain;

import java.util.List;

public class Task {
	
	private int id;
	
	private String title;
	
	private int effort;
	
	private String descricao;
	
	private List<TaskDependence> taskDependenceList;
	
	public Task(String title, int effort, String descricao) {
		super();
		this.id = 0;
		this.title = title;
		this.effort = effort;
		this.descricao = descricao;
	}

	public Task(int id, String title, int effort, String descricao) {
		super();
		this.id = id;
		this.title = title;
		this.effort = effort;
		this.descricao = descricao;
	}
	
	public Task() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getEffort() {
		return effort;
	}

	public void setEffort(int effort) {
		this.effort = effort;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<TaskDependence> getTaskDependenceList() {
		return taskDependenceList;
	}

	public void setTaskDependenceList(List<TaskDependence> taskDependenceList) {
		this.taskDependenceList = taskDependenceList;
	}

	

}
