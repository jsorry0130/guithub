<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

  <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
    <div class="container">
      <a class="navbar-brand" href="/home/index">Guithub</a>
      <!-- <button class="btn btn-primary" id="menu-toggle">Toggle Menu</button> -->
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav ml-auto">
          <li class="nav-item">
            <a class="nav-link" href="/home/index">Home
              <span class="sr-only">(current)</span>
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">About
            	<span class="sr-only">(current)</span>
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/board/general/list">Community</a>
          </li>
          <c:if test="${sessionScope.mem_id==null}">          
          <li class="nav-item">
            <a class="nav-link" href="/home/login">Login</a>
          </li>
          </c:if>
          <c:if test="${sessionScope.mem_id!=null}">
          <li class="nav-item">
            <a class="nav-link" href="/member/meminfo">MyInfo</a>
          </li>
          </c:if>
        </ul>
      </div>
    </div>
  </nav>

