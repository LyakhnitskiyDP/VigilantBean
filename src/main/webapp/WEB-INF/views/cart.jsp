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

   <spring:url value="/resources/scripts/cartPageScript.js" var="cartPageScript"/>
   <script type="text/javascript" src="${cartPageScript}"></script>

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

        <table>
          <thead>
           <tr>
            <th></th>
            <th>Price</th>
            <th>Quantity</th>
            <th>Total</th>
           </tr>
          </thead>
          <tbody id="cartTableBody">

          </tbody>
        </table>
    </div>

    <div id="grantTotal" class="clause">
        <p>Grant Total</span>
        <span id="grantTotalValue"></span><span id="grantTotalCurrency">$</span>
    </div>

    <div id="checkout-clause" class="clause">

        <form id="coupon-form">
            <input type="text" />
            <input type="submit" value="Apply" />
        </form>

        <button>
            Checkout
        </button>

    </div>

  </main>

</div>

<c:import url='includes/footer.jsp' />

</body>
</html>