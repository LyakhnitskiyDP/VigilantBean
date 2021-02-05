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


    <title>${product.name}</title>

    <link rel="stylesheet" href="${rootStyle}" />
    <link rel="stylesheet" href="${mainStyle}" />
    <link rel="stylesheet" href="${registrationStyle}" />
    <link rel="stylesheet" href="${headerStyle}" />
    <link rel="stylesheet" href="${footerStyle}" />
</head>
<body>

<div class="wrapper">

  <c:import url="includes/header.jsp" />

  <section class="form-container">

      <form action="login" method="POST" >

          <fieldset>

              <legend>Sing In</legend>

              <div id="error-field">
                <span id="error">${errorMsg}</span>
              </div>

              <c:if test="${msg != null}">
              <div id="message-field">
                <span id="message">${msg}</span>
              </div>
              </c:if>

              <div class="form-group">

                <div class="input-group">
                  <label class="form-label" for="email">
                    <spring:message code="view.registration.email.label" />
                  </label>

                  <div class="form-input-field">

                      <input id="email" name="username"
                             type="text" autocomplete="off"/>
                  </div>
                </div>

              </div>

              <div class="form-group">

                <div class="input-group">
                  <label class="form-label" for="password">
                    <spring:message code="view.registration.password.label" />
                  </label>

                  <div class="form-input-field">

                      <input id="password" name="password"
                             type="password" autocomplete="off" />
                  </div>
                </div>

              </div>

              <div id="submit-button">

                  <input type="submit" value="Sign In" />

              </div>

          </fieldset>

      </form>

    </section>


</div>

<c:import url='includes/footer.jsp' />

</body>
</html>
