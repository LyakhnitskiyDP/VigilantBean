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

    <title>Admit page</title>

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
        <span class="tab-label"><spring:message code="view.admin.account.label" /></span>
    </div>

    <div class="tab" id="addProductTab">
        <span class="tab-label"><spring:message code="view.admin.addProduct.label" /></span>
    </div>

    <div class="tab" id="editProductTab">
        <span class="tab-label"><spring:message code="view.admin.editProduct.label" /></span>
    </div>

    <div class="tab" id="addCategoryTab">
        <span class="tab-label"><spring:message code="view.admin.addCategory.label" /></span>
    </div>

    <div class="tab" id="editCategoryTab">
        <span class="tab-label"><spring:message code="view.admin.editCategory.label" /></span>
    </div>


    <div class="tab" id="applyDiscountTab">
        <span class="tab-label"><spring:message code="view.admin.applyDiscount.label" /></span>
    </div>

    <div class="tab" id="logoutTab">
        <span class="tab-label"><spring:message code="view.account.logout.label" /></span>
    </div>

  </div>

  <div id="tab-content">

    <div id="myAccountTab-content">
        <h2><spring:message code="view.admin.account.label" /></h2>

        <div id="user-data">
            <div class="field">
                <span class="key"><spring:message code="view.account.myAccount.username.label" />: </span>
                <span class="value">${user.username}</span>
            </div>

            <div class="field">
                <span class="key"><spring:message code="view.account.myAccount.email.label" />: </span>
                <span class="value">${userEmail}</span>
            </div>

            <div class="field">
                <span class="key"><spring:message code="view.account.myAccount.registrationDate.label" />: </span>
                <span class="value">${user.registrationDate}</span>
            </div>
        </div>
    </div>

    <div id="addProductTab-content">
        <h2><spring:message code="view.admin.addProduct.label" /></h2>
        <p><spring:message code="view.admin.addProduct.description" /></p>
    </div>

    <div id="editProductTab-content">
        <p>TODO: implement it</p>
    </div>

    <div id="addCategoryTab-content">
        <p>Impl</p>
    </div>

    <div id="editCategoryTab-content">
        <p>Impl</p>
    </div>

    <div id="applyDiscountTab-content">
        <p>TODO: implement it</p>
    </div>

    <div id="logoutTab-content">
        <h2><spring:message code="view.account.logout.label" /></h2>

        <p><spring:message code="view.account.logout.description" /></p>

        <spring:url value="/logout" var="logoutURL"/>
        <form action="${logoutURL}">
            <input type="submit" value='<spring:message code="view.yes"/>'/>
        </form>
    </div>
  </div>

  </main>

</div>

<c:import url='/WEB-INF/views/includes/footer.jsp' />

</body>
</html>