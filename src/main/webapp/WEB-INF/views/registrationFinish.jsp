<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">

    <spring:url value="/resources/styles/rootStyle.css" var="rootStyle" />
    <spring:url value="/resources/styles/mainStyle.css" var="mainStyle" />
    <spring:url value="/resources/styles/registrationFinishStyle.css" var="registrationFinishStyle" />
    <spring:url value="/resources/styles/includesStyles/headerStyle.css" var="headerStyle" />
    <spring:url value="/resources/styles/includesStyles/footerStyle.css" var="footerStyle" />


    <title>Sign Up</title>

    <link rel="stylesheet" href="${rootStyle}" />
    <link rel="stylesheet" href="${mainStyle}" />
    <link rel="stylesheet" href="${headerStyle}" />
    <link rel="stylesheet" href="${footerStyle}" />

    <link rel="stylesheet" href="${registrationFinishStyle}"
</head>
<body>

<div class="wrapper">

  <c:import url="includes/header.jsp" />

  <main>

  <spring:url value="/resources/images/icons/check.svg" var="checkIconURL" />
  <img src="${checkIconURL}" alt="Success icon" />

  <h1><spring:message code="view.registration.success.title"/></h1>

  <p><spring:message code="view.registration.success.description" /><p>

  </main>


</div>

<c:import url='includes/footer.jsp' />

</body>
</html>