<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">

    <spring:url value="/resources/styles/rootStyle.css" var="rootStyle" />
    <spring:url value="/resources/styles/mainStyle.css" var="mainStyle" />
    <spring:url value="/resources/styles/confirmEmailPageStyle.css" var="confirmEmailPageStyle" />
    <spring:url value="/resources/styles/includesStyles/headerStyle.css" var="headerStyle" />
    <spring:url value="/resources/styles/includesStyles/footerStyle.css" var="footerStyle" />


    <title>Sign Up</title>

    <link rel="stylesheet" href="${rootStyle}" />
    <link rel="stylesheet" href="${mainStyle}" />
    <link rel="stylesheet" href="${headerStyle}" />
    <link rel="stylesheet" href="${footerStyle}" />

    <link rel="stylesheet" href="${confirmEmailPageStyle}"
</head>
<body>

<div class="wrapper">

  <c:import url="includes/header.jsp" />

  <main>

    <div id="title">

        <spring:url value="/resources/images/icons/letter.svg" var="letterIconURL" />
        <img src="${letterIconURL}" alt="LETTER" />

        <h1>
            <spring:message code="view.emailConfirm.header" />
        </h1>
    </div>

    <div id="description">

        <p>
          <spring:message code="view.emailConfirm.description" />
        </p>

    </div>

  </main>


</div>

<c:import url='includes/footer.jsp' />

</body>
</html>