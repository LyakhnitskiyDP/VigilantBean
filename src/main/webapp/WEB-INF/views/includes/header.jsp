<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<script>

$(document).ready(function() {

    hideAccountNav();

    refreshProductCounter();

    $('#userIcon').click(function() {

        if ($('#account-nav').css('display') == 'none') {
            $('#account-nav').css('display', 'flex')
        } else
            $('#account-nav').css('display', 'none')
    });

    $('#loupeIcon').click(function() {

        hideAccountNav();

        hideUserNav();

        showSearchBar();


    });

    $('#search-bar-close').click(function() {

        hideSearchBar();

        showAccountNav();
    });

    function hideAccountNav() {
       $('#account-nav').css('display', 'none');
    }

    function showAccountNav() {
        $('#user-navigation').css('display', 'flex');
    }

    function hideUserNav() {
        $('#user-navigation').css('display', 'none');
    }

    function showSearchBar() {
        $('#search-bar').css('display', 'block');
    }

    function hideSearchBar() {
        $('#search-bar').css('display', 'none');
    }


});

function refreshProductCounter() {

    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/vigilantBean/api/cart/getProductCount',
        success: function(data) {
            console.log(data);
        },
        error: function(data) {
        }
    });

}



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

<div id="right-toolbar">
    <nav id="user-navigation">

      <spring:url value="/resources/images/icons/loupe.svg" var="loupeIconURL" />
      <img id="loupeIcon" src="${loupeIconURL}" />

      <spring:url value="/cart" var="cartURL" />
      <a href="${cartURL}">
      <spring:url value="/resources/images/icons/shopping-cart.svg" var="cartIconURL" />
      <img id="cartIcon" src="${cartIconURL}" />
      </a>

      <div class="dropdown">
      <spring:url value="/resources/images/icons/user.svg" var="userIconURL" />
      <img id="userIcon" src="${userIconURL}" />

      <div id="account-nav">

        <spring:url value="/login" var="loginURL" />
        <a href="${loginURL}">Sign&nbsp;In</a>

        <spring:url value="/signUp" var="signUpURL" />
        <a href="${signUpURL}">Sign&nbsp;Up</a>

        <div class="delimiter"> </div>

        <spring:url value="/account" var="accountURL" />
        <a href="${accountURL}">My&nbsp;Account</a>
        </div>

      </div>

    </nav>

<div id="search-bar" class="hidden">
    <spring:url value="/shop/search" var="searchURL" />
    <form action="${searchURL}" id="search-form">
        <spring:url value="/resources/images/icons/close.svg" var="closeImgUrl" />
        <img id="search-bar-close" src="${closeImgUrl}" />
        <input id="search-key-field" name="searchKey"
         type="text" placeholder="Search for..." />
        <button>
            <span id="search-button-label">Search</span>
        </button>
    </form>
</div>

</div>

</header>
