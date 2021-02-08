<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">

    <spring:url value="/resources/styles/rootStyle.css" var="rootStyle" />
    <spring:url value="/resources/styles/mainStyle.css" var="mainStyle" />
    <spring:url value="/resources/styles/accountStyle.css" var="accountStyle" />
    <spring:url value="/resources/styles/includesStyles/headerStyle.css" var="headerStyle" />
    <spring:url value="/resources/styles/includesStyles/footerStyle.css" var="footerStyle" />

    <spring:url value="/resources/scripts/tabsScript.js" var="tabsScript"/>

    <title>${product.name}</title>

    <link rel="stylesheet" href="${rootStyle}" />
    <link rel="stylesheet" href="${mainStyle}" />
    <link rel="stylesheet" href="${accountStyle}" />
    <link rel="stylesheet" href="${headerStyle}" />
    <link rel="stylesheet" href="${footerStyle}" />

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript" src="${tabsScript}"></script>

</head>
<body>

<div class="wrapper">

  <c:import url="/WEB-INF/views/includes/header.jsp" />

  <main>

  <div id="tabs">

    <div class="tab" id="myAccountTab">
        <span class="tab-label">Account</span>
    </div>

    <div class="tab" id="myOrdersTab">
        <span class="tab-label">My Orders</span>
    </div>

    <div class="tab" id="logoutTab">
        <span class="tab-label">Logout</span>
    </div>

  </div>

  <div id="tab-content">

    <div id="myAccountTab-content">
        <p>MyAccount</p>
    </div>

    <div id="myOrdersTab-content">
        <p>MyOrders</p>
    </div>

    <div id="logoutTab-content">
        <p>Logout</p>
    </div>
  </div>

  </main>

</div>

<c:import url='/WEB-INF/views/includes/footer.jsp' />

</body>
</html>
