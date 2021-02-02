<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">

    <spring:url value="/resources/styles/rootStyle.css" var="rootStyle" />
    <spring:url value="/resources/styles/mainStyle.css" var="mainStyle" />
    <spring:url value="/resources/styles/registrationPageStyle.css" var="registrationStyle" />
    <spring:url value="/resources/styles/includesStyles/headerStyle.css" var="headerStyle" />
    <spring:url value="/resources/styles/includesStyles/footerStyle.css" var="footerStyle" />


    <title>Sign Up</title>

    <link rel="stylesheet" href="${rootStyle}" />
    <link rel="stylesheet" href="${mainStyle}" />
    <link rel="stylesheet" href="${headerStyle}" />
    <link rel="stylesheet" href="${footerStyle}" />

    <link rel="stylesheet" href="${registrationStyle}"
</head>
<body>

<div class="wrapper">

  <c:import url="includes/header.jsp" />

  <section class="form-container">

    <spring:url value="" var="registrationURL" />
    <form:form action="signUp" method="POST" modelAttribute="newUser">

        <fieldset>

            <legend>Sign Up</legend>

            <div class="form-group">

              <div class="input-group" />
                <label class="form-label" for="username">
                    <spring:message code="view.registration.username.label" />
                </label>

                <div class="form-input-field">

                    <form:input id="username" path="username"
                                type="text" autocomplete="off"/>
                </div>
              </div>

              <div class="error-group" />
                <form:errors path="username" cssClass="label-error" />
              </div>

            </div>

            <div class="form-group">

              <div class="input-group">
                <label class="form-label" for="email">
                  <spring:message code="view.registration.email.label" />
                </label>

                <div class="form-input-field">

                    <form:input id="email" path="email"
                                type="text" autocomplete="off"/>
                </div>
              </div>

              <div class="error-group">
                <form:errors path="email" cssClass="label-error" />
              </div>

            </div>

            <div class="form-group">

              <div class="input-group">
                <label class="form-label" for="password">
                  <spring:message code="view.registration.password.label" />
                </label>

                <div class="form-input-field">

                    <form:input id="password" path="password"
                                type="password" autocomplete="off" />
                </div>
              </div>

              <div class="error-group">
                <form:errors path="password" cssClass="label-error" />
              </div>

            </div>

            <div id="submit-button">

                <input type="submit" value="Add" />

            </div>



        </fieldset>

    </form:form>

  </section>


</div>

<c:import url='includes/footer.jsp' />

</body>
</html>