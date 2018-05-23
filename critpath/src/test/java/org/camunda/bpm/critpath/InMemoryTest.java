package org.camunda.bpm.critpath;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.init;

import java.io.File;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import org.apache.ibatis.logging.LogFactory;
import org.camunda.bpm.critpath.domain.NetworkPath;
import org.camunda.bpm.critpath.domain.Task;
import org.camunda.bpm.critpath.service.TaskService;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.extension.process_test_coverage.junit.rules.TestCoverageProcessEngineRuleBuilder;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * Classe de teste.
 * 
 * @author Rachel
 *
 */
public class InMemoryTest {


	  @ClassRule
	  @Rule
	  public static ProcessEngineRule rule = TestCoverageProcessEngineRuleBuilder.create().build();

	  private static final String PROCESS_DEFINITION_KEY = "critpath";

	  static {
	    LogFactory.useSlf4jLogging(); // MyBatis
	  }

	  @Before
	  public void setup() {
	    init(rule.getProcessEngine());
	  }

	  /**
	   * Just tests if the process definition is deployable.
	   */
	  @Test
	  @Deployment(resources = "process.bpmn")
	  public void testParsingAndDeployment() {
	    // nothing is done here, as we just want to check for exceptions during deployment
	  }

	  @Test
	  @Deployment(resources = "process.bpmn")
	  public void testHappyPath() throws SQLException {
		  
		// read a model from a file
		  ClassLoader classLoader = getClass().getClassLoader();
		  File file = new File(classLoader.getResource("process.bpmn").getFile());
		  
		// read tasks from a file
		   File fileTasks = new File(classLoader.getResource("tarefas_ANA_Descrever_UC.csv").getFile());
		   
		  
		  List<Task> listaTarefas = TaskService.getInstance().loadTaskFromFile(fileTasks);
		  
		  List<NetworkPath> listaCaminhos = TaskService.getInstance().criticalPath(listaTarefas);
		  
		  Collections.sort(listaCaminhos);
		 
		  Collections.reverse(listaCaminhos);
		
		  
		  System.out.println(">>> lista caminhos : " + listaCaminhos);

	  }
	  
	  @Test
	  @Deployment(resources = "process.bpmn")
	  public void testDeployProduction() throws SQLException {
		  
		  ClassLoader classLoader = getClass().getClassLoader();
		  
		  // read tasks from a file
		   File fileTasks = new File(classLoader.getResource("tarefas_Liberar_versao_producao.csv").getFile());
		   
		  
		  List<Task> listaTarefas = TaskService.getInstance().loadTaskFromFile(fileTasks);
		  
		  List<NetworkPath> listaCaminhos = TaskService.getInstance().criticalPath(listaTarefas);
		  
		  Collections.sort(listaCaminhos);
		 
		  Collections.reverse(listaCaminhos);
		
		  
		  System.out.println(">>> lista caminhos : " + listaCaminhos);

	  }


}
