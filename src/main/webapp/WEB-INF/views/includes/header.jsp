<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<header>
<nav id="page-navigation">
  <div class="nav-item">
    <p>Home</p>
  </div>

  <div class="nav-item">
    <spring:url value="/shop" var="shopURL" />
    <a href="${shopURL}">
    <p>Shop</p>
    </a>
  </div>
</nav>

<div class="logo">

    <spring:url value="/resources/images/icons/logo.svg" var="logoURL" />
    <img src="${logoURL}" alt="LOGO" />
</div>

<nav id="user-navigation">

  <spring:url value="/resources/images/icons/loupe.svg" var="loupeIconURL" />
  <img src="${loupeIconURL}" />

  <spring:url value="/resources/images/icons/shopping-cart.svg" var="cartIconURL" />
  <img src="${cartIconURL}" />

  <spring:url value="/resources/images/icons/user.svg" var="userIconURL" />
  <img src="${userIconURL}" />
</nav>
</header>
