package org.camunda.bpm.critpath.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.prisma.kip.domain.projectInstance.Actor;
import org.prisma.kip.domain.projectInstance.BlockTask;
import org.prisma.kip.domain.projectInstance.Task;
import org.prisma.kip.domain.util.Effort;



public class TaskServiceRedmine {
	
	private static TaskServiceRedmine instance;
	
	public static TaskServiceRedmine getInstance() {
		
		if(instance == null) {
			instance = new TaskServiceRedmine();
		}
		return instance;
	}

	private TaskServiceRedmine() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Ler as tarefas de um arquivo CSV do Redmine
	 * 
	 * @return		Lista de Tarefas
	 */
	public List<Task> loadTaskFromFile(File file){
		
		    BufferedReader br = null;
	        String line = "";
	        String cvsSplitBy = ";";
	        // Armazena o Id da tarefa relacionado q bloqueia
	        String idTask = "";
	        String[] tarefaRelacionada;
	        
	        Map<String[], Task> mapDependencia = new HashMap<String[],Task>();
	        
	        Task task = null;
	        
	        Effort effort = null;
	        
	        
	        List<Task> listaTarefas = new ArrayList<Task>();
	      //  List<TaskDependence> listaDependencias = null;

	        try {

	            br = new BufferedReader(new FileReader(file));
	            while ((line = br.readLine()) != null) {
	            	
	            	//listaDependencias = new ArrayList<TaskDependence>();
	            	
	                // use comma as separator
	                String[] lineFile = line.split(cvsSplitBy);
	                
	                if(!lineFile[0].equals("#")) {
	                	task = new Task();
	                	task.setId(Long.valueOf(lineFile[0]));
	                	task.setStatus(lineFile[2]);
	                	task.setName(lineFile[3]);
	                	
	                	effort = new Effort();
	                	
	                	//Recupera o valor da estimativa
	                	if(lineFile[4] != null
	                			&& !lineFile[4].equals("")
	                			&&!lineFile[4].equals("\"\"")) {
	                		lineFile[4] = lineFile[4].replace(",", ".");
	                		effort.setEstimated((long) (Double.parseDouble(lineFile[4])));
	                	
		                	
	                	}else {
	                		effort.setEstimated(Long.valueOf(0));
	                	}
	                	
	                	//Recupera o valor realizado
	                	if(lineFile[5] != null) {
	                		lineFile[5] = lineFile[5].replace(",", ".");
	                		effort.setReal((long) (Double.parseDouble(lineFile[5])));
		                	
	                	}else {
	                		effort.setReal(Long.valueOf(0));
	                	}
	                	
	                	
	                	task.setEffort(effort);
	                	
	                	if(lineFile[6] != null
	                			&& !lineFile[6].equals("\"\"")){
		                	Actor actor = new Actor();
		                	actor.setName(lineFile[6]);
		                	
		                	task.setPerformer(actor);
	                }
	                	
	                	String tarefasRelacionada = lineFile[9];
	                	
	                	String[] tarefasRelacionadas = tarefasRelacionada.split(",");
	                	
	                	mapDependencia.put(tarefasRelacionadas, task);
	                	
	                	// inclui a tarefa na lista
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
	        
	        //Depois que carrega todas as tarefas, carrega as dependencias
	        Iterator entries = mapDependencia.entrySet().iterator();
	        while (entries.hasNext()) {
	            Map.Entry entry = (Map.Entry) entries.next();
	            String[] key = (String[])entry.getKey();
	            Task taskValue = (Task)entry.getValue();
	            System.out.println("Key = " + key + ", Value = " + taskValue);
	            
	            
		        for(String tRelacionada: key) {
		        	
	        		if(tRelacionada.contains("bloqueia")) {
	        			tarefaRelacionada = tRelacionada.split("#");
	        			idTask = tarefaRelacionada[1];
	        	
	        			//Busca a tarefa que bloqueia
	        			
	        			List<BlockTask> blockingTasks = taskValue.getBlockingTasks();
        				BlockTask blockTask = new BlockTask();
        				
        				blockTask.setTask(this.buscaTarefaID(listaTarefas, Long.valueOf(idTask)));
        				blockingTasks.add(blockTask);
        				
        				taskValue.setBlockingTasks(blockingTasks);
	        		
	        		}
	        		
	        		if(tRelacionada.contains("bloqueado por")) {
	        			tarefaRelacionada = tRelacionada.split("#");
	        			idTask = tarefaRelacionada[1];
	        	
	        			//Busca a tarefa que bloqueia
	        			
	        			List<BlockTask> blockedByTasks = taskValue.getBlockedByTasks();
        				BlockTask blockTask = new BlockTask();
        				
        				blockTask.setTask(this.buscaTarefaID(listaTarefas, Long.valueOf(idTask)));
        				blockedByTasks.add(blockTask);
        				
        				taskValue.setBlockedByTasks(blockedByTasks);
        				
	        		}
	        	}
	        }
	        


		
		return listaTarefas;
		
	}
	
	/**
	 * Return uma tarefa de uma lista de tarefas
	 * @param listaTarefa		Lista da tarefa onde será consultado
	 * @param idTarefa			ID da tarefa que está buscando
	 * @return
	 */
	private Task buscaTarefaID(List<Task> listaTarefa, Long idTarefa) {
		
		for(Task t: listaTarefa) {
			if(t.getId().equals(idTarefa)) {
				return t;
			}
		}
		return null;
	}

}
