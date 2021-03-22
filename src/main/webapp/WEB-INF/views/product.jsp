<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">

    <spring:url value="/resources/styles/rootStyle.css" var="rootStyle" />
    <spring:url value="/resources/styles/mainStyle.css" var="mainStyle" />
    <spring:url value="/resources/styles/productPageStyle.css" var="productStyle" />
    <spring:url value="/resources/styles/includesStyles/headerStyle.css" var="headerStyle" />
    <spring:url value="/resources/styles/includesStyles/footerStyle.css" var="footerStyle" />


    <title>${product.name}</title>

    <link rel="stylesheet" href="${rootStyle}" />
    <link rel="stylesheet" href="${mainStyle}" />
    <link rel="stylesheet" href="${productStyle}" />
    <link rel="stylesheet" href="${headerStyle}" />
    <link rel="stylesheet" href="${footerStyle}" />

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

   <spring:url value="/resources/scripts/productPageScript.js" var="productPageScript"/>
   <script type="text/javascript" src="${productPageScript}"></script>

</head>
<body>

<div class="wrapper">

  <c:import url="includes/header.jsp" />

  <main>


    <spring:url value="/shop" var="shopURL" />
    <div id="back-link">
    <a href="${shopURL}">
        <spring:url value="/resources/images/icons/left-pointing-triangle.svg"
            var="backIcon" />
        <img src="${backIcon}" />
        <span id="back-label">All products<span>
    </a>
    </div>


    <div id="product-showcase">

    <section id="product-images">

        <spring:url value="/resources/images/products/${product.mainPicture.name}.${product.mainPicture.extension}"
                    var="productImageURL" />
        <img id="mainImage" src="${productImageURL}" alt="product" />

        <c:if test="${fn:length(product.pictures) > 1}">
        <div id="secondary-pictures">

            <c:forEach items="${product.pictures}" var="secondaryImage">
             <spring:url value="/resources/images/products/${secondaryImage.name}.${secondaryImage.extension}"
                         var="secondaryImageURL" />
             <img class="secondaryImage" src="${secondaryImageURL}" alt="productSecondaryPicture" />
            </c:forEach>

        </div>
        </c:if>

    </section>

    <section id="product-details">

        <h1>${product.name}</h1>

        <p class="product-detail" id="product-price">${product.unitPrice}$</p>

        <p class="product-detail" id="product-quantity_per_order">
            <span class="product-detail-label">Quantity per order: </span>${product.quantityPerUnit}
        </p>

        <spring:url value="/api/cart/addProduct"  var="addProductURL" />
        <form action="${addProductURL}" method="PUT" id="add-product">

            <input type="number"  value="1" name="quantity" id="numberToAdd" />
            <input type="hidden" name="productId" value="${product.productId}" id="productIdToAdd" />

            <input type="submit" id="addProduct" value="Add to Cart" />
        </form>

        <div id="addProductPopUp" class="popUp">

        </div>

        <p class="product-detail" id="product-description">${product.description}</p>

        <p class="product-detail" id="product-weight">
            <span class="product-detail-label">Weight: </span>${product.unitWeight}
            <span class="product-detail-label">g</span>
        </p>

        <p class="product-detail" id="product-origins">
            <span class="product-detail-label">Origins: </span>${product.origins}
        </p>

        <p class="product-detail" id="product-manufacturer">
            <span class="product-detail-label">Manufacturer: </span>${product.manufacturer}
        </p>

        <p class="product-detail" id="product-ingredients">
            <span class="product-detail-label">Ingredients: </span>${product.ingredients}
        </p>

        <p class="product-detail" id="product-allergy_information">
            <span class="product-detail-label">Allergy information: </span>${product.allergyInformation}
        </p>

    </section>

    </div>

  </main>


</div>

<c:import url='includes/footer.jsp' />

</body>
</html>