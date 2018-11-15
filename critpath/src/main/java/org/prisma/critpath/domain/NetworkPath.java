package org.prisma.critpath.domain;

import java.util.ArrayList;
import java.util.List;

import org.prisma.kip.domain.projectInstance.Task;



public class NetworkPath implements Comparable<NetworkPath> {
	
	/**
	 * Mede o tamanho do caminho (esforço)
	 */
	private int length;
	
	private int remainingLenght;
	
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
	
	public int getRemainingLenght() {
		return remainingLenght;
	}

	public void setRemainingLenght(int remainingLenght) {
		this.remainingLenght = remainingLenght;
	}

	public void addTaskEffort(Task task) {
		tasks.add(task);
		if(task.getEffort().getEstimated() != null) {
			length+= task.getEffort().getEstimated();
		}
		
		if(!(task.getStatus().toUpperCase().equals("DONE") ||task.getStatus().toUpperCase().equals("CLOSED"))) {
			if(task.getEffort() != null && task.getEffort().getEstimated() != null) {
				remainingLenght+= task.getEffort().getEstimated();
			}
		}
		
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
	

    public int compareTo(NetworkPath path) {
		 
	     return this.remainingLenght - path.remainingLenght;
    }

    @Override
    public String toString() {
    	
    	StringBuffer tarefas = new StringBuffer();
    	
    	for (Task task : this.getTasks()) {
			tarefas.append(" ," + task.getName());
		}
    	
        return "Caminho da Rede [tamanho =" + length + ", tarefas=" + tarefas.toString() + "] \n";
    }


}
