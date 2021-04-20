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
  
</head>

<body>    
  <!-- Navigation -->

  <tiles:insertAttribute name="nav" />

  <!-- Header -->

  <tiles:insertAttribute name="header" />
 
    
  <!-- Page Content -->
  <tiles:insertAttribute name="body" />

    <!-- /.row -->


    <!-- /.row -->

  <!-- /.container -->
  
  <!-- Footer -->

  <tiles:insertAttribute name="footer" />


  <!-- Bootstrap core JavaScript -->
  <script src="vendor/jquery/jquery.min.js"></script>
  <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>

</html>