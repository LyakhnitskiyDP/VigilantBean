<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">

    <spring:url value="/resources/styles/rootStyle.css" var="rootStyle" />
    <spring:url value="/resources/styles/mainStyle.css" var="mainStyle" />
    <spring:url value="/resources/styles/cartPageStyle.css" var="cartStyle" />
    <spring:url value="/resources/styles/includesStyles/headerStyle.css" var="headerStyle" />
    <spring:url value="/resources/styles/includesStyles/footerStyle.css" var="footerStyle" />


    <title>Cart</title>

    <link rel="stylesheet" href="${rootStyle}" />
    <link rel="stylesheet" href="${mainStyle}" />
    <link rel="stylesheet" href="${cartStyle}" />
    <link rel="stylesheet" href="${headerStyle}" />
    <link rel="stylesheet" href="${footerStyle}" />

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

    <spring:url value="/resources/scripts/react/react-dev-17.js" var="react"/>
    <script type="text/javascript" src="${react}"></script>

    <spring:url value="/resources/scripts/react/react-dom-dev-17.js" var="reactDom"/>
    <script type="text/javascript" src="${reactDom}"></script>

   <spring:url value="/resources/scripts/react/InjectCartComponent.js" var="cartPageScript"/>
   <script type="module" type="text/javascript" src="${cartPageScript}"></script>

</head>
<body>

<div class="wrapper">

  <c:import url="includes/header.jsp" />

  <main>

    <div id="continueShopping" class="clause">
      <spring:url value="/shop" var="shopURL"/>
      <a href="${shopURL}">
        <spring:url value="/resources/images/icons/left-pointing-triangle.svg" var="backIcon" />
        <img class="icon" src="${backIcon}" />
        <span id="back-label">Continue shopping</span>
      </a>
    </div>

    <div id="cart" class="clause">

    </div>

  </main>

</div>

<c:import url='includes/footer.jsp' />

</body>
</html>