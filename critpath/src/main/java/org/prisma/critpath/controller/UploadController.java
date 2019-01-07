package org.prisma.critpath.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.camunda.bpm.critpath.service.TaskServiceRedmine;
import org.prisma.critpath.domain.NetworkPath;
import org.prisma.critpath.domain.Repository;
import org.prisma.critpath.service.CritPathService;
import org.prisma.kip.domain.projectInstance.Task;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UploadController {

    //Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = "C://temp//";

    @GetMapping("/")
    public String index() {
        return "upload";
    }

    @PostMapping(value= "/uploadFile") //new annotation since 4.3
 //   public String singleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
//    @RequestMapping(value = "/upload", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String singleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) throws IOException{


//        if (file.isEmpty()) {
        	System.out.println(">>>>>> Passou aqui");
      //     redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
           return "redirect:uploadStatus";
//       }

//        try {
//
//            // Get the file and save it somewhere
//            byte[] bytes = file.getBytes();
//            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
//            Files.write(path, bytes);
//
////            redirectAttributes.addFlashAttribute("message",
////                    "You successfully uploaded '" + file.getOriginalFilename() + "'");
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        return "redirect:/uploadStatus";
    }
    
    @PostMapping(value= "/upload") //new annotation since 4.3
	public String singleFileUpload(@ModelAttribute Repository repositoryData) throws IOException{
    	
    	// read a model from a file
		  ClassLoader classLoader = getClass().getClassLoader();
		  
		// read tasks from a file
		   File fileTasks = new File(classLoader.getResource("tarefas_Billing_v9.csv").getFile());
		   
		  
		  List<Task> listaTarefas = TaskServiceRedmine.getInstance().loadTaskFromFile(fileTasks);
		  
		  List<NetworkPath> listaCaminhos = CritPathService.calculePaths(listaTarefas);
		  
		  System.out.println("*************************************************");
		  System.out.println("**** Lista de Caminhos");
		  for(NetworkPath networkPath: listaCaminhos) {
			  System.out.println(networkPath);
		  }
		  
		 repositoryData.setListNetworkPath(listaCaminhos);


	    	return "consultaTarefasUpload";
       }
    
 
    @PostMapping("/uploadStatus")
    public String uploadStatus() {
    	
    	System.out.println(">>> Oiiiiiii");
    	
        return "uploadStatus";
    }

}
