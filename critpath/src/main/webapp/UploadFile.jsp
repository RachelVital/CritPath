<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<form id="sampleUploadFrm" action="/critpath/upload.html" method="POST" enctype="multipart/form-data">

            <!-- COMPONENT START -->
        <input type="file" name="file" /> 

        <input type="submit" value="Enviar" >
           
	<button type="reset" >Reset</button>
            

</form>

</body>
</html>