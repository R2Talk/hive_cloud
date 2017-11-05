<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0">

<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.indigo-pink.min.css">
<script defer src="https://code.getmdl.io/1.3.0/material.min.js"></script>

<link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.red-blue.min.css" />

<title>Yabee HIVE</title>

</head>
<body>

<!-- Simple header with scrollable tabs. -->
<div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
  <header class="mdl-layout__header">
    <div class="mdl-layout__header-row">
      <!-- Title -->
      <span class="mdl-layout-title"><h3>Yabee HIVE</h3></span>
    </div>
    <!-- Tabs -->
    <div class="mdl-layout__tab-bar mdl-js-ripple-effect">
      <a href="#scroll-tab-home" class="mdl-layout__tab is-active">Home</a>
      <a href="#scroll-tab-reports" class="mdl-layout__tab">Reports</a>
      <a href="#scroll-tab-about" class="mdl-layout__tab">About</a>
    </div>
  </header>
  
  
  <div class="mdl-layout__drawer">
    <span class="mdl-layout-title">Hive Cloud</span>
  </div>
  
  <main class="mdl-layout__content">
    <section class="mdl-layout__tab-panel is-active" id="scroll-tab-home">
      <div class="page-content">
      <!-- Your content goes here -->
      <p><H2>Welcome</H2></p>
      <p><H4>With Yabee HIVE you have access to activities reports.</H4></p>
      <p>This is an alpha version. Expect more.</p>
      </div>
    </section>
    
    <section class="mdl-layout__tab-panel" id="scroll-tab-reports">
      
      <!-- Your content goes here -->
      <c:forEach var="deliverableVo" items="${deliverables}">
      <div class="mdl-card mdl-shadow--4dp">
        <div class="mdl-card__title">
        <c:out value="${deliverableVo.title}"/>
  		</div>
  		<div class="mdl-card__supporting-text">
  		<c:out value="${deliverableVo.description}"/>
  		</div>
      </div>
       </c:forEach>      
    </section>
    
    <section class="mdl-layout__tab-panel" id="scroll-tab-about">
      <div class="page-content">
      <!-- Your content goes here -->
      <p>Hive Cloud Server v 1.0</p>
      </div>
    </section>
  </main>
</div>

</body>
</html>