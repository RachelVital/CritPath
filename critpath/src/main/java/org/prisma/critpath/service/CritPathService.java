package org.prisma.critpath.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.prisma.critpath.domain.NetworkPath;
import org.prisma.kip.domain.projectInstance.BlockTask;
import org.prisma.kip.domain.projectInstance.Iteration;
import org.prisma.kip.domain.projectInstance.Project;
import org.prisma.kip.domain.projectInstance.Task;

public class CritPathService {
	

	
	
	public static List<NetworkPath> calculeCriticalPath(Project project) {
		
		List<Task> taskFolha = recuperaNoFolha(project.getTasks());
		
		//TODO por enquanto estou pegando tarefas da Sprit1
		List<NetworkPath> listaCaminhos = criticalPath(taskFolha);
		 
		System.out.println(">>> lista caminhos : " );
		System.out.println(listaCaminhos);
		
		return listaCaminhos;
	}
	
	public static List<NetworkPath> calculeCriticalPath(Iteration iteration) {
		
		//List<Task> taskRaiz = recuperaNoRaiz(iteration.getTasks());
		
		List<Task> taskFolha = recuperaNoFolha(iteration.getTasks());
		
		//TODO por enquanto estou pegando tarefas da Sprit1
		List<NetworkPath> listaCaminhos = criticalPath(taskFolha);
		  
		  System.out.println(">>> lista caminhos : " );
		  System.out.println(listaCaminhos);
		
		  return listaCaminhos;
	}
	
	
	/**
	 * Retorna a tarefas que serão nós rais da arvore
	 * @param taskList
	 * @return
	 */
	private static List<Task> recuperaNoRaiz(List<Task> taskList){
		
		List<Task> taskRaiz = new ArrayList<Task>();
		
		for (Task task : taskList) {
			if (task.getBlockedByTasks()== null
					|| (task.getBlockedByTasks() != null && task.getBlockedByTasks().isEmpty())) {
				taskRaiz.add(task);
			}
		
		}
		return taskRaiz;
	}
	
	/**
	 * Retorna a tarefas que serão nós folha da arvore
	 * @param taskList
	 * @return
	 */
	private static List<Task> recuperaNoFolha(List<Task> taskList){
		
		List<Task> taskFolha = new ArrayList<Task>();
		
		for (Task task : taskList) {
			if (task.getBlockingTasks()==null
					|| (task.getBlockingTasks() != null && task.getBlockingTasks().isEmpty())) {
				taskFolha.add(task);
			}
		
		}
		return taskFolha;
	}
	
	/**
	 * Encontra o caminho crítico de uma lista de tarefas.
	 * O método retorna a lista de tarefas de um caminho crítico.
	 * 
	 * @deprecated
	 * @param taskList
	 * @return
	 */
	public static List<NetworkPath> criticalPath(List<Task> taskList){
		
		NetworkPath networkPath = new NetworkPath();
		
		
		List<NetworkPath> networkPathList = new ArrayList<NetworkPath>();
		
		for (Task task : taskList) {
			
			networkPath.addTaskEffort(task);
			
			if(!task.getBlockedByTasks().isEmpty()) {
				
				for (BlockTask blockTask: task.getBlockedByTasks()) {
					// TODO considero sempre que a relacao 'e TERMINO_INICIO
					networkPath.addTaskEffort(blockTask.getTask());
				
					// Verificando as dependencias em 2 nivel
					if(!blockTask.getTask().getBlockedByTasks().isEmpty()) {
						for (BlockTask blockTask2: blockTask.getTask().getBlockedByTasks()) {
							// TODO considero sempre que a relacao 'e TERMINO_INICIO
							networkPath.addTaskEffort(blockTask2.getTask());
							
							
							// Verificando as dependencias em 3 nivel
							if(!blockTask2.getTask().getBlockedByTasks().isEmpty()) {
								for (BlockTask blockTask3: blockTask2.getTask().getBlockedByTasks()) {
									// TODO considero sempre que a relacao 'e TERMINO_INICIO
									networkPath.addTaskEffort(blockTask3.getTask());
									
									// Verificando as dependencias em 4 nivel
									if(!blockTask3.getTask().getBlockedByTasks().isEmpty()) {
										for (BlockTask blockTask4: blockTask3.getTask().getBlockedByTasks()) {
											// TODO considero sempre que a relacao 'e TERMINO_INICIO
											networkPath.addTaskEffort(blockTask4.getTask());
											
											// Verificando as dependencias em 5 nivel
											if(!blockTask4.getTask().getBlockedByTasks().isEmpty()) {
												for (BlockTask blockTask5: blockTask4.getTask().getBlockedByTasks()) {
													// TODO considero sempre que a relacao 'e TERMINO_INICIO
													networkPath.addTaskEffort(blockTask5.getTask());
													
													// Verificando as dependencias em 6 nivel
													if(!blockTask5.getTask().getBlockedByTasks().isEmpty()) {
														for (BlockTask blockTask6: blockTask5.getTask().getBlockedByTasks()) {
															// TODO considero sempre que a relacao 'e TERMINO_INICIO
															networkPath.addTaskEffort(blockTask6.getTask());
															
															// Verificando as dependencias em 7 nivel
															if(!blockTask6.getTask().getBlockedByTasks().isEmpty()) {
																for (BlockTask blockTask7: blockTask6.getTask().getBlockedByTasks()) {
																	// TODO considero sempre que a relacao 'e TERMINO_INICIO
																	networkPath.addTaskEffort(blockTask7.getTask());
																	
																	// Verificando as dependencias em 8 nivel
																	if(!blockTask7.getTask().getBlockedByTasks().isEmpty()) {
																		for (BlockTask blockTask8: blockTask7.getTask().getBlockedByTasks()) {
																			// TODO considero sempre que a relacao 'e TERMINO_INICIO
																			networkPath.addTaskEffort(blockTask7.getTask());
																		}//for nivel 8
																	}//if nivel 8
																	
																}//for nivel 7
															}//if nivel 7
															
														}//for nivel 6
													}//if nivel 6
														
												}//for nivel 5
											}//if nivel 5
												
										}//for nivel 4
									}//if nivel 4
											
								}//for nivel 3
							}//if nivel3
						}// for nivel2
					}//if nivel 2
						}// for nivel 1
					}// if nivel 1
					
			// reverse the list
		     Collections.reverse(networkPath.getTasks());
		     
			networkPathList.add(networkPath);
			// reseta a variavel
			networkPath = new NetworkPath();
		
		}//for nivel 0
				
		// ordeno os caminho
		 Collections.sort(networkPathList);
		 
		 // Deixo o caminho em ordem descrescente
		  Collections.reverse(networkPathList);
		
		return networkPathList;
	}
	
	public static List<NetworkPath> calculePaths(Project project){
		
		return calculePaths(project.getTasks());
	}
	
	public static List<NetworkPath> calculePaths(List<Task> listaTarefas){
		
		List<NetworkPath> networkPathList = new ArrayList<NetworkPath>();
		
		List<Task> listTaskFolha = recuperaNoFolha(listaTarefas);
		
		List<Task> listTaskRaiz = recuperaNoRaiz(listaTarefas);
		
		Task taskRaizP = new Task();
		taskRaizP.setName("Raiz Principal");
		Task taskFolhaP = new Task();
		taskFolhaP.setName("Folha Principal");
		
		List<BlockTask> listBlockTask = new ArrayList<BlockTask>();
		
		BlockTask blockTask = null;
		
		// Transformando a raiz em lista de tarefas que bloqueam
		for(Task taskRaiz: listTaskRaiz) {
			
			blockTask = new BlockTask();
			
			blockTask.setTask(taskRaiz);
			
			listBlockTask.add(blockTask);
		}	
		
		// Todos os nós raiz vira filho da TaskRaizP
		taskRaizP.setBlockingTasks(listBlockTask);
		
		
		// fechar as pontas (folhas) do grafo
		
		// Limpo a lista
		listBlockTask = new ArrayList<BlockTask>();
		
		List<BlockTask> listBlockingTask = new ArrayList<BlockTask>();
		
		// Crio uma BlockTask apontando para a Task FolhaP
		BlockTask blockTaskFolhaP = new BlockTask();
		
		blockTaskFolhaP.setTask(taskFolhaP);
		
		listBlockingTask.add(blockTaskFolhaP);
			
		for(Task taskFolha: listTaskFolha) {
			
			blockTask = new BlockTask();
			
			blockTask.setTask(taskFolha);
			
			listBlockTask.add(blockTask);
			
			//Todas as taskFolha passam a apontar para a FolhaP
			taskFolha.setBlockingTasks(listBlockingTask);
			
		}
		// todos as folhas são filhas da taskFolhaP
		taskFolhaP.setBlockedByTasks(listBlockTask);
		
		// Listo os caminhos entre os dois nós principais do Grafo taskRaizP e taskFolhaP
		networkPathList = new GraphService().printAllPaths(taskRaizP, taskFolhaP);
		
		Collections.sort(networkPathList, Collections.reverseOrder());
		
		return networkPathList;
	}
	
	
	
	
	//TODO esse método poderia ir para a classe Task
	public static boolean checkFolha(Task task) {
		
		if(task.getBlockingTasks()==null ||
				(task.getBlockingTasks() != null && task.getBlockingTasks().size() == 0)) {
			return true;
		}
		
		return false;
	}

  
	
	
	
	

}
