<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<script>

$(document).ready(function() {

    $('#account-nav').toggleClass('hidden');

    $('#userIcon').click(function() {
        $('#account-nav').toggleClass('hidden');
    });

});

</script>

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

  <div class="dropdown">
  <spring:url value="/resources/images/icons/user.svg" var="userIconURL" />
  <img id="userIcon" src="${userIconURL}" />

  <div id="account-nav">

    <spring:url value="/login" var="loginURL" />
    <a href="${loginURL}">Sign&nbsp;In</a>

    <spring:url value="/signUp" var="signUpURL" />
    <a href="${signUpURL}">Sign&nbsp;Up</a>

    <div class="delimiter"> </div>

    <a href="#">Orders</a>

    <spring:url value="/signUp" var="signUpURL" />
    <a href="#">My&nbsp;Account</a>
  </div>

  </div>

</nav>

</header>
