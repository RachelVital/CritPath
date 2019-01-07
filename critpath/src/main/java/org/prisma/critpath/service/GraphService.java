package org.prisma.critpath.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.prisma.critpath.domain.NetworkPath;
import org.prisma.kip.domain.projectInstance.BlockTask;
import org.prisma.kip.domain.projectInstance.Task; 


public class GraphService {

	private Map<String, Task> visitedList; 
		
	private List<Task> pathList;
	
	private List<NetworkPath> networkPathList = new ArrayList<NetworkPath>();
	
	
	public GraphService(){ 
		
		visitedList = new HashMap<String, Task>();
		
	} 
		
	/**
	 * Prints all paths from 's' to 'd' 
	 * @param s
	 * @param d
	 */
	public List<NetworkPath> printAllPaths(Task s, Task d) { 
		
		pathList = new ArrayList<>(); 
		
		//pathList.add(s); 
		
		//Call recursive utility 
		printAllPathsUtil(s, d); 
		
		return networkPathList;
		
	}


	
	/**
	 * A recursive function to find all paths from 'u' to 'd'
	 * 
	 * @param u
	 * @param d
	 */
	private void printAllPathsUtil(Task u, Task d) { 
		
		// Mark the current node 
		visitedList.put(u.getName(), u);
		
		if (u.equals(d)) { 
			List<Task> newPathList = new ArrayList<Task>();
			//newPathList.addAll(pathList);
			//Desconsidero os nós Principais e Raiz do algorítmo
			for(Task t: pathList) {
				if(! (t.getName().equals("Folha Principal") ||
						t.getName().equals("Raiz Principal"))){
					
					newPathList.add(t);
					
				}
			}
			
			this.networkPathList.add(new NetworkPath(newPathList));
		}
	

		
	//	if (!u.equals(d)) { 
			
			// Recur for all the vertices 
			// adjacent to current vertex 
			for (BlockTask i : u.getBlockingTasks()) { 
				if (!visitedList.containsKey(i.getTask().getName())) { 
					// store current node 
					// in path[] 
					pathList.add(i.getTask()); 
					printAllPathsUtil(i.getTask(), d); 
					
					// remove current node 
					// in path[] 
					pathList.remove(i.getTask()); 
				} 
			} 
			
			
			// Mark the current node 
			visitedList.remove(u.getName());
		
	//	}//fim if
	
	} 




}
