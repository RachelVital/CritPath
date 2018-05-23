package org.camunda.bpm.critpath.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.camunda.bpm.critpath.domain.NetworkPath;
import org.camunda.bpm.critpath.domain.Task;
import org.camunda.bpm.critpath.domain.TaskDependence;
import org.camunda.bpm.critpath.domain.enumerations.DependenceType;

public class TaskService {
	
	private static TaskService instance;
	
	public static TaskService getInstance() {
		
		if(instance == null) {
			instance = new TaskService();
		}
		return instance;
	}

	private TaskService() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Ler as tarefas de um arquivo CSV
	 * @return
	 */
	public List<Task> loadTaskFromFile(File file){
		
		    BufferedReader br = null;
	        String line = "";
	        String cvsSplitBy = ",";
	        
	        Task task = null;
	        TaskDependence taskDependence= null;
	        
	        List<Task> listaTarefas = new ArrayList<Task>();
	        List<TaskDependence> listaDependencias = null;

	        try {

	            br = new BufferedReader(new FileReader(file));
	            while ((line = br.readLine()) != null) {
	            	
	            	listaDependencias = new ArrayList<TaskDependence>();
	            	
	                // use comma as separator
	                String[] lineFile = line.split(cvsSplitBy);
	                
	                if(!lineFile[0].equals("#")) {
	                	task = new Task();
	                	task.setId(Integer.parseInt(lineFile[0]));
	                	task.setTitle(lineFile[1]);
	                	task.setDescricao(lineFile[2]);
	                	task.setEffort(Integer.parseInt(lineFile[3]));
	                	if(lineFile.length > 4) {
	                		taskDependence = new TaskDependence();
	                		taskDependence.setTask(listaTarefas.get(Integer.parseInt(lineFile[4])-1));
	                		taskDependence.setDependenceType(DependenceType.fromInteger(Integer.parseInt(lineFile[5])));
	                		taskDependence.setDayLeadLag(Integer.parseInt(lineFile[6]));
	                		
	                		listaDependencias.add(taskDependence);
	                		
	                		
	                	if(lineFile.length > 8) {
	                		taskDependence = new TaskDependence();
	                		taskDependence.setTask(listaTarefas.get(Integer.parseInt(lineFile[8])-1));
	                		taskDependence.setDependenceType(DependenceType.fromInteger(Integer.parseInt(lineFile[9])));
	                		taskDependence.setDayLeadLag(Integer.parseInt(lineFile[10]));
	                		
	                		listaDependencias.add(taskDependence);
	                	}
	               }
	               
	               // Incluo as dependencias das tarefas 	
	               task.setTaskDependenceList(listaDependencias);
	               
	              // Incluo a tarefa na lista de tarefas
	              listaTarefas.add(task);

	            }//fim if
	          }//fim while
	            
	            

	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            if (br != null) {
	                try {
	                    br.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }

		
		return listaTarefas;
		
	}
	
	/**
	 * Encontra o caminho crítico de uma lista de tarefas.
	 * O método retorna a lista de tarefas de um caminho crítico.
	 * 
	 * @param listaTarefas
	 * @return	Lista de caminhos ordenados
	 */
	public List<NetworkPath> criticalPath(List<Task> listaTarefas){
		
		NetworkPath networkPath = new NetworkPath();
		
		List<NetworkPath> networkPathList = new ArrayList<NetworkPath>();
		
		for (Task task : listaTarefas) {
			
			if(task.getTaskDependenceList().isEmpty()) {
				networkPath.addTaskEffort(task);
			
			}else {
				
				for (TaskDependence taskDependence: task.getTaskDependenceList()) {
					
					if(taskDependence.getDependenceType().equals(DependenceType.FINISH_START)){
						networkPath.addTaskEffort(task);
						acountPathDependenceFinishStart(networkPath, taskDependence);
					
					} if(taskDependence.getDependenceType().equals(DependenceType.START_FINISH)){
						
						acountPathDependenceStartFinish(networkPath, taskDependence, task);
					
					}if(taskDependence.getDependenceType().equals(DependenceType.START_START)){
						
						acountPathDependenceStartStart(networkPath, taskDependence, task);
					
					} if(taskDependence.getDependenceType().equals(DependenceType.FINISH_FINISH)){
						
						acountPathDependenceFinishFinish(networkPath, taskDependence, task);
					}
					
					// Verificando as dependencias em 2 nivel
					if(!taskDependence.getTask().getTaskDependenceList().isEmpty()) {
						for(TaskDependence taskDependence2: taskDependence.getTask().getTaskDependenceList()) {
							
							if(taskDependence2.getDependenceType().equals(DependenceType.FINISH_START)){
							
								acountPathDependenceFinishStart(networkPath, taskDependence2);
							
							}if(taskDependence2.getDependenceType().equals(DependenceType.START_FINISH)){
								
								acountPathDependenceStartFinish(networkPath, taskDependence2, taskDependence.getTask());
							
							}if(taskDependence2.getDependenceType().equals(DependenceType.START_START)){
								
								acountPathDependenceStartStart(networkPath, taskDependence2, taskDependence.getTask());
							
							} if(taskDependence.getDependenceType().equals(DependenceType.FINISH_FINISH)){
								
								acountPathDependenceFinishFinish(networkPath, taskDependence, taskDependence.getTask());
							}
							
						}
						
					}
					
					// TODO Verificar condição para os outros tipos de dependencia
					
				}
				
			}
			
			networkPathList.add(networkPath);
			// reseta a variavel
			networkPath = new NetworkPath();
		}
		
		
		return networkPathList;
	}
	/**
	 * Calcula o caminho com a dependencia é Finish_Start
	 * 
	 * @param networkPath
	 * @param taskDependence
	 */
	private void acountPathDependenceFinishStart(NetworkPath networkPath, TaskDependence taskDependence) {
		
		networkPath.addLength(taskDependence.getDayLeadLag());
		networkPath.addTaskEffort(taskDependence.getTask());
			
		
	}
	
	/**
	 * Calcula o caminho da dependencia Start_Start
	 * 
	 * @param networkPath
	 * @param taskDependence
	 * @param task
	 */
	private void acountPathDependenceStartStart(NetworkPath networkPath, TaskDependence taskDependence,Task task ) {
		
		if(task.getEffort() > (taskDependence.getDayLeadLag()+ taskDependence.getTask().getEffort()) ) {
			
			networkPath.addLength(task.getEffort());
			
		}else {
			networkPath.addLength(taskDependence.getDayLeadLag()+ taskDependence.getTask().getEffort());
		}
		networkPath.addTask(task);
		networkPath.addTask(taskDependence.getTask());
				
	}
	
	/**
	 * Calcula o caminho da dependencia Finish_Finish
	 * 
	 * @param networkPath
	 * @param taskDependence
	 * @param task
	 */
	private void acountPathDependenceFinishFinish(NetworkPath networkPath, TaskDependence taskDependence,Task task ) {
		
		if(task.getEffort() > (taskDependence.getDayLeadLag()+ taskDependence.getTask().getEffort()) ) {
			
			networkPath.addLength(task.getEffort());
			
		}else {
			networkPath.addLength(taskDependence.getDayLeadLag()+ taskDependence.getTask().getEffort());
		}
		networkPath.addTask(task);
		networkPath.addTask(taskDependence.getTask());
				
	}
	
	/**
	 * Calcula o caminho da dependencia Start_Finish
	 * 
	 * @param networkPath
	 * @param taskDependence
	 * @param task
	 */
	private void acountPathDependenceStartFinish(NetworkPath networkPath, TaskDependence taskDependence,Task task ) {
		
		networkPath.addTaskEffort(taskDependence.getTask());
		networkPath.addLength(taskDependence.getDayLeadLag());
		networkPath.addTaskEffort(task);	
				
	}
	
	

}
