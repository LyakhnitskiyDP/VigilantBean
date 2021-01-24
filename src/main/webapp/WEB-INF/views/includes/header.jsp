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
  <p>L</p>
  <p>C</p>
  <p>U</p>
</nav>
</header>
