package org.camunda.bpm.critpath.domain;

import java.util.ArrayList;
import java.util.List;

public class NetworkPath implements Comparable<NetworkPath>{
	
	private int length;
	
	private List<Task> tasks;
	
	public NetworkPath() {
		super();
		length = 0;
		tasks = new ArrayList<Task>();
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
	
	public void addTaskEffort(Task task) {
		tasks.add(task);
		length+= task.getEffort();
		
	}
	
	public void addTask(Task task) {
		tasks.add(task);
		
	}
	
	/**
	 * Inclui valor de tamaho no caminho crítico
	 * 
	 * @param length
	 */
	public void addLength(int length) {
		this.length += length;
	}
	
	 @Override
    public int compareTo(NetworkPath path) {
		 
	     return this.length - path.length;
    }

    @Override
    public String toString() {
    	
    	StringBuffer tarefas = new StringBuffer();
    	
    	for (Task task : this.getTasks()) {
			tarefas.append(" ," + task.getTitle());
		}
    	
        return "Caminho da Rede [tamanho =" + length + ", tarefas=" + tarefas.toString() + "] \n";
    }

}
