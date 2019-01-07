<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN""http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>


<html lang="zxx" class="no-js" xmlns:th="http://www.thymeleaf.org">

<head>
	
	<!-- Mobile Specific Meta -->
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<!-- Favicon-->
	<link rel="shortcut icon" href="img/fav.png">
	<!-- Author Meta -->
	<meta name="author" content="CodePixar">
	<!-- Meta Description -->
	<meta name="description" content="">
	<!-- Meta Keyword -->
	<meta name="keywords" content="">
	<!-- meta character set -->
	 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

	<!-- Site Title -->
	<title>Agile CritPath</title>

	<link href="https://fonts.googleapis.com/css?family=Poppins:300,500,600" rel="stylesheet">
		<!--
		CSS
		============================================= -->
		<link rel="stylesheet" href="css/linearicons.css">
		<link rel="stylesheet" href="css/owl.carousel.css">
		<link rel="stylesheet" href="css/font-awesome.min.css">
		<link rel="stylesheet" href="css/nice-select.css">
		<link rel="stylesheet" href="css/magnific-popup.css">
		<link rel="stylesheet" href="css/bootstrap.css">
		<link rel="stylesheet" href="css/main.css">
		
		
		<link rel="stylesheet"  href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>



</head>
		
		
		
	</head>
	<body>
	
	
	
	
		<div class="main-wrapper-first relative">
			<header>
				<div class="container">
					<div class="banner-area" style="width:100%;">
						<div class="container header-top d-flex justify-content-between align-items-center">
							<div class="logo">
								<img src="img/logo.png" style="width:50%; " alt="">
							</div>
							<div class="main-menubar d-flex align-items-center">
								<nav class="hide">
									<a href="index.html">Home</a>
									<a href="generic.html">Generic</a>
									<a href="elements.html">Elements</a>
								</nav>
								<div class="menu-bar"><span class="lnr lnr-menu"></span></div>
							</div>
						</div>
					</div>
				</div>
			</header>
				<div class="banner-area">
					<div class="container">
						<div class="row height align-items-center">
							<div class="col-lg-7">
								<div class="banner-content">
									<h1 class="text-white text-uppercase mb-20">Acompanhe a execução das tarefas e gerencie as depêndencias.</h1>
									<p class="text-white mb-30">Análise do Caminho Crítico em ambientes ágeis.  </p>
									<a href="/critpath/" class="primary-btn d-inline-flex align-items-center"><span class="mr-10">Home</span><span class="lnr lnr-arrow-right"></span></a>
								</div>
							</div>
						</div>
					</div>
				</div>

			
			
			<!-- Start Align Area -->
			<div class="white-bg">
		
			<div class="section-top-border">
				<div class="row">
					<div class="col-lg-8 col-md-8 mb-40">
							<div class="form-group mb-40 rounded" style="background-color: rgba(0,0,255,0.3)">
						<h4 class="mb-5"><small> Selecione um arquivo para ser importado: </small></h4>
						
						
						
						
							<form id="sampleUploadFrm" action="/critpath/upload.html" method="POST" enctype="multipart/form-data">
			
				                <!-- COMPONENT START -->
				                <div class="form-group">
				                    
				                      
					                     <input type="file" name="file" class="form-control" /> 
					                
				            
				                </div>

				                <!-- COMPONENT END -->
				                <div class="form-group">
				                    <input type="submit" value="Enviar" id="uploadBtn2" class="genric-btn primary circle">
				               
									<button type="reset" class="genric-btn primary circle">Reset</button>
				                
				                </div>
				
				            </form>
													
									
									
									
									
								
									</div>
									
									<!-- Start Align Area -->
			<div class="white-bg">
		
			
						
						<!-- Resultado -->
							 
					 		
				 		<c:if test="${not empty repository.listNetworkPath}"> 
							<h4 class="mb-30 text-center">Repositório:  <b> ${repository.repository} </b>  </h4>
							<c:if test="${not empty repository.iterationName}">
								<h4 class="mb-30 text-center">Iteração:  <b> ${repository.iterationName} </b>  </h4>
							</c:if>
							<table class="table table-striped">
							<thead>
								<tr class="p-3 mb-2 bg-primary text-white text-center rounded">
								        <th colspan="10" style="width:10%">Caminhos encontrados</th>
								     
								   
								    </tr>
							</thead>
							<tbody>
							<c:forEach items="${repository.listNetworkPath}" var="networkPath">
							    <tr>
							        
							
							            <th scope="row" class="border border-primary p-3 mb-2 bg-warning text-dark text-center" style="width:10%;  heigth: 50%;">
							            	<h6><small>  Esforço remanescente: </small></h6><h6>${networkPath.remainingLenght}</h6>
							            	<h6><small>  Esforço total: </small></h6><h6>${networkPath.length}</h6> 
							            	<h6><small>  Progresso: </small></h6>
							            	<div class="progress">
											  <div class="progress-bar progress-bar-striped" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width: ${(networkPath.remainingLenght/networkPath.length)*100}%"></div>
											</div>
							            
							            </th>
							           
							            <c:forEach items="${networkPath.tasks}" var="task">

								            <td style="width:10% height=50%" class="border border-primary rounded">
								            	<h6> <small> 
								            		<b>Tarefa</b> <br> 
								            		${task.name}
								            		
							            		</small> </h6>
								            	
								            	<c:choose>
         
											         <c:when test = "${task.status == 'TO DO'}">
											            <div class="h-25 d-inline-block" style="width: 120px; background-color: rgba(0,0,255,0.1)">
											            	<span class="text-info"><b><h6> <small> ${task.status}</small> </h6></b></span> 
									            		</div>
											         </c:when>
											         
											         <c:when test = "${task.status == 'DOING'}">
											            <div class="h-25 d-inline-block" style="width: 120px; background-color: rgba(0,0,248,0.3)">
											            	<span class="text-info"><b><h6> <small> ${task.status}</small> </h6></b></span> 
									            		</div>
											         </c:when>
											         
											         <c:when test = "${task.status == 'TO TEST'}">
											            <div class="h-25 d-inline-block" style="width: 120px; background-color: rgba(0,0,250,0.3)">
											            	<span class="text-info"><b><h6> <small> ${task.status}</small> </h6></b></span> 
									           			</div>
											         </c:when>
											         
											         <c:when test = "${task.status == 'TESTING'}">
											            <div class="h-25 d-inline-block" style="width: 120px; background-color: rgba(0,0,253,0.5)">
											           		<span class="text-info"><b><h6> <small> ${task.status}</small> </h6></b></span> 
									           			 </div>
											         </c:when>
											         
											         <c:when test = "${task.status == 'DONE'}">
											            <div class="h-25 d-inline-block" style="width: 120px; background-color: rgba(0,0,255,0.7)">
											            	<span class="text-info"><b><h6> <small> ${task.status}</small> </h6></b></span> 
									            		</div>
											         </c:when>
									
											         
											         <c:otherwise>
											            <div class="h-25 d-inline-block" style="width: 120px; background-color: rgba(0,0,255,0.1)">
											             	<span class="text-info"><b><h6> <small> ${task.status}</small> </h6></b></span> 
									           			 </div>
											         </c:otherwise>
											      </c:choose>
								            	
								                
									            
									           <span class="text-white bg-dark text-center rounded-circle align-middle">${task.effort.estimated} </span> 
										        
										           <c:if test="${not empty task.performer}"> 
										        
										           		<img src="img/personMaleIcon.png" style="width:20%; " alt="${task.performer.name}" title="${task.performer.name}" class="rounded-circle"/>
										           </c:if> 
									       </td>
								
								        </c:forEach>
							    </tr>
							</tbody>
							</c:forEach>
							</table>



						</c:if>
				 	
					   
				 		 
					
				 	
				 		
				 		 
				<!-- Fim do resultado -->
			<!-- End Align Area -->
			<!-- Start Footer Widget Area -->
			<section class="footer-widget-area pt-70">
				<div class="container">
					<div class="row">
						<div class="col-md-4">
							<div class="single-widget">
								<div class="desc">
									<h6 class="title">Address</h6>
									<p>56/8, panthapath, west <br> Rio de Janeiro, Brasil</p>
								</div>
							</div>
						</div>
						<div class="col-md-4">
							<div class="single-widget">
								<div class="desc">
									<h6 class="title">Email Address</h6>
									<div class="contact">
										<a href="mailto:rachelvital@gmail.com">rachelvital@gmail.com</a> <br>

									</div>
								</div>
							</div>
						</div>
						<div class="col-md-4">
							<div class="single-widget">
								<div class="desc">
									<h6 class="title">Phone Number</h6>
									<div class="contact">
										<a href="tel:1545">099 9999-9999</a> <br>
								
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<footer>
					<div class="container">
						<div class="footer-content d-flex justify-content-between align-items-center flex-wrap">
							<div class="logo">
								<a href="index.html"><img src="img/logo.png" alt="" style="width:20%; "></a>
							</div>
							<div class="copy-right-text">Copyright &copy; 2017  |  All rights reserved to <a href="#">Dinomuz inc.</a> Designed by <a href="https://colorlib.com" target="_blank">Colorlib</a></div>
							<div class="footer-social">
								<a href="#"><i class="fa fa-facebook"></i></a>
								<a href="#"><i class="fa fa-twitter"></i></a>
								<a href="#"><i class="fa fa-dribbble"></i></a>
								<a href="#"><i class="fa fa-behance"></i></a>
							</div>
						</div>
					</div>
				</footer>
			</section>
			<!-- End Footer Widget Area -->




		</div>




		<script src="js/vendor/jquery-2.2.4.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
		<script src="js/vendor/bootstrap.min.js"></script>
		<script src="js/jquery.ajaxchimp.min.js"></script>
		<script src="js/owl.carousel.min.js"></script>
		<script src="js/jquery.nice-select.min.js"></script>
		<script src="js/jquery.magnific-popup.min.js"></script>
		<script src="js/main.js"></script>
	</body>
</html>
