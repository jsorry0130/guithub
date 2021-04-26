<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html>
<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Business Frontpage - Start Bootstrap Template</title>

  <!-- Bootstrap core CSS -->
  <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href="css/business-frontpage.css" rel="stylesheet">
  <link href="css/simple-sidebar.css" rel="stylesheet">
  
  <style type="text/css">
	a:link {text-decoration: none; color: black;}
	a:visited {text-decoration: none; color: black;}
	a:active {text-decoration: none; color: black;}
	a:hover {text-decoration: underline; color: black;}
  </style>

</head>

<body class="bg-secondary" link="black" alink="black" vlink="black">    
  <!-- Navigation -->

  <tiles:insertAttribute name="nav" />

  <!-- Header -->

  <tiles:insertAttribute name="header" />
 
  <div class="d-flex" id="wrapper"> <!-- side bar 나타내기용 감싸기 -->
  <!-- Side Bar -->
  <tiles:insertAttribute name="side" />
     
  <!-- Page Content -->
  <tiles:insertAttribute name="body" />

    <!-- /.row -->


    <!-- /.row -->

  <!-- /.container -->
  </div>
  
  <!-- Footer -->

  <tiles:insertAttribute name="footer" />


  <!-- Bootstrap core JavaScript -->
  <script src="vendor/jquery/jquery.min.js"></script>
  <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>

</html>