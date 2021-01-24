<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">

    <spring:url value="/resources/styles/rootStyle.css" var="rootStyle" />
    <spring:url value="/resources/styles/mainStyle.css" var="mainStyle" />
    <spring:url value="/resources/styles/includesStyles/headerStyle.css" var="headerStyle" />
    <spring:url value="/resources/styles/includesStyles/footerStyle.css" var="footerStyle" />
    <spring:url value="/resources/styles/itemNotFoundPageStyle.css" var="exceptionStyle" />

    <title>Category Not Found</title>

    <link rel="stylesheet" href="${rootStyle}" />
    <link rel="stylesheet" href="${mainStyle}" />
    <link rel="stylesheet" href="${headerStyle}" />
    <link rel="stylesheet" href="${footerStyle}" />

    <link rel="stylesheet" href="${exceptionStyle}" />
</head>
<body>

<div class="wrapper">

  <c:import url="/WEB-INF/views/includes/header.jsp" />

  <main>

    <div id="exception-image">

        <spring:url value="/resources/images/icons/not-found.svg" var="exceptionImageURL" />
        <img src="${exceptionImageURL}" alt="Exception Image" />

    </div>

    <div id="exception-description">

        <h1>There is no such category: <span class="error-label">${categoryName}</span>.</h1>
        <p>It could be deleted or never added at all, we would never know</p>

    </div>

  </main>


</div>

<c:import url='/WEB-INF/views/includes/footer.jsp' />

</body>
</html>