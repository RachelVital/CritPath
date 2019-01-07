package org.camunda.bpm.critpath.domain;


import org.camunda.bpm.critpath.domain.enumerations.DependenceType;
import org.camunda.bpm.critpath.domain.enumerations.LeadLagType;

/**
 * 
 * @author Rachel
 *
 */
public class TaskDependence {
	
	private Task task;
	
	/**
	 * Tipo da Dependencia. Default é finish_Start
	 */
	private DependenceType dependenceType;
	
	/**
	 * Espera ou Atraso conforme a dependencia
	 */
	private LeadLagType leadLagType;
	
	/**
	 * Quantidade de dias de espera ou atraso
	 */
	private int dayLeadLag;
	
	

	public TaskDependence(Task task, DependenceType dependenceType, LeadLagType leadLagType, int dayLeadLag) {
		super();
		this.task = task;
		this.dependenceType = dependenceType;
		this.leadLagType = leadLagType;
		this.dayLeadLag = dayLeadLag;
	}
	
	
	public TaskDependence(Task task, DependenceType dependenceType) {
		super();
		this.task = task;
		this.dependenceType = dependenceType;
		this.leadLagType = LeadLagType.LEAD;
		this.dayLeadLag = 0;
	}

	public TaskDependence() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public DependenceType getDependenceType() {
		return dependenceType;
	}

	public void setDependenceType(DependenceType dependenceType) {
		this.dependenceType = dependenceType;
	}

	public LeadLagType getLeadLagType() {
		return leadLagType;
	}

	public void setLeadLagType(LeadLagType leadLagType) {
		this.leadLagType = leadLagType;
	}

	public int getDayLeadLag() {
		return dayLeadLag;
	}

	public void setDayLeadLag(int dayLeadLag) {
		this.dayLeadLag = dayLeadLag;
	}
	
	

}
