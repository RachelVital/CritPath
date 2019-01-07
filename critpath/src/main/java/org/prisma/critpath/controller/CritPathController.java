package org.prisma.critpath.controller;

import java.util.Iterator;
import java.util.List;

import org.prisma.critpath.domain.NetworkPath;
import org.prisma.critpath.domain.Repository;
import org.prisma.critpath.service.CritPathService;
import org.prisma.kip.domain.projectInstance.Iteration;
import org.prisma.kip.domain.projectInstance.Project;
import org.prisma.zenhubetl.Zenhub2Project;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class CritPathController {

	
    @GetMapping("/servicoNovo")
    public String consultaDadosRepositorioForm(@ModelAttribute Repository repositoryData) {
    	
       // model.addAttribute("repository", new Repository());
        return "consultaDadosRepositorio.html";
    }
    
    @PostMapping("/result")
    public String resultadoDadosRepositorioForm(@ModelAttribute Repository repositoryData) {
    	
       // model.addAttribute("repository", new Repository());
        return "result";
    }
    
    

    @GetMapping("/consultaDadosRepositorioSubmit")
    public String dadosRepositorioFormSubmit(@ModelAttribute Repository repositoryData) {
    	
    	Zenhub2Project zenhub2Project = new Zenhub2Project(repositoryData.getOwner(), repositoryData.getRepository(), repositoryData.getZenhubRepoId());
		
		Project project = zenhub2Project.loadFromZenhub();
		
	
		Iteration iteration = this.getIterationFromProject(project, repositoryData.getIterationName());
		
		List<NetworkPath> listNetworkPath = null;
	
		listNetworkPath = CritPathService.calculePaths(project);
//		
//		if(iteration != null) {
//			//verifico o caminho apenas da iteração
//			listNetworkPath = CritPathService.calculeCriticalPath(iteration);
//		}else {
//			// verifico o caminho de todas as tarefas do projeto
//		
//			listNetworkPath = CritPathService.calculeCriticalPath(project);
//		}
		
		repositoryData.setListNetworkPath(listNetworkPath);
    	
		//return "redirect:/consultaTarefasMapping";

    	return "consultaTarefas";
    	
    }
    
    @GetMapping("/consultaDadosPlanilhaSubmit")
    public String dadosPlanilhaFormSubmit(@ModelAttribute Repository repositoryData) {
    	
    	System.out.println("Chamou o método");
    	
    	
//    	Zenhub2Project zenhub2Project = new Zenhub2Project(repositoryData.getOwner(), repositoryData.getRepository(), repositoryData.getZenhubRepoId());
//		
//		Project project = zenhub2Project.loadFromZenhub();
//		
//	
//		Iteration iteration = this.getIterationFromProject(project, repositoryData.getIterationName());
//		
//		List<NetworkPath> listNetworkPath = null;
//	
//		listNetworkPath = CritPathService.calculePaths(project);
////		
////		if(iteration != null) {
////			//verifico o caminho apenas da iteração
////			listNetworkPath = CritPathService.calculeCriticalPath(iteration);
////		}else {
////			// verifico o caminho de todas as tarefas do projeto
////		
////			listNetworkPath = CritPathService.calculeCriticalPath(project);
////		}
//		
//		repositoryData.setListNetworkPath(listNetworkPath);
//    	
//		//return "redirect:/consultaTarefasMapping";

    	return "consultaTarefas";
    	
    }
    
    /**
     * Recupera a Iteração pelo nome
     * 
     * @param project
     * @param iterationName
     * @return
     */
    private Iteration getIterationFromProject (Project project, String iterationName) {
    	
    	List<Iteration> iterations = project.getIterations();
    	
   	 	for(Iterator<Iteration> iter = iterations.iterator(); 
             iter.hasNext();) {
               Iteration iterationAtual = iter.next();
               if(iterationAtual.getName().toUpperCase().equals(iterationName.toUpperCase())){
            	   return iterationAtual;
               }
           }
   	 	
    	return null;
    }
   


}
